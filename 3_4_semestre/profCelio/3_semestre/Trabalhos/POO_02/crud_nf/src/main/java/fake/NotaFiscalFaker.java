package fake;

import com.github.javafaker.Faker;
import model.*;

import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class NotaFiscalFaker {
    private static final Faker faker = new Faker(new Locale("pt-BR"));;
    private static final Random random = new Random();


    public static NotaFiscalModel gerarNotaFiscalFake() {
        EmitenteModel emitente = new EmitenteModel(
            faker.company().name(),
            faker.number().digits(14),
            gerarEnderecoFake()
        );

        RemetenteModel remetente = new RemetenteModel(
            faker.company().name(),
            faker.number().digits(14),
            gerarEnderecoFake()
        );

        DestinatarioModel destinatario = new DestinatarioModel(
            faker.name().fullName(),
            faker.number().digits(11),
            gerarEnderecoFake()
        );


        TransportadoraModel transportadora = new TransportadoraModel(
            faker.company().name(),
            faker.number().digits(14),
            gerarEnderecoFake(),
            gerarTelefoneFake()
        );


        FaturaModel fatura = new FaturaModel(
            faker.number().digits(6),
            faker.number().randomDouble(2, 1000, 10000)
        );

        CalculoImpostoModel imposto = new CalculoImpostoModel(
            faker.number().randomDouble(2, 100, 500),   // ICMS
            faker.number().randomDouble(2, 50, 300),    // IPI
            faker.number().randomDouble(2, 10, 100),    // PIS
            faker.number().randomDouble(2, 10, 100),    // COFINS
            faker.number().randomDouble(2, 20, 200)     // ISS
        );


        NotaFiscalModel nota = new NotaFiscalModel();
        nota.setChaveAcesso(faker.number().digits(44));
        nota.setModelo(55);
        nota.setSerie(random.nextInt(100));
        nota.setNumero(faker.number().numberBetween(1000, 9999));
        nota.setDataAutorizacao(new Date());
        nota.setDataEmissao(new Date());
        nota.setDataSaidaEntrada(new Date());
        nota.setHoraSaidaEntrada("15:30:00");
        nota.setNaturezaDaOperacao("Venda de Mercadoria");
        nota.setProtocoloAutorizacao(faker.number().digits(15));
        nota.setValorTotalNf(faker.number().randomDouble(2, 1000, 10000));
        nota.setValorProdutos(faker.number().randomDouble(2, 500, 9000));
        nota.setValorServicos(faker.number().randomDouble(2, 0, 2000));
        nota.setValorDesconto(faker.number().randomDouble(2, 0, 500));
        nota.setValorOutrasDespesas(faker.number().randomDouble(2, 0, 300));
        nota.setValorFrete(faker.number().randomDouble(2, 0, 200));
        nota.setEmitente(emitente);
        nota.setRemetente(remetente);
        nota.setDestinatario(destinatario);
        nota.setTransportadora(transportadora);
        nota.setFatura(fatura);
        nota.setCalculoImposto(imposto);
        nota.setStatus(StatusModel.StatusNFE.AUTORIZADA);
        nota.setTipo(TipoNfModel.TipoNFE.SAIDA);
        nota.setMetodoGerar(TipoNfModel.MetodoGerado.FAKE);

        return nota;
    }
    
    public static NotaFiscalModel gerarNotaFiscal() {
        EmitenteModel emitente = new EmitenteModel(
            faker.company().name(),
            faker.number().digits(14),
            gerarEnderecoFake()
        );

        RemetenteModel remetente = new RemetenteModel(
            faker.company().name(),
            faker.number().digits(14),
            gerarEnderecoFake()
        );

        DestinatarioModel destinatario = new DestinatarioModel(
            faker.name().fullName(),
            faker.number().digits(11),
            gerarEnderecoFake()
        );


        TransportadoraModel transportadora = new TransportadoraModel(
            faker.company().name(),
            faker.number().digits(14),
            gerarEnderecoFake(),
            gerarTelefoneFake()
        );


        FaturaModel fatura = new FaturaModel(
            faker.number().digits(6),
            faker.number().randomDouble(2, 1000, 10000)
        );

        CalculoImpostoModel imposto = new CalculoImpostoModel(
            faker.number().randomDouble(2, 100, 500),   // ICMS
            faker.number().randomDouble(2, 50, 300),    // IPI
            faker.number().randomDouble(2, 10, 100),    // PIS
            faker.number().randomDouble(2, 10, 100),    // COFINS
            faker.number().randomDouble(2, 20, 200)     // ISS
        );


        NotaFiscalModel nota = new NotaFiscalModel();
        nota.setChaveAcesso(faker.number().digits(44));
        nota.setModelo(55);
        nota.setSerie(random.nextInt(100));
        nota.setNumero(faker.number().numberBetween(1000, 9999));
        nota.setDataAutorizacao(new Date());
        nota.setDataEmissao(new Date());
        nota.setDataSaidaEntrada(new Date());
        nota.setHoraSaidaEntrada("15:30:00");
        nota.setNaturezaDaOperacao("Venda de Mercadoria");
        nota.setProtocoloAutorizacao(faker.number().digits(15));
        nota.setValorTotalNf(faker.number().randomDouble(2, 1000, 10000));
        nota.setValorProdutos(faker.number().randomDouble(2, 500, 9000));
        nota.setValorServicos(faker.number().randomDouble(2, 0, 2000));
        nota.setValorDesconto(faker.number().randomDouble(2, 0, 500));
        nota.setValorOutrasDespesas(faker.number().randomDouble(2, 0, 300));
        nota.setValorFrete(faker.number().randomDouble(2, 0, 200));
        nota.setEmitente(emitente);
        nota.setRemetente(remetente);
        nota.setDestinatario(destinatario);
        nota.setTransportadora(transportadora);
        nota.setFatura(fatura);
        nota.setCalculoImposto(imposto);
        nota.setStatus(StatusModel.StatusNFE.AUTORIZADA);
        nota.setTipo(TipoNfModel.TipoNFE.SAIDA);
        nota.setMetodoGerar(TipoNfModel.MetodoGerado.NORMAL);

        return nota;
    }
        
    private static EnderecoModel gerarEnderecoFake() {
        return new EnderecoModel(
            faker.address().streetName(),
            faker.address().buildingNumber(),
            faker.address().cityName(),
            faker.address().city(),
            faker.address().stateAbbr(),
            faker.address().zipCode()
        );
    }
    
    public static TelefoneModel gerarTelefoneFake() {
        String celular = faker.phoneNumber().cellPhone(); // Ex: "(11) 91234-5678"
        String ddd = celular.replaceAll("\\D", "").substring(0, 2);
        String numero = celular.replaceAll("\\D", "").substring(2);
        return new TelefoneModel(ddd, numero);
    }
}
