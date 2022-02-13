package academy.learnprogramming.config;

import academy.learnprogramming.util.ViewNames;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    //this will display the home page automatically
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
       registry.addViewController("/").setViewName(ViewNames.HOME);
    }
}


/**
 * In this class we are going to register the View controller
 */