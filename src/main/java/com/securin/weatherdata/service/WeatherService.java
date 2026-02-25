package com.securin.weatherdata.service;

import com.securin.weatherdata.entity.Weather;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface WeatherService {

    void uploadCSV(MultipartFile file);

    List<Weather> getWeatherByMonth(int year, int month);

    Map<String, Double> getYearlyTemperatureStats(int year);
}