package com.thoughtworks.movierental;

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

    public String getName() {
        return name;
    }

    public String outputStatement() {
        CustomerRentalSummary customerRentalSummary = getCustomerRentalSummary(rentals);
        return outputCustomerStatementAsString(customerRentalSummary);
    }

    public String outputHtmlStatement() {
        CustomerRentalSummary customerRentalSummary = getCustomerRentalSummary(rentals);
        return outputCustomerStatementInHtml(customerRentalSummary);
    }

    public String outputCustomerStatementAsString(CustomerRentalSummary customerRentalSummary) {
        String statement = "Rental Record for " + customerRentalSummary.getCustomerName() + "\n";

        for (MovieInfo movieInfo : customerRentalSummary.getMovieInfoList()) {
            statement += "\t" + movieInfo.getMovieTitle() + "\t" + movieInfo.getCost() + "\n";
        }

        statement += "Amount owed is " + customerRentalSummary.getTotalCost() + "\n";
        statement += "You earned " + customerRentalSummary.getTotalPoints()
                + " frequent renter points";

        return statement;
    }

    public String outputCustomerStatementInHtml(CustomerRentalSummary customerRentalSummary) {
        String statement = "<h1>Rental Record for <b>" + customerRentalSummary.customerName + "</b></h1><p>";

        for (MovieInfo movieInfo : customerRentalSummary.getMovieInfoList()) {
            statement += movieInfo.getMovieTitle() + ": <b>" + movieInfo.getCost() + "</b><br/>";
        }

        statement += "</p><p>Amount owed is <b>" + customerRentalSummary.getTotalCost() + "</b></p></br>";
        statement += "<p>You earned <b>" + customerRentalSummary.getTotalPoints()
                + "</b> frequent renter points</p></br>";

        return statement;
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
            double cost = calculateRentalCost(rental);
            String movieTitle = rental.getMovie().getTitle();

            MovieInfo movieInfo = new MovieInfo(movieTitle, cost);
            movieInfoList.add(movieInfo);
        }
        return movieInfoList;
    }

    private double calculateTotalRentalCost(List<Rental> rentals) {
        double totalCost = 0;
        for (Rental rental : rentals) {
            totalCost += calculateRentalCost(rental);
        }
        return totalCost;
    }
    private double calculateRentalCost(Rental rental) {
        double amount = 0;
        int dayRented = rental.getDaysRented();
        switch (rental.getMovie().getPriceCode()) {
            case Movie.REGULAR:
                amount += 2;
                if (rental.getDaysRented() > 2)
                    amount += (dayRented - 2) * 1.5;
                break;
            case Movie.NEW_RELEASE:
                amount += dayRented * 3;
                break;
            case Movie.CHILDRENS:
                amount += 1.5;
                if (dayRented > 3)
                    amount += (dayRented - 3) * 1.5;
                break;
        }
        return amount;
    }

    private int addFrequentPoints(Rental rental, int frequentRenterPoints) {
            frequentRenterPoints++;

            if ((rental.getMovie().getPriceCode() == Movie.NEW_RELEASE)
                    &&
                    rental.getDaysRented() > 1)
                frequentRenterPoints++;

            return frequentRenterPoints;
    }
    private int calculateTotalPoints(List<Rental> rentals) {
        int points = 0;
        for (Rental rental : rentals) {
            points = addFrequentPoints(rental, points);
        }
        return points;
    }


}

