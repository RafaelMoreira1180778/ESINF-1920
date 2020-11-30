import DEI.Graph;
import org.junit.Test;

import java.io.IOException;
import java.util.*;

import static org.junit.Assert.*;

/**
 * @author Paulo Moreira 1180778
 * @author Pedro Borda de Água 1180809
 */
public class LoaderTest {

    private Loader instance = new Loader("paises_teste.txt", "fronteiras_teste.txt");
    private Pais argentina = new Pais("argentina", "americasul", 41.67, "buenosaires", -34.6131500, -58.3772300);
    private Pais bolivia = new Pais("bolivia", "americasul", 9.70, "lapaz", -16.5000000, -68.1500000);
    private Pais brasil = new Pais("brasil", "americasul", 206.12, "brasilia", -15.7797200, -47.9297200);
    private Pais chile = new Pais("chile", "americasul", 16.80, "santiago", -33.4569400, -70.6482700);
    private Pais colombia = new Pais("colombia", "americasul", 46.86, "bogota", 4.6097100, -74.0817500);
    private Pais portugal = new Pais("portugal", "europa", 10.31, "lisboa", 38.7071631, -9.135517);

    public LoaderTest() throws IOException {
    }

    @Test
    public void getG() {
        System.out.println("Funcionalidade 1 (Carregar Info)");
        Graph<Pais, Pais> actual = instance.getG();

        Graph<Pais, Pais> expected = new Graph<Pais, Pais>(false);
        expected.insertVertex(argentina);
        expected.insertVertex(bolivia);
        expected.insertVertex(brasil);
        expected.insertVertex(chile);
        expected.insertVertex(colombia);
        expected.insertEdge(argentina, bolivia, null, Loader.distance(argentina, bolivia));
        expected.insertEdge(argentina, brasil, null, Loader.distance(argentina, brasil));
        expected.insertEdge(bolivia, brasil, null, Loader.distance(bolivia, brasil));
        expected.insertEdge(bolivia, chile, null, Loader.distance(bolivia, chile));
        expected.insertEdge(brasil, colombia, null, Loader.distance(brasil, colombia));

        System.out.println(String.format("Número de vértices de 'actual': %d | Número de vértices de 'expected': %d", actual.numVertices(), expected.numVertices()));
        System.out.println(String.format("Número de edges de 'actual': %d | Número de edges de 'expected': %d", actual.numEdges(), expected.numEdges()));

        assertEquals(actual, expected);
    }

    @Test
    public void obterPais() {
        System.out.println("Teste Obter País");
        Pais actual = instance.obterPais("argentina");
        Pais expected = argentina;
        System.out.println(String.format("actual: %s\nexpected: %s", actual.getNome(), expected.getNome()));
        assertEquals(actual, expected);
    }

    @Test
    public void obterPaisPorCapital() {
        System.out.println("Teste Obter País por Capital");
        Pais actual = instance.obterPaisPorCapital("buenosaires");
        Pais expected = argentina;
        System.out.println(String.format("actual: %s\nexpected: %s", actual.getNome(), expected.getNome()));
        assertEquals(actual, expected);
    }

    @Test
    public void obterPaisPorIndex() {
        System.out.println("Teste Obter País por Index");
        Pais actual = instance.obterPaisPorIndex(0);
        Pais expected = argentina;
        System.out.println(String.format("actual: %s\nexpected: %s", actual.getNome(), expected.getNome()));
        assertEquals(actual, expected);
    }

    @Test
    public void obterIndexPais() {
        System.out.println("Teste Obter Index do País");
        int actual = instance.obterIndexPais(argentina);
        System.out.println(String.format("actual: %d\nexpected: %s", actual, 0));
        assertTrue("Should be 0", actual == 0);
    }

    @Test
    public void distance() {
        double actual = Loader.distance(argentina, chile);
        double expected = 1137.246983;
        System.out.println(String.format("actual: %f\nexpected: %f", actual, expected));
        assertEquals(actual, expected, 5);
    }

