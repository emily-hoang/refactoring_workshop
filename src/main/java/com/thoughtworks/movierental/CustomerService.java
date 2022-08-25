package com.thoughtworks.movierental;

import java.util.ArrayList;
import java.util.List;

public class CustomerService {
    private Customer customer;
    private IFormatterFactory formatterFactory;

    public CustomerService(Customer customer, IFormatterFactory formatterFactory) {
        this.customer = customer;
        this.formatterFactory = formatterFactory;
    }

    public String outputStatementAsString() {
        CustomerRentalSummary customerRentalSummary = getCustomerRentalSummary(customer.getRentals());
        IFormatterFactory factory = new TextFormatterFactory();
        IStatementFormatter formatter = factory.createFormatter();
        return formatter.outputStatement(customerRentalSummary);
    }

    public String outputHtmlStatement() {
        CustomerRentalSummary customerRentalSummary = getCustomerRentalSummary(customer.getRentals());
        IFormatterFactory factory = new HtmlFormatterFactory();
        IStatementFormatter formatter = factory.createFormatter();
        return formatter.outputStatement(customerRentalSummary);
    }

    private CustomerRentalSummary getCustomerRentalSummary(List<Rental> rentals) {
        String customerName = customer.getName();
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