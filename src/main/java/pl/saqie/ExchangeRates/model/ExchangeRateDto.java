package pl.saqie.ExchangeRates.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ExchangeRateDto {

    private String currencyCode;
    private String currencyName;
    private double bid;
    private double ask;
    private String date;



}
