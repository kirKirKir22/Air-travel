package com.gridnine.testing;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;



public class DepartureBeforeCurrentTimeFilter implements FlightFilter {

    @Override
    public List<Flight> filterFlights(List<Flight> flights) {

        return flights.stream()
                .filter(flight -> flight.getSegments().stream()
                        .allMatch(segment -> segment.getDepartureDate().isAfter(LocalDateTime.now())))
                .collect(Collectors.toList());
    }
}