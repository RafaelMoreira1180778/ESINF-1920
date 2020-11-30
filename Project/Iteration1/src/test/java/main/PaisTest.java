package main;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PaisTest {

    /**
     * Test of getNome method, of class Pais.
     */
    @Test
    public void testGetNome() {
        System.out.println("getNome");
        Pais instance = new Pais("portugal", "europa", 10.31, "lisboa", 38.7071631, -9.135517);
        String expResult = "portugal";
        String result = instance.getNome();
        assertEquals(expResult, result);
    }

    /**
     * Test of getContinente method, of class Pais.
     */
    @Test
    public void testGetContinente() {
        System.out.println("getContinente");
        Pais instance = new Pais("portugal", "europa", 10.31, "lisboa", 38.7071631, -9.135517);
        String expResult = "europa";
        String result = instance.getContinente();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPopulacao method, of class Pais.
     */
    @Test
    public void testGetPopulacao() {
        System.out.println("getPopulacao");
        Pais instance = new Pais("portugal", "europa", 10.31, "lisboa", 38.7071631, -9.135517);
        double expResult = 10.31;
        double result = instance.getPopulacao();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCapital method, of class Pais.
     */
    @Test
    public void testGetCapital() {
        System.out.println("getCapital");
        Pais instance = new Pais("portugal", "europa", 10.31, "lisboa", 38.7071631, -9.135517);

        String expResult = "lisboa";
        String result = instance.getCapital();
        assertEquals(expResult, result);
    }

    /**
     * Test of getLatitude method, of class Pais.
     */
    @Test
    public void testGetLatitude() {
        System.out.println("getLatitude");
        Pais instance = new Pais("portugal", "europa", 10.31, "lisboa", 38.7071631, -9.135517);
        double expResult = 38.7071631;
        double result = instance.getLatitude();
        assertEquals(expResult, result);
    }

    /**
     * Test of getLongitude method, of class Pais.
     */
    @Test
    public void testGetLongitude() {
        System.out.println("getLongitude");
        Pais instance = new Pais("portugal", "europa", 10.31, "lisboa", 38.7071631, -9.135517);
        double expResult = -9.135517;
        double result = instance.getLongitude();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Pais.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Pais instance = new Pais("portugal", "europa", 10.31, "lisboa", 38.7071631, -9.135517);
        String expResult = String.format("Nome: %s\nContinente: %s\nCapital: %s\nPopulação: %.2f M\n, Latitude: %f\n Longitude: %f\n", "portugal", "europa", "lisboa", 10.31, 38.7071631, -9.135517);
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class Pais.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Pais x = new Pais("portugal", "europa", 10.31, "lisboa", 38.7071631, -9.135517);
        Pais y = new Pais("portugal", "europa", 10.31, "lisboa", 38.7071631, -9.135517);
        Assert.assertTrue(x.equals(y) && y.equals(x));
        Assert.assertTrue(x.hashCode() == y.hashCode());
    }

    /**
     * Test of equals method, of class Pais.
     */
    @Test
    public void testEquals1() {
        System.out.println("equals");
        Object obj = null;
        Pais instance = new Pais("portugal", "europa", 10.31, "lisboa", 38.7071631, -9.135517);
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Pais.
     */
    @Test
    public void testEquals2() {
        System.out.println("equals");
        Pais instance = new Pais("portugal", "europa", 10.31, "lisboa", 38.7071631, -9.135517);
        Object obj = instance;
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Pais.
     */
    @Test
    public void testEquals3() {
        System.out.println("equals");
        Object obj = "teste";
        Pais instance = new Pais("portugal", "europa", 10.31, "lisboa", 38.7071631, -9.135517);
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Pais.
     */
    @Test
    public void testEquals4() {
        System.out.println("equals");
        Object obj = new Pais("servia", "europa", 7.04, "belgrado", 44.802416, 20.465601);
        ;
        Pais instance = new Pais("portugal", "europa", 10.31, "lisboa", 38.7071631, -9.135517);
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of compareTo method, of class Pais.
     */
    @Test
    public void testCompareTo1() {
        System.out.println("compareTo");
        Object o = new Pais("portugal", "europa", 10.31, "lisboa", 38.7071631, -9.135517);
        Pais instance = new Pais("portugal", "europa", 10.31, "lisboa", 38.7071631, -9.135517);
        int expResult = 0;
        int result = instance.compareTo(o);
        assertEquals(expResult, result);
    }

    /**
     * Test of compareTo method, of class Pais.
     */
    @Test
    public void testCompareTo2() {
        System.out.println("compareTo");
        Object o = new Pais("servia", "europa", 7.04, "belgrado", 44.802416, 20.465601);
        Pais instance = new Pais("portugal", "europa", 10.31, "lisboa", 38.7071631, -9.135517);
        int expResult = 3;
        int result = instance.compareTo(o);
        assertEquals(expResult, result);
    }

    /**
     * Test of compareTo method, of class Pais.
     */
    @Test
    public void testCompareTo3() {
        System.out.println("compareTo");
        Object o = new Pais("bielorrusia", "europa", 9.48, "minsk", 53.905117, 27.5611845);
        Pais instance = new Pais("portugal", "europa", 10.31, "lisboa", 38.7071631, -9.135517);
        int expResult = -14;
        int result = instance.compareTo(o);
        assertEquals(expResult, result);
    }

}
