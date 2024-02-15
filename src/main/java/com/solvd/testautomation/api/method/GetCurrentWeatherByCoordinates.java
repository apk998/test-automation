package com.solvd.testautomation.api.method;

import com.solvd.testautomation.api.domain.Coordinate;
import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.annotation.Endpoint;
import com.zebrunner.carina.api.annotation.ResponseTemplatePath;
import com.zebrunner.carina.api.apitools.builder.NotStringValuesProcessor;
import com.zebrunner.carina.api.http.HttpMethodType;
import com.zebrunner.carina.utils.config.Configuration;

@Endpoint(url="${config.api_url1}/data/2.5/weather?lat=${lat}&lon=${lon}&appid=${key}",methodType = HttpMethodType.GET)
@ResponseTemplatePath(path = "api/get/current_weather_rs.json")
public class GetCurrentWeatherByCoordinates extends AbstractApiMethodV2 {

    public GetCurrentWeatherByCoordinates(double latitude, double longitude) {
        super(null, "api/get/current_weather_rs.json");
        replaceUrlPlaceholder("base_url", Configuration.getRequired("api_url1"));
        replaceUrlPlaceholder("lat", String.valueOf(latitude));
        replaceUrlPlaceholder("lon", String.valueOf(longitude));
        replaceUrlPlaceholder("key", Configuration.getRequired("api_key1"));

        ignorePropertiesProcessor(NotStringValuesProcessor.class);
    }

    public String replaceDynamicValues(String jsonResponse) {
        Coordinate coordinate = new Coordinate();
        String replacedJsonResponse = jsonResponse.replace("${coordinate.latitude}", String.valueOf(coordinate.getLatitude()));
        replacedJsonResponse = replacedJsonResponse.replace("${coordinate.longitude}", String.valueOf(coordinate.getLongitude()));
        return replacedJsonResponse;
    }
}
