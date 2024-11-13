package br.com.aula.conexao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class PushDB {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        System.out.println("=================================");
        System.out.println(" Bem-vindo ao Sistema de Gerenciamento de Alunos");
        System.out.println("=================================");

        // Loop do menu principal
        while (continuar) {
            // Exibindo o menu de opções
            System.out.println("\n=== Menu Principal ===");
            System.out.println("1. Inserir aluno");
            System.out.println("2. Atualizar aluno");
            System.out.println("3. Deletar aluno");
            System.out.println("4. Ler registros de alunos");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir o \n

            // Conectando ao banco de dados
            try (Connection conexao = ConexaoDB.conectar()) {
                if (conexao == null) {
                    System.err.println("Erro ao conectar ao banco de dados. Encerrando o programa.");
                    break;
                }

                switch (opcao) {
                    case 1:
                        // Inserir novo aluno
                        System.out.print("Digite o nome do aluno: ");
                        String nome = scanner.nextLine();
                        System.out.print("Digite a idade do aluno: ");
                        int idade = scanner.nextInt();
                        scanner.nextLine(); // Consumir o \n

                        try {
                            InserirDados.inserirAluno(conexao, nome, idade);
                            System.out.println("Aluno inserido com sucesso!");
                        } catch (SQLException e) {
                            System.err.println("Erro ao inserir aluno: " + e.getMessage());
                        }
                        break;

                    case 2:
                        // Atualizar aluno existente
                        System.out.print("Digite o ID do aluno a ser atualizado: ");
                        int idAtualizar = scanner.nextInt();
                        scanner.nextLine(); // Consumir o \n
                        System.out.print("Digite o novo nome do aluno: ");
                        String novoNome = scanner.nextLine();
                        System.out.print("Digite a nova idade do aluno: ");
                        int novaIdade = scanner.nextInt();
                        scanner.nextLine(); // Consumir o \n

                        try {
                            AtualizarDados.atualizarAluno(conexao, idAtualizar, novoNome, novaIdade);
                            System.out.println("Dados do aluno atualizados com sucesso!");
                        } catch (SQLException e) {
                            System.err.println("Erro ao atualizar aluno: " + e.getMessage());
                        }
                        break;

                    case 3:
                        // Deletar aluno
                        System.out.print("Digite o ID do aluno a ser deletado: ");
                        int idDeletar = scanner.nextInt();
                        scanner.nextLine(); // Consumir o \n

                        try {
                            DeletarDados.DeletarAluno(conexao, idDeletar);
                            System.out.println("Aluno deletado com sucesso!");
                        } catch (SQLException e) {
                            System.err.println("Erro ao deletar aluno: " + e.getMessage());
                        }
                        break;

                    case 4:
                        // Listar todos os alunos
                        try {
                            LerDados.lerAlunos(conexao);
                        } catch (SQLException e) {
                            System.err.println("Erro ao listar alunos: " + e.getMessage());
                        }
                        break;

                    case 0:
                        // Sair do programa
                        System.out.println("Encerrando o programa. Até logo!");
                        continuar = false;
                        break;

                    default:
                        System.err.println("Opção inválida, escolha uma opção válida.");
                        break;
                }
            } catch (SQLException e) {
                System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            }
        }

        scanner.close();
    }
}
