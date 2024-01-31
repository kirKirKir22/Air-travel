package test;

import com.gridnine.testing.flight.Flight;
import com.gridnine.testing.filter.GroundTimeExceedsTwoHoursFilter;
import com.gridnine.testing.flight.Segment;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GroundTimeExceedsTwoHoursFilterTest {



    @Test
    void filterFlights_GroundTimeWithinTwoHours_AllFlightsShouldPassFilter() {
        GroundTimeExceedsTwoHoursFilter filter = new GroundTimeExceedsTwoHoursFilter();

        Flight flight1 = createFlight(
                LocalDateTime.now(),
                LocalDateTime.now().plusHours(1),
                LocalDateTime.now().plusHours(2),
                LocalDateTime.now().plusHours(3)
        );

        Flight flight2 = createFlight(
                LocalDateTime.now(),
                LocalDateTime.now().plusHours(1),
                LocalDateTime.now().plusHours(2),
                LocalDateTime.now().plusHours(2)
        );

        List<Flight> flights = Arrays.asList(flight1, flight2);

        List<Flight> filteredFlights = filter.filterFlights(flights);

        assertEquals(2, filteredFlights.size());
        assertEquals(flights, filteredFlights);
    }

    private Flight createFlight(LocalDateTime... dates) {
        if (dates.length % 2 != 0) {
            throw new IllegalArgumentException("you must pass an even number of dates");
        }

        int segmentCount = dates.length / 2;
        List<Segment> segments = Arrays.asList(new Segment[segmentCount]);

        for (int i = 0; i < segmentCount; i++) {
            segments.set(i, new Segment(dates[i * 2], dates[i * 2 + 1]));
        }

        return new Flight(segments);
    }
}