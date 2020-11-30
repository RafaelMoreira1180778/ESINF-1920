/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esinf;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author paulo
 */
public class PalindromeTest {

    public PalindromeTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of isPalindrome method, of class Palindrome.
     */
    @Test
    public void testIsPalindrome() {
        System.out.println("isPalindrome");
        String number = "12321";
        boolean expResult = true;
        boolean result = Palindrome.isPalindrome(number);
        assertEquals(expResult, result);
    }

    /**
     * Test of isPalindrome method, of class Palindrome.
     */
    @Test
    public void testIsPalindrome2() {
        System.out.println("isPalindrome");
        String number = "12345";
        boolean expResult = false;
        boolean result = Palindrome.isPalindrome(number);
        assertEquals(expResult, result);
    }

}
