package controller;

import java.util.Scanner;
import model.EnderecoModel;

public class EnderecoController {

    public EnderecoModel criarEndereco(String logradouro, String numero, String bairro,
            String cidade, String estado, String cep) {
        return new EnderecoModel(logradouro, numero, bairro, cidade, estado, cep);
    }
    
    public EnderecoModel criarEndereco(){
        Scanner scanner = new Scanner(System.in);
        EnderecoModel endereco = new EnderecoModel();
        
        System.out.print("Logradouro: ");
        endereco.setLogradouro(scanner.nextLine());
        
        System.out.print("Número: ");
        endereco.setNumero(scanner.nextLine());
        
        System.out.print("Bairro: ");
        endereco.setBairro(scanner.nextLine());
        
        System.out.print("Cidade: ");
        endereco.setCidade(scanner.nextLine());
        
        System.out.print("Estado: ");
        endereco.setEstado(scanner.nextLine());
        
        System.out.print("CEP: ");
        endereco.setCep(scanner.nextLine());
        
        return endereco;
    }


}
