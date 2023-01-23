package com.gridnine.testing.test;

import com.gridnine.testing.main.model.Segment;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.gridnine.testing.test.TestHelpers.generateSegments2;
import static org.junit.jupiter.api.Assertions.*;

class SegmentTest {

    @Nested
    class PositiveTests {
        @Test
        void getDepartureDate_success() {
            Segment segment = new Segment(LocalDateTime.now().plusHours(1), LocalDateTime.now().plusHours(4));
            assertEquals(LocalDateTime.now().plusHours(1), segment.getDepartureDate());
        }

        @Test
        void getArrivalDate_success() {
            Segment segment = new Segment(LocalDateTime.now().plusHours(1), LocalDateTime.now().plusHours(4));
            assertEquals(LocalDateTime.now().plusHours(4), segment.getArrivalDate());
        }

        @Test
        void testToString_success() {
            LocalDateTime departure = LocalDateTime.now().minusHours(1);
            LocalDateTime arrival = LocalDateTime.now().plusHours(1);
            Segment segment = new Segment(departure, arrival);
            DateTimeFormatter fmt =
                    DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            assertEquals('[' + departure.format(fmt) + '|' + arrival.format(fmt)
                    + ']', segment.toString());
        }

    }

    @Nested
    class NegativeTests {
        @Test
        void getDepartureDate_failure() {
            assertThrows(NullPointerException.class, () -> new Segment(null, null));
        }

        @Test
        void getArrivalDate_failure() {
            assertThrows(NullPointerException.class, () -> new Segment(null, null));
        }


        @Test
        void testToString_failure() {
            DateTimeFormatter fmt =
                    DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            assertNotEquals('[' + generateSegments2().get(1).getDepartureDate().format(fmt)
                    + '|' + generateSegments2().get(1).getArrivalDate().format(fmt)
                    + ']', generateSegments2().get(0).toString());
        }

    }
}
