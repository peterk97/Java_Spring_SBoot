package academy.learnprogramming.util.thymeleaf;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;

import javax.annotation.PostConstruct;

@Slf4j
@Component
public class DecoupledLogicSetup {
    //This class is created to enable the Thymeleaf Decoupled template logic!!!!!

    //The bean we have to configure here is the Spring resource template resolver!
    //this used to resolve the templates, it will find the template when the controller returns the view name

    //== fields ==
    private final SpringResourceTemplateResolver templateResolver;

    // == constructor ==
    public DecoupledLogicSetup(SpringResourceTemplateResolver templateResolver){
        this.templateResolver = templateResolver;
    }

    // == init method ==
    @PostConstruct
    public void init(){
        templateResolver.setUseDecoupledLogic(true);
        log.info("Decoupled template logic enabled");
    }




}
