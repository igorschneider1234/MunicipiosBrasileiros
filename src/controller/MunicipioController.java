package controller;

import dao.MunicipioDAO;
import Model.Municipio;

import java.util.List;

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
}
