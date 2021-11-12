import java.util.Collections;
import java.util.Map;

public class CurrencyChooser {

    //more expensive then EURO
//        Map<String, Double> moreExpensiveCurrency = output.rates.entrySet().stream().filter(rate -> rate.getValue() < 1)
//                .collect(Collectors.toMap(rate -> rate.getKey(), rate -> rate.getValue()));
//        output.rates = moreExpensiveCurrency;
    //european currency
//        List<String> currencyNames = Arrays.asList("GBP","CHF","USD","PLN","CZK","DKK","UAH","RUB");
//        Map<String, Double> europeanCurrency = output.rates.entrySet().stream()
//                .filter(currencyName->currencyNames.contains(currencyName.getKey()))
//                .collect(Collectors.toMap(rate -> rate.getKey(), rate -> rate.getValue()));
//        output.rates = europeanCurrency;

    //cheapest

//        Double cheapestCurrencyValue = Collections.max(output.rates.values());
//        Map<String, Double> cheapestCurrency = output.rates.entrySet().stream()
//                .filter(rate->rate.getValue()==cheapestCurrencyValue)
//                .collect(Collectors.toMap(rate -> rate.getKey(), rate -> rate.getValue()));
//        output.rates = cheapestCurrency;

    //mostExpensive
//        Double mostExpensiveCurrencyValue = Collections.min(output.rates.values());
//        Map<String, Double> mostExpensiveCurrency = output.rates.entrySet().stream()
//                .filter(rate->rate.getValue()==mostExpensiveCurrencyValue)
//                .collect(Collectors.toMap(rate -> rate.getKey(), rate -> rate.getValue()));
//        output.rates = mostExpensiveCurrency;
}
