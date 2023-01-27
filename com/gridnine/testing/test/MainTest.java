package com.gridnine.testing.test;

import com.gridnine.testing.main.model.Flight;
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
        void excludeTimeOnTheGroundExceedsTwoHours_success() {
            List<Flight> flightList = generateListFlight();
            List<Flight> flightsMore2Hours = excludeTotalTimeOnTheGroundExceedsTwoHours(flightList);
            assertEquals(1, flightsMore2Hours.size());
            assertEquals(flightsMore2Hours.get(0), flightList.get(1));

        }

        @Test
        void arrivalDateBeforeDepartureDate_success() {
            List<Flight> flightList = generateListFlight();
            assertEquals(2, flightList.size());
            assertEquals(flightList.get(0),
                    excludeArrivalDateBeforeDepartureDate(flightList).get(0));
        }

        @Test
        void getSegmentsUpToCurrentTime_success() {
            List<Flight> flightList = generateListFlight();
            assertEquals(flightList.get(0), excludeSegmentsUpToCurrentTime(flightList).get(0));
        }

    }

    @Nested
    class NegativeTests {

        @Test
        void When_ListOfFlightISEmpty_totalTimeOnTheGroundExceedsTwoHours_Exception() {
            List<Flight> unExpectedAnswer = new ArrayList<>();
            assertThrows(IllegalArgumentException.class, () -> excludeTotalTimeOnTheGroundExceedsTwoHours(unExpectedAnswer));
        }

        @Test
        void When_ListOfFlightISEmpty_arrivalDateBeforeDepartureDate_Exception() {
            List<Flight> unExpectedAnswer = new ArrayList<>();
            assertThrows(IllegalArgumentException.class, () -> excludeArrivalDateBeforeDepartureDate(unExpectedAnswer));
        }

        @Test
        void When_ListOfFlightISEmpty_getSegmentsUpToCurrentTime_Exception() {
            List<Flight> unExpectedAnswer = new ArrayList<>();
            assertThrows(IllegalArgumentException.class, () -> excludeSegmentsUpToCurrentTime(unExpectedAnswer));
        }
    }

}
