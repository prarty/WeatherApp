package com.weather.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherApiResponse {
    private Location location;
    private Current current;
    private Forecast forecast;

    @Data
    private static class Location {
        private String name;
        private String country;
        private double lat;
        private double lon;
    }

    @Data
    private static class ForecastDay {
        private String date;
        private Day day;
    }

    @Data
    private static class Forecast {
        @JsonProperty("forecastday")
        private List<ForecastDay> forecastDay;
    }

    @Data
    private static class Day {
        @JsonProperty("maxtemp_c")
        private double maxTempC;
        @JsonProperty("maxtemp_f")
        private double maxTempf;
        @JsonProperty("mintemp_c")
        private double minTempC;
        @JsonProperty("mintemp_f")
        private double minTempF;
    }

    @Data
    private static class Current {
        @JsonProperty("temp_c")
        private double tempC;
        @JsonProperty("temp_f")
        private double tempF;
    }
}