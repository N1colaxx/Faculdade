package controller;

import java.util.Scanner;

public abstract interface InterfaceCadastro {
    Scanner scanner = new Scanner(System.in);
    public void incluir();
    public void alterarID();
    public void ConsultarPosicaoLista();
    public void excluir();
}

