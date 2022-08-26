package com.thoughtworks.movierental.Customer;

import com.thoughtworks.movierental.Movie.MovieInfo;

import java.util.List;



public class CustomerRentalSummary {
    public String customerName;

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    private double totalCost;

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    private int totalPoints;

    public List<MovieInfo> getMovieInfoList() {
        return movieInfoList;
    }

    public void setMovieInfoList(List<MovieInfo> movieInfoList) {
        this.movieInfoList = movieInfoList;
    }

    private List<MovieInfo> movieInfoList;

    public CustomerRentalSummary(
            String customerName,
            double totalCost,
            int totalPoints,
            List<MovieInfo> movieInfoList) {
        this.customerName = customerName;
        this.totalCost = totalCost;
        this.totalPoints = totalPoints;
        this.movieInfoList = movieInfoList;
    }


    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
