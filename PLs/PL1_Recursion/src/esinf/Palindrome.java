package esinf;

public class Palindrome {

    //2
    //Verifica se um número no formato string é uma palíndrome.
    public static boolean isPalindrome(String number) {
        if (number.length() < 2) {
            return true;
        }
        if (number.charAt(0) != number.charAt(number.length() - 1)) {
            return false;
        }
        return isPalindrome(number.substring(1, number.length() - 1));
    }
}
