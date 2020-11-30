package main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Loader {

    /**
     * Mapa do loader que cont�m a informa��o dos Pa�ses e o Set referente �s suas fronteiras
     */
    public Map<Pais, Set<Pais>> m;

    /**
     * Mapa do loader que cont�m o n�mero de fronteiras e os pa�ses referentes a cada valor
     */
    private Map<Integer, Set<String>> k;

    /**
     * Lista de pa�ses ordenados por ordem crescente de popula��o
     */
    private List<Pais> l;

    /**
     * Nome do ficheiro com a informa��o dos pa�ses
     */
    private String nomeFicheiroPaises;

    /**
     * Nome do ficheiro com a informa��o das fronteiras
     */
    private String nomeFicheiroFronteiras;

    /**
     * Construtor completo do loader
     *
     * @param nomeFicheiroPaises nome do ficheiro com a info dos paises
     * @param nomeFicheiroFronteiras nome dos ficheiros com a info das fronteiras
     * @throws java.io.IOException caso nao encontre um dos ficheiros
     */
    public Loader(String nomeFicheiroPaises, String nomeFicheiroFronteiras) throws IOException {
        this.m = new HashMap<>();
        this.nomeFicheiroPaises = nomeFicheiroPaises;
        this.nomeFicheiroFronteiras = nomeFicheiroFronteiras;
        carregarFicheiroPaises();
        carregarFicheiroFronteiras();
        this.k = ordenarNumFronteiras();
    }

    /**
     * @return Devolve o Mapa do loader que cont�m a informa��o dos Pa�ses e o Set referente �s suas fronteiras
     */
    public Map<Pais, Set<Pais>> getM() {
        return m;
    }

    /**
     * @return Devolve o Mapa do loader que cont�m o n�mero de fronteiras e os pa�ses referentes a cada valor
     */
    public Map<Integer, Set<String>> getK() {
        return k;
    }

    /**
     * @return Devolve a Lista de pa�ses ordenados por ordem crescente de popula��o
     */
    public List<Pais> getL() {
        return l;
    }

    /**
     * Carrega, para a isnt�ncia do Loader, a informa��o presente no ficheiro dos pa�ses - funcionalidade 1
     */
    private void carregarFicheiroPaises() throws IOException {

        List<String> l = Files.lines(Paths.get(this.nomeFicheiroPaises)).collect(Collectors.toList());

        for (String sL : l) {
            String[] linha = sL.split(",");
            Pais p = new Pais(linha[0].trim(), linha[1].trim(), Double.parseDouble(linha[2].trim()), linha[3].trim(), Double.parseDouble(linha[4].trim()), Double.parseDouble(linha[5].trim()));
            Set<Pais> s = new HashSet<Pais>();
            this.m.put(p, s);
        }
    }

    /**
     * Carrega, para a isnt�ncia do Loader, a informa��o presente no ficheiro das fronteiras - funcionalidade 1
     */
    private void carregarFicheiroFronteiras() throws IOException {

        List<String> l = Files.lines(Paths.get(this.nomeFicheiroFronteiras)).collect(Collectors.toList());

        for (String sL : l) {
            String[] temp = sL.split(",");
            for (Pais p : this.m.keySet()) {
                if (temp[0].trim().equalsIgnoreCase(p.getNome())) {
                    this.m.get(p).add(obterPais(temp[1].trim()));
                    this.m.get(obterPais(temp[1].trim())).add(p);
                }
                if (temp[1].trim().equalsIgnoreCase(p.getNome())) {
                    this.m.get(p).add(obterPais(temp[0].trim()));
                    this.m.get(obterPais(temp[0].trim())).add(p);
                }
            }
        }
    }

    /**
     * Permite ir ao mapa buscar a informa��o de um pa�s
     */
    public Pais obterPais(String nome) {
        for (Pais p : this.m.keySet()) {
            if (nome.equalsIgnoreCase(p.getNome())) {
                return p;
            }
        }
        return null;
    }

    /**
     * Permite ordenar os pa�ses por ordem da quantidade da sua popula��o - funcionalidade 2
     */
    public void ordenarPopulacao(String continente, int N) {
        List<Pais> temp = new ArrayList<>();

        for (Pais p : this.m.keySet()) {
            if (p.getContinente().equalsIgnoreCase(continente) && (int) p.getPopulacao() > N) {
                temp.add(p);
            }
        }

        Collections.sort(temp, (o1, o2) -> -((int) o2.getPopulacao() - (int) o1.getPopulacao()));

        this.l = temp;
    }

    /**
     * Permite ir ao mapa e agrupar os pa�ses, segundo o seu n�mero de fronteiras, por ordem decrescente - funcionalidade 3
     */
    private Map<Integer, Set<String>> ordenarNumFronteiras() {

        int n;
        Set<String> s;

        SortedMap<Integer, Set<String>> mTemp = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer i1, Integer i2) {
                return -(i1 - i2);
            }
        });

        for (Pais p : this.m.keySet()) {

            n = this.m.get(p).size();
            s = new TreeSet<>();
            s.add(p.getNome());

            for (Pais p2 : this.m.keySet()) {
                if (p != p2 && this.m.get(p2).size() == n) {
                    s.add(p2.getNome());
                }
            }
            mTemp.put(n, s);
        }

        return mTemp;
    }

    //Obtém o número mínimo de fronteiras entre dois países.
    public int obterMinFronteiras(Pais P1, Pais P2) {
        int numFronteiras = 0;
        ArrayList paisesAnalisados = new ArrayList<>();
        ArrayList<Pais> paisesComFronteiras = new ArrayList<>();
        paisesComFronteiras.add(P1);
        numFronteiras = calcularFronteiras(P2, paisesAnalisados, paisesComFronteiras, numFronteiras);
        return numFronteiras;
    }

    //Calcula o número de fronteiras existentes entre dois países.
    private int calcularFronteiras(Pais P2, ArrayList<Pais> paisesAnalisados, ArrayList<Pais> paisesComFronteiras, int numFronteiras) {
        ArrayList<Pais> list = new ArrayList<>();
        if (paisesComFronteiras.isEmpty()) {
            return 0;
        }
        if (paisesComFronteiras.contains(P2)) {
            return numFronteiras;
        }
        int size = paisesComFronteiras.size();
        for (int i = 0; i < size; i++) {
            Pais c = paisesComFronteiras.get(i);
            for (Pais c1 : m.get(c)) {
                if (!paisesAnalisados.contains(c1)) {
                    paisesComFronteiras.add(c1);
                }
            }
            list.add(c);
            paisesAnalisados.add(c);
        }

        ArrayList<Pais> temp = new ArrayList<>(paisesComFronteiras);
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < paisesComFronteiras.size(); j++) {
                if (list.get(i).equals(paisesComFronteiras.get(j))) {
                    paisesComFronteiras.remove(list.get(i));
                }
            }
        }

        paisesComFronteiras = temp;
        numFronteiras++;

        return calcularFronteiras(P2, paisesAnalisados, paisesComFronteiras, numFronteiras);
    }
}
