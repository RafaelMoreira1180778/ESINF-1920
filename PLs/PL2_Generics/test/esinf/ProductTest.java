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

/**
 *
 * @author DEI-ISEP
 */
public class ProductTest {
    
    public ProductTest() {
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
     * Test of equals method, of class Product.
     */
    @Test
    public void testEquals01() {
        System.out.println("equals01");
        Object obj = new Product("P001",0,0);
        Product instance = new Product("P002",0,0);
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }
    /**
     * Test of equals method, of class Product.
     */
    @Test
    public void testEquals02() {
        System.out.println("equals02");
        Object obj = new Product("P001",0,0);
        Product instance = new Product("P001",1,1);
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }    

    /**
     * Test of hashCode method, of class Product.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Product instance = new Product("P001",1,1);
        int expResult = instance.getIdentification().hashCode();
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of compareTo method, of class Product.
     */
    @Test
    public void testCompareTo01() {
        System.out.println("compareTo01");
        Product o = new Product("P002",1,1);
        Product instance = new Product("P001",2,2);
        int result = instance.compareTo(o);
        assertTrue(result<0);

    }        
    /**
     * Test of compareTo method, of class Product.
     */
    @Test
    public void testCompareTo02() {
        System.out.println("compareTo02");
        Product o = new Product("P001",1,1);
        Product instance = new Product("P002",2,2);
        int result = instance.compareTo(o);
        assertTrue(result>0);

    }    
    /**
     * Test of compareTo method, of class Product.
     */
    @Test
    public void testCompareTo03() {
        System.out.println("compareTo03");
        Product o = new Product("P001",1,1);
        Product instance = new Product("P001",2,2);
        int expResult = 0;
        int result = instance.compareTo(o);
        assertEquals(expResult, result);

    }

    /**
     * Test of getIdentification method, of class Product.
     */
    @Test
    public void testGetIdentification() {
        System.out.println("getIdentification");
        Product instance = null;
        String expResult = "";
        String result = instance.getIdentification();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setIdentification method, of class Product.
     */
    @Test
    public void testSetIdentification() {
        System.out.println("setIdentification");
        String identification = "";
        Product instance = null;
        instance.setIdentification(identification);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getQuantity method, of class Product.
     */
    @Test
    public void testGetQuantity() {
        System.out.println("getQuantity");
        Product instance = null;
        int expResult = 0;
        int result = instance.getQuantity();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setQuantity method, of class Product.
     */
    @Test
    public void testSetQuantity() {
        System.out.println("setQuantity");
        int quantity = 0;
        Product instance = null;
        instance.setQuantity(quantity);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPrice method, of class Product.
     */
    @Test
    public void testGetPrice() {
        System.out.println("getPrice");
        Product instance = null;
        long expResult = 0L;
        long result = instance.getPrice();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPrice method, of class Product.
     */
    @Test
    public void testSetPrice() {
        System.out.println("setPrice");
        long price = 0L;
        Product instance = null;
        instance.setPrice(price);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
