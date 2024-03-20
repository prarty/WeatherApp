package com.weather.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class WeatherResponse {
    WeatherApiResponse weatherApiResponse;

    boolean isCached;

    public WeatherResponse(WeatherApiResponse weatherApiResponse, boolean isCached) {
        this.weatherApiResponse = weatherApiResponse;
        this.isCached = isCached;
    }
}
