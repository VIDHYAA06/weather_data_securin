package com.securin.weatherdata.repository;
import com.securin.weatherdata.entity.Weather;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.*;
import java.time.LocalDateTime;
public interface WeatherRepository extends MongoRepository<Weather,String> {
  List<Weather> findByDateTimeBetween(LocalDateTime start, LocalDateTime end);
} 