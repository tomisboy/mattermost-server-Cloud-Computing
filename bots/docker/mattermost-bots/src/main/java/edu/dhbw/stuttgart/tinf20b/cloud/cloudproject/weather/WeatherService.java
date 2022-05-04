package edu.dhbw.stuttgart.tinf20b.cloud.cloudproject.weather;

import edu.dhbw.stuttgart.tinf20b.cloud.cloudproject.mattermost.model.WebHookResponseWeather;
import edu.dhbw.stuttgart.tinf20b.cloud.cloudproject.mattermost.model.WebHookResponseWeatherAttachment;
import edu.dhbw.stuttgart.tinf20b.cloud.cloudproject.weather.model.WeatherForecast;
import edu.dhbw.stuttgart.tinf20b.cloud.cloudproject.weather.model.WeatherLocation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.LinkedList;
import java.util.List;

@Service
public class WeatherService {

    @Value("${app.openweatherApiKey}")
    private String apiKey;
    @Value("${app.openweatherIconUrl}")
    private String iconUrl;
    @Value("${app.showWeatherDays}")
    private int weatherDays;
    private final WeatherConnector weatherConnector;

    public WeatherService(WeatherConnector weatherConnector) {
        this.weatherConnector = weatherConnector;
    }

    public WebHookResponseWeather weatherCommand(String text) {

        String city;
        long lat, lon;
        WebHookResponseWeatherAttachment weatherAttachment;

        List<WeatherLocation> locations = weatherConnector.getLocation(text, this.apiKey);

        if (locations.isEmpty()) {
            return WebHookResponseWeather.builder().text("Unknown city").build();
        }

        city = locations.get(0).getName();
        lat = locations.get(0).getLat();
        lon = locations.get(0).getLon();

        List<List<String>> tableList = convertToWeatherEntitiesStrings(weatherConnector.getWeather(lat, lon, apiKey, "minutely,hourly,alerts,current", "metric"));

        weatherAttachment = WebHookResponseWeatherAttachment.builder()
                .color("#e37446")
                .text(convertToTableString(tableList, getTableInfo()))
                .build();

        return WebHookResponseWeather.builder()
                .text("### Weather forecast for " + city)
                .response_type("in_channel")
                .username("Weathergirl")
                .attachments(List.of(weatherAttachment))
                .build();

    }

    private String getWeekday(long unixTime) {
        LocalDateTime localDateTime = Instant.ofEpochSecond(unixTime).atZone(ZoneId.systemDefault()).toLocalDateTime();
        return localDateTime.getDayOfWeek().toString();
    }

    private List<List<String>> convertToWeatherEntitiesStrings(WeatherForecast weatherForecast) {
        List<List<String>> tableList = new LinkedList<>();

        for (int i = 0; i < this.weatherDays; i++) {
            List<String> weatherEntityString = new LinkedList<>();

            weatherEntityString.add(getWeekday(weatherForecast.getDaily().get(i).getDt()));
            weatherEntityString.add("![ " + weatherForecast.getDaily().get(i).getWeather().get(0).getDescription() + " ](" + iconUrl + weatherForecast.getDaily().get(i).getWeather().get(0).getIcon() + ".png)");
            weatherEntityString.add(weatherForecast.getDaily().get(i).getWeather().get(0).getDescription());
            weatherEntityString.add(String.valueOf(weatherForecast.getDaily().get(i).getTemp().getMax()));
            weatherEntityString.add(String.valueOf(weatherForecast.getDaily().get(i).getTemp().getMin()));
            weatherEntityString.add(String.valueOf(weatherForecast.getDaily().get(i).getPop() * 100) + "%");
            weatherEntityString.add(String.valueOf(weatherForecast.getDaily().get(i).getHumidity()) + "%");
            weatherEntityString.add(String.valueOf(weatherForecast.getDaily().get(i).getWind_speed() + " m/s"));

            tableList.add(weatherEntityString);
        }

        return tableList;
    }

    private List<String> getTableInfo() {
        List<String> tableInfo = new LinkedList<>();

        tableInfo.add("Weekday");
        tableInfo.add("");
        tableInfo.add("Weather");
        tableInfo.add("Max temp");
        tableInfo.add("Min temp");
        tableInfo.add("Probability");
        tableInfo.add("Humidity");
        tableInfo.add("Wind speed");

        return tableInfo;
    }

    private String convertToTableString(List<List<String>> tableList, List<String> tableInfo) {
        String message = "|";
        int i = 0;
        message += tableInfo.get(i) + "|";
        for (int j = 0; j < this.weatherDays; j++) {
            message += tableList.get(j).get(i);
            message += "|";
        }
        i++;
        message += "\n|";
        for(int j = 0; j < this.weatherDays + 1; j++) {
            message += ":---:|";
        }
        for (; i < tableInfo.size(); i++) {
            message += "\n|";
            if (tableInfo.get(i).contentEquals("")) {
                message += tableInfo.get(i) + "|";
            } else {
                message += "**" + tableInfo.get(i) + "**|";
            }

            for (int j = 0; j < this.weatherDays; j++) {
                message += tableList.get(j).get(i);
                message += "|";
            }
        }

        return message;
    }
}
