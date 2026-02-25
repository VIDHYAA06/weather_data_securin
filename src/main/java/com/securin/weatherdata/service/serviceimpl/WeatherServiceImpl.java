package com.securin.weatherdata.service.serviceimpl;
import com.securin.weatherdata.exception.CSVProcessingException;
import com.opencsv.CSVReader;
import com.securin.weatherdata.dto.MonthlyTemperatureStats;
import com.securin.weatherdata.dto.WeatherResponseDTO;
import com.securin.weatherdata.entity.Weather;
import com.securin.weatherdata.exception.CSVProcessingException;
import com.securin.weatherdata.repository.WeatherRepository;
import com.securin.weatherdata.service.WeatherService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class WeatherServiceImpl implements WeatherService {

    private final WeatherRepository repository;

    public WeatherServiceImpl(WeatherRepository repository) {
        this.repository = repository;
    }


    @Override
    public void uploadCSV(MultipartFile file) {

        try (CSVReader reader = new CSVReader(
                new InputStreamReader(file.getInputStream()))) {

            List<String[]> rows = reader.readAll();
            List<Weather> weatherList = new ArrayList<>();

            for (int i = 1; i < rows.size(); i++) {

                String[] row = rows.get(i);
                if (row.length < 12) continue;

                Weather weather = new Weather();
                weather.setDateTime(convertToDateTime(row[0]));
                weather.setCondition(row[1]);
                weather.setTemperature(parseDoubleSafe(row, 11));
                weather.setHumidity(parseDoubleSafe(row, 6));
                weather.setPressure(parseDoubleSafe(row, 8));

                weatherList.add(weather);
            }

            repository.saveAll(weatherList);

        } catch (Exception e) {
           throw new CSVProcessingException("Error processing CSV file", e);
        }
    }

    @Override
    public List<WeatherResponseDTO> getWeatherByMonth(int year, int month) {

        LocalDateTime start = LocalDateTime.of(year, month, 1, 0, 0);
        LocalDateTime end = start.withDayOfMonth(
                start.toLocalDate().lengthOfMonth())
                .withHour(23).withMinute(59);

        return repository.findByDateTimeBetween(start, end)
                .stream()
                .map(w -> new WeatherResponseDTO(
                        w.getDateTime().toString(),
                        w.getCondition(),
                        w.getTemperature(),
                        w.getHumidity(),
                        w.getPressure()
                ))
                .toList();
    }


    @Override
    public List<MonthlyTemperatureStats> getMonthlyTemperatureStats(int year) {

        List<Weather> yearlyData = repository.findAll().stream()
                .filter(w -> w.getDateTime().getYear() == year)
                .filter(w -> w.getTemperature() != null)
                .toList();

        Map<Integer, List<Double>> groupedByMonth = new HashMap<>();

        for (Weather weather : yearlyData) {
            int month = weather.getDateTime().getMonthValue();

            groupedByMonth
                    .computeIfAbsent(month, k -> new ArrayList<>())
                    .add(weather.getTemperature());
        }

        List<MonthlyTemperatureStats> result = new ArrayList<>();

        for (Map.Entry<Integer, List<Double>> entry : groupedByMonth.entrySet()) {

            List<Double> temps = entry.getValue().stream()
                    .sorted()
                    .toList();

            double max = temps.stream().mapToDouble(Double::doubleValue).max().orElse(0);
            double min = temps.stream().mapToDouble(Double::doubleValue).min().orElse(0);
            double median = calculateMedian(temps);

            result.add(new MonthlyTemperatureStats(
                    entry.getKey(),
                    max,
                    min,
                    median
            ));
        }

        return result;
    }


    private double calculateMedian(List<Double> list) {
        int size = list.size();
        if (size == 0) return 0;

        if (size % 2 == 0)
            return (list.get(size / 2 - 1) + list.get(size / 2)) / 2;
        else
            return list.get(size / 2);
    }

    private LocalDateTime convertToDateTime(String value) {

        String datePart = value.substring(0, 8);
        String timePart = value.substring(9);

        String formatted = datePart.substring(0, 4) + "-" +
                datePart.substring(4, 6) + "-" +
                datePart.substring(6, 8) + "T" +
                timePart;

        return LocalDateTime.parse(formatted);
    }

    private Double parseDoubleSafe(String[] row, int index) {

        if (index >= row.length) return null;

        String value = row[index];

        if (value == null || value.isEmpty() ||
                value.equals("-9999") ||
                value.equalsIgnoreCase("N/A")) {
            return null;
        }

        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}