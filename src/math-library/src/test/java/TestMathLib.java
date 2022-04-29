/****************************************************
 * Název projektu: Kalkulačka
 * Soubor: TestMathLib.java
 * Autoři: Vítězslav Šafář (xsafar26)
 *         Jan Škrabal (xskrab12)
 *         Richard Kocián (xkocia19)
 *         Petr Cafourek (xcafou01)
 *
 * Popis: Testy nad matematickou knihovnou
 ****************************************************/
/**
 * @file TestMathLib.java
 *
 * @brief Testy nad matematickou knihovnou
 * @author Vítězslav Šafář (xsafar26)
 * @author Jan Škrabal (xskrab12)
 * @author Richard Kocián (xkocia19)
 * @author Petr Cafourek (xcafou01)
 */

import org.junit.*;
import fit.ivs.calc.mathlibrary.MathLib;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TestMathLib {

    private MathLib mathLib;
    private final double delta = 0.0000000000001;  //tolerovaná přesnost

    @Before
    public void setUp() {
        mathLib = new MathLib();
    }

    /**
     * Testy nad funkcí plus
     */
    @Test
    public void plusTest() {
        assertEquals(60,mathLib.plus(30,30),0);
        assertEquals(77,mathLib.plus(29,48),0);
        assertEquals(-62990,mathLib.plus(1597,-64587),0);
        assertEquals(-33397.5709,mathLib.plus(923.8291,-34321.4),0);
        assertEquals(421.5865,mathLib.plus(-46.5481,468.1346),0);
    }

    /**
     * Testy nad funkcí mínus
     */
    @Test
    public void minusTest() {
        assertEquals(0,mathLib.minus(30,30),0);
        assertEquals(-31,mathLib.minus(43,74),0);
        assertEquals(-4619.054,mathLib.minus(58.946,4678),0);
        assertNotEquals(-6745.2455885459,mathLib.minus(3323.8223442329,-3421.423244313),0);
        assertEquals(6745.2455885459,mathLib.minus(3323.8223442329,-3421.423244313),0);
    }

    /**
     * Testy nad funkcí násobení
     */
    @Test
    public void mulTest() {
        assertEquals(15,mathLib.mul(3,5),0);
        assertEquals(45,mathLib.mul(3,15),0);
        assertEquals(-448,mathLib.mul(16,-28),0);
        assertEquals(35614426.3776875,mathLib.mul(4628.6375, 7694.365),delta);
    }

    /**
     * Testy nad funkcí dělení
     */
    @Test
    public void divTest() {
        assertEquals(16,mathLib.div(64,4),0);
        assertEquals(0.257600025576631,mathLib.div(1658.365,6437.75169),delta);
        assertEquals(4.114015916158117,mathLib.div(-264.73569,-64.3497),delta);
    }

    /**
     * Test chybového stavu při dělení
     */
    @Test(expected = ArithmeticException.class)
    public void divExceptionTest() {
        mathLib.div(7.7,0);
    }

    /**
     * Testy nad funkcí modulo
     */
    @Test
    public void modTest() {
        assertEquals(0,mathLib.mod(20,5),0);
        assertEquals(11.140000000000025,mathLib.mod(281.3,12.28),delta);
        assertEquals(-23.54607939999994,mathLib.mod(-2329.294132,384.2913421),delta);
    }

    /**
     * Testy nad funkcí odmocnina
     */
    @Test
    public void sqrtTest() {
        assertEquals(4,mathLib.sqrt(16),0);
        assertEquals(0,mathLib.sqrt(0),0);
        assertEquals(22.360679774997897,mathLib.sqrt(500),delta);
        assertEquals(104.704345659576135,mathLib.sqrt(10963),delta);
        assertEquals(1,mathLib.sqrt(1),0);
        assertEquals(2,mathLib.sqrt(4),0);
        assertEquals(0,mathLib.sqrt(0),0);
        assertEquals(0.9486832980505138,mathLib.sqrt(0.9),delta);
        assertEquals(0.5477225575051661,mathLib.sqrt(0.3),delta);
        assertEquals(0.7063993204979744,mathLib.sqrt(0.499),delta);
        assertEquals(0.8017880019057406,mathLib.sqrt(0.642864),delta);
    }

    /**
     * Test chybového stavu odmocnině ze záporného čísla
     */
    @Test(expected = ArithmeticException.class)
    public void sqrtExceptionTest1() {
        mathLib.sqrt(-1);

    }
    /**
     * Test chybového stavu odmocnině ze záporného desetiného čísla
     */
    @Test(expected = ArithmeticException.class)
    public void sqrtExceptionTest2() {
        mathLib.sqrt(-0.7);
    }
    /**
     * Test chybového stavu odmocnině ze záporného desetiného čísla na více desetiných míst
     */
    @Test(expected = ArithmeticException.class)
    public void sqrtExceptionTest3() {
        mathLib.sqrt(-0.859362472);
    }

    /**
     * Testy nad funkcí n-té odmocniny
     */
    @Test
    public void nSqrtTest() {
        assertEquals(6,mathLib.nSqrt(216,3),0);
        assertEquals(8.6451640684383,mathLib.nSqrt(48291,5),delta);
        assertEquals(1.0006887048116,mathLib.nSqrt(29,4891),delta);
        assertEquals(55,mathLib.nSqrt(55,1),0);
        assertEquals(1,mathLib.nSqrt(1,-1),0);
        assertEquals(0,mathLib.nSqrt(0,23),0);
        assertEquals(-0.7663094323935531,mathLib.nSqrt(-0.45,3),delta);
        assertEquals(-0.9404247266162327,mathLib.nSqrt(-0.45,13),delta);
    }

    /**
     * Test chybového stavu sudé omocniny ze záporného čísla
     */
    @Test(expected = ArithmeticException.class)
    public void nSqrtExceptionTest() {
        mathLib.nSqrt(-1,4);
    }

    /**
     * Test chybového stavu n-té omocniny z nuly
     */
    @Test(expected = ArithmeticException.class)
    public void nSqrtExceptionTest2() {
        mathLib.nSqrt(0,-20);
    }

    /**
     * Test chybového stavu nulté omocniny z čísla
     */
    @Test(expected = ArithmeticException.class)
    public void nSqrtExceptionTest3() {
        mathLib.nSqrt(43,0);
    }

    /**
     * Test chybového stavu nulté omocniny z čísla
     */
    @Test(expected = ArithmeticException.class)
    public void nSqrtExceptionTest4() {
        mathLib.nSqrt(78,0);
    }

    /**
     * Test chybového stavu sudé omocniny ze záporného desetiného čísla
     */
    @Test(expected = ArithmeticException.class)
    public void nSqrtExceptionTest5() {
        mathLib.nSqrt(-0.47,8);
    }

    /**
     * Testy nad funkcí druhé mocniny
     */
    @Test
    public void powTest() {
        assertEquals(43681,mathLib.pow(209),0);
        assertEquals(85266756,mathLib.pow(9234),0);
        assertEquals(49,mathLib.pow(-7),0);
        assertEquals(1,mathLib.pow(-1),0);
        assertEquals(1,mathLib.pow(1),0);
        assertEquals(0,mathLib.pow(0),0);
        assertEquals(0.2025,mathLib.pow(-0.45),0);
        assertEquals(0.21064180322329,mathLib.pow(-0.4589573),delta);
    }

    /**
     * Testy nad funkcí n-té mocniny
     */
    @Test
    public void nPowTest() {
        assertEquals(4096,mathLib.nPow(8,4),0);
        assertEquals(9138686662951d,mathLib.nPow(391,5),0);
        assertEquals(49,mathLib.nPow(-7,2),0);
        assertEquals(0.001890359168241,mathLib.nPow(23,-2),delta);
        assertEquals(196,mathLib.nPow(-14,2),delta);
        assertEquals(-0.047619047619047,mathLib.nPow(-21,-1),delta);
        assertEquals(0,mathLib.nPow(0,6),0);
        assertEquals(1,mathLib.nPow(52,0),0);
        assertEquals(-0.075686967,mathLib.nPow(-0.423,3),0);
        assertEquals(0.0000039923420738,mathLib.nPow(0.323,11),delta);
    }

    /**
     * Testy nad funkcí faktoriál
     */
    @Test
    public void factTest() {
        assertEquals(362880,mathLib.fact(9),0);
        assertEquals(5040,mathLib.fact(7),0);
        assertEquals(1,mathLib.fact(0),0);
        assertEquals(1,mathLib.fact(1),0);
    }

    /**
     * Test chybového stavu faktoriálu ze záporného čísla
     */
    @Test(expected = ArithmeticException.class)
    public void factTestException() {
        mathLib.fact(-7);
    }

    /**
     * Test chybového stavu faktoriálu příliš velkého čísla
     */
    @Test(expected = ArithmeticException.class)
    public void factTestException2() {
        mathLib.fact(171);
    }

    /**
     * Test nad funkcí pí
     */
    @Test
    public void piTest() {
        assertEquals(3.141592653589793,mathLib.pi(),0);
    }

    /**
     * Testy nad funkcí tangensu
     */
    @Test
    public void tanTest() {
        assertEquals(1.60033452904105,mathLib.tan(58),delta);
        assertEquals(4.142994851093586,mathLib.tan(-643.57),delta);
    }

    /**
     * Testy nad funkcí kosinu
     */
    @Test
    public void cosTest() {
        assertEquals(0.866025403784439,mathLib.cos(30),delta);
        assertEquals(0.975029342307275,mathLib.cos(-1092.831),delta);
    }

    /**
     * Testy nad funkcí sinu
     */
    @Test
    public void sinTest() {
        assertEquals(0.642787609686539,mathLib.sin(40),delta);
        assertEquals(0.472443089813982,mathLib.sin(28.193),delta);
    }

}
