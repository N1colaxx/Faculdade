package model;

public class FornecedorModel extends PessoaModel {
    private int FOR_CODIGO;
    private String FOR_CONTATO;

    public FornecedorModel() {}

    public FornecedorModel(
            // Pessoa
            int PES_CODIGO, String PES_NOME, String PES_FANTAZIA, String PES_FISICA, String PES_CPFCNPJ,
            String PES_RGIE, java.time.LocalDate PES_CADASTRO, String PES_ENDERECO, String PES_NUMERO, String PES_COMPLEMENTO, String PES_BAIRRO,
            String PES_CIDADE, String PES_UF, String PES_CEP, String PES_FONE1, String PES_FONE2,
            String PES_CELULAR, String PES_SITE, String PES_EMAIL, String PES_ATIVO,
            // Fornecedor
            int FOR_CODIGO, String FOR_CONTATO
    ) {
        super(
            PES_CODIGO, PES_NOME, PES_FANTAZIA, PES_FISICA, PES_CPFCNPJ,
            PES_RGIE, PES_CADASTRO, PES_ENDERECO, PES_NUMERO, PES_COMPLEMENTO, PES_BAIRRO,
            PES_CIDADE, PES_UF, PES_CEP, PES_FONE1, PES_FONE2,
            PES_CELULAR, PES_SITE, PES_EMAIL, PES_ATIVO
        );
        this.FOR_CODIGO = FOR_CODIGO;
        this.FOR_CONTATO = FOR_CONTATO;
    }

    public int getFOR_CODIGO() { return FOR_CODIGO; }
    public void setFOR_CODIGO(int FOR_CODIGO) { this.FOR_CODIGO = FOR_CODIGO; }

    public String getFOR_CONTATO() { return FOR_CONTATO; }
    public void setFOR_CONTATO(String FOR_CONTATO) { this.FOR_CONTATO = FOR_CONTATO; }
}
