package com.thoughtworks.movierental.Movie;

import com.thoughtworks.movierental.Customer.Rental;

import static com.thoughtworks.movierental.Movie.Movie.*;

public class MovieCalculator {
    public Rental getRental() {
        return rental;
    }

    public void setRental(Rental rental, MovieCategory movieCategory) {
        this.rental = rental;
        this.movieCategory = movieCategory;
    }

    private Rental rental;
    private MovieCategory movieCategory;

    public MovieCalculator(Rental rental, MovieCategory movieCategory) {
        this.rental = rental;
        this.movieCategory = movieCategory;
    }

    private double calculateRentalCost() {
        double amount = 0;
        int dayRented = this.rental.getDaysRented();
        switch (movieCategory.getPriceCode()) {
            case REGULAR:
                amount += 2;
                if (rental.getDaysRented() > 2)
                    amount += (dayRented - 2) * 1.5;
                break;
            case NEW_RELEASE:
                amount += dayRented * 3;
                break;
            case CHILDRENS:
                amount += 1.5;
                if (dayRented > 3)
                    amount += (dayRented - 3) * 1.5;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + movieCategory.getPriceCode());
        }
        return amount;
    }
}
