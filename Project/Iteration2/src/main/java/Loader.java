import DEI.Graph;
import DEI.GraphAlgorithms;

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

    /**
     * Grafo elaborado com base nos dois ficheiros fornecidos. Inclui todos os
     * países como vértices e as fronteiras como 'edges'.
     */
    private Graph<Pais, Pais> g;
    /**
     * Mapa que atribui a cada país o seu índice dentro do grafo.
     */
    private Map<Pais, Integer> mI;

    /**
     * Construtor da classe Loader.
     *
     * @param nomeFicheiroPaises     nome do ficheiro com a informação dos países.
     * @param nomeFicheiroFronteiras nome do ficheiro com a informação das
     *                               fronteiras.
     */
    public Loader(String nomeFicheiroPaises, String nomeFicheiroFronteiras) throws IOException {
        this.g = new Graph<>(false);
        this.mI = new HashMap<>();
        carregarFicheiroPaises(nomeFicheiroPaises);
        carregarFicheiroFronteiras(nomeFicheiroFronteiras);
    }

    /**
     * Carrega a informação dos países a partir da informação que o ficheiro contém.
     * Elabora vértices no grafo.
     */
    private void carregarFicheiroPaises(String nomeFicheiroPaises) throws IOException {

        List<String> l = Files.lines(Paths.get(nomeFicheiroPaises)).collect(Collectors.toList());
        int i = 0;

        for (String sL : l) {
            String[] linha = sL.split(",");
            Pais p = new Pais(linha[0].trim(), linha[1].trim(), Double.parseDouble(linha[2].trim()), linha[3].trim(),
                    Double.parseDouble(linha[4].trim()), Double.parseDouble(linha[5].trim()));
            mI.put(p, i);
            this.g.insertVertex(p);
            i++;
        }
    } // Funcionalidade 1

    /**
     * Carrega a informação das fronteiras a partir da informação que o ficheiro
     * contém. Elabora edges no grafo.
     */
    private void carregarFicheiroFronteiras(String nomeFicheiroFronteiras) throws IOException {

        List<String> l = Files.lines(Paths.get(nomeFicheiroFronteiras)).collect(Collectors.toList());

        for (String sL : l) {
            String[] temp = sL.split(",");
            Pais p1 = obterPais(temp[0].trim());
            Pais p2 = obterPais(temp[1].trim());
            double dist = distance(p1, p2);
            this.g.insertEdge(p1, p2, null, dist);
        }
    } // Funcionalidade 1

    /**
     * Método genérico para calcular a distância entre dois países (p1 e p2).
     * Baseado em http://www.movable-type.co.uk/scripts/latlong.html.
     *
     * @return distância entre os dois países.
     */
    public static double distance(Pais p1, Pais p2) {

        if (p1 == p2) return 0;
        if (!p1.getContinente().equalsIgnoreCase(p2.getContinente())) return 0;

        double lat1 = p1.getLatitude();
        double lat2 = p2.getLatitude();
        double lon1 = p1.getLongitude();
        double lon2 = p2.getLongitude();

        double theta = lon1 - lon2;
        double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2))
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
        dist = Math.acos(dist);
        dist = Math.toDegrees(dist);
        dist = dist * 60 * 1.1515;
        return (dist * 1.609344);

    }

    /**
     * @return devolve um mapa com pais e a cor correspondente.
     */
    public Map<Pais, Integer> colorir() {
        HashMap<Pais, Integer> mc = new HashMap<>();
        Pais[] pv = this.g.allkeyVerts();
        ArrayList<Pais> pl = new ArrayList<>(Arrays.asList(pv));
        for (int i = 0; i < pl.size() - 1; i++) {
            int nf = this.g.outDegree(pl.get(i));
            for (int j = i; j < pl.size(); j++) {
                int numeroFronteiras_j = this.g.outDegree(pl.get(j));
                if (nf < numeroFronteiras_j) {
                    Pais pais_i = pl.get(i);
                    pl.set(i, pl.get(j));
                    pl.set(j, pais_i);
                }
            }
        }
        int c = 0;
        while (true) {
            boolean b = true;
            for (Pais pais1 : pl) {
                if ((!mc.containsKey(pais1))) {
                    b = false;
                    break;
                }
            }
            if (b) break;
            for (Pais pais : pl) {
                if (!mc.containsKey(pais)) {
                    boolean cd = true;
                    for (Pais fronteira : this.g.adjVertices(pais)) {
                        if (mc.get(fronteira) != null) {
                            if (mc.get(fronteira) == c) {
                                cd = false;
                                break;
                            }
                        }
                    }
                    if (cd) mc.put(pais, c);
                }
            }
            c++;
        }
        return mc;
    } // Funcionalidade 2

    /**
     * @return devolve o caminho mais curto entre dois países passando por várias capitais.
     */
    public double shortestPathCapitals(String cOrig, String cDest, LinkedList<String> capitais) {
        Pais pOrig = obterPaisPorCapital(cOrig);
        Pais pDest = obterPaisPorCapital(cDest);
        if (pOrig == null || pDest == null) return 0;
        if (pOrig == pDest && capitais.size() == 0) return 0;
        Set<LinkedList<String>> permutacoes = new LinkedHashSet<>();
        int size = capitais.size();
        double sdf = Double.MAX_VALUE;
        heapPermutation(capitais, size, permutacoes);
        for (LinkedList<String> ll : permutacoes) {
            double sdt = 0;
            Pais fp = obterPaisPorCapital(ll.getFirst());
            Pais lp = obterPaisPorCapital(ll.getLast());
            sdt += shortestPath(pOrig, fp, new LinkedList<>());
            sdt += shortestPath(lp, pDest, new LinkedList<>());
            for (int i = 0; i < ll.size() - 1; i++) {
                Pais p1 = obterPaisPorCapital(ll.get(i));
                Pais p2 = obterPaisPorCapital(ll.get(i + 1));
                sdt += shortestPath(p1, p2, new LinkedList<>());
            }
            if (sdt < sdf) sdf = sdt;
        }
        return sdf;
    } // Funcionalidade 4

    /**
     * Devolve o caminho mais curto (em km) entre dois países.
     *
     * @param pOrig     país de origem do caminho.
     * @param pDest     país de destino do caminho.
     * @param shortPath lista com todos os países percorridos entre o pOrigem e o
     *                  pDestino
     * @return o valor do caminho (em km)
     */
    public double shortestPath(Pais pOrig, Pais pDest, LinkedList<Pais> shortPath) {
        return GraphAlgorithms.shortestPath(this.g, pOrig, pDest, shortPath);
    } // Funcionalidade 3

    /**
     * @return devolve o grafo criado quando a informação é carregada.
     */
    public Graph<Pais, Pais> getG() {
        return g;
    }

    /**
     * @return devolve um país existente (ou não) no grafo a partir do nome do
     * mesmo.
     */
    public Pais obterPais(String nomePais) {
        for (Pais p : this.g.vertices()) {
            if (nomePais.equalsIgnoreCase(p.getNome())) {
                return p;
            }
        }
        return null;
    }

    /**
     * @return devolve um país existente (ou não) no grafo a partir do nome da sua
     * capital.
     */
    public Pais obterPaisPorCapital(String nomeCapital) {
        for (Pais p : this.g.vertices()) if (nomeCapital.equalsIgnoreCase(p.getCapital())) return p;
        return null;
    }

    /**
     * @return devolve um país existente (ou não) no grafo a partir de um dado
     * índice.
     */
    public Pais obterPaisPorIndex(int index) {
        for (Pais p : mI.keySet()) if (mI.get(p) == index) return p;
        return null;
    }

    /**
     * @return devolve o index do país no grafo
     */
    public int obterIndexPais(Pais p) {
        for (Pais pm : mI.keySet()) if (pm == p) return mI.get(pm);
        return 0;
    }

    /**
     * @param cOrig país de origem do curcuito
     * @return devolve uma lista com o maior circuito de acordo com o que é pedido no enunciado.
     */
    public LinkedList<String> biggestPath(String cOrig) {
        LinkedList<String> path = new LinkedList<>();
        if (!this.g.validVertex(obterPaisPorCapital(cOrig))) return path;
        boolean[] visited = new boolean[g.numVertices()];
        path.add(cOrig);
        visited[g.getKey(obterPaisPorCapital(cOrig))] = true;
        longestCircuitRecursive(cOrig, path, visited);
        return path;
    } // Funcionalidade 5

    /**
     * Coloca dentro do set de permutaçoes listas com as capitais permutadas entre si.
     */
    private void heapPermutation(LinkedList<String> capitais, int size, Set<LinkedList<String>> permutacoes) {
        if (size == 1) {
            LinkedList<String> ct = new LinkedList<>();
            ct.addAll(capitais);
            permutacoes.add(ct);
        }
        for (int i = 0; i < size; i++) {
            heapPermutation(capitais, size - 1, permutacoes);
            if (size % 2 == 1) {
                String s = capitais.get(0);
                capitais.set(0, capitais.get(size - 1));
                capitais.set(size - 1, s);
            } else {
                String s = capitais.get(i);
                capitais.set(i, capitais.get(size - 1));
                capitais.set(size - 1, s);
            }
        }
    }

    /**
     * Calcola o maior caminho começando num dado país e passando no maior número de capitais possíveis.
     *
     * @param cOrig   capital do país de origem em cada iteração.
     * @param path    caminho atual.
     * @param visited array de booleanos com os países já visitados até ao momento.
     * @return 0 caso tenha chegado ao fim.
     */
    private int longestCircuitRecursive(String cOrig, LinkedList<String> path, boolean[] visited) {
        Pais cont = obterPaisPorCapital(cOrig);
        if (cOrig.equalsIgnoreCase(path.get(0)) && path.size() != 1) return 0;
        if (g.outDegree(cont) == 1) {
            if (path.size() == 1) {
                path.remove(cOrig);
                return 0;
            }
        }
        Pais vert = closestNeighboor(cont, visited);
        if (vert == null) {
            if (verticeEqualsSource(cont, obterPaisPorCapital(path.get(0)))) {
                if (!path.get(1).equalsIgnoreCase(cOrig)) {
                    path.addLast(path.get(0));
                    return 0;
                } else {
                    path.clear();
                    return 0;
                }
            }
            while (vert == null) {
                path.remove(cOrig);
                if (path.isEmpty()) return 0;
                cOrig = path.getLast();
                vert = closestNeighboor(obterPaisPorCapital(cOrig), visited);
                if (vert == null) {
                    if (verticeEqualsSource(obterPaisPorCapital(cOrig), obterPaisPorCapital(path.get(0)))) {
                        if (!path.get(1).equalsIgnoreCase(cOrig)) {
                            path.addLast(path.get(0));
                            return 0;
                        } else {
                            path.clear();
                            return 0;
                        }
                    }
                }
            }
        }
        String cVert = vert.getCapital();
        path.add(cVert);
        return longestCircuitRecursive(cVert, path, visited);
    }

    /**
     * Itera o grafo e descobre o país mais próximo ainda não visitado.
     *
     * @param p       país sobre o qual é preciso descobrir o país mais próximo.
     * @param visited array de booleanos com os países já visitados até ao momento.
     * @return o país mais próximo do pais p
     */
    private Pais closestNeighboor(Pais p, boolean[] visited) {
        Pais pClose = null;
        double dMin = Double.MAX_VALUE, aux;
        for (Pais c : g.adjVertices(p)) {
            if (!visited[g.getKey(c)]) {
                aux = distance(p, c);
                if (aux < dMin) {
                    dMin = aux;
                    pClose = c;
                }
            }
        }
        if (pClose != null) visited[g.getKey(pClose)] = true;
        return pClose;
    }

    /**
     * @return true caso o vértice seja igual ao país de origem
     */
    private boolean verticeEqualsSource(Pais vert, Pais pOrig) {
        for (Pais p : g.adjVertices(vert)) if (p.equals(pOrig)) return true;
        return false;
    }

}
