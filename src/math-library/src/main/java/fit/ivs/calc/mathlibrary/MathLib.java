/****************************************************
 * Název projektu: Kalkulačka
 * Balíček: fit.ivs.calc.mathlibrary
 * Soubor: MathLib.java
 * Autoři: Vítězslav Šafář (xsafar26)
 *         Jan Škrabal (xskrab12)
 *         Richard Kocián (xkocia19)
 *         Petr Cafourek (xcafou01)
 *
 * Popis: Matematická knihovna kalkulačky
 ****************************************************/
/**
 * @file MathLib.java
 *
 * @brief Matematická knihovna kalkulačky
 * @author Vítězslav Šafář (xsafar26)
 * @author Jan Škrabal (xskrab12)
 * @author Richard Kocián (xkocia19)
 * @author Petr Cafourek (xcafou01)
 */
package fit.ivs.calc.mathlibrary;

public class MathLib {
    /**
     * Privátní funkce absolutní hodnoty
     * @param a hodnota
     * @return Vrací absolutní hodnotu čísla 'a'
     */
    private double abs(double a) {  //privátní funkce, využívá se pouze v matematické knihovně
        return a < 0 ? -a : a;
    }

    /**
     * Funkce pro sečtení hodnot 'a' a 'b'
     * @param a sčítanec b
     * @param b sčítanec b
     * @return Vrací sočet 'a + b'
     */
    public double plus(double a, double b) {
        return a + b;
    }

    /**
     * Funkce pro odečtení hodnot 'b' od 'a'
     * @param a  menšenec
     * @param b  menšitel
     * @return Vrací rodíl 'a - b'
     */
    public double minus(double a, double b) {
        return a - b;
    }

    /**
     * Funkce pro násobení hodnot 'a' a 'b'
     * @param a  činitel
     * @param b  činitel
     * @return Vrací součin 'a * b'
     */
    public double mul(double a, double b) {
        return a * b;
    }

