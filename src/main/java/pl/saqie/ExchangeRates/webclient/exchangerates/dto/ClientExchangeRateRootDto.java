package pl.saqie.ExchangeRates.webclient.exchangerates.dto;

import lombok.Getter;

@Getter
public class ClientExchangeRateRootDto {

    private double bid;
    private double ask;
    private String effectiveDate;

}
