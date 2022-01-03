package pl.saqie.ExchangeRates.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Currency {
    EUR("Euro"),
    USD("Dolar amerykański"),
    AUD("Dolar australijski"),
    CAD("Dolar kanadyjski"),
    CHF("Frank szwajcarski"),
    GBP("Funt szterling"),
    JPY("Jen"),
    CZK("Korona czeska"),
    DKK("Korona duńska"),
    NOK("Korona norweska"),
    SEK("Korona szwedzka");

    private String description;

    Currency(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public List<Currency> currencyList(){
        return new ArrayList<>(Arrays.asList(Currency.values()));
    }
}
