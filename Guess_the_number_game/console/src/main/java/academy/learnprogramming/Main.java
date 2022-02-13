package academy.learnprogramming;

import academy.learnprogramming.config.GameConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        log.info("Message Gen Challenge");

        SpringApplication.run(Main.class,args);


    }
}


/**
 * This is not the same as web main this is not related ot that class and tomcat does not start as well when we run this main!
 */
















