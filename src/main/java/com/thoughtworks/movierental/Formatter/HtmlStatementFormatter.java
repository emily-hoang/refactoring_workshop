package com.thoughtworks.movierental.Formatter;

import com.thoughtworks.movierental.Customer.CustomerRentalSummary;
import com.thoughtworks.movierental.Movie.MovieInfo;

public class HtmlStatementFormatter implements IStatementFormatter {
    public String outputStatement(CustomerRentalSummary customerRentalSummary) {
        String statement = "<h1>Rental Record for <b>" + customerRentalSummary.customerName + "</b></h1><p>";

        for (MovieInfo movieInfo : customerRentalSummary.getMovieInfoList()) {
            statement += movieInfo.getMovieTitle() + ": <b>" + movieInfo.getCost() + "</b><br/>";
        }

        statement += "</p><p>Amount owed is <b>" + customerRentalSummary.getTotalCost() + "</b></p></br>";
        statement += "<p>You earned <b>" + customerRentalSummary.getTotalPoints()
                + "</b> frequent renter points</p></br>";

        return statement;
    }
}
