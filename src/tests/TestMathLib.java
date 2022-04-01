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
    }

    @Test
    public void divTest() {
        assertEquals(16,mathLib.div(64,4),0);
    }

    @Test
    public void modTest() {
        assertEquals(0,mathLib.mod(20,5),0);
    }

    @Test
    public void sqrtTest() {
        assertEquals(4,mathLib.sqrt(16),0);
    }

    @Test
    public void nSqrtTest() {
        assertEquals(6,mathLib.nSqrt(216,3),0);
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
        assertEquals(3.1415926536,mathLib.pi(),0);
    }

    @Test
    public void tanTest() {
        assertEquals(1.66003345429,mathLib.tan(58),0);
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
    }

}
