package edu.dhbw.stuttgart.tinf20b.cloud.cloudproject.cleverbot.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CleverbotChatHistory {

    private String channelId;
    private List<String> history;
    private LocalDateTime lastUpdate;

}
