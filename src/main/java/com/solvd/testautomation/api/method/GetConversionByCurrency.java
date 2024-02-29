package com.solvd.testautomation.api.method;

import com.solvd.testautomation.parsers.NinjasApiKeyParser;
import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.apitools.builder.NotStringValuesProcessor;
import com.zebrunner.carina.utils.config.Configuration;

import java.io.File;

public class GetConversionByCurrency extends AbstractApiMethodV2 {

    public GetConversionByCurrency(String have, String want, double amount) {
        super(null, "api/get/currency_rs.json");
        NinjasApiKeyParser apiKeyParser = new NinjasApiKeyParser(new File("src/main/resources/settings.xml"));
        String apiKey = apiKeyParser.parseElement("ninjasApiKey", 1);

        replaceUrlPlaceholder("base_url", Configuration.getRequired("api_url2"));
        replaceUrlPlaceholder("have", have);
        replaceUrlPlaceholder("want", want);
        replaceUrlPlaceholder("amount", String.valueOf(amount));

        ignorePropertiesProcessor(NotStringValuesProcessor.class);
        addUrlParameter("X-Api-Key", apiKey);
    }
}
