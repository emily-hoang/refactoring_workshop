package com.thoughtworks.movierental.Movie;

import com.thoughtworks.movierental.Customer.Rental;

public class RegularMovieCalculator extends MovieCalculator{
    public double calculateMovieCost(int numOfDays) {
        double amount = 2;
        if (numOfDays > 2)
            amount += (numOfDays - 2) * 1.5;
        return amount;
    }
}
