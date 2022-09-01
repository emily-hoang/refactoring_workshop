package com.thoughtworks.movierental.Movie;

import com.thoughtworks.movierental.Customer.Rental;

public class NewReleaseMovieCalculator extends MovieCalculator{
    public double calculateMovieCost(int numOfDays) {
        double amount;
        amount = numOfDays * 3;
        return amount;
    }
    @Override
    public int addFrequentPoints(int numOfDays) {
        int frequentRenterPoints = 0;
        frequentRenterPoints++;

        if (numOfDays > 1)
            frequentRenterPoints++;

        return frequentRenterPoints;
    }
}
