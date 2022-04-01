package tests;

import library.MathLib;
import org.junit.*;

import static org.junit.Assert.assertEquals;

public class TestMathLib {

    private MathLib mathLib;
    private final double delta = 0.0000000000001;

    @Before
    public void setUp() {
        mathLib = new MathLib();
    }

    @Test
    public void addTest() {
        assertEquals(60,mathLib.plus(30,30),0);
        assertEquals(77,mathLib.plus(29,48),0);
        assertEquals(-62990,mathLib.plus(1597,-64587),0);
        assertEquals(-33397.5709,mathLib.plus(923.8291,-34321.4),0);
        assertEquals(421.5865,mathLib.plus(-46.5481,468.1346),0);
    }

    @Test
    public void minusTest() {
        assertEquals(0,mathLib.minus(30,30),0);
        assertEquals(-31,mathLib.minus(43,74),0);
        assertEquals(-4619.054,mathLib.minus(58.946,4678),0);
    }

    @Test
    public void mulTest() {
        assertEquals(15,mathLib.mul(3,5),0);
        assertEquals(45,mathLib.mul(3,15),0);
        assertEquals(-448,mathLib.mul(16,-28),0);
        assertEquals(35614426.3776875,mathLib.mul(4628.6375, 7694.365),delta);
    }

    @Test
    public void divTest() {
        assertEquals(16,mathLib.div(64,4),0);
        assertEquals(0.257600025576631,mathLib.div(1658.365,6437.75169),delta);
        assertEquals(4.114015916158117,mathLib.div(-264.73569,-64.3497),delta);
    }

    @Test
    public void modTest() {
        assertEquals(0,mathLib.mod(20,5),0);
        assertEquals(11.140000000000025,mathLib.mod(281.3,12.28),delta);
        assertEquals(-23.54607939999994,mathLib.mod(-2329.294132,384.2913421),delta);
    }

    @Test
    public void sqrtTest() {
        assertEquals(4,mathLib.sqrt(16),0);
        assertEquals(0,mathLib.sqrt(0),0);
    }

    @Test(expected = ArithmeticException.class)
    public void sqrtExceptionTest() {
        mathLib.sqrt(-1);
    }

    @Test
    public void nSqrtTest() {
        assertEquals(6,mathLib.nSqrt(216,3),0);
    }

    @Test(expected = ArithmeticException.class)
    public void nSqrtExceptionTest() {
        mathLib.nSqrt(-1,4);
    }

    @Test
    public void powTest() {
        assertEquals(43681,mathLib.pow(209),0);
    }

    @Test
    public void nPowTest() {
        assertEquals(4096,mathLib.nPow(8,4),0);
    }

    @Test
    public void factTest() {
        assertEquals(362880,mathLib.fact(9),0);
    }

    @Test
    public void piTest() {
        assertEquals(3.141592653589793,mathLib.pi(),0);
    }

    @Test
    public void tanTest() {
        assertEquals(1.60033452904105,mathLib.tan(58),delta);
        assertEquals(4.142994851093586,mathLib.tan(-643.57),delta);
    }

    @Test
    public void cosTest() {
        assertEquals(0.866025403784439,mathLib.cos(30),delta);
        assertEquals(0.975029342307275,mathLib.cos(-1092.831),delta);
    }

    @Test
    public void sinTest() {
        assertEquals(0.642787609686539,mathLib.sin(40),delta);
        assertEquals(0.472443089813982,mathLib.sin(28.193),delta);
    }

}
