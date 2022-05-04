package edu.dhbw.stuttgart.tinf20b.cloud.cloudproject.weather.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WeatherForecastDailyWeather {

    private long id;
    private String main;
    private String description;
    private String icon;
}
