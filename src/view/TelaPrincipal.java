package view;

import controller.MunicipioController;
import Model.Municipio;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class TelaPrincipal {
    private static Scanner scanner = new Scanner(System.in);
    private static MunicipioController controller = new MunicipioController();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n==== MENU ====");
            System.out.println("1 - Buscar município pelo nome");
            System.out.println("2 - Ver população total de um estado");
            System.out.println("3 - Listar todas as capitais");
            System.out.println("4 - Listar municípios com população acima de um valor");
            System.out.println("5 - Listar municípios entre dois valores de população");
            System.out.println("6 - Mostrar cidade mais populosa de cada estado (não sendo capital)");
            System.out.println("7 - Top 10 cidades mais populosas que não são capitais");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // limpar buffer

            switch (opcao) {
                case 1:
                    buscarMunicipio();
                    break;
                case 2:
                    calcularPopulacaoEstado();
                    break;
                case 3:
                    listarCapitais();
                    break;
                case 4:
                    listarPorPopulacaoMinima();
                    break;
                case 5:
                    listarPorPopulacaoEntre();
                    break;
                case 6:
                    listarMaisPopulososNaoCapitaisPorEstado();
                    break;
                case 7:
                    top10MaisPopulososNaoCapitais();
                    break;
                case 0:
                    System.out.println("Encerrando...");
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    private static void buscarMunicipio() {
        System.out.print("Digite o nome do município: ");
        String nome = scanner.nextLine();

        Municipio municipio = controller.buscarMunicipio(nome);
        if (municipio != null) {
            System.out.println("\nMunicípio encontrado:");
            System.out.println(municipio);
        } else {
            System.out.println("Município não encontrado.");
        }
    }

    private static void calcularPopulacaoEstado() {
        System.out.print("Digite o nome do estado: ");
        String estado = scanner.nextLine();

        int total = controller.calcularPopulacaoEstado(estado);
        if (total > 0) {
            System.out.printf("População total de %s: %,d habitantes%n", estado, total);
        } else {
            System.out.println("Estado não encontrado ou sem dados.");
        }
    }

    private static void listarCapitais() {
        System.out.println("\n==== Capitais do Brasil ====");
        for (Municipio m : controller.listarCapitais()) {
            System.out.println(m);
            System.out.println("---------------------------");
        }
    }

    private static void listarPorPopulacaoMinima() {
        System.out.print("Digite o valor mínimo de população: ");
        int min = scanner.nextInt();
        scanner.nextLine();

        List<Municipio> municipios = controller.listarPorPopulacaoMinima(min);
        if (municipios.isEmpty()) {
            System.out.println("Nenhum município encontrado.");
        } else {
            for (Municipio m : municipios) {
                System.out.println(m);
                System.out.println("---------------------------");
            }
        }
    }

    private static void listarPorPopulacaoEntre() {
        System.out.print("Digite o valor mínimo de população: ");
        int min = scanner.nextInt();
        System.out.print("Digite o valor máximo de população: ");
        int max = scanner.nextInt();
        scanner.nextLine();

        List<Municipio> municipios = controller.listarPorPopulacaoEntre(min, max);
        if (municipios.isEmpty()) {
            System.out.println("Nenhum município encontrado.");
        } else {
            for (Municipio m : municipios) {
                System.out.println(m);
                System.out.println("---------------------------");
            }
        }
    }

    private static void listarMaisPopulososNaoCapitaisPorEstado() {
        System.out.println("\n==== Cidades mais populosas que não são capitais ====");
        Map<String, Municipio> mapa = controller.listarMaisPopulososNaoCapitaisPorEstado();

        if (mapa.isEmpty()) {
            System.out.println("Nenhum dado encontrado.");
        } else {
            for (Map.Entry<String, Municipio> entry : mapa.entrySet()) {
                System.out.println("Estado: " + entry.getKey());
                System.out.println(entry.getValue());
                System.out.println("---------------------------");
            }
        }
    }

    private static void top10MaisPopulososNaoCapitais() {
        System.out.println("\n==== Top 10 cidades mais populosas que não são capitais ====");
        List<Municipio> lista = controller.top10MaisPopulososNaoCapitais();

        if (lista.isEmpty()) {
            System.out.println("Nenhum município encontrado.");
        } else {
            int i = 1;
            for (Municipio m : lista) {
                System.out.printf("#%d - %s%n", i++, m);
                System.out.println("---------------------------");
            }
        }
    }
}
