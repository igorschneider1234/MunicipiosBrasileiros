package Model;

public class Municipio {
    private String estado;
    private int codigoEstado;
    private int codigoMunicipio;
    private String nomeMunicipio;
    private boolean capital;
    private int populacao;

    // Construtor completo
    public Municipio(String estado, int codigoEstado, int codigoMunicipio, String nomeMunicipio, boolean capital, int populacao) {
        this.estado = estado;
        this.codigoEstado = codigoEstado;
        this.codigoMunicipio = codigoMunicipio;
        this.nomeMunicipio = nomeMunicipio;
        this.capital = capital;
        this.populacao = populacao;
    }

    // Getters
    public String getEstado() { return estado; }
    public int getCodigoEstado() { return codigoEstado; }
    public int getCodigoMunicipio() { return codigoMunicipio; }
    public String getNomeMunicipio() { return nomeMunicipio; }
    public boolean isCapital() { return capital; }
    public int getPopulacao() { return populacao; }

    // toString
    @Override
    public String toString() {
        return String.format("Estado: %s\nCódigo Estado: %d\nCódigo Município: %d\nNome: %s\nCapital: %s\nPopulação: %d",
                estado, codigoEstado, codigoMunicipio, nomeMunicipio,
                capital ? "Sim" : "Não", populacao);
    }
}
