package com.thoughtworks.movierental.Movie;

import com.thoughtworks.movierental.Customer.Rental;

public class NewReleaseMovieCalculator extends MovieCalculator{
    public double calculateMovieCost(Rental rental) {
        int dayRented = rental.getDaysRented();
        double amount;
        amount = dayRented * 3;
        return amount;
    }
}
