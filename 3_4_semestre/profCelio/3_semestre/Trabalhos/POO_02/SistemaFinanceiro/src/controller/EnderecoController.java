package controller;

import java.util.Scanner;
import model.EnderecoModel;

public class EnderecoController {

    private Scanner scanner = new Scanner(System.in);

    public EnderecoModel entrar() {
        EnderecoModel endereco = new EnderecoModel();

        System.out.print("Logradouro: ");
        endereco.setLogradouro(scanner.nextLine());

        System.out.print("Número: ");
        endereco.setNumero(scanner.nextLine());

        System.out.print("Complemento: ");
        endereco.setComplemento(scanner.nextLine());

        System.out.print("Bairro: ");
        endereco.setBairro(scanner.nextLine());

        System.out.print("Cidade: ");
        endereco.setCidade(scanner.nextLine());

        System.out.print("Estado: ");
        endereco.setEstado(scanner.nextLine());

        System.out.print("CEP: ");
        endereco.setCep(Integer.parseInt(scanner.nextLine()));

        return endereco;
    }

}
