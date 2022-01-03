package pl.saqie.ExchangeRates.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString
public class ExchangeMultipleRatesDto{

    private String currency;
    private String code;
    private double bid;
    private double ask;
}
