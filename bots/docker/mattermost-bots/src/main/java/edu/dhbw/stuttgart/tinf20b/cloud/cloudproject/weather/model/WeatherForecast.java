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
public class WeatherForecast {

    private List<WeatherForecastDaily> daily;

}
