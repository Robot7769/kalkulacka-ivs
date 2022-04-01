package library;

public class MathLib {



    public double plus(double a, double b) {
        return a+b;
    }

    public double minus(double a, double b) {
        return a-b;
    }

    public double mul(double a, double b) {
        return a*b;
    }

    public double div(double a, double b) {
        return a/b;
    }

    public double mod(double a, double b) {
        return a%b;
    }

    public double sqrt(double a) {
        return 0;
    }

    public double nSqrt(double a, double n) {
        return 0;
    }

    public double pow(double a) {
        return a*a;
    }

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
        return a*(fact(a-1));
    }

    public double pi() {
        return 3.1415926536;
    }

    public double tan(double a) {
        return 0;
    }

    public double cos(double a) {
        return 0;
    }

    public double sin(double a) {
        return 0;
    }

    public double plusMinus(double a) {
        return -(a);
    }
}
