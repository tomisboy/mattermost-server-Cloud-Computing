package edu.dhbw.stuttgart.tinf20b.cloud.cloudproject.cleverbot.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CleverbotRequest {

    private String message;
    private List<String> history;

}
