package main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoaderTest {

    /**
     * Test of getM method, of class Loader.
     */
    @Test
    public void testGetM() throws IOException {
        System.out.println("getM");
        Loader instance = new Loader("paises_teste.txt", "fronteiras_teste.txt");
        Map<Pais, Set<Pais>> expResult = new HashMap<>();

        Pais p1 = new Pais("argentina", "americasul", 41.67, "buenosaires", -34.6131500, -58.3772300);
        Pais p2 = new Pais("bolivia", "americasul", 9.70, "lapaz", -16.5000000, -68.1500000);
        Pais p3 = new Pais("brasil", "americasul", 206.12, "brasilia", -15.7797200, -47.9297200);
        Pais p4 = new Pais("chile", "americasul", 16.80, "santiago", -33.4569400, -70.6482700);
        Pais p5 = new Pais("colombia", "americasul", 46.86, "bogota", 4.6097100, -74.0817500);

        Set<Pais> s1 = new HashSet<Pais>();
        Set<Pais> s2 = new HashSet<Pais>();
        Set<Pais> s3 = new HashSet<Pais>();
        Set<Pais> s4 = new HashSet<Pais>();
        Set<Pais> s5 = new HashSet<Pais>();

        s1.add(p2);
        s1.add(p3);
        s2.add(p1);
        s2.add(p3);
        s2.add(p4);
        s3.add(p1);
        s3.add(p2);
        s3.add(p5);
        s4.add(p2);
        s5.add(p3);

        expResult.put(p1, s1);
        expResult.put(p2, s2);
        expResult.put(p3, s3);
        expResult.put(p4, s4);
        expResult.put(p5, s5);

        Map<Pais, Set<Pais>> result = instance.getM();
        assertEquals(expResult, result);
    }

    /**
     * Test of getK method, of class Loader.
     */
    @Test
    public void testGetK() throws IOException {
        System.out.println("getK");
        Loader instance = new Loader("paises_teste.txt", "fronteiras_teste.txt");
        Map<Integer, Set<String>> expResult = new TreeMap<>();
        Set<String> s1 = new TreeSet<>();
        s1.add("bolivia");
        s1.add("brasil");
        Set<String> s2 = new TreeSet<>();
        s2.add("argentina");
        Set<String> s3 = new TreeSet<>();
        s3.add("chile");
        s3.add("colombia");
        expResult.put(3, s1);
        expResult.put(2, s2);
        expResult.put(1, s3);
        Map<Integer, Set<String>> result = instance.getK();
        assertEquals(expResult, result);
    }

    /**
     * Test of getL method, of class Loader.
     */
    @Test
    public void testGetL() throws IOException {
        System.out.println("getL");
        Loader instance = new Loader("paises_teste.txt", "fronteiras_teste.txt");
        List<Pais> expResult = new ArrayList<>();
        instance.ordenarPopulacao("americasul", 20);
        List<Pais> result = instance.getL();
        Pais p1 = new Pais("argentina", "americasul", 41.67, "buenosaires", -34.6131500, -58.3772300);
        Pais p2 = new Pais("colombia", "americasul", 46.86, "bogota", 4.6097100, -74.0817500);
        Pais p3 = new Pais("brasil", "americasul", 206.12, "brasilia", -15.7797200, -47.9297200);
        expResult.add(p1);
        expResult.add(p2);
        expResult.add(p3);
        assertEquals(expResult, result);
    }

    /**
     * Test of obterPais method, of class Loader.
     */
    @Test
    public void testObterPais() throws IOException {
        System.out.println("obterPais");
        String nome = "brasil";
        Loader instance = new Loader("paises_teste.txt", "fronteiras_teste.txt");
        Pais expResult = new Pais("brasil", "americasul", 206.12, "brasilia", -15.7797200, -47.9297200);
        Pais result = instance.obterPais(nome);
        assertEquals(expResult, result);
    }

    /**
     * Test of ordenarPopulacao method, of class Loader.
     */
    @Test
    public void testOrdenarPopulacao() throws IOException {
        System.out.println("ordenarPopulacao");
        String continente = "americasul";
        int N = 20;
        Loader instance = new Loader("paises_teste.txt", "fronteiras_teste.txt");
        instance.ordenarPopulacao(continente, N);
        List<Pais> expResult = new ArrayList<>();
        Pais p1 = new Pais("argentina", "americasul", 41.67, "buenosaires", -34.6131500, -58.3772300);
        Pais p2 = new Pais("colombia", "americasul", 46.86, "bogota", 4.6097100, -74.0817500);
        Pais p3 = new Pais("brasil", "americasul", 206.12, "brasilia", -15.7797200, -47.9297200);
        expResult.add(p1);
        expResult.add(p2);
        expResult.add(p3);
        List<Pais> result = instance.getL();
        assertEquals(expResult, result);
    }

    /**
     * Test of obterMinFronteiras method, of class Loader.
     */
    @Test
    public void testObterMinFronteiras() throws IOException {
        System.out.println("obterMinFronteiras");
        Pais P1 = new Pais("argentina", "americasul", 41.67, "buenosaires", -34.6131500, -58.3772300);
        Pais P2 = new Pais("colombia", "americasul", 46.86, "bogota", 4.6097100, -74.0817500);
        Loader instance = new Loader("paises_teste.txt", "fronteiras_teste.txt");
        int expResult = 2;
        int result = instance.obterMinFronteiras(P1, P2);
        assertEquals(expResult, result);
    }

}
