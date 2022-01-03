package pl.saqie.ExchangeRates.webclient.exchangerates.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class ClientExchangeRateDto {

    private String currency;
    private String code;
    private List<ClientExchangeRateRootDto> rates;
}
