package academy.learnprogramming.service;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

@Service // service layer holds the business logic in this case we use it to provide messages
public class DemoServiceImpl implements DemoService{

    @Override
    public String getHelloMessage(String user) {
        return "Hello " + user;
    }

    @Override
    public String getWelcomeMessage() {
        return "Welcome to this Demo Application!";
    }
}
