package controller;

import java.util.Scanner;
import model.TelefoneModel;

public class TelefoneController {

    private Scanner scanner = new Scanner(System.in);

    public TelefoneModel entrar() {
        TelefoneModel telefone = new TelefoneModel();

        System.out.println("=== Cadastro de Telefone ===");

        System.out.print("DDD: ");
        telefone.setDdd(Integer.parseInt(scanner.nextLine()));

        System.out.print("Número: ");
        telefone.setNumero(Long.parseLong(scanner.nextLine()));

        return telefone;
    }

    public void imprimir(TelefoneModel telefone) {
        System.out.println("=== Telefone ===");
        System.out.println("DDD: " + telefone.getDdd());
        System.out.println("Número: " + telefone.getNumero());
    }
}
