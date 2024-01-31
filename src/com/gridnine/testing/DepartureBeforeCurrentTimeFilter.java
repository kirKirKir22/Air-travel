package com.gridnine.testing;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;


public class DepartureBeforeCurrentTimeFilter implements FlightFilter {

    @Override
    public List<Flight> filterFlights(List<Flight> flights) {
        Predicate<Flight> departureBeforeNowPredicate = flight -> flight.getSegments().stream()
                .allMatch(segment -> segment.getDepartureDate().isAfter(LocalDateTime.now()));

        return flights.stream()
                .filter(departureBeforeNowPredicate)
                .collect(Collectors.toList());
    }

}