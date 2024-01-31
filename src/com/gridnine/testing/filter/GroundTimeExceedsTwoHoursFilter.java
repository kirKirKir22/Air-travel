package com.gridnine.testing.filter;

import com.gridnine.testing.flight.Flight;
import com.gridnine.testing.flight.Segment;

import java.time.Duration;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class GroundTimeExceedsTwoHoursFilter implements FlightFilter {

    @Override
    public List<Flight> filterFlights(List<Flight> flights) {
        Predicate<Flight> groundTimeExceedsTwoHoursPredicate = flight -> calculateTotalGroundTime(flight).compareTo(Duration.ofHours(2)) <= 0;

        return flights.stream()
                .filter(groundTimeExceedsTwoHoursPredicate)
                .collect(Collectors.toList());
    }

    private Duration calculateTotalGroundTime(Flight flight) {
        List<Segment> segments = flight.getSegments();
        long totalGroundTime = 0;
        for (int i = 0; i < segments.size() - 1; i++) {
            totalGroundTime += Duration.between(segments.get(i).getArrivalDate(), segments.get(i + 1).getDepartureDate()).toHours();
        }
        return Duration.ofHours(totalGroundTime);
    }
}
