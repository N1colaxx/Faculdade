package model;

public class FuncionarioModel extends PessoaFisicaModel {
    protected String dataAdmissao;
    protected String dataDemissao;
    protected String ctps;
    protected double salario;

    public FuncionarioModel() {
    }

    public FuncionarioModel(String dataAdmissao, String dataDemissao, String ctps, double salario, String cpf, String rg, String emissor, int id, String nome, String email, EnderecoModel endereco, TelefoneModel telefone) {
        super(cpf, rg, emissor, id, nome, email, endereco, telefone);
        this.dataAdmissao = dataAdmissao;
        this.dataDemissao = dataDemissao;
        this.ctps = ctps;
        this.salario = salario;
    }

    public String getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(String dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    public String getDataDemissao() {
        return dataDemissao;
    }

    public void setDataDemissao(String dataDemissao) {
        this.dataDemissao = dataDemissao;
    }

    public String getCtps() {
        return ctps;
    }

    public void setCtps(String ctps) {
        this.ctps = ctps;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
    
}
