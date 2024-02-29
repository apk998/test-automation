package com.solvd.testautomation.api.method;

import com.solvd.testautomation.parsers.NinjasApiKeyParser;
import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.apitools.builder.NotStringValuesProcessor;
import com.zebrunner.carina.utils.config.Configuration;

import java.io.File;

public class GetDefinitionByWord extends AbstractApiMethodV2 {

    public GetDefinitionByWord(String word) {
        super(null, "api/get/dictionary_rs.json");
        NinjasApiKeyParser apiKeyParser = new NinjasApiKeyParser(new File("src/main/resources/settings.xml"));
        String apiKey = apiKeyParser.parseElement("ninjasApiKey", 1);

        replaceUrlPlaceholder("base_url", Configuration.getRequired("api_url2"));
        replaceUrlPlaceholder("word", word);

        ignorePropertiesProcessor(NotStringValuesProcessor.class);
        addUrlParameter("X-Api-Key", apiKey);
    }
}
