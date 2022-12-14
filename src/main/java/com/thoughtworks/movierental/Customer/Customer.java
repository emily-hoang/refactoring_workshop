package com.thoughtworks.movierental.Customer;

import com.thoughtworks.movierental.Formatter.IStatementFormatter;
import com.thoughtworks.movierental.Movie.*;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private final String name;
    private final List<Rental> rentals = new ArrayList<>();
    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Rental arg) {
        rentals.add(arg);
    }
    public List<Rental> getRentals() {
        return rentals;
    }
    public String getName() {
        return name;
    }
    public String outputStatement(IStatementFormatter formatter) {
        CustomerRentalSummary customerRentalSummary = getCustomerRentalSummary(getRentals());
        return formatter.buildStatement(customerRentalSummary);
    }
    private CustomerRentalSummary getCustomerRentalSummary(List<Rental> rentals) {
        String customerName = getName();
        List<MovieInfo> movieInfoList = getRentalInfoList(rentals);
        double totalCost = calculateTotalRentalCost(rentals);
        int totalPoints = calculateTotalPoints(rentals);

        return new CustomerRentalSummary(customerName, totalCost, totalPoints, movieInfoList);
    }
    private List<MovieInfo> getRentalInfoList(List<Rental> rentals) {
        List<MovieInfo> movieInfoList = new ArrayList<>();
        for (Rental rental : rentals) {
            IMovieCalculator movieCalculator = createMovieCalculator(rental);

            assert movieCalculator != null;
            double cost = movieCalculator.calculateMovieCost(rental.getDaysRented());

            String movieTitle = rental.getMovie().getTitle();

            MovieInfo movieInfo = new MovieInfo(movieTitle, cost);
            movieInfoList.add(movieInfo);
        }
        return movieInfoList;
    }
    private double calculateTotalRentalCost(List<Rental> rentals) {
        double totalCost = 0;
        for (Rental rental : rentals) {
            IMovieCalculator movieCalculator = createMovieCalculator(rental);
            assert movieCalculator != null;
            totalCost += movieCalculator.calculateMovieCost(rental.getDaysRented());
        }
        return totalCost;
    }

    private int calculateTotalPoints(List<Rental> rentals) {
        int points = 0;
        for (Rental rental : rentals) {
            IMovieCalculator movieCalculator = createMovieCalculator(rental);
            assert movieCalculator != null;
            points += movieCalculator.addFrequentPoints(rental.getDaysRented());
        }
        return points;
    }

    private IMovieCalculator createMovieCalculator(Rental rental) {
        MovieCategory movieCategory = rental.getMovie().getMovieCategory();
        switch (movieCategory) {
            case CHILDREN:
                return new ChildrenMovieCalculator();
            case REGULAR:
                return new RegularMovieCalculator();
            case NEW_RELEASE:
                return new NewReleaseMovieCalculator();
            case BLU_RAY:
                return new BluRayMovieCalculator();
        }
        return null;
    }

}

