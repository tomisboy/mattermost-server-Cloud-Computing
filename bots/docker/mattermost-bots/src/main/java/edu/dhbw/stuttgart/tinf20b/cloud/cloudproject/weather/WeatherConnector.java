package edu.dhbw.stuttgart.tinf20b.cloud.cloudproject.weather;

import edu.dhbw.stuttgart.tinf20b.cloud.cloudproject.weather.model.WeatherForecast;
import edu.dhbw.stuttgart.tinf20b.cloud.cloudproject.weather.model.WeatherLocation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "OpenWeather", url="api.openweathermap.org")
public interface WeatherConnector {

    @GetMapping("/geo/1.0/direct")
    List<WeatherLocation> getLocation(@RequestParam String q, @RequestParam String appid);


    @GetMapping("/data/2.5/onecall")
    WeatherForecast getWeather(@RequestParam long lat,
                               @RequestParam long lon,
                               @RequestParam String appid,
                               @RequestParam String exclude,
                               @RequestParam String units);

}
