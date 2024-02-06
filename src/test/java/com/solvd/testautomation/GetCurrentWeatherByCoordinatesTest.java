package com.solvd.testautomation;

import com.solvd.testautomation.api.GetCurrentWeatherByCoordinates;
import com.solvd.testautomation.domain.Coordinate;
import com.zebrunner.carina.api.http.HttpResponseStatusType;
import org.testng.annotations.Test;

public class GetCurrentWeatherByCoordinatesTest {

    @Test(description = "verify getting current weather by coordinates")
    public void verifyGetCurrentWeatherByCoordinatesTest() {
        Coordinate coordinate = new Coordinate();
        coordinate.setLatitude(39.95);
        coordinate.setLongitude(-75.17);

        GetCurrentWeatherByCoordinates weather = new GetCurrentWeatherByCoordinates(coordinate.getLatitude(), coordinate.getLongitude());
        weather.addProperty("coordinate", coordinate);

        weather.expectResponseStatus(HttpResponseStatusType.OK_200);
        weather.callAPI();

        weather.validateResponse();
    }
}
