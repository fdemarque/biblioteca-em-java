import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Biblioteca {
    private final String NOME_ARQUIVO = "biblioteca.json";
    private ArrayList<Livro> livros;

    public Biblioteca() {
        this.livros = new ArrayList<>();
        carregarBiblioteca();
    }

    public void listarLivros() {
        System.out.println("\n --- Livros na Biblioteca ---");
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro encontrado");
        } else  {
            for (Livro livro : livros) {
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

        this.livros.add(new Livro(titulo, autor, anoDePublicacao));
        System.out.println("Livro adicionado com sucesso!");
    }

    public void sairSalvando() {
        salvarBiblioteca();
        System.out.println("Biblioteca salva com sucesso! Saindo...");
    }

    private void salvarBiblioteca(){
        Gson gson  = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter writer = new FileWriter(NOME_ARQUIVO)) {
            gson.toJson(this.livros, writer);
        } catch (IOException e){
            System.err.println("Erro ao salvar biblioteca em JSON: " + e.getMessage());
        }
    }

    private void carregarBiblioteca(){
        File arquivo = new File(NOME_ARQUIVO);
        if (!arquivo.exists()){
            return;
        }

        Gson gson  = new Gson();

        try (FileReader reader = new FileReader(NOME_ARQUIVO)) {
            Type tipoLista = new TypeToken<ArrayList<Livro>>(){}.getType();
            this.livros = gson.fromJson(reader, tipoLista);
            if (this.livros == null || this.livros.isEmpty()){
                this.livros = new ArrayList<>();
            }
        } catch (IOException e){
            System.err.println("Erro ao carregar biblioteca em JSON: " + e.getMessage());
            this.livros = new ArrayList<>();
        }
    }
}
