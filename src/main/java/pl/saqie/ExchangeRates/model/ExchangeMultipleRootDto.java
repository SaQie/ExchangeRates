package pl.saqie.ExchangeRates.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class ExchangeMultipleRootDto {

    private String date;
    private List<ExchangeMultipleRatesDto> rates;

}
