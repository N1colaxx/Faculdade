package view;

public interface InterfaceCadastro {
    public void incluir();
    public void alterar();
    public void consultar();
    public void excluir();
    public boolean validar(int operacao); //1=incluir, 2=alterar, 3=consultar, 4=excluir
    public void limparCampos();
}
