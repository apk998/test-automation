package com.solvd.testautomation.api.method;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.apitools.builder.NotStringValuesProcessor;
import com.zebrunner.carina.utils.config.Configuration;

public class GetConversionByCurrency extends AbstractApiMethodV2 {

    public GetConversionByCurrency(String have, String want, double amount) {
        super(null, "api/get/currency_rs.json");
        String apiKey = Configuration.getRequired("api_key2");

        replaceUrlPlaceholder("base_url", Configuration.getRequired("api_url2"));
        replaceUrlPlaceholder("have", have);
        replaceUrlPlaceholder("want", want);
        replaceUrlPlaceholder("amount", String.valueOf(amount));

        ignorePropertiesProcessor(NotStringValuesProcessor.class);
        addUrlParameter("X-Api-Key", apiKey);
    }
}
