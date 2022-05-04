package edu.dhbw.stuttgart.tinf20b.cloud.cloudproject.translator;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "GoogleTranslator", url="translate.googleapis.com/translate_a")
public interface TranslateConnector {

    @GetMapping("/single")
    List<Object> googleApi(@RequestParam String client,
                           @RequestParam String sl,
                           @RequestParam String tl,
                           @RequestParam String dt,
                           @RequestParam String q);
}
