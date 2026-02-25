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
    private Double dewPoint;
    private Integer fog;
    private Integer hail;
    private Double heatIndex;
    private Double humidity;
    private Double precipitation;
    private Double pressure;
    private Integer rain;
    private Integer snow;
    private Double temperature;
    private Integer thunder;
    private Integer tornado;
    private Double visibility;
    private Integer windDirectionDegrees;
    private String windDirection;
    private Double windGust;
    private Double windChill;
    private Double windSpeed;
    public Weather() {
    }
    public Weather(LocalDateTime dateTime,
                   String condition,
                   Double dewPoint,
                   Integer fog,
                   Integer hail,
                   Double heatIndex,
                   Double humidity,
                   Double precipitation,
                   Double pressure,
                   Integer rain,
                   Integer snow,
                   Double temperature,
                   Integer thunder,
                   Integer tornado,
                   Double visibility,
                   Integer windDirectionDegrees,
                   String windDirection,
                   Double windGust,
                   Double windChill,
                   Double windSpeed) {

        this.dateTime = dateTime;
        this.condition = condition;
        this.dewPoint = dewPoint;
        this.fog = fog;
        this.hail = hail;
        this.heatIndex = heatIndex;
        this.humidity = humidity;
        this.precipitation = precipitation;
        this.pressure = pressure;
        this.rain = rain;
        this.snow = snow;
        this.temperature = temperature;
        this.thunder = thunder;
        this.tornado = tornado;
        this.visibility = visibility;
        this.windDirectionDegrees = windDirectionDegrees;
        this.windDirection = windDirection;
        this.windGust = windGust;
        this.windChill = windChill;
        this.windSpeed = windSpeed;
    }


}