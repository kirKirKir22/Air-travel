package com.gridnine.testing;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public interface FlightFilter {

    List<Flight> filterFlights(List<Flight> flights);

    default FlightFilter combine(FlightFilter other) {
        return flights -> {
            List<Flight> filteredFlights = this.filterFlights(flights);
            return other.filterFlights(filteredFlights);
        };


    }
}