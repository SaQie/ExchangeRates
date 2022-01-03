package pl.saqie.ExchangeRates.webclient.exchangerates.dto;

import lombok.Getter;

@Getter
public class ClientExchangeMultipleRateDto {

    private String currency;
    private String code;
    private double bid;
    private double ask;
}
