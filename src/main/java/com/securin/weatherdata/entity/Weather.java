package com.securin.weatherdata.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@Document(collection = "weather")
public class Weather {

    @Id
    private String id;

    private LocalDateTime dateTime;
    private String condition;
    private Double temperature;
    private Double humidity;
    private Double pressure;

    public Weather() {}

    public Weather(LocalDateTime dateTime,
                   String condition,
                   Double temperature,
                   Double humidity,
                   Double pressure) {

        this.dateTime = dateTime;
        this.condition = condition;
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
    }
}