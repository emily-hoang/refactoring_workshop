package com.thoughtworks.movierental;

public interface IFormatterFactory {
    IStatementFormatter createFormatter();
}

class HtmlFormatterFactory implements IFormatterFactory {
    @Override
    public IStatementFormatter createFormatter() {
        return new HtmlStatementFormatter();
    }
}

class TextFormatterFactory implements IFormatterFactory {
    @Override
    public IStatementFormatter createFormatter() {
        return new TextStatementFormatter();
    }
}

