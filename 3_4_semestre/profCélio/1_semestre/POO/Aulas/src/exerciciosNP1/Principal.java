
package exerciciosNP1;


import com.github.javafaker.Faker;
import java.util.Locale;

public class Principal {
    public static void main(String[] args){
        Faker faker = new Faker(new Locale("pt-BR")); // ou só new Faker();

        for (int i = 0; i < 5; i++) {
            int id = faker.number().numberBetween(1, 100);
            int tipo = faker.number().numberBetween(1, 3);
            String nome = faker.name().fullName();
            String funcao = faker.job().title();
            double salario = faker.number().randomDouble(2, 2000, 10000);

            Funcionario f = new Funcionario(id, tipo, nome, salario, funcao);
            System.out.println(f);
        }
    }
}
