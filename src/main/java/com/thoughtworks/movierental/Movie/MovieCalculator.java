package com.thoughtworks.movierental.Movie;

import com.thoughtworks.movierental.Customer.Rental;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class MovieCalculator implements IMovieCalculator{

    public double calculateMovieCost(int numOfDays) {
        throw new NotImplementedException();
    }

    public int addFrequentPoints(int numOfDays) {
        int frequentRenterPoints = 0;
        frequentRenterPoints++;
        return frequentRenterPoints;
    }
}
