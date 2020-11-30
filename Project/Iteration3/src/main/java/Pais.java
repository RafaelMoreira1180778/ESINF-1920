import java.util.*;

/**
 * @author Paulo Moreira 1180778
 * @author Pedro Borda de Água 1180809
 */
public class Pais implements Comparable<Pais> {

    private String nome;
    private String continente;
    private double populacao;
    private String capital;
    private double latitude;
    private double longitude;
    private int numFronteiras;

    public Pais(String nome, String continente, double populacao, String capital, double latitude, double longitude) {
        this.nome = nome;
        this.continente = continente;
        this.populacao = populacao;
        this.capital = capital;
        this.latitude = latitude;
        this.longitude = longitude;
        this.numFronteiras = 0;
    }

    public Pais(String nome, double latitude, double longitude) {
        this.nome = nome;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Pais(String nome) {
        this.nome = nome;
    }

    public Pais() {
        this.nome = "null";
        this.continente = "null";
        this.populacao = 0;
        this.capital = "null";
        this.latitude = 0;
        this.longitude = 0;
    }

    public String getNome() {
        return this.nome;
    }

    public int getNumFronteiras() {
        return this.numFronteiras;
    }

    public double getPopulacao() {
        return this.populacao;
    }

    public String getContinente() {
        return this.continente;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getCapital() {
        return capital;
    }

    public void setNumFronteiras(int numFronteiras) {
        this.numFronteiras = numFronteiras;
    }

    @Override
    public int hashCode() {
        return nome.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (obj.getClass() != this.getClass()) return false;
        Pais o = (Pais) obj;
        if (!Objects.equals(this.nome, o.nome)) return false;
        return true;
    }

    @Override
    public String toString() {
        // return String.format("Nome: %s\nContinente: %s\nCapital: %s\nPopulação: %.2f M\n, Latitude: %f\n Longitude: %f\n", this.nome, this.continente, this.capital, this.populacao, this.latitude, this.longitude);
        //return String.format("Nome: %s\nNº Fronteiras: %d", this.nome, this.numFronteiras);
        return String.format("%s", this.nome);
    }

    @Override
    public int compareTo(Pais o) {
        return this.nome.compareTo(o.getNome());
    }
}
