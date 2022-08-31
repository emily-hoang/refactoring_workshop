package com.thoughtworks.movierental;

import com.thoughtworks.movierental.Customer.Customer;
import com.thoughtworks.movierental.Customer.Rental;
import com.thoughtworks.movierental.Formatter.HtmlStatementFormatter;
import com.thoughtworks.movierental.Formatter.IStatementFormatter;
import com.thoughtworks.movierental.Formatter.TextStatementFormatter;
import com.thoughtworks.movierental.Movie.Movie;
import com.thoughtworks.movierental.Movie.MovieCalculator;
import org.junit.Assert;
import org.junit.Test;

public class CustomerTest {
    private static final String CUSTOMER_NAME = "John";
    private static final String REGULAR_MOVIE_NAME = "The IT Crowd";
    private static final String BLU_RAY_MOVIE_NAME = "Avatar";
    private static final String NEW_RELEASE_MOVIE_NAME = "RRR";
    private static final String CHILDREN_MOVIE_NAME = "Frozen";

    @Test
    public void outputStatementForCustomerWithNoRental() {
        Customer customer = new Customer(CUSTOMER_NAME);

        IStatementFormatter textFormatter = new TextStatementFormatter();
        String actualStatement = customer.outputStatement(textFormatter);
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

        IStatementFormatter textFormatter = new TextStatementFormatter();
        String actualStatement = customer.outputStatement(textFormatter);
        String expectedStatement = "Rental Record for " + CUSTOMER_NAME + "\n";
        expectedStatement += "\t" + REGULAR_MOVIE_NAME + "\t2.0\n";
        expectedStatement += "Amount owed is 2.0\n";
        expectedStatement += "You earned 1 frequent renter points";

        Assert.assertEquals(actualStatement, expectedStatement);
    }
    @Test
    public void outputCustomerStatementWithANewReleaseMovieWithMoreThan1DayRented() {
        Movie newReleaseMovie = new Movie(NEW_RELEASE_MOVIE_NAME, 1);
        Rental rental = new Rental(newReleaseMovie, 3);
        Customer customer = new Customer(CUSTOMER_NAME);
        customer.addRental(rental);

        IStatementFormatter textFormatter = new TextStatementFormatter();
        String actualStatement = customer.outputStatement(textFormatter);
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

        IStatementFormatter textFormatter = new TextStatementFormatter();
        String actualStatement = customer.outputStatement(textFormatter);
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

        IStatementFormatter textFormatter = new TextStatementFormatter();
        String actualStatement = customer.outputStatement(textFormatter);
        String expectedStatement = "Rental Record for " + CUSTOMER_NAME + "\n";
        expectedStatement += "\t" + CHILDREN_MOVIE_NAME + "\t9.0\n";
        expectedStatement += "Amount owed is 9.0\n";
        expectedStatement += "You earned 2 frequent renter points";

        Assert.assertEquals(actualStatement, expectedStatement);
    }
    @Test
    public void outputCustomerStatementWithABluRayMovie() {
        Customer customer = new Customer(CUSTOMER_NAME);
        Movie blueRay = new Movie(BLU_RAY_MOVIE_NAME, 3);
        Rental rental = new Rental(blueRay, 3);
        customer.addRental(rental);

        IStatementFormatter textFormatter = new TextStatementFormatter();
        String actualStatement = customer.outputStatement(textFormatter);
        String expectedStatement = "Rental Record for " + CUSTOMER_NAME + "\n";
        expectedStatement += "\t" + BLU_RAY_MOVIE_NAME + "\t12.0\n";
        expectedStatement += "Amount owed is 12.0\n";
        expectedStatement += "You earned 3 frequent renter points";

        Assert.assertEquals(actualStatement, expectedStatement);
    }
    @Test
    public void outputHTMLStatementForCustomerWithMultipleMovies() {
        Customer customer = new Customer(CUSTOMER_NAME);
        Movie movie1 = new Movie(CHILDREN_MOVIE_NAME, 2);
        Rental rental1 = new Rental(movie1, 3);
        Movie movie2 = new Movie(NEW_RELEASE_MOVIE_NAME, 1);
        Rental rental2 = new Rental(movie2, 3);
        Movie movie3 = new Movie(BLU_RAY_MOVIE_NAME, 3);
        Rental rental3 = new Rental(movie3, 3);
        customer.addRental(rental1);
        customer.addRental(rental2);
        customer.addRental(rental3);

        IStatementFormatter htmlFormatter = new HtmlStatementFormatter();
        String actualStatement = customer.outputStatement(htmlFormatter);
        String expectedStatement = "<h1>Rental Record for <b>" + CUSTOMER_NAME + "</b></h1>" +
                "<p>" +
                CHILDREN_MOVIE_NAME + ": <b>1.5</b><br/>" +
                NEW_RELEASE_MOVIE_NAME + ": <b>9.0</b><br/>" +
                BLU_RAY_MOVIE_NAME + ": <b>12.0</b><br/>" +
                "</p><p>Amount owed is <b>22.5</b></p></br>" +
                "<p>You earned <b>6</b> frequent renter points</p></br>";

        Assert.assertEquals(actualStatement, expectedStatement);
    }
    @Test
    public void outputHTMLStatementForCustomerWithNoMovies() {
        Customer customer = new Customer(CUSTOMER_NAME);
        IStatementFormatter htmlFormatter = new HtmlStatementFormatter();

        String actualStatement = customer.outputStatement(htmlFormatter);
        String expectedStatement = "<h1>Rental Record for <b>" + CUSTOMER_NAME + "</b></h1><p>" +
                "</p><p>Amount owed is <b>0.0</b></p></br>" +
                "<p>You earned <b>0</b> frequent renter points</p></br>";

        Assert.assertEquals(actualStatement, expectedStatement);
    }
}