package edu.dhbw.stuttgart.tinf20b.cloud.cloudproject.mattermost.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WebhookRequest {
    private String channel_id;
    private String channel_name;
    private String command;
    private String text;
    private String user_id;
    private String user_name;
}
