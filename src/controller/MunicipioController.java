// MunicipioController.java
package controller;

import dao.MunicipioDAO;
import Model.Municipio;

import java.util.List;
import java.util.Map;

public class MunicipioController {
    private MunicipioDAO dao = new MunicipioDAO();

    public Municipio buscarMunicipio(String nome) {
        return dao.buscarPorNome(nome);
    }

    public int calcularPopulacaoEstado(String estado) {
        return dao.calcularPopulacaoTotalPorEstado(estado);
    }

    public List<Municipio> listarCapitais() {
        return dao.listarCapitais();
    }

    public List<Municipio> listarPorPopulacaoMinima(int min) {
        return dao.listarPorPopulacaoMinima(min);
    }

    public List<Municipio> listarPorPopulacaoEntre(int min, int max) {
        return dao.listarPorPopulacaoEntre(min, max);
    }

    public Map<String, Municipio> listarMaisPopulososNaoCapitaisPorEstado() {
        return dao.listarMaisPopulososNaoCapitaisPorEstado();
    }

    public List<Municipio> top10MaisPopulososNaoCapitais() {
        return dao.top10MaisPopulososNaoCapitais();
    }
}
