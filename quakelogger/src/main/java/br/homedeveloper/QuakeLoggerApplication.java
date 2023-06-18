package br.homedeveloper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class QuakeLoggerApplication {

    public static void main(String[] args){
        SpringApplication.run(QuakeLoggerApplication.class, args);
    }

}
