package com.thoughtworks.movierental.Movie;

import com.thoughtworks.movierental.Customer.Rental;

public interface IMovieCalculator {
    double calculateMovieCost(Rental rental);
    int addFrequentPoints(Rental rental);
}
