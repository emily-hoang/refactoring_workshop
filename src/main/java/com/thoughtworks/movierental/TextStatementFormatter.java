package com.thoughtworks.movierental;

public class TextStatementFormatter extends StatementFormatter {
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
