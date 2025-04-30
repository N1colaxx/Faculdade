package controller;

import java.util.Scanner;

public abstract interface InterfaceCadastro {
    Scanner scanner = new Scanner(System.in);
    public void incluir();
    public void alterarID();
    public void ConsultarPosicaoLista();
    public void consultarPorId();
    public void excluir();
}





//itfgenericaRp:
    //a. Incluir 
    //b. Alterar pelo número 
    //d. Consultar pelo Número 
    //g. Excluir pelo ID 
    //
////receber 
    //c. Consultar pelo Nome do Cliente 
    //d. Consultar pelo Número e. Consultar pelo Valor 
    //f. Consultar pela Nota Fiscal 
//
////pagar
    //c. Consultar pelo CNPJ do Fornecedor 
    //e. Consultar pelo Valor 
    //f. Consultar pelo Boleto 
