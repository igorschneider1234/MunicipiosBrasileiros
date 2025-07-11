import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Main.java
 * 
 * Lê o arquivo CSV "2022_IBGE -Municipios.csv" localizado na raiz do projeto
 * e insere os registros na tabela "municipios" do banco MySQL "cidades".
 * 
 * Ajuste as constantes JDBC_URL, USER e PASSWORD conforme o seu ambiente.
 */
public class Main {

    // ===== CONFIGURAÇÕES DO BANCO =====
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/cidades?useSSL=false&serverTimezone=America/Sao_Paulo";
    private static final String USER = "root";        // <-- seu usuário MySQL
    private static final String PASSWORD = "IgorAndrei12341234*"; // <-- sua senha MySQL

    // ===== CONFIGURAÇÃO DO CSV =====
    private static final String CSV_FILE = "2022_IBGE -Municipios.csv"; // caminho relativo ao diretório do projeto

    // ===== SQL DE INSERÇÃO =====
    private static final String INSERT_SQL =
            "INSERT INTO municipios (estado, codigo_estado, codigo_municipio, nome_municipio, capital, populacao) " +
            "VALUES (?, ?, ?, ?, ?, ?)";

    public static void main(String[] args) {
        int registros = 0;

        try (Connection conn = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(INSERT_SQL);
             BufferedReader br = Files.newBufferedReader(Paths.get(CSV_FILE), StandardCharsets.UTF_8)) {

            conn.setAutoCommit(false); // transação única

            // Pular a primeira linha (cabeçalho)
            br.readLine();
            String line;

            while ((line = br.readLine()) != null) {
                String[] campos = line.split(";", -1);
                if (campos.length < 6) continue; // linha incompleta

                String estado = campos[0].trim();
                int codigoEstado = Integer.parseInt(campos[1].trim());
                int codigoMunicipio = Integer.parseInt(campos[2].trim());
                String nomeMunicipio = campos[3].trim();
                boolean capital = campos[4].trim().equalsIgnoreCase("Sim");
                int populacao = Integer.parseInt(campos[5].trim().replace(".", ""));

                stmt.setString(1, estado);
                stmt.setInt(2, codigoEstado);
                stmt.setInt(3, codigoMunicipio);
                stmt.setString(4, nomeMunicipio);
                stmt.setBoolean(5, capital);
                stmt.setInt(6, populacao);

                stmt.addBatch();
                registros++;

                // Executa em lotes de 1000 para não sobrecarregar
                if (registros % 1000 == 0) {
                    stmt.executeBatch();
                }
            }

            // Executa o restante que não fechou lote
            stmt.executeBatch();
            conn.commit();

            System.out.printf("Importação concluída! %d registros inseridos.%n", registros);

        } catch (SQLException e) {
            System.err.println("Erro de SQL: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
