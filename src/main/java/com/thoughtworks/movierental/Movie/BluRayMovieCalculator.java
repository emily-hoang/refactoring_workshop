package com.thoughtworks.movierental.Movie;

import com.thoughtworks.movierental.Customer.Rental;

public class BluRayMovieCalculator extends MovieCalculator{
    public double calculateMovieCost(Rental rental) {
        int dayRented = rental.getDaysRented();
        double amount;
        amount = dayRented * 4;
        return amount;
    }
    @Override
    public int addFrequentPoints(Rental rental) {
        int frequentRenterPoints = 0;

        frequentRenterPoints += 3;

        return frequentRenterPoints;
    }
}
