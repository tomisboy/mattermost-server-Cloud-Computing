package edu.dhbw.stuttgart.tinf20b.cloud.cloudproject.cleverbot;

import edu.dhbw.stuttgart.tinf20b.cloud.cloudproject.cleverbot.model.CleverbotChatHistory;
import edu.dhbw.stuttgart.tinf20b.cloud.cloudproject.cleverbot.model.CleverbotRequest;
import edu.dhbw.stuttgart.tinf20b.cloud.cloudproject.mattermost.model.WebhookResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Service
public class CleverbotService {

    private final CleverbotConnector cleverbotConnector;

    private List<CleverbotChatHistory> chatHistories = new LinkedList<>();

    @Autowired
    public CleverbotService(CleverbotConnector cleverbotConnector) {
        this.cleverbotConnector = cleverbotConnector;
    }

    public WebhookResponse conversation(String text, String channelId, String username) {
        String response;
        CleverbotChatHistory chat = null;

        if (text.contentEquals("new")) {
            for (CleverbotChatHistory fChat : chatHistories) {
                if (fChat.getChannelId().contentEquals(channelId)) {
                    chat = fChat;
                    break;
                }
            }

            if (chat != null) {
                chatHistories.remove(chat);
                response = "@" + username + ": Your conversation has been successfully reset!";
            } else {
                response = "@" + username + ": No conversation was found to reset!";
            }

        } else {

            for (CleverbotChatHistory fChat : chatHistories) {
                if (fChat.getChannelId().contentEquals(channelId)) {
                    chat = fChat;
                    break;
                }
            }
            if (chat == null) {
                chat = CleverbotChatHistory.builder()
                        .channelId(channelId)
                        .history(new LinkedList<>())
                        .lastUpdate(LocalDateTime.now())
                        .build();
            }

            CleverbotRequest cleverbotRequest = CleverbotRequest.builder()
                    .message(text)
                    .build();
            if (!chat.getHistory().isEmpty()) {
                cleverbotRequest.setHistory(chat.getHistory());
            }

            try {
                response = "@" + username + ": " + text + "\nCleverbot: " + cleverbotConnector.npmApi(cleverbotRequest).getResponse();
                chat.getHistory().add(text);
                chat.getHistory().add(response);
                chat.setLastUpdate(LocalDateTime.now());

            } catch (Exception e) {
                response =  "The Cleverbot API is broken. Please contact your local admin";
            }

            if (!chatHistories.contains(chat)) {
                chatHistories.add(chat);
            }
        }

        return WebhookResponse.builder()
                .text(response)
                .username("Cleverbot")
                .response_type("in_channel")
                .build();
    }

    @Scheduled(cron = "0 */2 * * * *")
    private void deleteConversations() {
        LocalDateTime localDateTime = LocalDateTime.now().minusMinutes(5);
        chatHistories.removeIf(fChat -> fChat.getLastUpdate().isBefore(localDateTime));
    }
}
