package view;

import controller.MunicipioController;
import Model.Municipio;

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
}
