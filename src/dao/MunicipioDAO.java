package dao;

import Model.Municipio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MunicipioDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/cidades?useSSL=false&serverTimezone=America/Sao_Paulo";
    private static final String USER = "root";
    private static final String PASSWORD = "IgorAndrei12341234*";

    public Municipio buscarPorNome(String nome) {
        String sql = "SELECT * FROM municipios WHERE nome_municipio = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Municipio(
                        rs.getString("estado"),
                        rs.getInt("codigo_estado"),
                        rs.getInt("codigo_municipio"),
                        rs.getString("nome_municipio"),
                        rs.getBoolean("capital"),
                        rs.getInt("populacao")
                );
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar município: " + e.getMessage());
        }
        return null;
    }

    public int calcularPopulacaoTotalPorEstado(String estado) {
        String sql = "SELECT SUM(populacao) AS total FROM municipios WHERE estado = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, estado);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("total");
            }

        } catch (SQLException e) {
            System.err.println("Erro ao calcular população do estado: " + e.getMessage());
        }
        return 0;
    }

    public List<Municipio> listarCapitais() {
        List<Municipio> capitais = new ArrayList<>();
        String sql = "SELECT * FROM municipios WHERE capital = true";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Municipio m = new Municipio(
                        rs.getString("estado"),
                        rs.getInt("codigo_estado"),
                        rs.getInt("codigo_municipio"),
                        rs.getString("nome_municipio"),
                        rs.getBoolean("capital"),
                        rs.getInt("populacao")
                );
                capitais.add(m);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar capitais: " + e.getMessage());
        }

        return capitais;
    }
}
