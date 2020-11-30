import java.util.Objects;

/**
 * @author Paulo Moreira 1180778
 * @author Pedro Borda de Água 1180809
 */
public class Pais implements Comparable {

    private String nome;
    private String continente;
    private double populacao;
    private String capital;
    private double latitude;
    private double longitude;

    public Pais(String nome, String continente, double populacao, String capital, double latitude, double longitude) {
        this.nome = nome;
        this.continente = continente;
        this.populacao = populacao;
        this.capital = capital;
        this.latitude = latitude;
        this.longitude = longitude;
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
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getContinente() {
        return continente;
    }

    public void setContinente(String continente) {
        this.continente = continente;
    }

    public double getPopulacao() {
        return populacao;
    }

    public void setPopulacao(double populacao) {
        this.populacao = populacao;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return String.format("Nome: %s\nContinente: %s\nCapital: %s\nPopulação: %.2f M\n, Latitude: %f\n Longitude: %f\n", this.nome, this.continente, this.capital, this.populacao, this.latitude, this.longitude);
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
    public int compareTo(Object o) {
        return ((Pais) o).getNome().compareToIgnoreCase(this.getNome());
    }
}
