package academy.learnprogramming;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
public class MessageGeneratorImpl implements MessageGenerator{

    //== Constants ==
    //key in messages.prop file
    private static final String MAIN_MESSAGE = "game.main.message";
    public static final String WIN = "game.win";
    public static final String LOSE = "game.lose";
    public static final String INVALID_RANGE = "game.invalid.range";
    public static final String FIRST_GUESS = "game.first.guess";
    public static final String HIGHER = "game.higher";
    public static final String LOWER = "game.lower";
    public static final String REMAINING = "game.remaining";


    //== Fields ==
    private final Game game;
    //we are going to inject the message source functionality
    private final MessageSource messageSource;

    //== Constructor ==
    @Autowired
    public MessageGeneratorImpl(Game game,MessageSource messageSource) {
        this.game = game;
        this.messageSource=messageSource;
    }

    //== init ==
    @PostConstruct
    public void init (){
        log.info("Game = {}",game);
    }


    //== Public methods ==
    @Override
    public String getMainMessage() {
        return getMessage(MAIN_MESSAGE,game.getSmallest(),game.getBiggest());
//        return "Number is between " +
//                game.getSmallest() +
//                " and " +
//                game.getBiggest() +
//                ". Can you guess it?";
    }

    @Override
    public String getResultMessage() {

        if (game.isGameWon()){
            return getMessage(WIN,game.getNumber());
        }else if (game.isGameLost()){
            return getMessage(LOSE,game.getNumber());
        }else if (!game.isValidNumberRange()){
            return getMessage(INVALID_RANGE);
        }else if (game.getRemainingGuesses() == game.getGuessCount()){
            return  getMessage(FIRST_GUESS);
        }else {
            String direction = getMessage(LOWER);

            if (game.getGuess()< game.getNumber()){
                direction = getMessage(HIGHER);
            }
            return getMessage(REMAINING,direction,game.getRemainingGuesses());
        }
    }

    //== Private methods ==

    //creating a method that will give us the message from message source, so we don't have to repeat the same code everywhere.
    //message source defines a method getMessage that we can use to get the message by key from the source -> source is the messages.properties file
    //spring will load the messages.properties file by default  we also need locale for the messages to get the locale spring provide locale context holder
    //get message needs 3 parameters code -> properties key
    //                                Object... -> pass as many arguments as we need
    //                                 3. arg Local -> message used in spec locale
    private String getMessage (String code, Object... args){
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }
}
























