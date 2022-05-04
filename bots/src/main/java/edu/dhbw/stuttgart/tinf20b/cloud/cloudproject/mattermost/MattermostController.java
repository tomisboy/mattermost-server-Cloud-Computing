package edu.dhbw.stuttgart.tinf20b.cloud.cloudproject.mattermost;

import edu.dhbw.stuttgart.tinf20b.cloud.cloudproject.mattermost.model.WebHookResponseWeather;
import edu.dhbw.stuttgart.tinf20b.cloud.cloudproject.mattermost.model.WebhookResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public interface MattermostController {

    @PostMapping ("/api/v1/mattermost/translate")
    WebhookResponse translateCommand(@RequestParam("text") String text);

    @PostMapping ("/api/v1/mattermost/cleverbot")
    WebhookResponse cleverbotCommand(@RequestParam("text") String text,
                                     @RequestParam("channel_id") String channelId,
                                     @RequestParam("user_name") String userName);

    @PostMapping ("/api/v1/mattermost/weather")
    WebHookResponseWeather weatherCommand(@RequestParam("text") String text);
}
