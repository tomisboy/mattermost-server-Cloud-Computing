package edu.dhbw.stuttgart.tinf20b.cloud.cloudproject.mattermost.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WebHookResponseWeather {
    private String response_type;
    private String username;
    private String text;
    private List<WebHookResponseWeatherAttachment> attachments = new LinkedList<>();
}
