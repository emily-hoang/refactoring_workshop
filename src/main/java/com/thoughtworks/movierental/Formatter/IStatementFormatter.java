package com.thoughtworks.movierental.Formatter;

import com.thoughtworks.movierental.Customer.CustomerRentalSummary;

public interface IStatementFormatter {
    String buildStatement(CustomerRentalSummary customerRentalSummary);
}
