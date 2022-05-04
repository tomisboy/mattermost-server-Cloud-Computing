package edu.dhbw.stuttgart.tinf20b.cloud.cloudproject.translator;

import edu.dhbw.stuttgart.tinf20b.cloud.cloudproject.mattermost.model.WebhookResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class TranslatorService {

    private final TranslateConnector translateConnector;

    @Autowired
    public TranslatorService(TranslateConnector translateConnector){
        this.translateConnector = translateConnector;
    }

    public WebhookResponse translate(String query){
        String response = "";
        try {
            List<Object> list = translateConnector.googleApi("gtx", "auto", "en", "t", query);

            list = (List<Object>) list.get(0);

            for (Object entry: list) {
                response += ((String) ((List<Object>) entry).get(0));
            }

            //response =  (String) ((List<Object>) ((List<Object>) list.get(0)).get(0)).get(0);
        } catch (Exception e) {
            response =  "The translator API is broken. Please contact your local admin";
        }
        return WebhookResponse.builder()
                .text(response)
                .username("Translator Bot")
                .build();
    }
}
