package edu.dhbw.stuttgart.tinf20b.cloud.cloudproject.mattermost;

import edu.dhbw.stuttgart.tinf20b.cloud.cloudproject.mattermost.model.WebHookResponseWeather;
import edu.dhbw.stuttgart.tinf20b.cloud.cloudproject.mattermost.model.WebhookRequest;
import edu.dhbw.stuttgart.tinf20b.cloud.cloudproject.mattermost.model.WebhookResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MattermostControllerImpl implements MattermostController {

    private final MattermostService mattermostService;

    @Autowired
    public MattermostControllerImpl(MattermostService mattermostService){
        this.mattermostService = mattermostService;
    }

    @Override
    public WebhookResponse translateCommand(String text) {
        return mattermostService.translateCommand(text);
    }

    @Override
    public WebhookResponse cleverbotCommand(@RequestParam("text") String text,
                                            @RequestParam("channel_id") String channel_id,
                                            @RequestParam("user_name") String userName) {
        return mattermostService.cleverbotCommand(text, channel_id, userName);
    }

    @Override
    public WebHookResponseWeather weatherCommand(@RequestParam("text") String text) {
        return mattermostService.weatherCommand(text);
    }
}
