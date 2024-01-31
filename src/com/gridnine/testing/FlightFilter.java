package com.gridnine.testing;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public interface FlightFilter {

    List<Flight> filterFlights(List<Flight> flights);

    default FlightFilter combine(Predicate<Flight> predicate) {
        return flights -> flights.stream().filter(predicate).collect(Collectors.toList());
    }


}