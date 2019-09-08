import com.mvnikitin.lesson6.Lesson6;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Collection;

//@RunWith(Parameterized.class)
public class Lesson6Test {

    private Lesson6 lesson6;

//    private Integer[] expected, input;

//    public Lesson6Test( Integer[] expected, Integer[] input) {
//        this.expected = expected;
//        this.input = input;
//    }
//
//    @Parameterized.Parameters
//    public static Collection<Object[]> testData() {
//        return Arrays.asList( new Object[][][] {
//                {{1, 2}, {1, 2, 3, 4, 1, 4, 1, 2}},
//                {{1, 7}, {1, 2, 4, 4, 2, 3, 4, 1, 7}},
//                {{3, 1, 2}, {1, 2, 3, 4, 1, 4, 4, 3, 1, 2}},
//                {{0}, {1, 2, 3, 4, 3, 3, 1, 4, 4, 0}}
//        });
//    }

    @Before
    public void init() {
        lesson6 = new Lesson6();
    }

//    @Test
//    public void testProcessArray() {
//        Assert.assertArrayEquals(Lesson6Test.arrayIntegerToInt(expected),
//                lesson6.processArray(Lesson6Test.arrayIntegerToInt(input), 4));
//    }

    @Test
    public void testProcessArray1() {
        Assert.assertArrayEquals(new int[]{1, 2},
                lesson6.processArray(new int[]{1, 2, 3, 4, 1, 4, 1, 2}, 4));
    }

    @Test
    public void testProcessArray2() {
        Assert.assertArrayEquals(new int[]{1, 7},
                lesson6.processArray(new int[]{1, 2, 4, 4, 2, 3, 4, 1, 7}, 4));
    }

    @Test
    public void testProcessArray3() {
        Assert.assertArrayEquals(new int[]{1, 4, 4, 0},
                lesson6.processArray(
                        new int[]{1, 2, 3, 4, 3, 3, 1, 4, 4, 0}, 3));
    }

    @Test(expected = RuntimeException.class)
    public void testProcessArray_NoRequiredDigit() {
        Assert.assertArrayEquals(new int[]{1, 2, 3},
                lesson6.processArray(new int[]{1, 2, 3, 4, 1, 4, 1, 2}, 9));
    }

    @Test(expected = RuntimeException.class)
    public void testProcessArray_NullPasses() {
        Assert.assertArrayEquals(new int[]{0},
                lesson6.processArray(null, 4));
    }


//    private static int[] arrayIntegerToInt(Integer[] array) {
//        int[] res = new int[array.length];
//        for (int i = 0; i < array.length; i++) {
//            res[i] = array[i].intValue();
//        }
//        return res;
//    }
}
