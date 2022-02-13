package academy.learnprogramming;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Slf4j
@Getter
@Component
public class GameImpl implements Game{

    //with lombok we can get rid of all the setter and getter, but we have to be careful because NumberGenerator field doesn't have a getter
    // one way of solving this to add the @Getter to every field except numberGen but there is a better way !
    //-> With the @Getter what we can do is specify the access level of generated methods (we can use the getter on the field doesn't need getter then set that access to non)
    //now that we added the getters in class lvl we can remove theme

    //== fields ==
    @Getter(AccessLevel.NONE)
    private final NumberGenerator numberGenerator;
    private final int guessCount;
    private int number;

    @Setter
    private int guess;

    private int smallest;
    private int biggest;
    private int remainingGuesses;
    private boolean validNumberRange = true;


    //== Constructor ==
    @Autowired
    public GameImpl(NumberGenerator numberGenerator, @GuessCount int guessCount) {
        this.numberGenerator = numberGenerator;
        this.guessCount = guessCount;
    }

    //== init method ==
    @PostConstruct
    @Override
    public void reset() {
        smallest = numberGenerator.getMinNumber(); //we changed these values to be loaded form the properties file
        guess = numberGenerator.getMinNumber();// this as well
        remainingGuesses = guessCount;
        biggest = numberGenerator.getMaxNumber();
        number = numberGenerator.next();
        log.debug("the number is {}",number);
    }

    //== pre-destroy ==
    //spring calls for us before bean gets destroyed
    @PreDestroy
    public void preDestroy(){
        log.info("in Game preDestroy");
    }


    //== Public Methods ==

    //adjusting the number for the player ! in this method
    @Override
    public void check() {
        checkValidNumberRange();

        if (validNumberRange){
            if (guess > number){
                biggest = guess - 1;
            }

            if (guess < number){
                smallest = guess + 1;
            }
            remainingGuesses--;
        }
    }

    @Override
    public boolean isGameWon() {
        return guess == number;
    }

    @Override
    public boolean isGameLost() {
        return !isGameWon() && remainingGuesses <= 0;
    }

    //private methods
     private void checkValidNumberRange(){
        validNumberRange = (guess >= smallest) && (guess <= biggest);
     }
}
