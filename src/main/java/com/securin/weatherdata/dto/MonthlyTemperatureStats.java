package com.securin.weatherdata.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MonthlyTemperatureStats {

    private int month;
    private double max;
    private double min;
    private double median;
}