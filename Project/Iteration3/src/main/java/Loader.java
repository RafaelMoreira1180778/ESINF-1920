import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Paulo Moreira 1180778
 * @author Pedro Borda de Água 1180809
 */
public class Loader {

    private BST<Link> bst; // bst usada no ex1
    private TDT<Link> tdt; // 2dTree usada no ex2
    private Map<Pais, List<Pais>> mp; // mapa com <País, listaFronteiras>

    /**
     * Construtor da classe Loader.
     *
     * @param nomeFicheiroPaises     nome do ficheiro com a informação dos países.
     * @param nomeFicheiroFronteiras nome do ficheiro com a informação das
     *                               fronteiras.
     */
    public Loader(String nomeFicheiroPaises, String nomeFicheiroFronteiras) throws IOException {
        this.bst = new BST<>();
        this.tdt = new TDT<>();
        this.mp = loadPaises(nomeFicheiroPaises);
        loadFronteiras(nomeFicheiroFronteiras);
        setBST();
        set2DT();
    }

    public BST<Link> getBst() {
        return bst;
    }

    public Map<Pais, List<Pais>> getMp() {
        return mp;
    }

    public TDT<Link> getTdt() {
        return tdt;
    }

    /**
     * Efetua a pesquisa na árvore binária em ordem e devolve as fronteiras do país
     * pretendido.
     *
     * @param pais país pretendido para obter as fronteiras.
     * @return uma lista de países que fazem fronteira com o país.
     */
    public List<Pais> obterFronteiras(String pais) {
        BST.Node<Link> l = this.bst.find(new Link(pais), this.bst.root());
        if (l != null) return l.getElement().getListaFronteiras();
        return new ArrayList<>();
    } // Funcionalidade 1A

    /**
     * Efetua uma pesquisa na árvore bst ordenada e devolve os países ordenados por
     * maior número de fronteiras e menor número de população.
     *
     * @param continente continente sobre o qual pretendemos obter os países.
     * @return lista de países ordenada de acordo com os parâmetros pretendidos.
     */
    public List<Link> obterPaisesPorContinente(String continente) {
        TPC<Link> temp = new TPC<>();
        for (Link l : this.bst.inOrder()) if (l.getContinente().equalsIgnoreCase(continente)) temp.insert(l);
        return (List<Link>) temp.inOrder();
    } // Funcionalidade 1B

    /**
     * Efetua uma pesquisa numa 2d-tree que organiza os países por latitude(x) e
     * longitude(y) e devolve o país com essas coordenadas.
     *
     * @param x latitude do país a encontrar.
     * @param y longitude do país a encontrar.
     * @return devolve o país cuja capital se situa em (x,y).
     */
    public Link obterPaisPorCoordenadas(double x, double y) {
        return this.tdt.find(x, y);
    } // Funcionalidade 2B

    /**
     * Procura a árvore e determina qual o país vizinho mais próxima de um determinado país A.
     *
     * @param x latitude do país A.
     * @param y longitude do país A.
     * @return o país vizinho mais próximo de A.
     */
    public Link obterVizinhoMaisProximo(double x, double y) {
        return this.tdt.procuraVizinhoMaisProximo(x, y);
    } // Funcionalidade 2C

    /**
     * Procura a árvore e determina quais os países que se encontram dentro de uma determinada área do mapa.
     *
     * @param x1 latitude1.
     * @param y1 longitude1.
     * @param x2 latitude2.
     * @param y2 longitude2.
     * @return a lista com os países que se encontram dentro da área pretendida.
     */
    public List<Link> obterPaisesRetangulo(double x1, double y1, double x2, double y2) {
        return this.tdt.encontrarPaisesRetangulo(x1, y1, x2, y2);
    } //Funcionalidade 2D

    /**
     * Carrega a informação dos países a partir da informação que o ficheiro contém.
     * Elabora vértices no grafo.
     */
    private Map<Pais, List<Pais>> loadPaises(String nomeFicheiroPaises) throws IOException {
        Map<Pais, List<Pais>> m = new HashMap<>();
        List<String> l = Files.lines(Paths.get(nomeFicheiroPaises)).collect(Collectors.toList());
        for (String s : l) {
            String[] ps = s.split(",");
            Pais p = new Pais(ps[0].trim(), ps[1].trim(), Double.parseDouble(ps[2].trim()), ps[3].trim(),
                    Double.parseDouble(ps[4].trim()), Double.parseDouble(ps[5].trim()));
            List<Pais> list = new ArrayList<>();
            m.put(p, list);
        }
        return m;
    }

    /**
     * Carrega a informação das fronteiras a partir da informação que o ficheiro.
     * Elabora os sets de fronteiras de cada país.
     */
    private void loadFronteiras(String nomeFicheiroFronteiras) throws IOException {
        List<String> l = Files.lines(Paths.get(nomeFicheiroFronteiras)).collect(Collectors.toList());
        for (String s : l) {
            String[] f = s.split(",");
            Pais p1 = getPais(f[0].trim());
            Pais p2 = getPais(f[1].trim());
            for (Pais p : mp.keySet()) {
                if (p.equals(p1)) {
                    addFronteira(p1, p2);
                    addFronteira(p2, p1);
                }
            }
        }
    }

    /**
     * Preenche a BST com todos os países.
     */
    private void setBST() {
        for (Pais p : this.mp.keySet()) this.bst.insert(new Link(p, this.mp.get(p)));
    }

    /**
     * Preenche a BST2 com todos os países.
     */
    private void set2DT() {
        for (Link l : this.bst.inOrder()) this.tdt.insert(l);
    }

    /**
     * @return devolve a lista de países que fazem fronteira com o pais pretendido a
     * partir da pesquisa da arvore.
     */
    private Pais getPais(String pais) {
        for (Pais p : this.mp.keySet())
            if (p.getNome().equalsIgnoreCase(pais))
                return p;
        return null;
    }

    /**
     * Adiciona as fronteiras e atualiza o numero de fronteiras.
     *
     * @param p1 país ao qual vai ser adicionada a fronteira.
     * @param p2 país que faz fronteira com p1.
     */
    private void addFronteira(Pais p1, Pais p2) {
        this.mp.get(p1).add(p2);
        p1.setNumFronteiras(this.mp.get(p1).size());
    }
}
