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


        FlightFilter combinedFilter1 = departureFilter.combine(arrivalFilter);
        List<Flight> combinedFilteredFlights1 = combinedFilter1.filterFlights(flights);
        System.out.println("\nРезультат комбинации фильтра вылета до текущего момента времени и фильтра прилета раньше вылета:");
        printFlights(combinedFilteredFlights1);

        FlightFilter combinedFilter2 = departureFilter.combine(arrivalFilter).combine(groundTimeFilter);
        List<Flight> combinedFilteredFlights2 = combinedFilter2.filterFlights(flights);
        System.out.println("\nРезультат комбинации трех фильтров:");
        printFlights(combinedFilteredFlights2);


    }


    private static void printFlights(List<Flight> flights) {
        for (Flight flight : flights) {
            System.out.println(flight);
        }
    }
}