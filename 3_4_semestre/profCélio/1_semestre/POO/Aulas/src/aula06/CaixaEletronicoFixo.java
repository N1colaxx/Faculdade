package aula06;


public class CaixaEletronicoFixo {
    int[] cedulaArray = {100, 50, 20, 10, 5, 2, 1}; // Array com os valores das cédulas
    
    final int VALOR_FIXO = 1576; // Definindo como constante
    int qtd_cedula;
    int resto; 
    int totalCedulas = 0;  

    public CaixaEletronicoFixo(){
        this.resto = VALOR_FIXO;
    }
   
    
    public void entrada(){
        System.out.println("\n  Carregado com Sucesso!!!");
        System.out.println("\nQunatidade de notas necessarias para chegar em " + VALOR_FIXO);
       
        for (int cedula : cedulaArray) {
            qtd_cedula = resto / cedula; // Calcula quantas cédulas desse valor são necessárias
            resto = resto % cedula; // Atualiza o valor restante
            
            System.out.println("Quantidade de Cedulas de " + qtd_cedula + " : " + qtd_cedula);
            totalCedulas += qtd_cedula;
        }
        System.out.println("Quantidade Total de Cedulas: " + totalCedulas);
    }
}
