package pl.saqie.ExchangeRates.webclient.exchangerates;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.saqie.ExchangeRates.model.ExchangeMultipleRatesDto;
import pl.saqie.ExchangeRates.model.ExchangeMultipleRootDto;
import pl.saqie.ExchangeRates.model.ExchangeRateDto;
import pl.saqie.ExchangeRates.webclient.exchangerates.dto.ClientExchangeMultipleRootDto;
import pl.saqie.ExchangeRates.webclient.exchangerates.dto.ClientExchangeRateDto;

import java.util.stream.Collectors;

@Component
public class ExchangeRatesClient {

    private final static String API_URL = "http://api.nbp.pl/api/exchangerates/";
    private RestTemplate restTemplate = new RestTemplate();


    public ExchangeRateDto exchangeSingleCurrencyFromClient(String czk) {
        ClientExchangeRateDto exchangeRatesDto = callGetMehod("rates/c/{code}/?format=json",
                ClientExchangeRateDto.class, czk);
        return ExchangeRateDto.builder()
                .currencyName(exchangeRatesDto.getCurrency())
                .currencyCode(exchangeRatesDto.getCode())
                .bid(exchangeRatesDto.getRates().get(0).getBid())
                .ask(exchangeRatesDto.getRates().get(0).getAsk())
                .date(exchangeRatesDto.getRates().get(0).getEffectiveDate())
                .build();
    }

    public ExchangeMultipleRootDto getAllExchangeRates(){
        ClientExchangeMultipleRootDto[] clientExchangeMultipleRootDto = callGetMehod("tables/C/?format=json",
              ClientExchangeMultipleRootDto[].class);
        return ExchangeMultipleRootDto.builder()
                .date(clientExchangeMultipleRootDto[0].getEffectiveDate())
                .rates(clientExchangeMultipleRootDto[0].getRates().stream()
                        .map(rates -> new ExchangeMultipleRatesDto(rates.getCurrency(), rates.getCode(), rates.getBid(), rates.getAsk()))
                        .collect(Collectors.toList()))
                .build();

    }

    private <T> T callGetMehod(String url, Class<T> responseType, Object... objects) {
        return restTemplate.getForObject(API_URL + url, responseType, objects);
    }
}
