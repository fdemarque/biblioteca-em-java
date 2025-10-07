import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Biblioteca {
    private final String NOME_ARQUIVO = "biblioteca_pessoal.txt";
    ArrayList<Livro> biblioteca = new ArrayList<>();

    public Biblioteca() {
        carregarBiblioteca();
    }

    public void listarLivros() {
        System.out.println("\n --- Livros na Biblioteca ---");
        if (biblioteca.isEmpty()) {
            System.out.println("Nenhum livro encontrado");
        } else  {
            for (Livro livro : biblioteca) {
                System.out.println("---");
                System.out.println("Título: " + livro.titulo);
                System.out.println("Autor: " + livro.autor);
                System.out.println("Ano de Publicação: " + livro.anoDePublicacao);
            }
        }
    }

    public void adicionarLivro(Scanner scanner) {
        System.out.println("Título do livro:");
        String titulo = scanner.nextLine();
        System.out.println("Autor:");
        String autor = scanner.nextLine();
        System.out.println("Ano de Publicacao:");
        int anoDePublicacao = 0;
        try {
            anoDePublicacao = parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Erro no formato, atribuindo valor padrão (0)");
        }

        this.biblioteca.add(new Livro(titulo, autor, anoDePublicacao));
        System.out.println("Livro adicionado com sucesso!");
    }

    public void sairSalvando() {
        salvarBiblioteca();
        System.out.println("Biblioteca salva com sucesso! Saindo...");
    }

    private void salvarBiblioteca(){
        try (PrintWriter writer = new PrintWriter(new FileWriter(NOME_ARQUIVO))) {
            for (Livro livro : biblioteca){
                writer.println(livro.titulo + ";" + livro.autor + ";" + livro.anoDePublicacao);
            }
        } catch (IOException e){
            System.err.println("Erro ao salvar o arquivo: " + e.getMessage());
        }
    }

    private void carregarBiblioteca(){
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
