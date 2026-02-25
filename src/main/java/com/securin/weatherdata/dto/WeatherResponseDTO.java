package com.securin.weatherdata.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WeatherResponseDTO {

    private String dateTime;
    private String condition;
    private Double temperature;
    private Double humidity;
    private Double pressure;
}