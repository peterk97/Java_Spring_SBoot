package academy.learnprogramming.config;

import academy.learnprogramming.GuessCount;
import academy.learnprogramming.MaxNumber;
import academy.learnprogramming.MinNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = "academy.learnprogramming")
@PropertySource("classpath:config/game.properties") // if path cannot be found --> file cannot found exc
public class GameConfig {

    //== fields ==
    @Value("${game.maxNumber:50}")  // we can specify default value if file not found
    private int maxNumber;

    @Value("${game.guessCount:5}")
    private int guessCount ;

    @Value("${game.minNumber:0}")// new value added here and loaded from properties file
    private int minNumber;

    //== bean methods ==
    @Bean
    @MaxNumber
    public int maxNumber(){  //you can use primitives as beans!
        return maxNumber;
    }

    @Bean
    @GuessCount
    public int guessCount(){
        return guessCount;
    }

    @Bean
    @MinNumber
    public int minNumber(){
        return minNumber;
    }


}