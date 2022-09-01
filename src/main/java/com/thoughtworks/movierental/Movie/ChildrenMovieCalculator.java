package com.thoughtworks.movierental.Movie;

import com.thoughtworks.movierental.Customer.Rental;

public class ChildrenMovieCalculator extends MovieCalculator {

    public double calculateMovieCost(int numOfDays) {
        double amount = 1.5;
        if (numOfDays > 3)
            amount += (numOfDays - 3) * 1.5;
        return amount;
    }
}
