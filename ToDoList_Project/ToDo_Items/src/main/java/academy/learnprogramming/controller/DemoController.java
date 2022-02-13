package academy.learnprogramming.controller;

import academy.learnprogramming.service.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
public class DemoController {

    //== fields ==
    private final DemoService demoService;

    //== constructor ==
    @Autowired
    public DemoController(DemoService demoService) {
        this.demoService = demoService;
    }



    // == request methods ==

    //http://localhost:8080/todo-list/hello
    @ResponseBody
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }


    //http://localhost:8080/todo-list/welcome
    //what the URL would look like with the added query parameter
    //http://localhost:8080/todo-list/welcome?user=Peter
    //basocally this is the query P.  that we are passing to our controller method -> so we can pass some date to our controller via the browser
    @GetMapping("welcome") //with this @ we are mapping the welcome method to the URL welcome
    public String welcome(@RequestParam String user, @RequestParam int age, Model model){
        model.addAttribute("helloMessage",demoService.getHelloMessage(user));
        model.addAttribute("age",age); // if you are using two or more par the URL will change ->  you need ot add an Ampersand and name of par instead of a ?
        log.info("model ={}", model);
        return "welcome";
    }

    //== model attributes ==

    //there is another way to add attribute to a model by using the annotation
    @ModelAttribute("welcomeMessage")//good practice specifying the attribute name
    public String welcomeMessage(){
        log.info("welcomeMessage() called");
        return demoService.getWelcomeMessage();

    }


}



