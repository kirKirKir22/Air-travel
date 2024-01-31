package test;

import com.gridnine.testing.filter.ArrivalBeforeDepartureFilter;
import com.gridnine.testing.flight.Flight;
import com.gridnine.testing.flight.Segment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArrivalBeforeDepartureFilterTest {


    private ArrivalBeforeDepartureFilter filter;
    private Flight validFlight;
    private Flight invalidFlight;
    private Flight flight1;
    private Flight flight2;

    @BeforeEach
    void setUp() {
        filter = new ArrivalBeforeDepartureFilter();
        validFlight = createFlight(LocalDateTime.now().plusHours(2), LocalDateTime.now().plusHours(4));
        invalidFlight = createFlight(LocalDateTime.now().plusHours(2), LocalDateTime.now().minusHours(1));
        flight1 = createFlight(LocalDateTime.now().plusHours(2), LocalDateTime.now().plusHours(4));
        flight2 = createFlight(LocalDateTime.now().plusHours(3), LocalDateTime.now().plusHours(5));
    }

    @Test
    void filterFlights_ArrivalBeforeDeparture_NoFlightShouldPassFilter() {
        List<Flight> flights = Arrays.asList(validFlight, invalidFlight);

        List<Flight> filteredFlights = filter.filterFlights(flights);

        assertEquals(1, filteredFlights.size());
    }

    @Test
    void filterFlights_ArrivalAfterDeparture_AllFlightsShouldPassFilter() {
        List<Flight> flights = Arrays.asList(flight1, flight2);

        List<Flight> filteredFlights = filter.filterFlights(flights);

        assertEquals(2, filteredFlights.size());
        assertEquals(flights, filteredFlights);
    }

    private Flight createFlight(LocalDateTime departureTime, LocalDateTime arrivalTime) {
        Segment segment = new Segment(departureTime, arrivalTime);
        return new Flight(Arrays.asList(segment));
    }
}