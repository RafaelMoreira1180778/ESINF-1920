package esinf;

public class GCD {

    //3
    //Calcula o maior divisor comum entre dois números.
    public static int gcd(int a, int b) {
        if (a == b) {
            return a;
        }
        if (a > b) {
            return gcd(a - b, b);
        }
        return gcd(a, b - a);
    }
}
