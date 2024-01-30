package com.gridnine.testing;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Flight> flights = FlightBuilder.createFlights();


        DepartureBeforeCurrentTimeFilter departureFilter = new DepartureBeforeCurrentTimeFilter();
        ArrivalBeforeDepartureFilter arrivalFilter = new ArrivalBeforeDepartureFilter();
        GroundTimeExceedsTwoHoursFilter groundTimeFilter = new GroundTimeExceedsTwoHoursFilter();


        List<Flight> departureFilteredFlights = departureFilter.filterFlights(flights);
        List<Flight> arrivalFilteredFlights = arrivalFilter.filterFlights(flights);
        List<Flight> groundTimeFilteredFlights = groundTimeFilter.filterFlights(flights);


        System.out.println("результат применения фильтра вылета до текущего момента времени:");
        printFlights(departureFilteredFlights);

        System.out.println("\nрезультат применения фильтра прилета раньше вылета:");
        printFlights(arrivalFilteredFlights);

        System.out.println("\nрезультат применения фильтра превышения двух часов времени на земле:");
        printFlights(groundTimeFilteredFlights);
    }


    private static void printFlights(List<Flight> flights) {
        for (Flight flight : flights) {
            System.out.println(flight);
        }
    }
}