package com.gridnine.testing.filter;

import com.gridnine.testing.flight.Flight;

import java.util.List;

public interface FlightFilter {

    List<Flight> filterFlights(List<Flight> flights);

    default FlightFilter combine(FlightFilter other) {
        return flights -> {
            List<Flight> filteredFlights = this.filterFlights(flights);
            return other.filterFlights(filteredFlights);
        };


    }
}