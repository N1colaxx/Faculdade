/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aula06;

//Linguagem de Programação Orientada a Objetos

import java.util.Scanner;

// 
    // CaixaEletronicoFixo.java
    // Defina um array com o valor das cédulas (100, 50, 20,
    // 10, 5, 2 e 1), e um atributo com valor fixo de R$ 1.576,00.
    // Implemente um método que imprima a quantidade de
    // cada cédula necessária para este valor, conforme exemplo
    // abaixo:
    // Quantidade de Cédulas de 100 :15
    // Quantidade de Cédulas de 50 :1
    // Quantidade de Cédulas de 20 :1
    // Quantidade de Cédulas de 10 :0
    // Quantidade de Cédulas de 5 :1
    // Quantidade de Cédulas de 2 :0
    // Quantidade de Cédulas de 1 :1
    // Quantidade Total de Cédulas: 19
    // 
    // Declaração e instância do array:
    // int[] cedulas = { 100, 50, 20, 10, 5, 2, 1 };
    // Prof. MSc Célio Ricardo Castelano
    //Linguagem de Programação Orientada a Objetos
// 
    // CaixaEletronicoEntrada.java
    // Com base na classe anterior, crie um método em que o
    // usuário entre com o valor. Após a entrada, imprima a
    // quantidade e espécie de cada cédula.
    // Prof. MSc Célio Ricardo Castelano
    //Linguagem de Programação Orientada a Objetos
// 
    // CaixaEletronicoSaldo.java
    // Crie o seguinte menu com os respectivos métodos:
    // === Caixa Eletrônico ===
    // 1-Alimentar Caixa
    // 2- Mostrar Saldo e Cédulas
    // 3- Saque
    // 9- Voltar ao Menu Principal (classe ArraysExemplo)
// 
// 
    // Criar um atributo que receba o saldo do caixa eletrônico.
    // Acada saque, debite o saldo.
    // Apenas permita saque de valores inferiores ao saldo
public class CaixaEletronicoFixo {
    private int val_fixo = 1576;
    
    public CaixaEletronicoFixo() {
        Scanner scanner = new Scanner(System.in);
    }
    
    private void entrada(){
        int[] cedulaArray = {100, 50, 20, 10, 5, 2, 1};
        
        contarCedula();
    }
    
    private void contarCedula(){
        
    }
    
    public static void mai(String[] args){
        CaixaEletronicoFixo obj = new CaixaEletronicoFixo();
        
        obj.entrada();
//        obj.imprimir();
    }
    
}
