package com.gridnine.testing.test;

import com.gridnine.testing.main.model.Flight;
import com.gridnine.testing.main.model.Segment;

import java.time.LocalDateTime;
import java.util.List;

public class TestHelpers {
    public static List<Segment> generateSegments() {
        LocalDateTime dep1 = LocalDateTime.now().plusHours(1);
        LocalDateTime arr1 = LocalDateTime.now().plusHours(3);
        LocalDateTime dep2 = LocalDateTime.now().plusHours(6);
        LocalDateTime arr2 = LocalDateTime.now().plusHours(8);
        Segment segment1 = new Segment(dep1, arr1);
        Segment segment2 = new Segment(dep2, arr2);
        List<Segment> segments = List.of(segment1, segment2);
        return segments;
    }

    public static List<Segment> generateSegments2() {
        LocalDateTime dep1 = LocalDateTime.now().minusHours(1);
        LocalDateTime arr1 = LocalDateTime.now().minusHours(2);
        LocalDateTime dep2 = LocalDateTime.now().plusHours(0);
        LocalDateTime arr2 = LocalDateTime.now().plusHours(3);
        Segment segment1 = new Segment(dep1, arr1);
        Segment segment2 = new Segment(dep2, arr2);
        List<Segment> segments = List.of(segment1, segment2);
        return segments;
    }

    public static Flight generateFlight(List<Segment> segments) {
        return new Flight(segments);
    }

    public static List<Flight> generateListFlight() {
        List<Flight> flightList = List.of(
                new Flight(generateSegments()),
                new Flight(generateSegments2())
        );
        return flightList;
    }


}
