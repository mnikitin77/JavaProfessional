import org.junit.Assert;
import com.mvnikitin.lesson6.Lesson6;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class Lesson6TestAdvanced {

    private Lesson6 lesson6;

    private int[] expected, input;
    private int digit;

    public Lesson6TestAdvanced( int[] expected, int[] input, int digit) {
        this.expected = expected;
        this.input = input;
        this.digit = digit;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testData() {
        return Arrays.asList( new Object[][] {
                {new int[]{1, 2}, new int[]{1, 2, 3, 4, 1, 4, 1, 2}, 4},
                {new int[]{1, 7}, new int[]{1, 2, 4, 4, 2, 3, 4, 1, 7}, 4},
                {new int[]{3, 1, 2}, new int[]{1, 2, 3, 4, 1, 4, 4, 3, 1, 2}, 4},
                {new int[]{0}, new int[]{1, 2, 3, 4, 3, 3, 1, 4, 4, 0}, 4}
        });
    }

    @Before
    public void init() {
        lesson6 = new Lesson6();
    }

    @Test
    public void testProcessArray() {
        String message = "Передали на вход: " + Arrays.toString(input) +
                ", ожидаемый результат: " + Arrays.toString(expected) +
                " контрольная цифра: " + digit;
        System.out.println(message);
        Assert.assertArrayEquals(message, expected,
                lesson6.processArray(input, digit));
    }

    @Test(expected = RuntimeException.class)
    public void processArray_NoRequiredDigit() {
        Assert.assertArrayEquals(new int[]{1, 2, 3},
                lesson6.processArray(new int[]{1, 2, 3, 4, 1, 4, 1, 2}, 9));
    }

    @Test(expected = RuntimeException.class)
    public void processArray_NullPasses() {
        Assert.assertArrayEquals(new int[]{0},
                lesson6.processArray(null, 4));
    }
}

