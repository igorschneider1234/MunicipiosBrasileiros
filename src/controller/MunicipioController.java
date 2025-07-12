package controller;

import Model.Municipio;
import repository.MunicipioRepository;

import java.util.List;
import java.util.Map;

public class MunicipioController {
    private MunicipioRepository repository = new MunicipioRepository();

    public Municipio buscarMunicipio(String nome) {
        return repository.buscarPorNome(nome);
    }

    public int calcularPopulacaoEstado(String estado) {
        return repository.calcularPopulacaoTotalPorEstado(estado);
    }

    public List<Municipio> listarCapitais() {
        return repository.listarCapitais();
    }

    public List<Municipio> listarPorPopulacaoMinima(int min) {
        return repository.listarPorPopulacaoMinima(min);
    }

    public List<Municipio> listarPorPopulacaoEntre(int min, int max) {
        return repository.listarPorPopulacaoEntre(min, max);
    }

    public Map<String, Municipio> listarMaisPopulososNaoCapitaisPorEstado() {
        return repository.listarMaisPopulososNaoCapitaisPorEstado();
    }

    public List<Municipio> top10MaisPopulososNaoCapitais() {
        return repository.top10MaisPopulososNaoCapitais();
    }
}
