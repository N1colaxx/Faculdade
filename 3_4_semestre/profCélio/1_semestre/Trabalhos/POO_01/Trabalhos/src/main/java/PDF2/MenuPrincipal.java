/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PDF2;

import java.util.Scanner;

/**
 *
 * @author Nicolas
 */
public class MenuPrincipal {
    private Cliente cliente;
    private Fornecedor fornecedor;
    private Funcionario funcionario;
    private Receber receber;
    private Pagar pagar;

    private Scanner scanner;
    int opc_menu; // Menu Principal
    int opc_sub;

    //Construtor
    public MenuPrincipal() {
        cliente = null;
        fornecedor = null;
        funcionario = null;
        receber = null;
        pagar = null;
        
        scanner = new Scanner(System.in);
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public Receber getReceber() {
        return receber;
    }

    public Pagar getPagar() {
        return pagar;
    }

    //Setters 
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public void setReceber(Receber receber) {
        this.receber = receber;
    }

    public void setPagar(Pagar pagar) {
        this.pagar = pagar;
    }

    public void menu()  {
        do {
            System.out.println("\n \n \n");
            System.out.println("|===============================================|");
            System.out.println("|           Menu Prinicipal                     |");
            System.out.println("|===============================================|");
            System.out.println("""
            |   1 -  Funcionario                            |
            |   2 -  Cliente                                |
            |   3 -  Fornecedores                           |
            |   4 -  Contas a Receber                       |
            |   5 -  Contas a Pagar                         |
            |   6 -  Fluxo de caixa                         |       
            |   7 -  seir                                   |    
            |===============================================|""");
            System.out.print("| Digite aqui: ");

            if (scanner.hasNextInt()) {
                opc_menu = scanner.nextInt();
                scanner.nextLine();

                switch (opc_menu) {
                    case 1:
                        System.out.println("\n| Vc escolheu Opcao 1 - Funcionario");
                        subMenu();
                        break;
                    case 2:
                        System.out.println("\n\n| Vc escolheu Opcao 2 - Cliente");
                        subMenu();
                        break;
                    case 3:
                        System.out.println("\n\n| Vc escolheu Opcao 3 - Fornecedores");
                        subMenu();
                        break;
                    case 4:
                        System.out.println("\n\n| Vc escolhu Opcao  4 - Contas a Receber");
                        subMenu();
                        break;
                    case 5:
                        System.out.println("\n\n| Vc escolheu Opcao 5 - Contas a Pagar");
                        subMenu();
                        break;
                    case 6:
                        System.out.println("\n\n| Vc escolheu Opcao 6 - Fluxo de caixa");
                        System.out.println("| Mostrando fluxo.....\n \n");
                        fluxoCaixa();
                        break;
                    case 7:                    
                        System.out.println("\n\n| Vc escolheu Opcao 7 - Sair");
                        System.out.println("| Sa11indo do progama........");
                        scanner.nextLine();
                        break;
                    default:
                        System.out.println("\nERRO: Opção INVALIDA!!! Digigite novamente\n");
                }
            } else {
                System.out.println("\nERRO: Texo do Menu Princial, INVALIDO!!! Digite novamente\n");
                scanner.nextLine();
            }
        } while (opc_menu != 7); // vai para quando poc igual 7   
    }

    private void textoSub() {
        System.out.println("|            Agora vc pode:                     |");
        System.out.println("|-----------------------------------------------|");
        System.out.println("""
        |       1 - Incluir                        |
        |       2 - Alterar                        |
        |       3 - Consultar                      |
        |       4 - Excluir                        |
        |       0 - Voltar                         |""");
        System.out.println("|==========================================|");
    }

    public void subMenu() {
        boolean sair = true;
        
        while(sair != false){
            System.out.println("|===============================================|");
            System.out.println("|                 Sub Menu                      |");
            System.out.println("|===============================================|");
            switch (opc_menu) {

                case 1 -> textoSub(); // Funcionario
                case 2 -> textoSub(); // Cliente
                case 3 -> textoSub(); // Fornecedor
                case 4 -> textoSub(); // Contas a Receber
                case 5 -> textoSub(); // Contas a Pagar
                default -> System.out.println("OPC INVALIDA!! digite novamente");
            }

            System.out.print("| Digite aqui oque quer fazer: ");

            if (scanner.hasNextInt()) {
                opc_sub = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer

                switch (opc_sub) {
                    case 1:
                        System.out.println("\n| Você escolheu 1 - Incluir");
                        incluir(opc_menu);
                        sair = false;
                        break;
                    case 2:
                        System.out.println("\n| Você escolheu 2 - Alterar");
                        alterar(opc_menu);
                        sair = false;
                        break;
                    case 3:
                        System.out.println("\n| Você escolheu 3 - Consultar");
                        consultar(opc_menu);
                        sair = false;
                        break;
                    case 4:
                        System.out.println("\n| Você escolheu 4 - Excluir");
                        excluir(opc_menu);
                        sair = false;
                        break;
                    case 0:
                        System.out.println("\n| Você escolheu 0 - Voltar");
                        sair = false;
                        // Apenas volta para o menu principal
                        break;
                    default:
                        System.out.println("\n| Opção INVÁLIDA!! Digite novamente");
                }
            } else {
                System.out.println("\n| ERRO: Entrada inválida! Digite um número.");
                scanner.nextLine(); // Limpar o buffer
            }
        }
    }
                
    
    private void incluir(int opcMenu) {
        switch (opcMenu) {
            case 1 -> {
                // Funcionario
                System.out.println("| Incluindo novo Funcionário...");
                funcionario = new Funcionario(); // Cria um novo objeto Funcionario
                funcionario.entrar(); // Chama o método entrar() da classe Funcionario
                funcionario.imprimir();
                break;
            }

            case 2 -> {
                // Cliente
                System.out.println("\n| Incluindo novo Cliente...");
                cliente = new Cliente();
                cliente.entrar();
                cliente.imprimir();
                break;
            }

            case 3 -> {
                // Fornecedor
                System.out.println("\n| Incluindo novo Fornecedor...");
                fornecedor = new Fornecedor();
                fornecedor.entrar();
                fornecedor.imprimir();
                break;
            }

            case 4 -> {
                // Contas a Receber
                System.out.println("\n| Incluindo nova conta a receber...");
                receber = new Receber();
                receber.entrar();
                receber.imprimir();
                break;
            }

            case 5 -> {
                // Contas a Pagar
                System.out.println("\n| Incluindo nova conta a pagar...");
                pagar = new Pagar();
                pagar.entrar();
                pagar.imprimir();
                break;
            }

            default ->
                System.out.println("\n| Opção inválida para inclusão!");
        }
    }

    private void alterar(int opcMenu) {
        int res;

        switch (opcMenu) {
            case 1 -> {
                // Funcionario
                System.out.println("| Alterando Funcionário...");
                if (funcionario != null) {
                    funcionario.entrar(); // Chama o método entrar() para alterar os dados
                    System.out.println("| Funcionário alterado com sucesso!");
                    funcionario.imprimir();
                } else {
                    System.out.println("ERRO: Nenhum funcionário CADASTRADO!!!");
                    System.out.print("\nQuer Cadastrá-lo? 1 = SIM ou 2 = NÃO: ");
                    if (scanner.hasNextInt()) {
                        res = scanner.nextInt();
                        scanner.nextLine(); // Limpar o buffer

                        if (res == 1) {
                            funcionario = new Funcionario(); // Cria um novo objeto Funcionario
                            funcionario.entrar(); // Chama o método entrar() para cadastrar
                            System.out.println("| Funcionário cadastrado com sucesso!");
                            funcionario.imprimir();
                        } else {
                            System.out.println("| Operação cancelada. Voltando ao menu principal...");
                        }
                    } else {
                        System.out.println("\nERRO: Entrada inválida! Voltando ao menu principal...");
                        scanner.nextLine(); // Limpar o buffer
                    }
                }
            }

            case 2 -> {
                // Cliente
                System.out.println("\n| Alterando Cliente...");
                if (cliente != null) {
                    cliente.entrar(); // Chama o método entrar() para alterar os dados
                    System.out.println("| Cliente alterado com sucesso!");
                    cliente.imprimir();
                } else {
                    System.out.println("ERRO: Nenhum cliente CADASTRADO!!!");
                    System.out.print("\nQuer Cadastrá-lo? 1 = SIM ou 2 = NÃO: ");
                    if (scanner.hasNextInt()) {
                        res = scanner.nextInt();
                        scanner.nextLine(); // Limpar o buffer

                        if (res == 1) {
                            cliente = new Cliente(); // Cria um novo objeto Cliente
                            cliente.entrar(); // Chama o método entrar() para cadastrar
                            System.out.println("| Cliente cadastrado com sucesso!");
                            cliente.imprimir();
                        } else {
                            System.out.println("| Operação cancelada. Voltando ao menu principal...");
                        }
                    } else {
                        System.out.println("\nERRO: Entrada inválida! Voltando ao menu principal...");
                        scanner.nextLine(); // Limpar o buffer
                    }
                }
            }

            case 3 -> {
                // Fornecedor
                System.out.println("\n| Alterando Fornecedor...");
                if (fornecedor != null) {
                    fornecedor.entrar(); // Chama o método entrar() para alterar os dados
                    System.out.println("| Fornecedor alterado com sucesso!");
                    fornecedor.imprimir();
                } else {
                    System.out.println("ERRO: Nenhum fornecedor CADASTRADO!!!");
                    System.out.print("\nQuer Cadastrá-lo? 1 = SIM ou 2 = NÃO: ");
                    if (scanner.hasNextInt()) {
                        res = scanner.nextInt();
                        scanner.nextLine(); // Limpar o buffer

                        if (res == 1) {
                            fornecedor = new Fornecedor(); // Cria um novo objeto Fornecedor
                            fornecedor.entrar(); // Chama o método entrar() para cadastrar
                            System.out.println("| Fornecedor cadastrado com sucesso!");
                            fornecedor.imprimir();
                        } else {
                            System.out.println("| Operação cancelada. Voltando ao menu principal...");
                        }
                    } else {
                        System.out.println("\nERRO: Entrada inválida! Voltando ao menu principal...");
                        scanner.nextLine(); // Limpar o buffer
                    }
                }
            }

            case 4 -> {
                // Contas a Receber
                System.out.println("\n| Alterando Conta a Receber...");
                if (receber != null) {
                    receber.entrar(); // Chama o método entrar() para alterar os dados
                    System.out.println("| Conta a receber alterada com sucesso!");
                    receber.imprimir();
                } else {
                    System.out.println("ERRO: Nenhuma conta a receber CADASTRADA!!!");
                    System.out.print("\nQuer Cadastrá-la? 1 = SIM ou 2 = NÃO: ");
                    if (scanner.hasNextInt()) {
                        res = scanner.nextInt();
                        scanner.nextLine(); // Limpar o buffer

                        if (res == 1) {
                            receber = new Receber(); // Cria um novo objeto Receber
                            receber.entrar(); // Chama o método entrar() para cadastrar
                            System.out.println("| Conta a receber cadastrada com sucesso!");
                            receber.imprimir();
                        } else {
                            System.out.println("| Operação cancelada. Voltando ao menu principal...");
                        }
                    } else {
                        System.out.println("\nERRO: Entrada inválida! Voltando ao menu principal...");
                        scanner.nextLine(); // Limpar o buffer
                    }
                }
            }

            case 5 -> {
                // Contas a Pagar
                System.out.println("\n| Alterando Conta a Pagar...");
                if (pagar != null) {
                    pagar.entrar(); // Chama o método entrar() para alterar os dados
                    System.out.println("| Conta a pagar alterada com sucesso!");
                    pagar.imprimir();
                } else {
                    System.out.println("ERRO: Nenhuma conta a pagar CADASTRADA!!!");
                    System.out.print("\nQuer Cadastrá-la? 1 = SIM ou 2 = NÃO: ");
                    if (scanner.hasNextInt()) {
                        res = scanner.nextInt();
                        scanner.nextLine(); // Limpar o buffer

                        if (res == 1) {
                            pagar = new Pagar(); // Cria um novo objeto Pagar
                            pagar.entrar(); // Chama o método entrar() para cadastrar
                            System.out.println("| Conta a pagar cadastrada com sucesso!");
                            pagar.imprimir();
                        } else {
                            System.out.println("| Operação cancelada. Voltando ao menu principal...");
                        }
                    } else {
                        System.out.println("\nERRO: Entrada inválida! Voltando ao menu principal...");
                        scanner.nextLine(); // Limpar o buffer
                    }
                }
            }

            default -> System.out.println("\n| Opção inválida para alteração!");
        }
    }
    
    private void consultar(int opcMenu) {
        switch (opcMenu) {
            case 1 -> {
                // Funcionario
                System.out.println("| Consultando Funcionário...");
                if (funcionario != null) {
                    funcionario.imprimir(); // Exibe os dados do funcionário
                } else {
                    System.out.println(" ERRO: Nenhum funcionário CADASTRADO!!!");
                    System.out.print("\nQuer Cadastrá-lo? 1 = SIM ou 2 = NÃO: ");
                    if (scanner.hasNextInt()) {
                        int res = scanner.nextInt();
                        scanner.nextLine(); // Limpar o buffer

                        if (res == 1) {
                            funcionario = new Funcionario(); // Cria um novo objeto Funcionario
                            funcionario.entrar(); // Chama o método entrar() para cadastrar
                            System.out.println("| Funcionário cadastrado com sucesso!");
                            funcionario.imprimir();
                        } else {
                            System.out.println("| Operação cancelada. Voltando ao menu principal...");
                        }
                    } else {
                        System.out.println("\nERRO: Entrada inválida! Voltando ao menu principal...");
                        scanner.nextLine(); // Limpar o buffer
                    }
                }
            }

            case 2 -> {
                // Cliente
                System.out.println("\n| Consultando Cliente...");
                if (cliente != null) {
                    cliente.imprimir(); // Exibe os dados do cliente
                } else {
                    System.out.println("ERRO: Nenhum cliente CADASTRADO!!!");
                    System.out.print("\nQuer Cadastrá-lo? 1 = SIM ou 2 = NÃO: ");
                    if (scanner.hasNextInt()) {
                        int res = scanner.nextInt();
                        scanner.nextLine(); // Limpar o buffer

                        if (res == 1) {
                            cliente = new Cliente(); // Cria um novo objeto Cliente
                            cliente.entrar(); // Chama o método entrar() para cadastrar
                            System.out.println("| Cliente cadastrado com sucesso!");
                            cliente.imprimir();
                        } else {
                            System.out.println("| Operação cancelada. Voltando ao menu principal...");
                        }
                    } else {
                        System.out.println("\nERRO: Entrada inválida! Voltando ao menu principal...");
                        scanner.nextLine(); // Limpar o buffer
                    }
                }
            }

            case 3 -> {
                // Fornecedor
                System.out.println("\n| Consultando Fornecedor...");
                if (fornecedor != null) {
                    fornecedor.imprimir(); // Exibe os dados do fornecedor
                } else {
                    System.out.println("ERRO: Nenhum fornecedor CADASTRADO!!!");
                    System.out.print("\nQuer Cadastrá-lo? 1 = SIM ou 2 = NÃO: ");
                    if (scanner.hasNextInt()) {
                        int res = scanner.nextInt();
                        scanner.nextLine(); // Limpar o buffer

                        if (res == 1) {
                            fornecedor = new Fornecedor(); // Cria um novo objeto Fornecedor
                            fornecedor.entrar(); // Chama o método entrar() para cadastrar
                            System.out.println("| Fornecedor cadastrado com sucesso!");
                            fornecedor.imprimir();
                        } else {
                            System.out.println("| Operação cancelada. Voltando ao menu principal...");
                        }
                    } else {
                        System.out.println("\nERRO: Entrada inválida! Voltando ao menu principal...");
                        scanner.nextLine(); // Limpar o buffer
                    }
                }
            }

            case 4 -> {
                // Contas a Receber
                System.out.println("\n| Consultando Conta a Receber...");
                if (receber != null) {
                    receber.imprimir(); // Exibe os dados da conta a receber
                } else {
                    System.out.println("ERRO: Nenhuma conta a receber CADASTRADA!!!");
                    System.out.print("\nQuer Cadastrá-la? 1 = SIM ou 2 = NÃO: ");
                    if (scanner.hasNextInt()) {
                        int res = scanner.nextInt();
                        scanner.nextLine(); // Limpar o buffer

                        if (res == 1) {
                            receber = new Receber(); // Cria um novo objeto Receber
                            receber.entrar(); // Chama o método entrar() para cadastrar
                            System.out.println("| Conta a receber cadastrada com sucesso!");
                            receber.imprimir();
                        } else {
                            System.out.println("| Operação cancelada. Voltando ao menu principal...");
                        }
                    } else {
                        System.out.println("\nERRO: Entrada inválida! Voltando ao menu principal...");
                        scanner.nextLine(); // Limpar o buffer
                    }
                }
            }

            case 5 -> {
                // Contas a Pagar
                System.out.println("\n| Consultando Conta a Pagar...");
                if (pagar != null) {
                    pagar.imprimir(); // Exibe os dados da conta a pagar
                } else {
                    System.out.println("ERRO: Nenhuma conta a pagar CADASTRADA!!!");
                    System.out.print("\nQuer Cadastrá-la? 1 = SIM ou 2 = NÃO: ");
                    if (scanner.hasNextInt()) {
                        int res = scanner.nextInt();
                        scanner.nextLine(); // Limpar o buffer

                        if (res == 1) {
                            pagar = new Pagar(); // Cria um novo objeto Pagar
                            pagar.entrar(); // Chama o método entrar() para cadastrar
                            System.out.println("| Conta a pagar cadastrada com sucesso!");
                            pagar.imprimir();
                        } else {
                            System.out.println("| Operação cancelada. Voltando ao menu principal...");
                        }
                    } else {
                        System.out.println("\nERRO: Entrada inválida! Voltando ao menu principal...");
                        scanner.nextLine(); // Limpar o buffer
                    }
                }
            }

            default -> System.out.println("\n| Opção inválida para consulta!");
        }
}

    private void excluir(int opcMenu) {
        switch (opcMenu) {
            case 1 -> {
                // Funcionario
                System.out.println("| Excluindo Funcionário...");
                if (funcionario != null) {
                    funcionario = null; // Remove o objeto Funcionario
                    System.out.println("| Funcionário excluído com sucesso!");
                } else {
                    System.out.println("ERRO: Nenhum funcionário CADASTRADO!!!");
                }
            }

            case 2 -> {
                // Cliente
                System.out.println("\n| Excluindo Cliente...");
                if (cliente != null) {
                    cliente = null; // Remove o objeto Cliente
                    System.out.println("| Cliente excluído com sucesso!");
                } else {
                    System.out.println("ERRO: Nenhum cliente CADASTRADO!!!");
                }
            }

            case 3 -> {
                // Fornecedor
                System.out.println("\n| Excluindo Fornecedor...");
                if (fornecedor != null) {
                    fornecedor = null; // Remove o objeto Fornecedor
                    System.out.println("| Fornecedor excluído com sucesso!");
                } else {
                    System.out.println("ERRO: Nenhum fornecedor CADASTRADO!!!");
                }
            }

            case 4 -> {
                // Contas a Receber
                System.out.println("\n| Excluindo Conta a Receber...");
                if (receber != null) {
                    receber = null; // Remove o objeto Receber
                    System.out.println("| Conta a receber excluída com sucesso!");
                } else {
                    System.out.println("ERRO: Nenhuma conta a receber CADASTRADA!!!");
                }
            }

            case 5 -> {
                // Contas a Pagar
                System.out.println("\n| Excluindo Conta a Pagar...");
                if (pagar != null) {
                    pagar = null; // Remove o objeto Pagar
                    System.out.println("| Conta a pagar excluída com sucesso!");
                } else {
                    System.out.println("ERRO: Nenhuma conta a pagar CADASTRADA!!!");
                }
            }

            default -> System.out.println("\n| Opção inválida para exclusão!");
        }
    }

  
    private void fluxoCaixa(){
        if(pagar != null && receber != null){
            // Calcular valores
            double credito = receber.getTotal();  // Contas a receber
            double debito = pagar.getTotal();    // Contas a pagar
            double saldo = debito - credito;     // Saldo final

            // Exibir a tabela formatada
            System.out.println("-------------------------------------------------");
            System.out.println("|                  Fluxo de Caixa               |");
            System.out.println("-------------------------------------------------");
            System.out.printf("| %-12s | %-9s | %-9s | %-9s |\n", "Vencimento", "Crédito", "Débito", "Saldo");
            System.out.println("-------------------------------------------------");

            // Exibir as transações individuais
//            Quebra da formatação
//            Cada "%-Xs" dentro da string formatada indica um espaço reservado para exibir um valor, onde:
//            % → Indica o início de um especificador de formato.
//            - → Alinha o texto à esquerda dentro do espaço reservado (se não houver, o alinhamento é à direita).
//            X → Define o número mínimo de caracteres para aquela coluna.
//            s → Define que aquele espaço será preenchido com uma String.
            System.out.printf("| %-12s | %-9.2f | %-9.2f | %-9.2f |\n", receber.getVencimento(), credito, 0.0, credito);
            System.out.printf("| %-12s | %-9.2f | %-9.2f | %-9.2f |\n", pagar.getVencimento(), 0.0, debito, saldo);

            // Exibir totais
            System.out.println("-------------------------------------------------");
            System.out.printf("| %-12s | %-9.2f | %-9.2f | %-9.2f |\n", "Totais", credito, debito, saldo);
            System.out.println("-------------------------------------------------");

        }else{
            System.out.println("-----------------------------------------------");
            System.out.println("             Não há dados no fluxo de caixa.   ");
            System.out.println("-----------------------------------------------");
            System.out.print("\nQuer Cadastrá-lo? 1 = SIM ou 2 = NÃO: ");
                    if (scanner.hasNextInt()) {
                        int res = scanner.nextInt();
                        scanner.nextLine(); // Limpar o buffer

                        if (res == 1) {
                            System.out.println("|\n==================================================|");
                            System.out.println("|       Cadastrando Conta a Receber                  |");
                            receber = new Receber();
                            receber.entrar();
                            System.out.println("|   Conta a Receber cadastrada com sucesso!          |");
                            receber.imprimir();
                            
                            System.out.println("|\n==================================================|");
                            System.out.println("|       Cadastrando Conta a Pagar                    |");
                            pagar = new Pagar(); 
                            pagar.entrar(); 
                            System.out.println("|  Conta a Pagar cadastradoa com sucesso!            |");
                            pagar.imprimir();

                        } else {
                            System.out.println("| Operação cancelada. Voltando ao menu principal...");
                        }
                    } else {
                        System.out.println("\nERRO: Entrada inválida! Voltando ao menu principal...");
                        scanner.nextLine(); // Limpar o buffer
                    }
        }
    }
    
    
    public static void main(String[] args) {        
        MenuPrincipal obj = new MenuPrincipal();

        obj.menu();
        obj.subMenu();
    }
}
