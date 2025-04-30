package faker;

import com.github.javafaker.Faker;
import java.util.Locale;
import java.util.Random;
import model.EnderecoModel;
import model.FornecedorModel;
import model.TelefoneModel;

public class FornecedorFaker {
    
    private static final Faker faker = new Faker(new Locale("pt-BR"));
    private static final Random random = new Random();
    
    public static FornecedorModel gerarFornecedorFake() {
        int id = faker.number().numberBetween(1, 9999);
        String nome = faker.company().name();
        String email = faker.internet().emailAddress();
        String cnpj = faker.number().digits(14);
        String inscricaoEstadual = faker.number().digits(12);
        String contato = faker.name().fullName();
        double limiteCompra = faker.number().randomDouble(2, 1000, 50000);
        String dataCadastro = faker.date().birthday().toString(); 
        String site = faker.internet().url();

        // Endereço
        EnderecoModel endereco = new EnderecoModel(
            faker.address().streetName(),
            String.valueOf(faker.number().numberBetween(1, 1000)),
            faker.address().secondaryAddress(),
            faker.address().cityName(),
            faker.address().city(),
            faker.address().state(),
            faker.number().numberBetween(10000000, 99999999)
        );

        // Telefone
        TelefoneModel telefone = new TelefoneModel(
            faker.number().numberBetween(11, 99),
            faker.number().numberBetween(900000000L, 999999999L)
        );

        return new FornecedorModel(
            limiteCompra,
            dataCadastro,
            site,
            cnpj,
            inscricaoEstadual,
            contato,
            id,
            nome,
            email,
            endereco,
            telefone
        );
    }
}
