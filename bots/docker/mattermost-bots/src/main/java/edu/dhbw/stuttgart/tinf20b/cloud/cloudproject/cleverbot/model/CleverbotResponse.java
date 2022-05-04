package edu.dhbw.stuttgart.tinf20b.cloud.cloudproject.cleverbot.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CleverbotResponse {

    private String response;
}
