

import POJO.Livro;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Criando livros
        Livro livro1 = new Livro("Dom Casmurro", "Machado de Assis", 1899);
        Livro livro2 = new Livro("O Pequeno Príncipe", "Antoine de Saint-Exupéry", 1943);

        // Guardando em uma lista
        ArrayList<Livro> biblioteca = new ArrayList<>();
        biblioteca.add(livro1);
        biblioteca.add(livro2);

        // Exibindo os livros
        System.out.println("📚 Livros cadastrados:");
        for (Livro l : biblioteca) {
            System.out.println(l); // usa o toString do POJO
        }

        // Exemplo de acesso individual
        System.out.println("\n📖 Título do primeiro livro: " + biblioteca.get(0).getTitulo());
    }
}
