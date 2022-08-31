package com.thoughtworks.movierental.Movie;

public class Movie {
    public static final int CHILDREN = 2;
    public static final int REGULAR = 0;
    public static final int NEW_RELEASE = 1;
    public static final int BLU_RAY = 3;

    private String title;
    private int priceCode;

    public Movie(String title, int priceCode) {
        this.title = title;
        this.priceCode = priceCode;
    }

    public int getPriceCode() {
        return priceCode;
    }

    public void setPriceCode(int arg) {
        priceCode = arg;
    }

    public String getTitle() {
        return title;
    }

}