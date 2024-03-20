package com.weather.controller;

import com.weather.response.WeatherResponse;
import com.weather.service.WeatherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/forecast")
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping()
    WeatherResponse getForecast(@RequestParam(value = "zipcode") String zipcode,
                                @RequestParam(value = "days", required = false, defaultValue = "3") int days) {
        return weatherService.getForecast(zipcode, days);
    }
}
