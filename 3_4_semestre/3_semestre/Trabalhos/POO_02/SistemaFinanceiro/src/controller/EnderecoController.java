package controller;

import java.util.Scanner;
import model.EnderecoModel;

public class EnderecoController {
    
    // Implementação do Singleton
    private static EnderecoController instancia;
    private Scanner scanner = new Scanner(System.in);
    
    // Construtor privado
    private EnderecoController() {}
    
    // Método estático para obter a instância única
    public static EnderecoController getInstancia() {
        if (instancia == null) {
            instancia = new EnderecoController();
        }
        return instancia;
    }
    
    
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
        endereco.setCep(scanner.nextInt());
        
        return endereco;
    }
}