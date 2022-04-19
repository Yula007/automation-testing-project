package common;

import org.junit.Assert;
import org.junit.Test;

/**
 * The type Calculator test.
 */
public class CalculatorTest {
    /**
     * The Calculator.
     */
    Calculator calculator = new Calculator();

    /**
     * Test calc 001.
     *
     * @throws Exception the exception
     */
    @Test
    public void testCalc001() throws Exception {
        Integer result = calculator.calc(3, 4, "+");
        Assert.assertEquals(Integer.valueOf(7), result);
    }

    /**
     * Test calc 002.
     *
     * @throws Exception the exception
     */
    @Test
    public void testCalc002() throws Exception {
        Integer result = calculator.calc(4, 3, "-");
        Assert.assertEquals(Integer.valueOf(1), result);
    }

    /**
     * Test calc 003.
     *
     * @throws Exception the exception
     */
    @Test
    public void testCalc003() throws Exception {
        Integer result = calculator.calc(4, 3, "*");
        Assert.assertEquals(Integer.valueOf(12), result);
    }

    /**
     * Test calc 004.
     *
     * @throws Exception the exception
     */
    @Test
    public void testCalc004() throws Exception {
        Integer result = calculator.calc(4, 3, "/");
        Assert.assertEquals(Integer.valueOf(1), result);
    }

    /**
     * Test calc 005.
     *
     * @throws Exception the exception
     */
    @Test
    public void testCalc005() throws Exception {
        Integer result = calculator.calc(4, 0, "/");
        Assert.assertEquals(Integer.valueOf(0), result);
    }

    /**
     * Test calc marina.
     *
     * @throws Exception the exception
     */
    @Test
    public void testCalcMarina() throws Exception {
        Integer result = calculator.calc(5, 3, "/");
        Assert.assertEquals(Integer.valueOf(1), result);
    }

    /**
     * Test calc 006.
     *
     * @throws Exception the exception
     */
    @Test
    public void testCalc006() throws Exception {

        Integer result = calculator.calc(1, 1, "$");
        Assert.assertEquals(Integer.valueOf(-1), result);

    }

    /**
     * Test calc 007.
     *
     * @throws Exception the exception
     */
    @Test
    public void testCalc007() throws Exception {

        Integer result = calculator.calc(57, 3, "/");
        Assert.assertEquals(Integer.valueOf(19), result);
    }

    /**
     * Test calc 008.
     *
     * @throws Exception the exception
     */
    @Test
    public void testCalc008() throws Exception {
        Integer result = calculator.calc(5, 3, "*");
        Assert.assertEquals(Integer.valueOf(15), result);
    }

    /**
     * Test calc 009.
     *
     * @throws Exception the exception
     */
    @Test
    public void testCalc009() throws Exception {

            Integer result = calculator.calc(25, 5, "/");
            Assert.assertEquals(Integer.valueOf(5), result);
    }

    /**
     * Test calc 010.
     *
     * @throws Exception the exception
     */
    @Test
    public void testCalc010() throws Exception {

        Integer result = calculator.calc(9, 3, "*");
        Assert.assertEquals(Integer.valueOf(27), result);
    }

    /**
     * Test calc 011.
     *
     * @throws Exception the exception
     */
    @Test
    public void testCalc011() throws Exception {
        Integer result = calculator.calc(4, 0, "/");
        Assert.assertEquals(Integer.valueOf(0), result);
    }

    /**
     * Test calc 012.
     *
     * @throws Exception the exception
     */
    @Test
    public void testCalc012() throws Exception {
        Integer result = calculator.calc(1, 1, "$");
        Assert.assertEquals(Integer.valueOf(-1), result);
    }

    /**
     * Test calc 013.
     *
     * @throws Exception the exception
     */
    @Test
    public void testCalc013() throws Exception {
        Integer result = calculator.calc(100, 2, "/");
        Assert.assertEquals(Integer.valueOf(50), result);
    }

    /**
     * Test calc 014.
     *
     * @throws Exception the exception
     */
    @Test
    public void testCalc014() throws Exception {
            Integer result = calculator.calc(45, 15, "/");
            Assert.assertEquals(Integer.valueOf(3), result);
            }
}