package academy.learnprogramming;
//CUSTOM @ FOR GuessCount field


import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD,ElementType.PARAMETER,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Qualifier
public @interface GuessCount {

}

// To use these custom @ we have to annotate the bean methods in the GameConfig class and the fields
//in classes that actually uses that bean