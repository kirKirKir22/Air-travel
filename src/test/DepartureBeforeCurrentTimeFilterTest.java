package test;

import com.gridnine.testing.DepartureBeforeCurrentTimeFilter;
import com.gridnine.testing.Flight;
import com.gridnine.testing.Segment;
import org.junit.jupiter.api.Test;


import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DepartureBeforeCurrentTimeFilterTest {

    @Test
    void filterFlights_DepartureBeforeCurrentTime_ValidFlightShouldPassFilter() {
        DepartureBeforeCurrentTimeFilter filter = new DepartureBeforeCurrentTimeFilter();

        Flight validFlight = createFlight(LocalDateTime.now().plusHours(1));
        Flight invalidFlight = createFlight(LocalDateTime.now().minusHours(1));

        List<Flight> flights = Arrays.asList(validFlight, invalidFlight);

        List<Flight> filteredFlights = filter.filterFlights(flights);

        assertEquals(1, filteredFlights.size());
        assertEquals(validFlight, filteredFlights.get(0));
    }

    private Flight createFlight(LocalDateTime departureTime) {

        Segment segment = new Segment(departureTime, LocalDateTime.now().plusHours(2));
        return new Flight(Arrays.asList(segment));
    }
}