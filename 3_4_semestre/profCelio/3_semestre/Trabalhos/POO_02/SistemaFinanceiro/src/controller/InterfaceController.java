package controller;

import java.util.Scanner;

public interface InterfaceController {

    Scanner scanner = new Scanner(System.in);

    public void incluir();

    public void alterar();

    public void ConsultarPosicao();

    public void excluir();

}
