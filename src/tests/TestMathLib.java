package tests;

import library.MathLib;
import org.junit.*;

import static org.junit.Assert.assertEquals;

public class TestMathLib {

    private MathLib mathLib;

    @Before
    public void setUp() {
        mathLib = new MathLib();
    }

    @Test
    public void addTest() {
        assertEquals(60,mathLib.plus(30,30),0);
    }

    @Test
    public void minusTest() {
        assertEquals(60,mathLib.minus(30,30),0);
    }

}
