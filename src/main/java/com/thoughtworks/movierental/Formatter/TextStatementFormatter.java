package com.thoughtworks.movierental.Formatter;

import com.thoughtworks.movierental.Customer.CustomerRentalSummary;
import com.thoughtworks.movierental.Movie.MovieInfo;

public class TextStatementFormatter implements IStatementFormatter {
    public String outputStatement(CustomerRentalSummary customerRentalSummary) {
        String statement = "Rental Record for " + customerRentalSummary.getCustomerName() + "\n";

        for (MovieInfo movieInfo : customerRentalSummary.getMovieInfoList()) {
            statement += "\t" + movieInfo.getMovieTitle() + "\t" + movieInfo.getCost() + "\n";
        }

        statement += "Amount owed is " + customerRentalSummary.getTotalCost() + "\n";
        statement += "You earned " + customerRentalSummary.getTotalPoints()
                + " frequent renter points";

        return statement;
    }
}
