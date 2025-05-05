package controller;

import java.util.Scanner;
import model.TelefoneModel;

public class TelefoneController {

    public TelefoneModel criarTelefone(String ddd, String numero) {
        return new TelefoneModel(ddd, numero);
    }

    
    public TelefoneModel criarTelefone() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("DDD: ");
        String ddd = scanner.nextLine();

        System.out.print("Número: ");
        String numero = scanner.nextLine();

        return new TelefoneModel(ddd, numero);
    }
    
    
    public String formatarTelefone(TelefoneModel telefone) {
        return String.format("(%s) %s", telefone.getDDD(), telefone.getNumero());
    }
}
