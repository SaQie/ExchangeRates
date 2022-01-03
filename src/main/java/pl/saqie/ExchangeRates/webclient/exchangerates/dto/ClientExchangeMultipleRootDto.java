package pl.saqie.ExchangeRates.webclient.exchangerates.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class ClientExchangeMultipleRootDto {

    private String table;
    private String no;
    private String tradingDate;
    private String effectiveDate;
    private List<ClientExchangeMultipleRateDto> rates;

}
