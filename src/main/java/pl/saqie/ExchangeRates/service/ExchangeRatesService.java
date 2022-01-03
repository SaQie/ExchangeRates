package pl.saqie.ExchangeRates.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.saqie.ExchangeRates.model.ExchangeMultipleRootDto;
import pl.saqie.ExchangeRates.model.ExchangeRateDto;
import pl.saqie.ExchangeRates.model.UserValue;
import pl.saqie.ExchangeRates.webclient.exchangerates.ExchangeRatesClient;

@Service
@RequiredArgsConstructor
public class ExchangeRatesService {

    private final ExchangeRatesClient exchangeRatesClient;

    public ExchangeRateDto getSingleExchange(String code) {
        return exchangeRatesClient.exchangeSingleCurrencyFromClient(code);
    }
    public ExchangeMultipleRootDto getRatesOfAllCurrencies(){
        return exchangeRatesClient.getAllExchangeRates();
    }


    public double exchangeValueSell(ExchangeRateDto exchangeRatesDto, UserValue userValue) {
        double value = exchangeRatesDto.getBid() * userValue.getMoney();
        return roundToTwoDecimalPlace(value);
    }

    public double exchangeValueBuy(ExchangeRateDto exchangeRatesDto, UserValue userValue) {
        double value = (userValue.getMoney()/exchangeRatesDto.getAsk());
        return roundToTwoDecimalPlace(value);
    }

    private double roundToTwoDecimalPlace(double value){
        return Math.round(value * 100.0) / 100.0;
    }
}
