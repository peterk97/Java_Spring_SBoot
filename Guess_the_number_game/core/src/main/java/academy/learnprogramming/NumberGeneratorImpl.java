package academy.learnprogramming;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

@Slf4j
@Getter
@Component
public class NumberGeneratorImpl implements NumberGenerator {

    //== Fields ==
    @Getter(AccessLevel.NONE)
    private final Random random = new Random();

    private int maxNumber;
    private int minNumber;

    //== Constructors ==
    @Autowired
    public NumberGeneratorImpl(@MaxNumber int maxNumber, @MinNumber int minNumber) {
        this.maxNumber = maxNumber;
        this.minNumber = minNumber;
    }

    //== Public methods ==
    @Override
    public int next() {
        return random.nextInt(maxNumber - minNumber) + minNumber;
    }

}
