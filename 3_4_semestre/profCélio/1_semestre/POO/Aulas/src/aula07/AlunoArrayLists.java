package aula07;

import com.github.javafaker.Faker;
import java.util.ArrayList;
import java.util.Locale;

public class AlunoArrayLists {
    public static void main(String[] args) {
        ArrayList<Aluno> listaAluno = new ArrayList<Aluno>();
        Faker faker = new Faker(new Locale("pt-BR")); // dados brasileiros

        System.out.println("Cadastro de Alunos com Faker:\n");

        for (int i = 0; i < 6; i++) {
            String nome = faker.name().fullName();
            String ra = String.valueOf(faker.number().numberBetween(100000, 999999));
            String celular = faker.phoneNumber().cellPhone();
            String email = faker.internet().emailAddress();

            Aluno aluno = new Aluno(nome, ra, celular, email);
            listaAluno.add(aluno);
        }

        // Exibindo os alunos gerados
        for (Aluno a : listaAluno) {
            System.out.println(a);
            System.out.println("Celular: " + a.getCelular());
            System.out.println("Email: " + a.getEmail());
            System.out.println("--------------------------");
        }
    }
}
