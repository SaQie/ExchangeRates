package pl.saqie.ExchangeRates.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.saqie.ExchangeRates.model.Currency;
import pl.saqie.ExchangeRates.model.CurrencyCode;
import pl.saqie.ExchangeRates.model.UserValue;
import pl.saqie.ExchangeRates.service.ExchangeRatesService;

import java.util.Arrays;

@Controller
@RequiredArgsConstructor
public class ExchangeRatesController {

    private final ExchangeRatesService exchangeRatesService;

    @GetMapping("/waluta")
    public String getHomePage(Model model){
        model.addAttribute("currency", Currency.values());
        model.addAttribute(new CurrencyCode());
        return "home";
    }

    @PostMapping("/waluta")
    public String getRatesPageWithSelectedCurrency(@ModelAttribute CurrencyCode currencyCode){
        return "redirect:/waluta/" + currencyCode.getCode();
    }

    @GetMapping("/waluta/{code}")
    public String getExchangeSingleCurrency(@PathVariable String code, Model model){
        model.addAttribute("rates", exchangeRatesService.getSingleExchange(code));
        model.addAttribute(new UserValue());
        return "rates";
    }

    @PostMapping("/waluta/{code}/sprzedaj")
    public String getExchangeSingleCurrencyWithValue(@PathVariable String code, @ModelAttribute UserValue userValue,  Model model){
        double value = exchangeRatesService.exchangeValueSell(exchangeRatesService.getSingleExchange(code), userValue);
        model.addAttribute("rates", exchangeRatesService.getSingleExchange(code));
        model.addAttribute("sell", true);
        model.addAttribute("valueAfter", value);
        return "rates";
    }

    @GetMapping("/waluta/{code}/sprzedaj")
    public String redirectToCorrectUrlSell(@PathVariable String code, @ModelAttribute UserValue userValue){
        return "redirect:/waluta/" + code;
    }


    @PostMapping("/waluta/{code}/kup")
    public String postExchangeSingleCurrencyWithValue(@PathVariable String code, @ModelAttribute UserValue userValue, Model model){
        double value = exchangeRatesService.exchangeValueBuy(exchangeRatesService.getSingleExchange(code), userValue);
        model.addAttribute("rates", exchangeRatesService.getSingleExchange(code));
        model.addAttribute("valueAfter", value);
        model.addAttribute("buy", true);
        return "rates";
    }

    @GetMapping("/waluta/{code}/kup")
    public String redirectToCorrectUrlBuy(@PathVariable String code, @ModelAttribute UserValue userValue){
        return "redirect:/waluta/" + code;
    }

    @GetMapping("/kursy")
    public String getExchangeMultipleCurrencyValues(Model model){
        model.addAttribute("multipleCurrency", exchangeRatesService.getRatesOfAllCurrencies());
        return "values";
    }
}
