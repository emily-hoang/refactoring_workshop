package com.thoughtworks.movierental.Movie;

import com.thoughtworks.movierental.Customer.Rental;

public class RegularMovieCalculator extends MovieCalculator{
    public double calculateMovieCost(Rental rental) {
        int dayRented = rental.getDaysRented();
        double amount = 2;
        if (rental.getDaysRented() > 2)
            amount += (dayRented - 2) * 1.5;
        return amount;
    }
    }
