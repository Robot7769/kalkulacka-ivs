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
        assertEquals(35614426.3776875,mathLib.mul(4628.6375, 7694.365),0);
    }

    @Test
    public void divTest() {
        assertEquals(16,mathLib.div(64,4),0);
        assertEquals(0.257600025576631,mathLib.div(1658.365,6437.75169),0);
        assertEquals(4.114015916158117,mathLib.div(-264.73569,-64.3497),0);
    }

    @Test
    public void modTest() {
        assertEquals(0,mathLib.mod(20,5),0);
        assertEquals(11.1433,mathLib.mod(281.3891,12.2839),0);
        assertEquals(-360.7452627,mathLib.mod(-2329.294132,384.2913421),0);
    }

    @Test
    public void sqrtTest() {
        assertEquals(4,mathLib.sqrt(16),0);
        assertEquals(25.442424609301685,mathLib.sqrt(647.31697),0);
        assertEquals(0,mathLib.sqrt(0),0);
    }

    @Test(expected = ArithmeticException.class)
    public void sqrtExceptionTest() {
        mathLib.sqrt(-1);
    }

    @Test
    public void nSqrtTest() {
        assertEquals(6,mathLib.nSqrt(216,3),0);
        assertEquals(1.005598786218176,mathLib.nSqrt(67.137,753.467),0);
        assertEquals(0.975707169815815,mathLib.nSqrt(4,-56.37),0);
    }

    @Test(expected = ArithmeticException.class)
    public void nSqrtExceptionTest() {
        mathLib.nSqrt(-1.3133,391);
    }

    @Test
    public void powTest() {
        assertEquals(43681,mathLib.pow(209),0);
        assertEquals(581172.49100089,mathLib.pow(762.3467),0);
        assertEquals(29.56781675432209,mathLib.pow(5.4376297),0);
        assertEquals(4069.39374724, mathLib.pow(-63.7918),0);
    }

    @Test
    public void nPowTest() {
        assertEquals(4096,mathLib.nPow(8,4),0);
        assertEquals(1005102.747883873097743,mathLib.nPow(24.3687,4.328),0);
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
        assertEquals(1.60033452904105,mathLib.tan(58),0);
        assertEquals(4.142994851093586,mathLib.tan(-643.57),0);
    }

    @Test
    public void cosTest() {
        assertEquals(0.8660254038,mathLib.cos(30),0);
    }

    @Test
    public void sinTest() {
        assertEquals(0.6427876097,mathLib.sin(40),0);
    }

    @Test
    public void plusMinusTest() {
        assertEquals(-20,mathLib.plusMinus(20),0);
        assertEquals(283.10293,mathLib.plusMinus(-283.10293),0);
    }

}
