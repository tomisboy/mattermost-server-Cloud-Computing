package edu.dhbw.stuttgart.tinf20b.cloud.cloudproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableFeignClients
@EnableScheduling
public class CloudProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudProjectApplication.class, args);
    }

}
