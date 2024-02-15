package com.solvd.testautomation.api;

import com.solvd.testautomation.api.domain.Currency;
import com.solvd.testautomation.api.method.GetConversionByCurrency;
import com.zebrunner.carina.api.http.HttpResponseStatusType;
import org.testng.annotations.Test;

public class GetConversionByCurrencyTest {

    @Test(description = "verify getting currency conversion")
    public void verifyGetConversionByCurrencyTest() {
        Currency currency = new Currency();
        currency.setHave("USD");   // must be a 3-character currency code
        currency.setWant("KRW");
        currency.setAmount(1250);

        GetConversionByCurrency conversion =
                new GetConversionByCurrency(currency.getHave(), currency.getWant(), currency.getAmount());
        conversion.addProperty("currency", currency);

        conversion.expectResponseStatus(HttpResponseStatusType.OK_200);
        conversion.callAPI();

        conversion.validateResponse();
    }
}
