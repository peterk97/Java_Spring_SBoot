package academy.learnprogramming;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebMain {
    public static void main(String[] args) {
        SpringApplication.run(WebMain.class,args);
    }
}

/**
 * Do not mix this with the console module because in the console module we are not using anything related to the Spring web or MVC
 * and that is why tomcat does not start when we run the console
 *
 * only the core module is shared between the web and console module.
 */