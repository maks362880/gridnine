package com.gridnine.testing.main;

import com.gridnine.testing.main.model.Flight;
import com.gridnine.testing.main.model.FlightBuilder;
import com.gridnine.testing.main.model.Segment;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        List<Flight> flightList = FlightBuilder.createFlights();
        flightList.forEach(System.out::println);
        Scanner scanner = new Scanner(System.in);
        System.out.println("""
                Выберите вариант:
                1.Исключить вылеты до текущего момента времени.\s
                2.Исключить имеются ли сегменты с датой прилёта раньше даты вылета.
                3.Исключить если общее время, проведённое на земле превышает два часа.""");
        int sortOption = scanner.nextInt();
        switch (sortOption) {
            case (1) -> System.out.println(excludeSegmentsUpToCurrentTime(flightList));
            case (2) -> System.out.println(excludeArrivalDateBeforeDepartureDate(flightList));
            case (3) -> System.out.println(excludeTotalTimeOnTheGroundExceedsTwoHours(flightList));
            default -> System.out.println("Выбран неверный параметр");
        }
    }

    public static List<Flight> excludeTotalTimeOnTheGroundExceedsTwoHours(List<Flight> flightList) {
        if (flightList.isEmpty()) {
            throw new IllegalArgumentException("");
        }
        List<Flight> resultFlightList = new ArrayList<>(flightList);
        Iterator<Flight> iterator = resultFlightList.iterator();
        while (iterator.hasNext()) {
            Flight flight = iterator.next();
            List<Segment> segments = flight.getSegments();
            long dateDiffInMinutes = 0;
            for (int i = 1; i < segments.size(); i++) {
                dateDiffInMinutes = dateDiffInMinutes +
                        ChronoUnit.MINUTES.between(
                                segments.get(i - 1).getArrivalDate(), segments.get(i).getDepartureDate());
            }
            if (dateDiffInMinutes > 120) {
                iterator.remove();
            }
        }
        return resultFlightList;
    }

    public static List<Flight> excludeArrivalDateBeforeDepartureDate(List<Flight> flightList) {
        if (flightList.isEmpty()) {
            throw new IllegalArgumentException("");
        }
        List<Flight> resultFlightList = new ArrayList<>(flightList);
        resultFlightList
                .removeIf(flight -> flight.getSegments()
                .stream()
                .anyMatch(element -> element.getDepartureDate().isAfter(element.getArrivalDate())));
        return resultFlightList;
    }

    public static List<Flight> excludeSegmentsUpToCurrentTime(List<Flight> flightList) {
        if (flightList.isEmpty()) {
            throw new IllegalArgumentException("");
        }
        List<Flight> resultFlightList = new ArrayList<>(flightList);
        LocalDateTime localDateTime = LocalDateTime.now();
        resultFlightList
                .removeIf(flight -> flight.getSegments()
                        .stream()
                .anyMatch(element -> element.getDepartureDate().isBefore(localDateTime)));
        return resultFlightList;
    }

}
