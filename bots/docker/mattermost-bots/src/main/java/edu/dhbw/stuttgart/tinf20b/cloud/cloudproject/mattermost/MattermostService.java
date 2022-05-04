package edu.dhbw.stuttgart.tinf20b.cloud.cloudproject.mattermost;

import edu.dhbw.stuttgart.tinf20b.cloud.cloudproject.cleverbot.CleverbotService;
import edu.dhbw.stuttgart.tinf20b.cloud.cloudproject.mattermost.model.WebHookResponseWeather;
import edu.dhbw.stuttgart.tinf20b.cloud.cloudproject.mattermost.model.WebhookResponse;
import edu.dhbw.stuttgart.tinf20b.cloud.cloudproject.translator.TranslatorService;
import edu.dhbw.stuttgart.tinf20b.cloud.cloudproject.weather.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MattermostService {

    private final TranslatorService translatorService;
    private final CleverbotService cleverbotService;
    private final WeatherService weatherService;

    @Autowired
    public MattermostService(TranslatorService translatorService,
                             CleverbotService cleverbotService,
                             WeatherService weatherService) {
        this.translatorService = translatorService;
        this.cleverbotService = cleverbotService;
        this.weatherService = weatherService;
    }

    public WebhookResponse translateCommand(String text) {
        return translatorService.translate(text);
    }

    public WebhookResponse cleverbotCommand(String text, String channelId, String userName) {
        return cleverbotService.conversation(text, channelId, userName);
    }

    public WebHookResponseWeather weatherCommand(String text) {
        return weatherService.weatherCommand(text);
    }
}
