
package controller;

import java.util.Scanner;

public abstract interface InterfaceFinanceiro {
public Scanner scanner = new Scanner(System.in);
public void Incluir();
public void AlterarPorNumero(); 
public void ConsultarPorNumero();
public void ConsultarPorValor();
public void ExcluirPorID();     
}
