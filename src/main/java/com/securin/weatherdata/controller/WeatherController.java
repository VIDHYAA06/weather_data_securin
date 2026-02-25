package com.securin.weatherdata.controller;

import com.securin.weatherdata.entity.Weather;
import com.securin.weatherdata.service.WeatherService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    private final WeatherService service;

    public WeatherController(WeatherService service) {
        this.service = service;
    }
    @PostMapping("/upload")
    public String uploadCSV(@RequestParam("file") MultipartFile file) {
        service.uploadCSV(file);
        return "CSV uploaded successfully!";
    }
    @GetMapping("/month")
    public List<Weather> getWeatherByMonth(
            @RequestParam int year,
            @RequestParam int month) {

        return service.getWeatherByMonth(year, month);
    }

    @GetMapping("/stats/{year}")
    public Map<String, Double> getYearlyStats(
            @PathVariable int year) {

        return service.getYearlyTemperatureStats(year);
    }
}