    @Test
    public void colorir() throws IOException {
        System.out.println("Funcionalidade 2 (Colorir)");
        Map<Pais, Integer> actual = instance.colorir();
        Map<Pais, Integer> expected = new HashMap<>();
        expected.put(bolivia, 1);
        expected.put(argentina, 2);
        expected.put(brasil, 0);
        expected.put(chile, 0);
        expected.put(colombia, 1);
        for (Pais p : actual.keySet()) System.out.println(String.format("%10s | %5d", p.getNome(), actual.get(p)));
        assertEquals(actual, expected);
    } // Funcionalidade 2

    @Test
    public void shortestPathGraph() {
        System.out.println("Funcionalidade 3 (Shortest Path)");
        LinkedList<Pais> shortPath = new LinkedList<Pais>();
        double length = instance.shortestPath(argentina, bolivia, shortPath);
        assertTrue("Sould be 2236.781000", length == 2236.7809710310194);
        System.out.println("Capitais Percorridas:");
        for (Pais p : shortPath) System.out.println(String.format("País: %s | Capital: %s", p.getNome(), p.getCapital()));
        length = instance.shortestPath(argentina, portugal, shortPath);
        assertTrue("Should be 0", length == 0);
        length = instance.shortestPath(argentina, argentina, shortPath);
        assertTrue("Should be 1", length == 1);
    } //Funcionalidade 3

    @Test
    public void shortestPathCapitals() throws IOException {
        System.out.println("Funcionalidade 4 (Shortest Path por Capitais)");
        Loader l = new Loader("paises.txt", "fronteiras.txt");
        LinkedList<String> capitais = new LinkedList<>();
        capitais.add("madrid");
        capitais.add("paris");
        capitais.add("roma");
        double length = l.shortestPathCapitals("lisboa", "berlim", capitais);
        assertTrue("Sould be 2236.781000", length == 3949.830930420667);
        capitais.clear();
        capitais.add("varsovia");
        capitais.add("kiev");
        capitais.add("moscovo");
        capitais.add("riga");
        length = l.shortestPathCapitals("paris", "erevan", capitais);
        assertTrue("Sould be 5460.446232570601", length == 5460.446232570601);
        capitais.clear();
        capitais.add("chisinau");
        capitais.add("atenas");
        capitais.add("ancara");
        capitais.add("amsterdam");
        length = l.shortestPathCapitals("lisboa", "moscovo", capitais);
        assertTrue("Sould be 7912.669023239214", length == 7912.669023239214);
        capitais.clear();
        length = l.shortestPathCapitals("lisboa", "lisboa", capitais);
        assertTrue("Sould be 0", length == 0);
        length = l.shortestPathCapitals("nulo", "nulo", capitais);
        assertTrue("Sould be 0", length == 0);
    } //Funcionalidade 4

    @Test
    public void biggestCircuit() throws IOException {
        System.out.println("Funcionalidade 5 (Maior circuito iniciado numa capital)");
        Loader l = new Loader("paises.txt", "fronteiras.txt");
        LinkedList<String> actual = l.biggestPath("paris");
        LinkedList<String> expected = new LinkedList<>();
        expected.add("paris");
        expected.add("bruxelas");
        expected.add("luxemburgo");
        expected.add("berlim");
        expected.add("praga");
        expected.add("viena");
        expected.add("bratislava");
        expected.add("budapeste");
        expected.add("zagreb");
        expected.add("liubliana");
        expected.add("roma");
        expected.add("berna");
        expected.add("paris");
        assertEquals(expected, actual);
        expected.clear();
        actual = l.biggestPath("moscovo");
        expected.add("moscovo");
        expected.add("minsk");
        expected.add("vilnius");
        expected.add("riga");
        expected.add("tallinn");
        expected.add("moscovo");
        assertEquals(expected, actual);
        actual = l.biggestPath("nulo");
        expected.clear();
        assertEquals(expected, actual);
    } // Funcionalidade 5
}
