import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 * @author Paulo Moreira 1180778
 * @author Pedro Borda de Água 1180809
 */
public class Link implements Comparable<Link> {

    private Pais pais;
    private List<Pais> listaFronteiras;

    public Link(Pais p, List<Pais> l) {
        this.pais = p;
        this.listaFronteiras = l;
    }

    public Link(Pais p) {
        this.pais = p;
    }

    public Link(String pais) {
        this.pais = new Pais(pais);
    }

    public Link() {
        this.pais = new Pais();
        this.listaFronteiras = new ArrayList<>();
    }

    public Pais getPais() {
        return this.pais;
    }

    public List<Pais> getListaFronteiras() {
        return listaFronteiras;
    }

    public String getNome() {
        return this.pais.getNome();
    }

    public double getLatitude() {
        return this.pais.getLatitude();
    }

    public double getLongitude() {
        return this.pais.getLongitude();
    }

    public double getPopulacao() {
        return this.pais.getPopulacao();
    }

    public int getNumFronteiras() {
        return this.pais.getNumFronteiras();
    }

    public String getContinente() {
        return this.pais.getContinente();
    }

    @Override
    public String toString() {
        return pais.getNome() + " " + listaFronteiras;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Link)) return false;
        Link link = (Link) o;
        return this.getNome().equals((link.getNome()));
    }

    @Override
    public int compareTo(Link link) {
        return this.getPais().compareTo(link.getPais());
    }
}
