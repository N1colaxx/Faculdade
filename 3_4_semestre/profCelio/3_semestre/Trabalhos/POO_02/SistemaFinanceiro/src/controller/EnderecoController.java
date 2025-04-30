package controller;

import java.util.Scanner;
import model.EnderecoModel;

public class EnderecoController {

    private Scanner scanner = new Scanner(System.in);

    public EnderecoModel entrar() {
        EnderecoModel endereco = new EnderecoModel();

        System.out.println("=== Cadastro de Endereço ===");

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

    public void imprimir(EnderecoModel endereco) {
        System.out.println("=== Endereço ===");
        System.out.println("Logradouro: " + endereco.getLogradouro());
        System.out.println("Número: " + endereco.getNumero());
        System.out.println("Complemento: " + endereco.getComplemento());
        System.out.println("Bairro: " + endereco.getBairro());
        System.out.println("Cidade: " + endereco.getCidade());
        System.out.println("Estado: " + endereco.getEstado());
        System.out.println("CEP: " + endereco.getCep());
    }
}
