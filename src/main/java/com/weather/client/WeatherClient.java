package com.weather.client;

import com.weather.config.WeatherConfig;
import com.weather.response.WeatherApiResponse;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class WeatherClient {

    private final WeatherConfig weatherConfig;

    private final WebClient webClient;

    public WeatherClient(WeatherConfig weatherConfig) {
        this.weatherConfig = weatherConfig;
        this.webClient = WebClient.builder().build();
    }

    @Cacheable(value = "weatherForecast", key = "#zipcode")
    public WeatherApiResponse getData(String zipcode, int days) {
        return webClient
                .get()
                .uri(weatherConfig.getApiUrl(), weatherConfig.getApiKey(), zipcode, days)
                .retrieve()
                .bodyToMono(WeatherApiResponse.class)
                .block();
    }
}
