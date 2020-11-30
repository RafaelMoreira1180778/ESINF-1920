package esinf;

/**
 *
 * @author DEI-ESINF
 */
public class Labirinth {

    //Verifica se a posição é final
    public static boolean isFinal(int[][] actual, int y, int x) {
        return ((y == actual.length - 1) && (x == actual[0].length - 1));
    }

    //Verifica se a posição é válida
    public static boolean isValid(int[][] actual, int y, int x) {
        return y >= 0 && y < actual.length && x >= 0 && x < actual[0].length && actual[y][x] == 1;
    }

    //Verifica as posições e desloca-se no labirinto
    public static int[][] check(int[][] actual, int y, int x) {
        if (false == isValid(actual, y, x)) {
            return null;
        }
        actual[y][x] = 9;
        if (isFinal(actual, y, x)) {
            return actual;
        }
        //Norte
        int[][] r = check(actual, y - 1, x);
        if (r != null) {
            return r;
        }

        //Este
        r = check(actual, y, x + 1);
        if (r != null) {
            return r;
        }

        //Sul
        r = check(actual, y + 1, x);
        if (r != null) {
            return r;
        }

        //Oeste
        r = check(actual, y, x - 1);
        if (r != null) {
            return r;
        }

        actual[y][x] = 2;
        return null;
    }

    public static int[][] checkPath(int[][] actual, int y, int x) {
        if (!isFinal(actual, y, x)) {
            return null;
        }
        actual[y][x] = 2;
        check(actual, y - 1, x);
        check(actual, y, x + 1);
        check(actual, y + 1, x);
        check(actual, y, x - 1);

        return actual;
    }
}
