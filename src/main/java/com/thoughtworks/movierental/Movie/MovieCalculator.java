package com.thoughtworks.movierental.Movie;

import com.thoughtworks.movierental.Customer.Rental;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class MovieCalculator implements IMovieCalculator{

    public double calculateMovieCost(Rental rental) {
        throw new NotImplementedException();
    }

    public int addFrequentPoints(Rental rental) {
        int frequentRenterPoints = 0;
        frequentRenterPoints++;
        return frequentRenterPoints;
    }
}
