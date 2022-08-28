package com.thoughtworks.movierental.Movie;

import com.thoughtworks.movierental.Customer.Rental;

public class ChildrenMovieCalculator extends MovieCalculator {

    public double calculateMovieCost(Rental rental) {
        int dayRented = rental.getDaysRented();
        double amount = 1.5;
        if (dayRented > 3)
            amount += (dayRented - 3) * 1.5;
        return amount;
    }
}
