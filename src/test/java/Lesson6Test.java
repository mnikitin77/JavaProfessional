import org.junit.Assert;
import com.mvnikitin.lesson6.Lesson6;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class Lesson6Test {

    private Lesson6 lesson6;

    @Before
    public void init() {
        lesson6 = new Lesson6();
    }

    @Test
    public void processArray_Test1() {
        Assert.assertArrayEquals(new int[]{1, 2},
                lesson6.processArray(new int[]{1, 2, 3, 4, 1, 4, 1, 2}, 4));
    }

    @Test
    public void processArray_Test2() {
        Assert.assertArrayEquals(new int[]{1, 7},
                lesson6.processArray(new int[]{1, 2, 4, 4, 2, 3, 4, 1, 7}, 4));
    }

    @Test
    public void processArray_Test3() {
        Assert.assertArrayEquals(new int[]{1, 4, 4, 0},
                lesson6.processArray(
                        new int[]{1, 2, 3, 4, 3, 3, 1, 4, 4, 0}, 3));
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

    @Test
    public void  isArrayContainDigit_Test1() {
        Assert.assertTrue(lesson6.isArrayContainDigit(
                new int[]{1, 2, 3, 4, 3, 3, 1, 4, 4, 0}, new int[]{1, 4}));
    }

    @Test
    public void  isArrayContainDigit_Test2() {
        Assert.assertTrue(lesson6.isArrayContainDigit(
                new int[]{3, 1, 3, 3, 3, 3, 2, 3, 2, 2}, new int[]{1, 4}));
    }

    @Test
    public void  isArrayContainDigit_Test3() {
        Assert.assertFalse(lesson6.isArrayContainDigit(
                new int[]{3, 2, 3, 3, 3, 3, 2, 3, 2, 2}, new int[]{1, 4}));
    }

    @Test
    public void  isArrayContainDigit_Test4() {
        Assert.assertFalse(lesson6.isArrayContainDigit(
                new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, new int[]{1, 4}));
    }
}
