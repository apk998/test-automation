package com.solvd.testautomation.api;

import com.solvd.testautomation.api.method.GetCurrentWeatherByCoordinates;
import com.solvd.testautomation.api.domain.Coordinate;
import com.zebrunner.carina.api.http.HttpResponseStatusType;
import com.zebrunner.carina.utils.config.Configuration;
import org.checkerframework.checker.units.qual.C;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

public class GetCurrentWeatherByCoordinatesTest {

    @Test(description = "verify getting current weather by valid coordinates")
    public void verifyGetCurrentWeatherByValidCoordinatesTest() {
        Coordinate coordinate = new Coordinate();
        coordinate.setLatitude(39.95);
        coordinate.setLongitude(-75.17);

        GetCurrentWeatherByCoordinates weather =
                new GetCurrentWeatherByCoordinates(coordinate.getLatitude(), coordinate.getLongitude());
        weather.addProperty("coordinate", coordinate);

        weather.expectResponseStatus(HttpResponseStatusType.OK_200);
        weather.callAPI();

        weather.validateResponse();
    }

    @Test(description = "verify getting current weather by invalid coordinates")
    public void verifyGetCurrentWeatherByInvalidCoordinatesTest() {
        Coordinate invalidCoordinate = new Coordinate();
        invalidCoordinate.setLatitude(999.99);
        invalidCoordinate.setLongitude(-999.99);

        GetCurrentWeatherByCoordinates weather =
                new GetCurrentWeatherByCoordinates(invalidCoordinate.getLatitude(), invalidCoordinate.getLongitude());
        weather.addProperty("coordinate", invalidCoordinate);

        weather.expectResponseStatus(HttpResponseStatusType.NOT_FOUND_404);
        weather.callAPI();

        weather.validateResponse("Invalid coordinates");
    }

    @Test(description = "verify getting current weather without providing API key")
    public void verifyGetCurrentWeatherWithoutApiKeyTest() {
        Coordinate coordinate = new Coordinate();
        coordinate.setLatitude(39.95);
        coordinate.setLongitude(-75.17);

        GetCurrentWeatherByCoordinates weather =
                new GetCurrentWeatherByCoordinates(coordinate.getLatitude(), coordinate.getLongitude());
        weather.addProperty("coordinate", coordinate);

        String apiKey = String.valueOf(Configuration.get("api_key1"));
        weather.expectResponseStatus(HttpResponseStatusType.UNAUTHORIZED_401);

        weather.removeProperty("appid");
        weather.replaceUrlPlaceholder("appid", apiKey);

        weather.callAPI();

        weather.validateResponse("Invalid API key");
    }

    @Test(description = "verify getting current weather with incorrect API key")
    public void verifyGetCurrentWeatherWithIncorrectApiKeyTest() {
        Coordinate coordinate = new Coordinate();
        coordinate.setLatitude(39.95);
        coordinate.setLongitude(-75.17);

        GetCurrentWeatherByCoordinates weather =
                new GetCurrentWeatherByCoordinates(coordinate.getLatitude(), coordinate.getLongitude());
        weather.addProperty("coordinate", coordinate);

        weather.expectResponseStatus(HttpResponseStatusType.UNAUTHORIZED_401);
        weather.replaceUrlPlaceholder("appid", "incorrectApiKey");

        weather.callAPI();

        weather.validateResponse("Invalid API key");
    }

    @Test(description = "verify getting current weather with units parameter")
    public void verifyGetCurrentWeatherWithUnitsParameterTest() {
        Coordinate coordinate = new Coordinate();
        coordinate.setLatitude(39.95);
        coordinate.setLongitude(-75.17);

        GetCurrentWeatherByCoordinates weather =
                new GetCurrentWeatherByCoordinates(coordinate.getLatitude(), coordinate.getLongitude());
        weather.addProperty("coordinate", coordinate);

        weather.addProperty("units", "metric");
        weather.expectResponseStatus(HttpResponseStatusType.OK_200);
        weather.callAPI();

        Map<String, Object> response = (Map<String, Object>) weather.getResponse();

        Double temperatureInCelsius = Double.parseDouble(((Map<String, Object>) response.get("main")).get("temp").toString());
        Double windSpeedInMetersPerSecond = Double.parseDouble(((Map<String, Object>) response.get("wind")).get("speed").toString());

        weather.validateResponse();

        Assert.assertTrue(temperatureInCelsius >= -273.15, "Temperature is in Celsius");
        Assert.assertTrue(windSpeedInMetersPerSecond >= 0, "Wind speed is in meters per second");
    }
}
