package IntList;


import static org.junit.Assert.*;
import org.junit.Test;

public class FirstDigitEqualsLastDigitTest {
    @Test()
    public void test10 () {
        // because we have figure out by observation that 10 fails the test
        int num = 10;
        boolean result = IntListExercises.firstDigitEqualsLastDigit(num);
        boolean expected = false;
        assertEquals(expected, result);
    }
}
