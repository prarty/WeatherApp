package com.weather.service;

import com.weather.client.WeatherClient;
import com.weather.response.WeatherResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WeatherServiceTest {

    @Mock
    private WeatherClient weatherClient;

    @Mock
    private CacheManager cacheManager;

    @InjectMocks
    WeatherService weatherService;

    @Test
    void shouldCallWeatherClientSuccessfully() {
        String zipcode = "110001";
        int days = 2;

        weatherService.getForecast(zipcode, days);

        Mockito.verify(weatherClient).getData(zipcode, days);
    }

    @Test
    void shouldReturnDataFromWeatherClientSuccessfully() {
        String zipcode = "110001";
        int days = 2;
        Cache mockCache = mock(Cache.class);
        when(cacheManager.getCache(anyString())).thenReturn(mockCache);
        when(mockCache.get(zipcode)).thenReturn(null);

        WeatherResponse weatherResponse = weatherService.getForecast(zipcode, days);

        Mockito.verify(weatherClient).getData(zipcode, days);
        assertFalse(weatherResponse.isCached());
    }

    @Test
    void shouldReturnDataFromCacheSuccessfully() {
        String zipcode = "110001";
        int days = 2;
        Cache mockCache = mock(Cache.class);
        when(cacheManager.getCache(anyString())).thenReturn(mockCache);
        when(mockCache.get(zipcode)).thenReturn(mock(Cache.ValueWrapper.class));

        WeatherResponse weatherResponse = weatherService.getForecast(zipcode, days);

        Mockito.verify(weatherClient).getData(zipcode, days);
        assertTrue(weatherResponse.isCached());
    }

    @Test
    void shouldThrowExceptionWhenWeatherClientThrowException() {
        String zipcode = "110001";
        int days = 2;
        Cache mockCache = mock(Cache.class);
        when(cacheManager.getCache(anyString())).thenReturn(mockCache);
        when(mockCache.get(zipcode)).thenThrow(new RuntimeException("exception !!"));

        assertThrows(RuntimeException.class, () -> weatherService.getForecast(zipcode, days));
    }
}