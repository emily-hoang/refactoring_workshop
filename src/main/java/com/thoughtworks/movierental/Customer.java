package com.thoughtworks.movierental;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String name;
    private List<Rental> rentals = new ArrayList<>();

    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Rental arg) {
        rentals.add(arg);
    }

    public String getName() {
        return name;
    }

    public String statement() {
        int frequentRenterPoints = 0;
        String statement = "Rental Record for " + getName() + "\n";
        
        for (Rental rental : rentals) {
            Movie movie = rental.getMovie();
            int dayRented = rental.getDaysRented();
            frequentRenterPoints = addFrequentRenterPoints(movie, dayRented, frequentRenterPoints);

            double rentalAmount = calculateRentalAmount(movie, dayRented);

            statement += "\t" + movie.getTitle() + "\t" + rentalAmount + "\n";
        }

        statement += "Amount owed is " + calculateTotalAmount(rentals) + "\n";
        statement += "You earned " + frequentRenterPoints
                + " frequent renter points";
        return statement;
    }

    public double calculateTotalAmount(List<Rental> rentals) {
        double totalAmount = 0;
        for (Rental rental : rentals) {
            totalAmount += calculateRentalAmount(rental.getMovie(), rental.getDaysRented());
        }
        return totalAmount;
    }
    public double calculateRentalAmount(Movie movie, int dayRented) {
        double amount = 0;
        switch (movie.getPriceCode()) {
            case Movie.REGULAR:
                amount += 2;
                if (dayRented > 2)
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

    public int addFrequentRenterPoints(Movie movie, int dayRented, int frequentRenterPoints) {
            frequentRenterPoints++;

            if ((movie.getPriceCode() == Movie.NEW_RELEASE)
                    &&
                    dayRented > 1)
                frequentRenterPoints++;

            return frequentRenterPoints;
    }
}

