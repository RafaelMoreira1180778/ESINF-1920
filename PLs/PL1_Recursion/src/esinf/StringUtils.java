package esinf;

/**
 *
 * @author paulo
 */
public class StringUtils {

    //1B
    //Apresenta uma string na ordem inversa através da recursividade.
    public static String reverse(String s) {
        if (s.length() < 2) {
            return s;
        }
        /*
        char c = s.charAt(0);
        String resto = s.substring(1);
        String restoReversed = reverse(resto);
        return restoReversed + c;
         */
        return reverse(s.substring(1)) + s.charAt(0); //Recursivo -> chama o método dentro dele
    }

    //1A
    //Apresenta uma string na mesma ordem usando a recursividade.
    public static String same(String s) {
        if (s.length() < 2) {
            return s;
        }
        return s.charAt(0) + same(s.substring(1)); //Recursivo -> chama o método dentro dele
    }

}
