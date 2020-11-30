package main;

import java.util.Objects;

public class Pais implements Comparable {

    /**
     * Nome do pa�s
     */
    private String nome;
    /**
     * Nome do Continente do qual o pa�s faz parte
     */
    private String continente;
    /**
     * Popula��o do pa�s
     */
    private double populacao;
    /**
     * Capital do pa�s
     */
    private String capital;
    /**
     * Valor da Latitude onde se encontra o pa�s
     */
    private double latitude;
    /**
     * Valor da Longitude onde se encontra o pa�s
     */
    private double longitude;

    /**
     * Construtor completo de um pa�s
     *
     * @param nome
     * @param continente
     * @param populacao
     * @param capital
     * @param latitude
     * @param longitude
     */
    public Pais(String nome, String continente, double populacao, String capital, double latitude, double longitude) {
        this.nome = nome;
        this.continente = continente;
        this.populacao = populacao;
        this.capital = capital;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Devolve o nome do pa�s
     *
     * @return
     */
    public String getNome() {
        return nome;
    }

    /**
     * Devolve o nome do pa�s
     *
     * @return
     */
    public String getContinente() {
        return continente;
    }

    /**
     * Devolve a popula��o do pa�s
     *
     * @return
     */
    public double getPopulacao() {
        return populacao;
    }

    /**
     * Devolve a capital do pa�s
     *
     * @return
     */
    public String getCapital() {
        return capital;
    }

    /**
     * Devolve a latitude do pa�s
     *
     * @return
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Devolve a longitude do pa�s
     *
     * @return
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Devolve as caracter�sticas do pais
     *
     * @return
     */
    @Override
    public String toString() {
        return String.format("Nome: %s\nContinente: %s\nCapital: %s\nPopulação: %.2f M\n, Latitude: %f\n Longitude: %f\n", this.nome, this.continente, this.capital, this.populacao, this.latitude, this.longitude);
    }

    /**
     * Devolve o hashcode associado ao pais
     *
     * @return
     */
    @Override
    public int hashCode() {
        return nome.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        final Pais o = (Pais) obj;
        if (!Objects.equals(this.nome, o.nome)) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(Object o) {
        return ((Pais) o).getNome().compareToIgnoreCase(this.getNome());
    }
}
