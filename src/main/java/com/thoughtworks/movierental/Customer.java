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
        return outputCustomerStatementAsString();
    }

    public String outputCustomerStatementAsString() {
        CustomerRentalSummary customerRentalSummary = getCustomerRentalSummary(rentals);
        String statement = "Rental Record for " + customerRentalSummary.customerName + "\n";

        for (RentalInfo rentalInfo : customerRentalSummary.rentalInfoList) {
            statement += "\t" + rentalInfo.movieTitle + "\t" + rentalInfo.cost + "\n";
        }

        statement += "Amount owed is " + customerRentalSummary.totalCost + "\n";
        statement += "You earned " + customerRentalSummary.totalPoints
                + " frequent renter points";

        return statement;
    }

    private CustomerRentalSummary getCustomerRentalSummary(List<Rental> rentals) {
        String customerName = getName();
        List<RentalInfo> rentalInfoList = getRentalInfoList(rentals);
        double totalCost = calculateTotalRentalCost(rentals);
        int totalPoints = calculateTotalPoints(rentals);

        return new CustomerRentalSummary(customerName, totalCost, totalPoints, rentalInfoList);
    }

    private List<RentalInfo> getRentalInfoList(List<Rental> rentals) {
        List<RentalInfo> rentalInfoList = new ArrayList<>();
        for (Rental rental : rentals) {
            double cost = calculateRentalCost(rental);
            String movieTitle = rental.getMovie().getTitle();

            RentalInfo rentalInfo = new RentalInfo(movieTitle, cost);
            rentalInfoList.add(rentalInfo);
        }
        return rentalInfoList;
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

    private static class RentalInfo {
        private final String movieTitle;
        private final double cost;

        public RentalInfo(String title, double cost) {
            this.movieTitle = title;
            this.cost = cost;
        }
    }

    private static class CustomerRentalSummary {
        private String customerName;
        private double totalCost;
        private int totalPoints;
        private List<RentalInfo> rentalInfoList;

        public CustomerRentalSummary(
                String customerName,
                double totalCost,
                int totalPoints,
                List<RentalInfo> rentalInfoList) {
            this.customerName = customerName;
            this.totalCost = totalCost;
            this.totalPoints = totalPoints;
            this.rentalInfoList =rentalInfoList;
        }
    }
}

