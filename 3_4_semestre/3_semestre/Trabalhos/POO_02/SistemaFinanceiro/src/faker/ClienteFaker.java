package faker;

import com.github.javafaker.Faker;
import java.util.Locale;
import model.ClienteModel;
import model.EnderecoModel;
import model.TelefoneModel;

public class ClienteFaker {
    
    private final Faker faker = new Faker(new Locale("pt-BR"));
    
    public ClienteModel gerarClienteFalso() {
        
        int id = faker.number().numberBetween(1, 1000);
        String nome = faker.company().name();
        String email = faker.internet().emailAddress();
        
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
        
        String cnpj = faker.number().digits(14);
        String inscricaoEstadual = faker.number().digits(9);
        String contato = faker.name().fullName();
        
        double limiteCredito = faker.number().randomDouble(2, 1000, 10000);
        
        return new ClienteModel(
            limiteCredito,
            endereco, // enderecoCobranca e endereco são o mesmo
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
