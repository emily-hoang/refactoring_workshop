package com.thoughtworks.movierental.Movie;

import com.thoughtworks.movierental.Customer.Rental;

public class BluRayMovieCalculator extends MovieCalculator{
    public double calculateMovieCost(int numOfDays) {
        double amount;
        amount = numOfDays * 4;
        return amount;
    }
    @Override
    public int addFrequentPoints(int numOfDays) {
        int frequentRenterPoints = 0;

        frequentRenterPoints += 3;

        return frequentRenterPoints;
    }
}
