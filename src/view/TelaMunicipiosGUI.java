package view;

import controller.MunicipioController;
import Model.Municipio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Map;

public class TelaMunicipiosGUI extends JFrame {
    private MunicipioController controller = new MunicipioController();

    private JTextField campoNomeMunicipio;
    private JTextField campoEstado;
    private JTextField campoMinPop;
    private JTextField campoMaxPop;
    private JTextArea resultadoArea;

    public TelaMunicipiosGUI() {
        setTitle("Consulta de Municípios Brasileiros");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane abas = new JTabbedPane();

        abas.addTab("Buscar por Nome", criarPainelBuscaNome());
        abas.addTab("População por Estado", criarPainelPopulacaoEstado());
        abas.addTab("Listar Capitais", criarPainelCapitais());
        abas.addTab("População Mínima", criarPainelPopMin());
        abas.addTab("População Entre Valores", criarPainelPopEntre());
        abas.addTab("Mais Populosa (Não Capitais)", criarPainelMaisPopNaoCapitais());
        abas.addTab("Top 10 Não Capitais", criarPainelTop10NaoCapitais());

        resultadoArea = new JTextArea();
        resultadoArea.setEditable(false);
        JScrollPane scroll = new JScrollPane(resultadoArea);

        add(abas, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
    }

    private JPanel criarPainelBuscaNome() {
        JPanel panel = new JPanel(new FlowLayout());
        campoNomeMunicipio = new JTextField(20);
        JButton btnBuscar = new JButton("Buscar");

        btnBuscar.addActionListener((ActionEvent e) -> {
            Municipio m = controller.buscarMunicipio(campoNomeMunicipio.getText());
            if (m != null) resultadoArea.setText(m.toString());
            else resultadoArea.setText("Município não encontrado.");
        });

        panel.add(new JLabel("Nome do Município:"));
        panel.add(campoNomeMunicipio);
        panel.add(btnBuscar);
        return panel;
    }

    private JPanel criarPainelPopulacaoEstado() {
        JPanel panel = new JPanel(new FlowLayout());
        campoEstado = new JTextField(10);
        JButton btnCalcular = new JButton("Calcular");

        btnCalcular.addActionListener((ActionEvent e) -> {
            int total = controller.calcularPopulacaoEstado(campoEstado.getText());
            resultadoArea.setText("População total: " + total);
        });

        panel.add(new JLabel("Estado:"));
        panel.add(campoEstado);
        panel.add(btnCalcular);
        return panel;
    }

    private JPanel criarPainelCapitais() {
        JPanel panel = new JPanel(new FlowLayout());
        JButton btnListar = new JButton("Listar Capitais");

        btnListar.addActionListener((ActionEvent e) -> {
            List<Municipio> lista = controller.listarCapitais();
            mostrarLista(lista);
        });

        panel.add(btnListar);
        return panel;
    }

    private JPanel criarPainelPopMin() {
        JPanel panel = new JPanel(new FlowLayout());
        campoMinPop = new JTextField(10);
        JButton btnListar = new JButton("Listar");

        btnListar.addActionListener((ActionEvent e) -> {
            int min = Integer.parseInt(campoMinPop.getText());
            List<Municipio> lista = controller.listarPorPopulacaoMinima(min);
            mostrarLista(lista);
        });

        panel.add(new JLabel("População mínima:"));
        panel.add(campoMinPop);
        panel.add(btnListar);
        return panel;
    }

    private JPanel criarPainelPopEntre() {
        JPanel panel = new JPanel(new FlowLayout());
        campoMinPop = new JTextField(10);
        campoMaxPop = new JTextField(10);
        JButton btnListar = new JButton("Listar");

        btnListar.addActionListener((ActionEvent e) -> {
            int min = Integer.parseInt(campoMinPop.getText());
            int max = Integer.parseInt(campoMaxPop.getText());
            List<Municipio> lista = controller.listarPorPopulacaoEntre(min, max);
            mostrarLista(lista);
        });

        panel.add(new JLabel("População mínima:"));
        panel.add(campoMinPop);
        panel.add(new JLabel("máxima:"));
        panel.add(campoMaxPop);
        panel.add(btnListar);
        return panel;
    }

    private JPanel criarPainelMaisPopNaoCapitais() {
        JPanel panel = new JPanel(new FlowLayout());
        JButton btnListar = new JButton("Listar por Estado");

        btnListar.addActionListener((ActionEvent e) -> {
            Map<String, Municipio> mapa = controller.listarMaisPopulososNaoCapitaisPorEstado();
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, Municipio> entry : mapa.entrySet()) {
                sb.append("Estado: ").append(entry.getKey()).append("\n").append(entry.getValue()).append("\n\n");
            }
            resultadoArea.setText(sb.toString());
        });

        panel.add(btnListar);
        return panel;
    }

    private JPanel criarPainelTop10NaoCapitais() {
        JPanel panel = new JPanel(new FlowLayout());
        JButton btnListar = new JButton("Listar Top 10");

        btnListar.addActionListener((ActionEvent e) -> {
            List<Municipio> lista = controller.top10MaisPopulososNaoCapitais();
            StringBuilder sb = new StringBuilder();
            int i = 1;
            for (Municipio m : lista) {
                sb.append("#").append(i++).append(" - ").append(m).append("\n\n");
            }
            resultadoArea.setText(sb.toString());
        });

        panel.add(btnListar);
        return panel;
    }

    private void mostrarLista(List<Municipio> lista) {
        if (lista.isEmpty()) {
            resultadoArea.setText("Nenhum município encontrado.");
        } else {
            StringBuilder sb = new StringBuilder();
            for (Municipio m : lista) {
                sb.append(m.toString()).append("\n\n");
            }
            resultadoArea.setText(sb.toString());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TelaMunicipiosGUI().setVisible(true);
        });
    }
}
