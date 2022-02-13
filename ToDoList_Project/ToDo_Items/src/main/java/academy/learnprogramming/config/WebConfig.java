package academy.learnprogramming.config;

import academy.learnprogramming.util.ViewNames;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

/**
 * This class is very similar to the AppConfig class in the GuessTheNumberGame Project
 * But at this case we are configuring the web part of the application!
 * we are using many of the same @annotations
 */

//web aspect of this class requires this annotation
@EnableWebMvc
@Configuration
@ComponentScan(basePackages = "academy.learnprogramming")
//this interface defines callback methods to customize the Java based config for MVC which was enabled via enable web MVC annotation
public class WebConfig implements WebMvcConfigurer {
    //== constants ==
    //these constant will be used by our V.resolver to resolve our views
    public static final String RESOLVER_PREFIX = "/WEB-INF/view/";
    public static final String RESOLVER_SUFFIX = ".jsp";

    //== bean methods ==
    @Bean
    public ViewResolver viewResolver(){
        //here we are going to create an internal resource view resolver
        // it will resolve the view based on the prefix and suffix
        UrlBasedViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix(RESOLVER_PREFIX);
        viewResolver.setSuffix(RESOLVER_SUFFIX);
        return viewResolver;
        //this type of resolver will automatically enable JSTL and a JSTL view to rendering the content (we haven't added JSTL dependence)

    }

//this is one of the methods of the interface
    //this method is used to configure simple automated controllers that are pre-configured with a response status code or the view to render
    //the response body (this case we are going to use a view)
    //simply put we're configuring the path to the home view and spring will use it without having a controller
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName(ViewNames.HOME);
    }
}
