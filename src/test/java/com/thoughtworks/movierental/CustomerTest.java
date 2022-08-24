package com.thoughtworks.movierental;

import org.junit.Assert;
import org.junit.Test;

public class CustomerTest {
    private static final String CUSTOMER_NAME = "John";
    private static final String REGULAR_MOVIE_NAME = "The IT Crowd";
    private static final String NEW_RELEASE_MOVIE_NAME = "RRR";
    private static final String CHILDREN_MOVIE_NAME = "Frozen";

    @Test
    public void outputStatementForCustomerWithNoRental() {
        Customer customer = new Customer(CUSTOMER_NAME);

        String actualStatement = customer.outputStatement();
        String expectedStatement = "Rental Record for " + CUSTOMER_NAME + "\n";
        expectedStatement += "Amount owed is 0.0\n";
        expectedStatement += "You earned 0 frequent renter points";

        Assert.assertEquals(actualStatement, expectedStatement);
    }

    @Test
    public void outputCustomerStatementWithARegularMovie() {
        Customer customer = new Customer(CUSTOMER_NAME);
        Movie regularMovie = new Movie("The IT Crowd", 0);
        Rental rental = new Rental(regularMovie, 2);

        customer.addRental(rental);

        String actualStatement = customer.outputStatement();
        String expectedStatement = "Rental Record for " + CUSTOMER_NAME + "\n";
        expectedStatement += "\t" + REGULAR_MOVIE_NAME + "\t2.0\n";
        expectedStatement += "Amount owed is 2.0\n";
        expectedStatement += "You earned 1 frequent renter points";

        Assert.assertEquals(actualStatement, expectedStatement);
    }

    @Test
    public void outputCustomerStatementWithANewReleaseMovieWithMoreThan1DayRented() {
        Customer customer = new Customer(CUSTOMER_NAME);
        Movie newReleaseMovie = new Movie(NEW_RELEASE_MOVIE_NAME, 1);
        Rental rental = new Rental(newReleaseMovie, 3);

        customer.addRental(rental);

        String actualStatement = customer.outputStatement();
        String expectedStatement = "Rental Record for " + CUSTOMER_NAME + "\n";
        expectedStatement += "\t" + NEW_RELEASE_MOVIE_NAME +"\t9.0\n";
        expectedStatement += "Amount owed is 9.0\n";
        expectedStatement += "You earned 2 frequent renter points";

        Assert.assertEquals(actualStatement, expectedStatement);
    }

    @Test
    public void outputCustomerStatementWithANewReleaseMovieWithLessThan1DayRented() {
        Customer customer = new Customer(CUSTOMER_NAME);
        Movie newReleaseMovie = new Movie(NEW_RELEASE_MOVIE_NAME, 1);
        Rental rental = new Rental(newReleaseMovie, 1);

        customer.addRental(rental);

        String actualStatement = customer.outputStatement();
        String expectedStatement = "Rental Record for " + CUSTOMER_NAME + "\n";
        expectedStatement += "\t" + NEW_RELEASE_MOVIE_NAME + "\t3.0\n";
        expectedStatement += "Amount owed is 3.0\n";
        expectedStatement += "You earned 1 frequent renter points";

        Assert.assertEquals(actualStatement, expectedStatement);
    }

    @Test
    public void outputCustomerStatementWithAChildrenMovie() {
        Customer customer = new Customer(CUSTOMER_NAME);
        Movie newReleaseMovie = new Movie(CHILDREN_MOVIE_NAME, 1);
        Rental rental = new Rental(newReleaseMovie, 3);

        customer.addRental(rental);

        String actualStatement = customer.outputStatement();
        String expectedStatement = "Rental Record for " + CUSTOMER_NAME + "\n";
        expectedStatement += "\t" + CHILDREN_MOVIE_NAME + "\t9.0\n";
        expectedStatement += "Amount owed is 9.0\n";
        expectedStatement += "You earned 2 frequent renter points";

        Assert.assertEquals(actualStatement, expectedStatement);
    }

    @Test
    public void outputHTMLStatementForMultipleMovies() {
        Customer customer = new Customer(CUSTOMER_NAME);
        Movie movie1 = new Movie(CHILDREN_MOVIE_NAME, 1);
        Rental rental1 = new Rental(movie1, 3);
        Movie movie2 = new Movie(NEW_RELEASE_MOVIE_NAME, 1);
        Rental rental2 = new Rental(movie2, 3);

        customer.addRental(rental1);
        customer.addRental(rental2);

        String actualStatement = customer.outputHtmlStatement();
        String expectedStatement = "<h1>Rental Record for <b>John</b></h1>" +
                "<p>" +
                "Frozen: <b>9.0</b><br/>" +
                "RRR: <b>9.0</b><br/>" +
                "</p><p>Amount owed is <b>18.0</b></p></br>" +
                "<p>You earned <b>4</b> frequent renter points</p></br>";

        Assert.assertEquals(actualStatement, expectedStatement);
    }
}