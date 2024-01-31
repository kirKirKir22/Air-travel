package com.gridnine.testing;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ArrivalBeforeDepartureFilter implements FlightFilter {

    @Override
    public List<Flight> filterFlights(List<Flight> flights) {
        Predicate<Flight> arrivalBeforeDeparturePredicate = flight -> flight.getSegments().stream()
                .allMatch(segment -> segment.getArrivalDate().isAfter(segment.getDepartureDate()));

        return flights.stream()
                .filter(arrivalBeforeDeparturePredicate)
                .collect(Collectors.toList());

    }
}