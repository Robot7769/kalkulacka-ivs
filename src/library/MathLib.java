package library;

public class MathLib {

    private double abs(double a) {
        return a < 0 ? -a : a;
    }

    public double plus(double a, double b) {
        return a + b;
    }

    public double minus(double a, double b) {
        return a - b;
    }

    public double mul(double a, double b) {
        return a * b;
    }

    public double div(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("Nelze dělit nulou");
        }
        return a / b;
    }

    public double mod(double a, double b) {
        return a % b;
    }

    public double sqrt(double a) {
        if (a < 0.0) {
            throw new ArithmeticException("Není definováno");
        }
        double min = 0.0;
        double max = a;
        double middle = a;
        while (abs(pow(middle) - a) > 0.000000000005 ) {
            middle = div((min + max), 2);
            if (pow(middle) > a) {
                max = middle;
            } else {
                min = middle;
            }
        }
        return middle;
    }

    public double nSqrt(double a, double n) {
        return 0;
    }

    public double pow(double a) {
        return a * a;
    }

    /**
     * Funkce na výpočet n-té mocniny čísla 'a'
     *
     * @param a zadané číslo
     * @param n musí být větší jak 1 (n > 1)
     * @return  a^n
     */
    public double nPow(double a, double n) {
        double a_n = a;
        for (int i = 1; i < n; i++) {
            a_n *= a;
        }
        return a_n;
    }

    public double fact(double a) {
        if (a == 1) {
            return 1;
        }
        return a * (fact(a - 1));
    }

    public double pi() {
        return 3.141592653589793;
    }

    public double tan(double a) {
        return sin(a)/cos(a);
    }

    public double cos(double a) {
        return 0;
    }

    public double sin(double a) {
        double sign = 1;
        int denominator = -1;
        double sina = mod(a,360);

        if (sina == 0)
            return sina;
        if(sina < 0.0)
            sign = -1;

        return 0;
    }

}
