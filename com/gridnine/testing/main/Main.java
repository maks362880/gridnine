package com.gridnine.testing.main;

import com.gridnine.testing.main.model.Flight;
import com.gridnine.testing.main.model.FlightBuilder;
import com.gridnine.testing.main.model.Segment;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        List<Flight> flightList = FlightBuilder.createFlights();
        flightList.forEach(System.out::println);
        Scanner scanner = new Scanner(System.in);
        System.out.println("""
                Выберите вариант:
                1.Показать вылеты до текущего момента времени.\s
                2.Показать имеются ли сегменты с датой прилёта раньше даты вылета.
                3.Показать если общее время, проведённое на земле превышает два часа.""");
        int sortOption = scanner.nextInt();
        switch (sortOption) {
            case (1) -> System.out.println(getSegmentsUpToCurrentTime(flightList));
            case (2) -> System.out.println(arrivalDateBeforeDepartureDate(flightList));
            case (3) -> System.out.println(totalTimeOnTheGroundExceedsTwoHours(flightList));
            default -> System.out.println("Выбран неверный параметр");
        }
    }

    public static List<Flight> totalTimeOnTheGroundExceedsTwoHours(List<Flight> flightList) {
        if (flightList.isEmpty()) {
            throw new IllegalArgumentException("");
        }
        List<Flight> resultList = new ArrayList<>();
        for (Flight flight : flightList) {
            List<Segment> segments = flight.getSegments();
            long dateDiffInMinutes = 0;
            for (int i = 1; i < segments.size(); i++) {
                dateDiffInMinutes = dateDiffInMinutes +
                        ChronoUnit.MINUTES.between(
                                segments.get(i - 1).getArrivalDate(), segments.get(i).getDepartureDate());
            }
            if (dateDiffInMinutes > 120) {
                resultList.add(flight);
            }
        }
        return resultList;
    }

    public static List<Segment> arrivalDateBeforeDepartureDate(List<Flight> flightList) {
        if (flightList.isEmpty()) {
            throw new IllegalArgumentException("");
        }
        List<Segment> resultList = new ArrayList<>();
        flightList.stream()
                .flatMap(segment -> segment.getSegments().stream())
                .filter(element -> element.getDepartureDate().isAfter(element.getArrivalDate()))
                .forEach(resultList::add);
        return resultList;
    }

    public static List<Segment> getSegmentsUpToCurrentTime(List<Flight> flightList) {
        if (flightList.isEmpty()) {
            throw new IllegalArgumentException("");
        }
        List<Segment> resultList = new ArrayList<>();
        LocalDateTime localDateTime = LocalDateTime.now();
        flightList.stream()
                .flatMap(segment -> segment.getSegments().stream())
                .filter(element -> element.getDepartureDate().isBefore(localDateTime))
                .forEach(resultList::add);
        return resultList;
    }

}
