package fit.ivs.calc.mathlibrary;

public class MathLib {
    /**
     * Privátní funkce absolutní hodnoty
     * @param a hodnota
     * @return Vrací absolutní hodnotu čísla 'a'
     */
    private double abs(double a) {
        return a < 0 ? -a : a;
    }

    /**
     * Funkce sčítá hodnoty 'a' a 'b'
     * @param a sčítanec b
     * @param b sčítanec b
     * @return Vrací sočet 'a + b'
     */
    public double plus(double a, double b) {
        return a + b;
    }

    /**
     * Funkce odečítá hodnoty 'b' od 'a'
     * @param a  menšenec
     * @param b  menšitel
     * @return Vrací rodíl 'a - b'
     */
    public double minus(double a, double b) {
        return a - b;
    }

    /**
     * Funkce násobí hodnoty 'a' a 'b'
     * @param a  činitel
     * @param b  činitel
     * @return Vrací součin 'a * b'
     */
    public double mul(double a, double b) {
        return a * b;
    }

    /**
     * Funkce dělí hodnotu 'a' hodnotou 'b'
     * @param a  čitatel
     * @param b  jmenovatel
     * @return Vrací podíl 'a / b'
     */
    public double div(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("Nelze dělit nulou");
        }
        return a / b;
    }

    /**
     * Funkce 'a' modulo 'b'
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
     * Funkce na výpočet druhé odmocniny z čísla 'a'
     * @param a  čitatel
     * @return Vrací '√a'
     */
    public double sqrt(double a) {
        return nSqrt(a,2);
    }

    /**
     * Funkce na výpočet n-té odmocnině z čísla 'a'
     * @param a odmocněnec
     * @param n odmocnitel
     * @return Vrací 'ᶰ√a'
     */
    public double nSqrt(double a, int n) {
        if (n == 0) {
            throw new ArithmeticException("Není definována nultá odmocnina");
        }
        int sing = 1;
        if (mod(n, 2) == 0) {
            if (a < 0.0) {
                throw new ArithmeticException("Není definována sudá odmocnina ze záporného čísla");
            }
        } else {
            if (a < 0.0) {
                sing = -1;
            }
        }
        double min = 0.0;
        double max = abs(a);
        if (abs(a) < 1 && abs(a) > 0) {
            max += 1;
        }
        if (abs(a) < 1) {
            max *= 2;
        }
        double middle = max;
        for (int i = 0; (abs(nPow(middle,(int) abs(n)) - abs(a)) > 0.0000000000000005 ) && i < 100; i++) {
            middle = div((min + max), 2);
            if (nPow(middle,(int) abs(n)) > abs(a)) {
                max = middle;
            } else {
                min = middle;
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
     * Funkce na výpočet druhé mocniny z čísla 'a'
     * @param a  čitatel
     * @return Vrací 'aᴺ'
     */
    public double pow(double a) {
        return a * a;
    }

    /**
     * Funkce na výpočet n-té mocniny čísla 'a'
     * @param a základ
     * @param n exponent
     * @return  'aᴺ'
     */
    public double nPow(double a, int n) {
        double a_n = 1;
        for (int i = 0; i < abs(n); i++) {
            a_n *= a;
        }
        if (n < 0) {
            return div(1,a_n);
        } else {
            return a_n;
        }

    }

    /**
     * Funkce na výpočet faktoriálu čísla 'a'
     * @param a základ
     * @return  'a!'
     */
    public double fact(int a) {
        if (a > 170) {
            throw new ArithmeticException("Příliš velký faktoriál čísla " + a);
        }
        if (a < 0) {
            throw new ArithmeticException("Factorial ze záporného čísla není definovám");
        }
        if (a == 1 || a == 0) {
            return 1;
        }
        return a * (fact(a - 1));
    }

    /**
     * Funkce π (pí)
     * @return  'π'
     */
    public double pi() {
        return 3.141592653589793;
    }

    /**
     * Funkce na výpočet tangensu úhlu 'a°'
     * @param a úhel ve ° (stupních)
     * @return  'tan(a°)'
     */
    public double tan(double a) {
        //System.out.println("a: " + a + " sin: " +sin(a) + " cos: " + cos(a));
        if (mod(a,90) == 0 && mod(a,180) != 0) {
            throw new ArithmeticException("Tangens úhlu "+ a + "° není definován");
        }
        return div(sin(a),cos(a));
    }

    /**
     * Funkce na výpočet kosinu úhlu 'a°'
     * @param a úhel ve ° (stupních)
     * @return  'cos(a°)'
     */
    public double cos(double a) {
        return sin(a + 90);
    }

    /**
     * Funkce na výpočet sinu úhlu 'a°'
     * @param a úhel ve ° (stupních)
     * @return  'sin(a°)'
     */
    public double sin(double a) {
        double sina = 0.0;
        double rad;
        rad = (a * pi())/180;

        a = mod(rad,(2 * pi()));
        int denominator = -1;
        if(a < 0.0)
            a = (2 * pi()) + a;

        int sign = 1;
        if (a > pi()){
            a -= pi();
            sign = -1;
        }

        for (int i = 0; i <= 50; i++){
            denominator += 2;
            sina += nPow(-1,i) * (nPow(a, denominator) / fact(denominator));
        }
        return sign*sina;
    }

}
