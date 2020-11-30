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
public class GCDTest {
    
    public GCDTest() {
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
     * Test of gcd method, of class GCD.
     */
    @Test
    public void testGcd() {
        System.out.println("gcd");
        int a = 90;
        int b = 30;
        int expResult = 30;
        int result = GCD.gcd(a, b);
        assertEquals(expResult, result);
    }
    
}
