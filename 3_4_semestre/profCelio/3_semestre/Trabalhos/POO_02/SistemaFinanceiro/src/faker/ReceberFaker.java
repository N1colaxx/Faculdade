//package faker;
//
//import com.github.javafaker.Faker;
//import java.util.Locale;
//import model.ClienteModel;
//import model.ReceberModel;
//
//public class ReceberFaker {
//    
//    private final Faker faker = new Faker(new Locale("pt-BR"));
//    private final ClienteFaker clienteFaker = new ClienteFaker();
//
//    public ReceberModel gerarRecebimentoFalso() {
//        
//        int id = faker.number().numberBetween(1, 10000);
//        int numero = faker.number().numberBetween(1000, 9999);
//        String emissao = faker.date().birthday().toString();
//        String vencimento = faker.date().future(30, java.util.concurrent.TimeUnit.DAYS).toString();
//        String pagamento = faker.date().future(15, java.util.concurrent.TimeUnit.DAYS).toString();
//        
//        double valor = faker.number().randomDouble(2, 100, 5000);
//        double juros = faker.number().randomDouble(2, 0, 100);
//        double multa = faker.number().randomDouble(2, 0, 50);
//        double desconto = faker.number().randomDouble(2, 0, 100);
//        
//        double total = valor + juros + multa - desconto;
//
//        ClienteModel cliente = clienteFaker.gerarClienteFalso();
//        String notaFiscal = faker.number().digits(8);
//
//        return new ReceberModel(
//            cliente,
//            notaFiscal,
//            id,
//            numero,
//            emissao,
//            vencimento,
//            pagamento,
//            valor,
//            juros,
//            multa,
//            desconto,
//            total
//        );
//    }
//}
