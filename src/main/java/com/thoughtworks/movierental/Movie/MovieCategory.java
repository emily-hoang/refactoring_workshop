package com.thoughtworks.movierental.Movie;


public enum MovieCategory {
    CHILDRENS(2), REGULAR(0), NEW_RELEASE(1);

    public int getPriceCode() {
        return this.priceCode;
    }

    private final int priceCode;

    MovieCategory(int priceCode) {
        this.priceCode = priceCode;
    }
}
