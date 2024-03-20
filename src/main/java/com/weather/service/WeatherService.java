package com.weather.service;

import com.weather.client.WeatherClient;
import com.weather.response.WeatherResponse;
import com.weather.response.WeatherApiResponse;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {
    private final WeatherClient weatherClient;
    private final CacheManager cacheManager;

    public WeatherService(WeatherClient weatherClient, CacheManager cacheManager) {
        this.weatherClient = weatherClient;
        this.cacheManager = cacheManager;
    }

    public WeatherResponse getForecast(String zipcode, int days) {
        Cache weatherCache = cacheManager.getCache("weatherForecast");
        boolean isCached = weatherCache != null && weatherCache.get(zipcode) != null;
        WeatherApiResponse apiResponse = weatherClient.getData(zipcode, days);
        return new WeatherResponse(apiResponse, isCached);
    }
}
