package edu.dhbw.stuttgart.tinf20b.cloud.cloudproject.weather.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WeatherForecastDaily {

    private long dt;
    private WeatherForecastDailyTemp temp;
    private long pressure;
    private long humidity;
    private long dew_point;
    private long wind_speed;
    private long wind_deg;
    private long wind_gust;
    private List<WeatherForecastDailyWeather> weather;
    private long clouds;
    private long pop;
    private long uvi;

}
