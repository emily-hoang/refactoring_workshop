package com.thoughtworks.movierental;

public class MovieInfo {
    public String getMovieTitle() {
        return movieTitle;
    }

    private final String movieTitle;
    private final double cost;

    public MovieInfo(String title, double cost) {
        this.movieTitle = title;
        this.cost = cost;
    }

    public double getCost() {
        return cost;
    }
}
