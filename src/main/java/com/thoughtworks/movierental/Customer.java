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
        String statement = "Rental Record for " + getName() + "\n";

        List<RentalInfo> rentalInfoList = extractRentalInfo(rentals);
        for (RentalInfo rentalInfo : rentalInfoList) {
            statement += "\t" + rentalInfo.movieTitle + "\t" + rentalInfo.rentalAmount + "\n";
        }

        statement += "Amount owed is " + calculateTotalRentalAmount(rentals) + "\n";
        statement += "You earned " + calculateTotalPoints(rentals)
                + " frequent renter points";

        return statement;
    }

    private List<RentalInfo> extractRentalInfo(List<Rental> rentals) {
        List<RentalInfo> rentalInfoList = new ArrayList<>();
        for (Rental rental : rentals) {
            double rentalAmount = calculateRentalAmount(rental);
            String movieTitle = rental.getMovie().getTitle();

            RentalInfo rentalInfo = new RentalInfo(movieTitle, rentalAmount);
            rentalInfoList.add(rentalInfo);
        }
        return rentalInfoList;
    }

    private double calculateTotalRentalAmount(List<Rental> rentals) {
        double totalAmount = 0;
        for (Rental rental : rentals) {
            totalAmount += calculateRentalAmount(rental);
        }
        return totalAmount;
    }
    private double calculateRentalAmount(Rental rental) {
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
        private final double rentalAmount;

        public RentalInfo(String title, double amount) {
            this.movieTitle = title;
            this.rentalAmount = amount;
        }
    }
}

