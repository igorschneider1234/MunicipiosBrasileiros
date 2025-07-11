package dao;

import Model.Municipio;

import java.sql.*;
import java.util.*;

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
                return extrairMunicipio(rs);
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
                capitais.add(extrairMunicipio(rs));
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar capitais: " + e.getMessage());
        }

        return capitais;
    }

    public List<Municipio> listarPorPopulacaoMinima(int min) {
        List<Municipio> lista = new ArrayList<>();
        String sql = "SELECT * FROM municipios WHERE populacao >= ? ORDER BY populacao DESC";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, min);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                lista.add(extrairMunicipio(rs));
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar municípios por população mínima: " + e.getMessage());
        }

        return lista;
    }

    public List<Municipio> listarPorPopulacaoEntre(int min, int max) {
        List<Municipio> lista = new ArrayList<>();
        String sql = "SELECT * FROM municipios WHERE populacao BETWEEN ? AND ? ORDER BY populacao DESC";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, min);
            stmt.setInt(2, max);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                lista.add(extrairMunicipio(rs));
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar municípios por faixa de população: " + e.getMessage());
        }

        return lista;
    }

    public Map<String, Municipio> listarMaisPopulososNaoCapitaisPorEstado() {
        Map<String, Municipio> resultado = new HashMap<>();

        String sql = """
            SELECT m.*
            FROM municipios m
            JOIN (
                SELECT estado, MAX(populacao) AS max_pop
                FROM municipios
                WHERE capital = false
                GROUP BY estado
            ) AS sub
            ON m.estado = sub.estado AND m.populacao = sub.max_pop
            WHERE m.capital = false
        """;

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String estado = rs.getString("estado");
                Municipio m = extrairMunicipio(rs);
                resultado.put(estado, m);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar mais populosos que não são capitais: " + e.getMessage());
        }

        return resultado;
    }

    public List<Municipio> top10MaisPopulososNaoCapitais() {
        List<Municipio> lista = new ArrayList<>();
        String sql = """
            SELECT * FROM municipios
            WHERE capital = false
            ORDER BY populacao DESC
            LIMIT 10
        """;

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(extrairMunicipio(rs));
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar top 10 não capitais mais populosas: " + e.getMessage());
        }

        return lista;
    }

    // Função auxiliar
    private Municipio extrairMunicipio(ResultSet rs) throws SQLException {
        return new Municipio(
            rs.getString("estado"),
            rs.getInt("codigo_estado"),
            rs.getInt("codigo_municipio"),
            rs.getString("nome_municipio"),
            rs.getBoolean("capital"),
            rs.getInt("populacao")
        );
    }
}
