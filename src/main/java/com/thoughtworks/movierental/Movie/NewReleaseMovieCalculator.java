package com.thoughtworks.movierental.Movie;

import com.thoughtworks.movierental.Customer.Rental;

public class NewReleaseMovieCalculator extends MovieCalculator{
    public double calculateMovieCost(Rental rental) {
        int dayRented = rental.getDaysRented();
        double amount;
        amount = dayRented * 3;
        return amount;
    }
    @Override
    public int addFrequentPoints(Rental rental) {
        int frequentRenterPoints = 0;
        frequentRenterPoints++;

        if (rental.getDaysRented() > 1)
            frequentRenterPoints++;

        return frequentRenterPoints;
    }
}
