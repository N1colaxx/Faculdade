package model;

import java.math.BigDecimal;

public class ClienteModel extends PessoaModel {
    private int CLI_CODIGO;
    private double CLI_LIMITECRED;

    public ClienteModel() {}

    public ClienteModel(
            // Pessoa
            int PES_CODIGO, String PES_NOME, String PES_FISICA, String PES_CPFCNPJ,
            String PES_RGIE, java.time.LocalDate PES_CADASTRO, String PES_ENDERECO, 
            String PES_NUMERO, String PES_COMPLEMENTO, String PES_BAIRRO,
            String PES_UF, String PES_CIDADE, String PES_CEP,
            String PES_CELULAR, String PES_SITE, String PES_EMAIL, String PES_ATIVO,
            // Cliente
            int CLI_CODIGO, double CLI_LIMITECRED
    ) {
        super(
            PES_CODIGO, PES_NOME, PES_FISICA, PES_CPFCNPJ,
            PES_RGIE, PES_CADASTRO, PES_ENDERECO, PES_NUMERO, PES_COMPLEMENTO, 
            PES_BAIRRO, PES_UF, PES_CIDADE, PES_CEP,
            PES_CELULAR, PES_SITE, PES_EMAIL, PES_ATIVO
        );
        this.CLI_CODIGO = CLI_CODIGO;
        this.CLI_LIMITECRED = CLI_LIMITECRED;
    }

    // Getters/Setters
    public int getCLI_CODIGO() { return CLI_CODIGO; }
    public void setCLI_CODIGO(int CLI_CODIGO) { this.CLI_CODIGO = CLI_CODIGO; }

    public double getCLI_LIMITECRED() { return CLI_LIMITECRED; }
    public void setCLI_LIMITECRED(double CLI_LIMITECRED) { this.CLI_LIMITECRED = CLI_LIMITECRED; }
}
