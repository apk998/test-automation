package com.solvd.testautomation.api.method;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.apitools.builder.NotStringValuesProcessor;
import com.zebrunner.carina.utils.config.Configuration;

public class GetDefinitionByWord extends AbstractApiMethodV2 {

    public GetDefinitionByWord(String word) {
        super(null, "api/get/dictionary_rs.json");
        String apiKey = Configuration.getRequired("api_key2");

        replaceUrlPlaceholder("base_url", Configuration.getRequired("api_url2"));
        replaceUrlPlaceholder("word", word);

        ignorePropertiesProcessor(NotStringValuesProcessor.class);
        addUrlParameter("X-Api-Key", apiKey);
    }
}
