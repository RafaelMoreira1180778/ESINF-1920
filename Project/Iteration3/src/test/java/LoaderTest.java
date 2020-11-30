import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class LoaderTest {

    private Loader l;
    private TDT<Pais> tdt;
    private List<Link> ll;
    private List<Link> lp;
    private Link argentina;
    private Link bolivia;
    private Link brasil;
    private Link chile;
    private Link colombia;
    private Link equador;
    private Link guiana;
    private Link paraguai;
    private Link peru;
    private Link suriname;
    private Link venezuela;
    private Link uruguai;
    private Link guianafrancesa;

    @Before
    public void setUp() throws Exception {
        String fp = "paises_testes.txt";
        String ff = "fronteiras_testes.txt";
        l = new Loader(fp, ff);
        ll = new ArrayList<>();
        lp = new ArrayList<>();

        Pais argentinap = new Pais("argentina", "americasul", 41.67, "buenosaires", -34.6131500, -58.3772300);
        Pais boliviap = new Pais("bolivia", "americasul", 9.70, "lapaz", -16.5000000, -68.1500000);
        Pais brasilp = new Pais("brasil", "americasul", 206.12, "brasilia", -15.7797200, -47.9297200);
        Pais chilep = new Pais("chile", "americasul", 16.80, "santiago", -33.4569400, -70.6482700);
        Pais colombiap = new Pais("colombia", "americasul", 46.86, "bogota", 4.6097100, -74.0817500);
        Pais equadorp = new Pais("equador", "americasul", 14.88, "quito", -0.2298500, -78.5249500);
        Pais guianap = new Pais("guiana", "americasul", 0.07, "georgetwon", 6.8044800, -58.1552700);
        Pais guianafrancesap = new Pais("guianafrancesa", "americasul", 2.88, "caiena", 4.9333300, -52.3333300);
        Pais paraguaip = new Pais("paraguai", "americasul", 6.24, "assuncao", -25.3006600, -57.6359100);
        Pais perup = new Pais("peru", "americasul", 28.22, "lima", -12.0431800, -77.0282400);
        Pais surinamep = new Pais("suriname", "americasul", 0.04, "paramaribo", 5.8663800, -55.1668200);
        Pais venezuelap = new Pais("venezuela", "americasul", 31.02, "caracas", 10.4880100, -66.8791900);
        Pais uruguaip = new Pais("uruguai", "americasul", 3.35, "montevideu", -34.9032800, -56.1881600);

        List<Pais> lp1 = new ArrayList<Pais>();
        lp1.add(boliviap);
        lp1.add(brasilp);
        lp1.add(chilep);
        lp1.add(paraguaip);
        lp1.add(uruguaip);
        argentina = new Link(argentinap, lp1);

        List<Pais> lp2 = new ArrayList<Pais>();
        lp2.add(argentinap);
        lp2.add(brasilp);
        lp2.add(chilep);
        lp2.add(paraguaip);
        lp2.add(perup);
        bolivia = new Link(boliviap, lp2);

        ArrayList<Pais> lp3 = new ArrayList<>();
        lp3.add(argentinap);
        lp3.add(boliviap);
        lp3.add(colombiap);
        lp3.add(guianap);
        lp3.add(guianafrancesap);
        lp3.add(paraguaip);
        lp3.add(perup);
        lp3.add(surinamep);
        lp3.add(uruguaip);
        lp3.add(venezuelap);
        brasil = new Link(brasilp, lp3);

        ArrayList<Pais> lp4 = new ArrayList<Pais>();
        lp4.add(argentinap);
        lp4.add(boliviap);
        lp4.add(perup);
        chile = new Link(chilep, lp4);

        ArrayList<Pais> lp5 = new ArrayList<>();
        lp5.add(brasilp);
        lp5.add(equadorp);
        lp5.add(perup);
        lp5.add(venezuelap);
        colombia = new Link(colombiap, lp5);

        ArrayList<Pais> lp6 = new ArrayList<Pais>();
        lp6.add(colombiap);
        lp6.add(perup);
        equador = new Link(equadorp, lp6);

        ArrayList<Pais> lp7 = new ArrayList<Pais>();
        lp7.add(brasilp);
        lp7.add(surinamep);
        lp7.add(venezuelap);
        guiana = new Link(guianap, lp7);

        ArrayList<Pais> lp8 = new ArrayList<Pais>();
        lp8.add(brasilp);
        lp8.add(surinamep);
        guianafrancesa = new Link(guianafrancesap, lp8);

        ArrayList<Pais> lp9 = new ArrayList<>();
        lp9.add(argentinap);
        lp9.add(boliviap);
        lp9.add(brasilp);
        paraguai = new Link(paraguaip, lp9);

        ArrayList<Pais> lp10 = new ArrayList<>();
        lp10.add(boliviap);
        lp10.add(brasilp);
        lp10.add(chilep);
        lp10.add(colombiap);
        lp10.add(equadorp);
        peru = new Link(perup, lp10);

        ArrayList<Pais> lp11 = new ArrayList<>();
        lp11.add(brasilp);
        lp11.add(guianap);
        lp11.add(guianafrancesap);
        suriname = new Link(surinamep, lp11);

        ArrayList<Pais> lp12 = new ArrayList<Pais>();
        lp12.add(brasilp);
        lp12.add(colombiap);
        lp12.add(guianap);
        venezuela = new Link(venezuelap, lp12);

        ArrayList<Pais> lp13 = new ArrayList<Pais>();
        lp13.add(argentinap);
        lp13.add(brasilp);
        uruguai = new Link(uruguaip, lp13);

        tdt = new TDT<>();
        tdt.insert(new Link(new Pais("(7,2)", 7, 2)));
        tdt.insert(new Link(new Pais("(5,4)", 5, 4)));
        tdt.insert(new Link(new Pais("(4,7)", 4, 7)));
        tdt.insert(new Link(new Pais("(2,3)", 2, 3)));
        tdt.insert(new Link(new Pais("(9,6)", 9, 6)));
        tdt.insert(new Link(new Pais("(8,1)", 8, 1)));

        lp.add(new Link(new Pais("(2,3)", 2, 3))); //0
        lp.add(new Link(new Pais("(5,4)", 5, 4))); //1
        lp.add(new Link(new Pais("(4,7)", 4, 7))); //2
        lp.add(new Link(new Pais("(7,2)", 7, 2))); //3
        lp.add(new Link(new Pais("(8,1)", 8, 1))); //4
        lp.add(new Link(new Pais("(9,6)", 9, 6))); //5

        ll.add(argentina);          //0
        ll.add(bolivia);            //1
        ll.add(brasil);             //2
        ll.add(chile);              //3
        ll.add(colombia);           //4
        ll.add(equador);            //5
        ll.add(guiana);             //6
        ll.add(guianafrancesa);     //7
        ll.add(paraguai);           //8
        ll.add(peru);               //9
        ll.add(suriname);           //10
        ll.add(uruguai);            //11
        ll.add(venezuela);          //12
    }

    @Test
    public void loadPaisesTest() {
        List<Link> actual = (List<Link>) l.getBst().inOrder();
        assertTrue(actual.removeAll(ll));
        assertEquals(l.getBst().inOrder(), ll);

        actual = (List<Link>) tdt.inOrder();
        assertTrue(actual.removeAll(lp));
        assertEquals(tdt.inOrder(), lp);
    } //1A

    @Test
    public void getFronteiras() {
        List<Pais> actual = l.obterFronteiras("argentina");
        assertTrue("Argentina", actual.removeAll(argentina.getListaFronteiras()));

        actual = l.obterFronteiras("bolivia");
        assertTrue("Bolivia", actual.removeAll(bolivia.getListaFronteiras()));

        actual = l.obterFronteiras("brasil");
        assertTrue("Brasil", actual.removeAll(brasil.getListaFronteiras()));

        actual = l.obterFronteiras("colombia");
        assertTrue("Colombia", actual.removeAll(colombia.getListaFronteiras()));

        actual = l.obterFronteiras("chile");
        assertTrue("Chile", actual.removeAll(chile.getListaFronteiras()));

        actual = l.obterFronteiras("null");
        assertTrue("Null", actual.isEmpty());
    } //1A

    @Test
    public void getPaisesPorContinente() {
        List<Link> actual = l.obterPaisesPorContinente("americasul");
        assertTrue(actual.removeAll(ll));

        actual = l.obterPaisesPorContinente("null");
        assertTrue(actual.isEmpty());
    } //1B

    @Test
    public void testObterPorCoordenadas() {
        assertTrue(tdt.find(7, 2).getPais().getNome().equalsIgnoreCase("(7,2)"));
        assertTrue(tdt.find(5, 4).getPais().getNome().equalsIgnoreCase("(5,4)"));
        assertTrue(tdt.find(4, 7).getPais().getNome().equalsIgnoreCase("(4,7)"));
        assertTrue(tdt.find(2, 3).getPais().getNome().equalsIgnoreCase("(2,3)"));
        assertTrue(tdt.find(9, 6).getPais().getNome().equalsIgnoreCase("(9,6)"));
        assertTrue(tdt.find(8, 1).getPais().getNome().equalsIgnoreCase("(8,1)"));

        assertNull(tdt.find(0, 0));
    } //2B

    @Test
    public void testObterPorCoordenadas2() {
        assertEquals(l.obterPaisPorCoordenadas(-34.6131500, -58.3772300), argentina);
        assertEquals(l.obterPaisPorCoordenadas(-16.5000000, -68.1500000), bolivia);
        assertEquals(l.obterPaisPorCoordenadas(-15.7797200, -47.9297200), brasil);
        assertEquals(l.obterPaisPorCoordenadas(-33.4569400, -70.6482700), chile);
        assertEquals(l.obterPaisPorCoordenadas(4.6097100, -74.0817500), colombia);
        assertEquals(l.obterPaisPorCoordenadas(-0.2298500, -78.5249500), equador);
        assertEquals(l.obterPaisPorCoordenadas(6.8044800, -58.1552700), guiana);

        assertNull(l.obterPaisPorCoordenadas(38.7071631, -9.135517));
        assertNull(l.obterPaisPorCoordenadas(52.5234051, 13.4113999));
        assertNull(l.obterPaisPorCoordenadas(48.8566667, 2.3509871));
    } //2B

    @Test
    public void getVizinhoMaisProximo() {
        assertTrue(tdt.procuraVizinhoMaisProximo(2, 3).getNome().equalsIgnoreCase("(2,3)"));
        assertTrue(tdt.procuraVizinhoMaisProximo(5, 4).getNome().equalsIgnoreCase("(5,4)"));
        assertTrue(tdt.procuraVizinhoMaisProximo(8, 1).getNome().equalsIgnoreCase("(8,1)"));

        assertTrue(tdt.procuraVizinhoMaisProximo(0, 0).getNome().equalsIgnoreCase("(2,3)"));
        assertTrue(tdt.procuraVizinhoMaisProximo(-1, -1).getNome().equalsIgnoreCase("(2,3)"));
        assertTrue(tdt.procuraVizinhoMaisProximo(20, 20).getNome().equalsIgnoreCase("(9,6)"));

    } //2C

    @Test
    public void getVizinhoMaisProximo2() {
        assertEquals(l.obterVizinhoMaisProximo(-34.9032800, -56.1881600), uruguai);
        assertEquals(l.obterVizinhoMaisProximo(-12.0431800, -77.0282400), peru);
        assertEquals(l.obterVizinhoMaisProximo(-25.3006600, -57.6359100), paraguai);
        assertEquals(l.obterVizinhoMaisProximo(-34.6131500, -58.3772300), argentina);
        assertEquals(l.obterVizinhoMaisProximo(-0.2298500, -78.5249500), equador);

        assertEquals(l.obterVizinhoMaisProximo(38.7071631, -9.135517), guianafrancesa);
        assertEquals(l.obterVizinhoMaisProximo(52.5234051, 13.4113999), guianafrancesa);
        assertEquals(l.obterVizinhoMaisProximo(48.8566667, 2.3509871), guianafrancesa);

        assertEquals(l.obterVizinhoMaisProximo(-15.897679, -47.058606), brasil);
        assertEquals(l.obterVizinhoMaisProximo(7.854422, -57.876001), guiana);
        assertEquals(l.obterVizinhoMaisProximo(5.620803, -51.379840), guianafrancesa);
        assertEquals(l.obterVizinhoMaisProximo(6.693192, -55.054734), suriname);

    } //2C

    @Test
    public void testObterDentroRetangulo() {
        List<Link> actual = tdt.encontrarPaisesRetangulo(0, 0, 10, 10);
        assertTrue(actual.removeAll(lp));

        actual = tdt.encontrarPaisesRetangulo(1, 1, 6, 8);
        assertTrue(actual.remove(lp.get(0)) && actual.remove(lp.get(1)) && actual.remove(lp.get(2)));

        actual = tdt.encontrarPaisesRetangulo(4, 7, 8, 1);
        assertTrue(actual.remove(lp.get(1)) && actual.remove(lp.get(2)) && actual.remove(lp.get(3)) && actual.remove(lp.get(4)));

        actual = tdt.encontrarPaisesRetangulo(0, 0, 1, 1);
        assertTrue(actual.isEmpty());
    } //2D

    @Test
    public void testObterDentroRetangulo2() {
        List<Link> actual = l.obterPaisesRetangulo(11.028920, -90.444675, -42.896740, -21.412525);
        assertTrue(actual.removeAll(ll));

        actual = l.obterPaisesRetangulo(-34.700892, -58.126422, -34.545990, -58.613536);
        assertTrue(actual.remove(argentina));

        actual = l.obterPaisesRetangulo(-15.311734, -48.367610, -16.426417, -47.221038);
        assertTrue(actual.remove(brasil));

        actual = l.obterPaisesRetangulo(-13.133651, -71.077383, -26.103548, -55.329054);
        assertTrue(actual.remove(bolivia) && actual.remove(paraguai));

        actual = l.obterPaisesRetangulo(0, 0, 1, 1);
        assertTrue(actual.isEmpty());
    } //2D
}