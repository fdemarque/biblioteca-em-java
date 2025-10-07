import java.util.Scanner;

public class Main {
    static void main(String[] args){
        Biblioteca biblioteca = new Biblioteca();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println(" --- Biblioteca Pessoal --- ");
            System.out.println(" 1. Listar Livros ");
            System.out.println(" 2. Adicionar Livro ");
            System.out.println(" 0. Sair ");
            System.out.println(" Escolha uma opção: ");

            String opcao = scanner.nextLine();

            switch (opcao) {
                case "1":
                    biblioteca.listarLivros();
                    break;
                case "2":
                    biblioteca.adicionarLivro(scanner);
                    break;
                case "0":
                    biblioteca.sairSalvando();
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println(" Opção inválida, tente de novo: ");
                    break;
            }
        }
    }
}