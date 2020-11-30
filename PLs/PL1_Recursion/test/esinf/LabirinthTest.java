/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esinf;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;

/**
 *
 * @author nunotmalheiro
 */
public class LabirinthTest {

    public LabirinthTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test of check method, of class Labirinth.
     */
    @Test
    public void testCheck() {
        System.out.println("check");
        int[][] actual = {
            {1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1},
            {1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1},
            {1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
            {1, 0, 0, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1},
            {1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1}
        };
        int y = 0;
        int x = 0;
        int[][] expResult = {
            {9, 9, 9, 0, 2, 2, 0, 0, 0, 2, 2, 2, 2},
            {1, 0, 9, 9, 9, 0, 2, 2, 2, 2, 2, 0, 2},
            {1, 0, 0, 0, 9, 0, 2, 0, 2, 0, 2, 0, 2},
            {1, 0, 0, 0, 9, 2, 2, 0, 2, 0, 2, 2, 2},
            {1, 1, 1, 1, 9, 0, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 9, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 9, 9, 9, 9, 9, 9, 9, 9, 9}
        };
        int[][] result = Labirinth.check(actual, y, x);
        assertArrayEquals(expResult, result);

        int[][] impossibleActual = {
            {1, 1, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1},
            {1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1},
            {1, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1},
            {1, 0, 0, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1},
            {1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1}
        };
        expResult = null;
        result = Labirinth.check(impossibleActual, y, x);
        assertArrayEquals(expResult, result);

    }

    /**
     * Test of isFinal method, of class Labirinth.
     */
    @Ignore
    public void testIsFinal() {
        System.out.println("isFinal");
        int[][] actual = null;
        int y = 0;
        int x = 0;
        boolean expResult = false;
        boolean result = Labirinth.isFinal(actual, y, x);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isValid method, of class Labirinth.
     */
    @Ignore
    public void testIsValid() {
        System.out.println("isValid");
        int[][] actual = null;
        int y = 0;
        int x = 0;
        boolean expResult = false;
        boolean result = Labirinth.isValid(actual, y, x);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkPath method, of class Labirinth.
     */
    @Test
    public void testCheckPath() {
        System.out.println("checkPath");
        int[][] actual = null;
        int y = 0;
        int x = 0;
        int[][] expResult = null;
        int[][] result = Labirinth.checkPath(actual, y, x);
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
