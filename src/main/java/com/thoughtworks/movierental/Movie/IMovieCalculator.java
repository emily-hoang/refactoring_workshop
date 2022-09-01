package com.thoughtworks.movierental.Movie;

import com.thoughtworks.movierental.Customer.Rental;

public interface IMovieCalculator {
    double calculateMovieCost(int numOfDays);
    int addFrequentPoints(int numOfDays);
}
