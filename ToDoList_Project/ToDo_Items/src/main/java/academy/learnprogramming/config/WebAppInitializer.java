package academy.learnprogramming.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

@Slf4j
public class WebAppInitializer implements WebApplicationInitializer {

    private static final String DISPATCHER_SERVLET_NAME = "dispatcher";

    //This onStartup method is used to configure the webapp (to initialize it at startup)
    @Override
    public void onStartup(ServletContext servletContext)  {
        log.info("onStartup");

        // next step is to create web app context also known as Spring container, we also need to create the register servlet
        //the ServletContext (argument) is not the same as the Spring context it defines the set of methods that a Servlet uses to
        //communicate with its container (tomcat)

        //create spring app context
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        //register WebConfig class
        context.register(WebConfig.class); // for this to work the other class config class has to be annotated with @Configuration

        //create the dispatcher servlet (passing the context we defined)
        DispatcherServlet dispatcherServlet = new DispatcherServlet(context);

        // to register and configure servlet we can use the servlet registration class and register the servlet dynamically
        //before that we need to name our servlet (use a constant)
        //we are going ot use the servlet context parameter to call the method addServlet() -> that method will return the Servlet registration.dynamic
        //which we can then use to configure the servlet!

        //register and configure the dispatcher servlet
        ServletRegistration.Dynamic registration = servletContext.addServlet(DISPATCHER_SERVLET_NAME,dispatcherServlet);

        //add some options to finalise the registration and configuration

        //we are using this method to configure the startup loading of the servlet so that the container will instantiate and initialize our servlet upon startup
        registration.setLoadOnStartup(1);

        //map's our servlet add a spatula servlet to the URL pattern specified in this case is a forward slash and that will override the default tomcat homepage
        registration.addMapping("/");

    }
}


//  AT THIS POINT THE DISPATCHER SERVLET IS RUNNING AND PROCESSING REQUESTS SUCCESSFULLY HOWEVER THERE ARE STILL SOME PARTS MISSING IN THIS CASE CONTROLLERS
//WITH MAPPINGS















