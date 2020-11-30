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
public class StringUtilsTest {

    public StringUtilsTest() {
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
     * Test of reverse method, of class StringUtils.
     */
    @Test
    public void testReverse() {
        System.out.println("reverse");
        String s = "mesmo";
        String expResult = "omsem";
        String result = StringUtils.reverse(s);
        assertEquals(expResult, result);
    }

    /**
     * Test of same method, of class StringUtils.
     */
    @Test
    public void testSame() {
        System.out.println("same");
        String s = "mesmo";
        String expResult = "mesmo";
        String result = StringUtils.same(s);
        assertEquals(expResult, result);
    }

}
