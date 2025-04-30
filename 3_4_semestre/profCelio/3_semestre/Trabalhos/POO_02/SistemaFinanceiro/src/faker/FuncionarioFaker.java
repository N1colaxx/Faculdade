package faker;

import com.github.javafaker.Faker;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;
import model.EnderecoModel;
import model.FuncionarioModel;
import model.TelefoneModel;

public class FuncionarioFaker {

    private static final Faker faker = new Faker(new Locale("pt-BR"));

    public static FuncionarioModel gerarFuncionario(int id) {
        EnderecoModel endereco = new EnderecoModel(
            faker.address().streetName(),
            faker.address().buildingNumber(),
            faker.address().secondaryAddress(),
            faker.address().cityName(),
            faker.address().cityName(),
            faker.address().stateAbbr(),
            faker.number().numberBetween(10000000, 99999999)
        );

        TelefoneModel telefone = new TelefoneModel(
            faker.number().numberBetween(11, 99), // DDD
            faker.number().numberBetween(900000000L, 999999999L) // número
        );

        String nome = faker.name().fullName();
        String email = faker.internet().emailAddress();
        String cpf = faker.idNumber().valid();
        String rg = String.valueOf(faker.number().numberBetween(10000000, 99999999));
        String emissor = "SSP";

        String ctps = String.valueOf(faker.number().numberBetween(100000, 999999));
        double salario = ThreadLocalRandom.current().nextDouble(1200.0, 9000.0);

        String dataAdmissao = faker.date().past(1000, java.util.concurrent.TimeUnit.DAYS)
                                    .toInstant().toString().substring(0, 10);
        String dataDemissao = ""; 

        return new FuncionarioModel(
            dataAdmissao,
            dataDemissao,
            ctps,
            salario,
            cpf,
            rg,
            emissor,
            id,
            nome,
            email,
            endereco,
            telefone
        );
    }
}
