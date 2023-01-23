package com.gridnine.testing.test;

import com.gridnine.testing.main.model.Flight;
import com.gridnine.testing.main.model.Segment;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static com.gridnine.testing.test.TestHelpers.*;
import static org.junit.jupiter.api.Assertions.*;

class FlightTest {

    @Nested
    class PositiveTests {
        @Test
        void getSegments_initialization_success() {
            List<Segment> segments = generateSegments();
            Flight flight = generateFlight(segments);
            assertArrayEquals(segments.toArray(), flight.getSegments().toArray());
        }

        @Test
        void toString_success() {
            List<Segment> segments = generateSegments();
            Flight flight = generateFlight(segments);
            assertEquals(
                    flight.getSegments()
                            .stream().map(Object::toString)
                            .collect(Collectors.joining(" ")),
                    flight.toString()
            );
        }
    }

    @Nested
    class NegativeTests {

        @Test
        void toString_failure() {
            Flight flight = new Flight(generateSegments());
            Flight anyFlight = new Flight(generateSegments2());
            assertNotEquals(anyFlight.getSegments()
                    .stream().map(Object::toString)
                    .collect(Collectors.joining(" ")), flight.toString());
        }
    }
}
