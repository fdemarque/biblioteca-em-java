import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Main {
    private static final String NOME_ARQUIVO = "biblioteca_pessoal.txt";
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        ArrayList<Livro> biblioteca = new ArrayList<>();

        carregarBiblioteca(biblioteca);

        while(true){
            System.out.println("\n ---Biblioteca Pessoal---");
            System.out.println("1 - Listar Livros");
            System.out.println("2 - Adicionar Livro");
            System.out.println("0 - Sair");
            System.out.println("\n ---Escolha uma opção: ---");

            String opcao = scanner.nextLine();

            switch(opcao){
                case "1":
                    System.out.println("Livros na biblioteca");
                    for(Livro livro : biblioteca) {
                        System.out.println("---");
                        System.out.println("Título: " + livro.titulo);
                        System.out.println("Autor: " + livro.autor);
                        System.out.println("Ano de publicacao: " + livro.anoDePublicacao);
                    }
                    break;
                case "2":
                    System.out.println("\n digite o título: ");
                    String titulo = scanner.nextLine();
                    System.out.println("\n digite o nome do autor: ");
                    String autor = scanner.nextLine();
                    System.out.println("\n digite o ano: ");
                    int anoDePublicacao = 0;
                    try{
                        anoDePublicacao = parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Entrada de ano inválida! Aplicando valor 0 (padrão)!");
                    }

                    biblioteca.add(new Livro(titulo, autor, anoDePublicacao));
                    System.out.println("Livro adicionado!");
                    break;
                case "0":
                    salvarBiblioteca(biblioteca);
                    System.out.println("Saindo...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("\n Opção inválida. Tente novamente!");
                    break;
            }
        }
    }

    private static void salvarBiblioteca(ArrayList<Livro> biblioteca){
        try (PrintWriter writer = new PrintWriter(new FileWriter(NOME_ARQUIVO))) {
            for (Livro livro : biblioteca){
                writer.println(livro.titulo + ";" + livro.autor + ";" + livro.anoDePublicacao);
            }
        } catch (IOException e){
            System.err.println("Erro ao salvar o arquivo: " + e.getMessage());
        }
    }

    private static void carregarBiblioteca(ArrayList<Livro> biblioteca){
        File arquivo = new File(NOME_ARQUIVO);
        if (!arquivo.exists()){
            System.out.println("Nenhum arquivo encontrado! Iniciando novo arquivo...");
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(NOME_ARQUIVO))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] linhaSplit = linha.split(";");
                if (linhaSplit.length == 3) {
                    String titulo  = linhaSplit[0];
                    String autor = linhaSplit[1];
                    int anoDePublicacao = parseInt(linhaSplit[2]);
                    biblioteca.add(new Livro(titulo, autor, anoDePublicacao));
                }
            }
        } catch (IOException | NumberFormatException e){
            System.err.println("Erro ao carregar biblioteca: " + e.getMessage());
        }
    }
}