    /**
     * Funkce pro dělení hodnot 'a' hodnotou 'b'
     * @param a  čitatel
     * @param b  jmenovatel
     * @return Vrací podíl 'a / b'
     */
    public double div(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("Dělení nulou není definováno");
        }
        return a / b;
    }

    /**
     * Funkce pro zjištění zbytku po dělení hodnot 'a' a 'b'
     * @param a  čitatel
     * @param b  jmenovatel
     * @return Vrací zbytek po dělení  'a / b'
     */
    public double mod(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("Modulo nulou není definováno");
        }
        return a % b;
    }

    /**
     * Funkce pro výpočet druhé odmocniny z čísla 'a'
     * @param a  čitatel
     * @return Vrací '√a'
     */
    public double sqrt(double a) {
        return nSqrt(a,2);
    }

    /**
     * Funkce pro výpočet n-té odmocnině z čísla 'a'
     * @param a odmocněnec
     * @param n odmocnitel
     * @return Vrací 'ᶰ√a'
     */
    public double nSqrt(double a, int n) {
        if (n == 0) {
            throw new ArithmeticException("Nultá odmocnina není definovaná");
        }
        int sing = 1;
        if (mod(n, 2) == 0) {
            if (a < 0.0) {
                throw new ArithmeticException("Sudá odmocnina ze záporného čísla není definovaná");
            }
        } else {
            if (a < 0.0) {
                sing = -1;
            }
        }
        double min = 0.0;           //spodní hranice pro hledání odmocniny
        double max = abs(a);        //horní hranice pro hledání odmocniny
        //pro odmocninu z malého čísla se zvýší horní hranice
        if (abs(a) < 1 && abs(a) > 0) {
            max += 1;
        }
        if (abs(a) < 1) {
            max *= 2;
        }
        double middle = max;        //střední hodnota pro hledání odmocniny

        //hledání odmocniny čísla za pomoci zkracování intervalu <min,max>
        //zkracování se provádí dokud není odchylka menší jak stanovená hodnota nebo nanejvýš 100x kdyby číslo oscilovalo
        for (int i = 0; (abs(nPow(middle,(int) abs(n)) - abs(a)) > 0.0000000000000005 ) && i < 100; i++) {
            middle = div((min + max), 2);
            if (nPow(middle,(int) abs(n)) > abs(a)) {   //zkracování itervalu na kterém leží odmocnina čísla 'a'
                max = middle;       //zmenšení horní hranice
            } else {
                min = middle;       //zvětšení spodní hranice
            }
            //System.out.println("i: " + i + " min: " + min + " max: " + max + " middle: " + middle + " nPow: " + nPow(middle,(int) abs(n)));
        }
        if ( n < 0) {
            return div(1,mul(sing, middle));
        } else {
            return sing * middle;
        }
    }

    /**
     * Funkce pro výpočet druhé mocniny z čísla 'a'
     * @param a  základ
     * @return Vrací 'a²'
     */
    public double pow(double a) {
        return a * a;
    }

    /**
     * Funkce pro výpočet n-té mocniny čísla 'a'
     * @param a základ
     * @param n exponent
     * @return Vrací 'aᴺ'
     */
    public double nPow(double a, int n) {
        double a_n = 1;
        for (int i = 0; i < abs(n); i++) {
            a_n *= a;
        }
        if (n < 0) {
            return div(1,a_n);      //záporná mocnina se dá počítat jako 'a^(1/n)'
        } else {
            return a_n;
        }

    }

    /**
     * Funkce pro výpočet faktoriálu čísla 'a'
     * @param a základ
     * @return Vrací 'a!'
     */
    public double fact(int a) {
        if (a > 170) {
            throw new ArithmeticException("Příliš velký faktoriál čísla " + a);
        }
        if (a < 0) {
            throw new ArithmeticException("Faktoriál ze záporného čísla není definován");
        }
        if (a == 1 || a == 0) {
            return 1;
        }
        return a * (fact(a - 1));
    }

    /**
     * Funkce π (pí)
     * @return Vrací 'π'
     */
    public double pi() {
        return 3.141592653589793;
    }

    /**
     * Funkce pro výpočet tangensu úhlu 'a°'
     * @param a úhel ve ° (stupních)
     * @return Vrací 'tan(a°)'
     */
    public double tan(double a) {
        //System.out.println("a: " + a + " sin: " +sin(a) + " cos: " + cos(a));
        if (mod(a,90) == 0 && mod(a,180) != 0) {
            throw new ArithmeticException("Tangens úhlu "+ a + "° není definován");
        }
        return div(sin(a),cos(a));  //tan(a) = sin(a)/cos(a)
    }

    /**
     * Funkce pro výpočet kosinu úhlu 'a°'
     * @param a úhel ve ° (stupních)
     * @return Vrací 'cos(a°)'
     */
    public double cos(double a) {
        return sin(a + 90);         //kosínus je stejný jakko sínus jen o 90° (stupňů) posunutý
    }

    /**
     * Funkce pro výpočet sinu úhlu 'a°'
     * @param a úhel ve ° (stupních)
     * @return Vrací 'sin(a°)'
     */
    public double sin(double a) {
        double sina = 0.0;
        double rad;
        rad = (a * pi())/180;       //přepočet úhlu ve stpních na radiány

        a = mod(rad,(2 * pi()));    //zmanšení veikosti úhlu v radiánech na interval <0,2π (pí)>
        int denominator = -1;
        if (a < 0.0) {
            a = (2 * pi()) + a;
        }

        int sign = 1;
        if (a > pi()) {             //nastavení znaménka podle kvadrantu
            a -= pi();
            sign = -1;
        }

        for (int i = 0; i <= 50; i++) {
            denominator += 2;
            sina += nPow(-1,i) * (nPow(a, denominator) / fact(denominator));  //výpočet Taylorovy řady
        }
        return sign*sina;
    }

}
