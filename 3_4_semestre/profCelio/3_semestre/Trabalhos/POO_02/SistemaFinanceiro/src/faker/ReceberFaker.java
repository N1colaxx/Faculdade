package faker;

import com.github.javafaker.Faker;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import model.ClienteModel;
import model.ReceberModel;

public class ReceberFaker {

    private final Faker faker = new Faker(new Locale("pt-BR"));
    private final ClienteFaker clienteFaker = new ClienteFaker();
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public ReceberModel gerarRecebimentoFalso() {

        int id = faker.number().numberBetween(1, 10000);
        int numero = faker.number().numberBetween(1000, 9999);

        // Datas com coerência lógica: emissão < vencimento <= pagamento
        Date dataEmissao = faker.date().past(10, TimeUnit.DAYS); // até 10 dias atrás
        Date dataVencimento = new Date(dataEmissao.getTime() + TimeUnit.DAYS.toMillis(faker.number().numberBetween(5, 30)));
        Date dataPagamento = new Date(dataVencimento.getTime() + TimeUnit.DAYS.toMillis(faker.number().numberBetween(0, 10)));

        String emissao = sdf.format(dataEmissao);
        String vencimento = sdf.format(dataVencimento);
        String pagamento = sdf.format(dataPagamento);

        double valor = faker.number().randomDouble(2, 100, 5000);
        double juros = faker.number().randomDouble(2, 0, 100);
        double multa = faker.number().randomDouble(2, 0, 50);
        double desconto = faker.number().randomDouble(2, 0, 100);

        double total = valor + juros + multa - desconto;

        ClienteModel cliente = clienteFaker.gerarClienteFalso();
        String notaFiscal = faker.number().digits(8);

        return new ReceberModel(
            cliente,
            notaFiscal,
            id,
            numero,
            emissao,
            vencimento,
            pagamento,
            valor,
            juros,
            multa,
            desconto,
            total
        );
    }
}
