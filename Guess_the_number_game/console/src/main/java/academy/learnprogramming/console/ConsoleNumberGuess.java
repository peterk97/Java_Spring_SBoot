package academy.learnprogramming.console;

import academy.learnprogramming.Game;
import academy.learnprogramming.MessageGenerator;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import java.util.Scanner;

@Slf4j
@Component
public class ConsoleNumberGuess  {

    //== fields ==
    private final Game game;
    private final MessageGenerator messageGenerator;

    //== Constructor ==
    @Autowired
    public ConsoleNumberGuess(Game game, MessageGenerator messageGenerator) {
        this.game = game;
        this.messageGenerator = messageGenerator;
    }

    //== events ==
    //annotations give us flexibility to name methods how we like
    //the parameter in the method will define the type of event some sort of parameter is mandatory but instead defining the parameter we are not using
    //we can add an event type to the annotation even listener
    //start method will implement the game logic (have a loop when the player stops the game)
    @EventListener(ContextRefreshedEvent.class)
    public void start(){
        log.info("start() --> Container ready for use.");

        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println(messageGenerator.getMainMessage());
            System.out.println(messageGenerator.getResultMessage());

            int guess = scanner.nextInt();
            scanner.nextLine();
            game.setGuess(guess);
            game.check();

            if (game.isGameWon() || game.isGameLost()){
                System.out.println(messageGenerator.getResultMessage());
                System.out.println("Play again y/n?");

                String playAgainString = scanner.nextLine().trim();
                if (!playAgainString.equalsIgnoreCase("y")){
                    break;
                }
                game.reset();
            }
        }
    }





}
/*
To finish the game we have to autowire the Game and MessageGenerator beans to the ConsoleNumberGuess Class
we already marked the class with the @Component so that is going to be scanned by the container
and because of that we can @Autowired it with the beans
 */







/*
This class needs to be scanned by the container to be able to listen for events so for that reason
we have to add the @Component annotation to the top of the class

so this class is going to be in the academy.learnprogramming package  hierarchy so it is going ot be scanned
because we defined the scanning on that package in that AppConfig.class


Another way to listen to events is to use annotations instead of implementing the applicationListener Interface
Spring provides the @EventListener annotation that can be added on the method that is going ot be executed
once the event gets fired
 */















