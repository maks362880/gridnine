package com.gridnine.testing.test;

import com.gridnine.testing.main.Main;
import com.gridnine.testing.main.model.Flight;
import com.gridnine.testing.main.model.Segment;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.gridnine.testing.main.Main.*;
import static com.gridnine.testing.test.TestHelpers.*;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Nested
    class PositiveTests {

        @Test
        void totalTimeOnTheGroundExceedsTwoHours_success() {
            List<Segment> segments = generateSegments();
            List<Flight> flightList = generateListFlight();
            var flightsMore2Hours = Main.totalTimeOnTheGroundExceedsTwoHours(flightList);
            assertEquals(1, flightsMore2Hours.size());
            assertEquals(flightsMore2Hours.get(0), flightList.get(0));
        }

        @Test
        void arrivalDateBeforeDepartureDate_success() {
            List<Segment> segments = generateSegments2();
            List<Flight> flightList = generateListFlight();
            assertEquals(segments.get(0),
                    arrivalDateBeforeDepartureDate(flightList).get(0));
        }

        @Test
        void getSegmentsUpToCurrentTime_success() {
            List<Flight> flightList = generateListFlight();
            assertEquals(flightList.get(1).getSegments().get(0), getSegmentsUpToCurrentTime(flightList).get(0));
        }

    }

    @Nested
    class NegativeTests {

        @Test
        void When_ListOfFlightISEmpty_totalTimeOnTheGroundExceedsTwoHours_Exception() {
            List<Flight> unExpectedAnswer = new ArrayList<>();
            assertThrows(IllegalArgumentException.class, () -> totalTimeOnTheGroundExceedsTwoHours(unExpectedAnswer));
        }

        @Test
        void When_ListOfFlightISEmpty_arrivalDateBeforeDepartureDate_Exception() {
            List<Flight> unExpectedAnswer = new ArrayList<>();
            assertThrows(IllegalArgumentException.class, () -> arrivalDateBeforeDepartureDate(unExpectedAnswer));
        }

        @Test
        void When_ListOfFlightISEmpty_getSegmentsUpToCurrentTime_Exception() {
            List<Flight> unExpectedAnswer = new ArrayList<>();
            assertThrows(IllegalArgumentException.class, () -> getSegmentsUpToCurrentTime(unExpectedAnswer));
        }
    }

}
