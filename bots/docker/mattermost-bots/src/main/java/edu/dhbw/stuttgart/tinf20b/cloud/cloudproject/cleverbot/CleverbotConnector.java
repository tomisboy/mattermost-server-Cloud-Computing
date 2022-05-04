package edu.dhbw.stuttgart.tinf20b.cloud.cloudproject.cleverbot;

import edu.dhbw.stuttgart.tinf20b.cloud.cloudproject.cleverbot.model.CleverbotRequest;
import edu.dhbw.stuttgart.tinf20b.cloud.cloudproject.cleverbot.model.CleverbotResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "Cleverbot", url="cleverserver:8090")
public interface CleverbotConnector {

    @PostMapping("/bot")
    CleverbotResponse npmApi(@RequestBody CleverbotRequest cleverbotRequest);
}
