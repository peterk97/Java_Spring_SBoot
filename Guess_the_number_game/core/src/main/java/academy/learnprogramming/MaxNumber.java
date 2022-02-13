package academy.learnprogramming;

//CUSTOM @ FOR MaxNumber field
import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD,ElementType.PARAMETER,ElementType.METHOD})  //context in which annotation type is applicable!  -> can be added to: fields,methods,parameters. The element type is an enum that provides constants the locations where @ may appear in a Java programme
@Retention(RetentionPolicy.RUNTIME) //indicates hwo long annotations with the annotated type are to be retained -->  retained by the JVM at runtime
@Qualifier //  Spring @ used to annotate other custom @ that can in turn be used as qualifiers
public @interface MaxNumber {




}

//we have to configure our annotation with some other ones




//Here we are creating custom annotation for max number
//this is when qualifiers can help
//@Qualifier is an @ that you apply to a bean
//so we will create a qualifier @ and use it with the bean def. method and autowiring
//that way the container will what needs to be @autowired, and we don't have to depend specifically on a bean name!




// To use these custom @ we have to annotate the bean methods in the GameConfig class and the fields
//in classes that actually uses that bean


