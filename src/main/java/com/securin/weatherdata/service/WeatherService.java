package com.securin.weatherdata.service;

import com.securin.weatherdata.dto.MonthlyTemperatureStats;
import com.securin.weatherdata.dto.WeatherResponseDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface WeatherService {

    void uploadCSV(MultipartFile file);

    List<WeatherResponseDTO> getWeatherByMonth(int year, int month);

    List<MonthlyTemperatureStats> getMonthlyTemperatureStats(int year);
}