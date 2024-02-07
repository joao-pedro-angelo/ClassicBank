package org.example;

import org.example.exception.RegraDeNegocioException;
import org.example.services.ContaService;

import java.math.BigDecimal;
import java.util.Scanner;

/**
 * Classe que realiza a interação com o usuário
 * Entrada e saída de dados
 *
 * @author carneiroangelojoaopedro@gmail.com
 *
 */
public class Main {

    private static final Scanner sc = new Scanner(System.in).useDelimiter("\n");
    private static final ContaService contaService = new ContaService();


    /**
     * Método main - ponto de partida de uma aplicação Java
     * <p>
     * Realiza o controle de seleção de método.
     * Os métodos invocados podem lançar a exceção RegraDeNegocioException(), que é capturada
     * por este método.
     *
     * @param args Parâmetro padrão do método main
     */
    public static void main(String[] args){
        int opcao = exibirMenu();
        while (opcao != 8){
            try{
                switch (opcao) {
                    case 1:
                        listarContas();
                        break;
                    case 2:
                        abrirConta();
                        break;
                    case 3:
                        encerrarConta();
                        break;
                    case 4:
                        consultarSaldo();
                        break;
                    case 5:
                        realizarSaque();
                        break;
                    case 6:
                        realizarDeposito();
                        break;
                    case 7:
                        consultarContaPorNumero();
                        break;
                }
            } catch (RegraDeNegocioException e){
                System.out.println("Erro: " + e.getMessage());
            }
            opcao = exibirMenu();
        }
        System.out.println("Finalizando a aplicação...");
    }


    /**
     * Exibe o menu e retorna a escolha do usuário (um inteiro).
     * <p>
     * Caso o usuário digite algo que não seja um número inteiro, a exceção será capturada
     * e o retorno será 8, que encerra a aplicação.
     *
     * @return O inteiro digitado pelo usuário. Ou 8, em caso de exceção capturada,
     * encerrando a aplicação.
     */
    private static int exibirMenu(){
        System.out.println("""
                CLASSIC BANK - ESCOLHA UMA OPÇÃO:
                1 - Listar contas abertas
                2 - Abertura de conta
                3 - Encerramento de conta
                4 - Consultar saldo de uma conta
                5 - Realizar saque em uma conta
                6 - Realizar depósito em uma conta
                7 - Consultar conta por número
                8 - Sair
                """);

        try{
            return Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e){
            System.out.println("Entrada inválida...");
            return 8;
        }
    }


    /**
     * Exibe todas as contas cadastradas no sistema.
     */
    private static void listarContas(){
        System.out.println("Contas cadastradas: ");
        System.out.println(contaService.listarContas());
        System.out.println("Tecle enter para voltar ao menu principal.");

        sc.nextLine();
    }


    /**
     * Captura as informações necessárias para a criação de uma conta.
     * Passa essas informações para outro método que é quem irá abrir a conta.
     * <p>
     * As informações serão checadas em outros métodos.
     */
    private static void abrirConta(){
        System.out.println("Digite o número da conta: ");
        Integer numeroConta = Integer.parseInt(sc.nextLine());

        System.out.println("Digite o CPF do cliente: ");
        String cpfTitular = sc.nextLine();

        contaService.abrir(numeroConta, cpfTitular);

        System.out.println("Conta aberta com sucesso!");
        System.out.println("Tecle enter para voltar ao menu principal.");

        sc.nextLine();
    }


    /**
     * Captura as informações necessárias para encerrar uma conta.
     * Passa essas informações para um outro método.
     * As validações (verificação das entradas) serão feitas em outros métodos.
     */
    private static void encerrarConta(){
        System.out.println("Digite o número da conta:");
        Integer numeroDaConta = Integer.parseInt(sc.nextLine());

        contaService.encerrar(numeroDaConta);

        System.out.println("Conta encerrada com sucesso!");
        System.out.println("Tecle enter para voltar ao menu principal");

        sc.nextLine();
    }


    /**
     * Captura as informações necessárias para consultar o saldo de uma conta.
     * Passa essas informações para um outro método.
     * Os dados serão checados (validados) em outro método.
     */
    private static void consultarSaldo(){
        System.out.println("Digite o número da conta:");
        Integer numeroDaConta = Integer.parseInt(sc.nextLine());
        BigDecimal saldo = contaService.consultarSaldo(numeroDaConta);
        System.out.println("Saldo da conta: " +saldo);

        System.out.println("Tecle enter para voltar ao menu principal");

        sc.nextLine();
    }


    /**
     * Captura as informações necessárias para realizar o saque em uma conta.
     * Esses dados são passados para outros métodos.
     * As validações são feitas em outro método.
     */
    private static void realizarSaque(){
        System.out.println("Digite o número da conta:");
        Integer numeroDaConta = Integer.parseInt(sc.nextLine());

        System.out.println("Digite o valor do saque:");
        BigDecimal valor = BigDecimal.valueOf(Integer.parseInt(sc.nextLine()));

        contaService.realizarSaque(numeroDaConta, valor);

        System.out.println("Saque realizado com sucesso!");
        System.out.println("Tecle enter para voltar ao menu principal");

        sc.nextLine();
    }


    /**
     * Captura as informações necessárias para realizar o depósito em uma conta.
     * Esses dados são passados para outros métodos.
     * As validações são feitas em outro método.
     */
    private static void realizarDeposito(){
        System.out.println("Digite o número da conta:");
        Integer numeroDaConta = Integer.parseInt(sc.nextLine());

        System.out.println("Digite o valor do depósito:");
        BigDecimal valor = BigDecimal.valueOf(Integer.parseInt(sc.nextLine()));

        contaService.realizarDeposito(numeroDaConta, valor);

        System.out.println("Depósito realizado com sucesso!");
        System.out.println("Tecle enter para voltar ao menu principal");

        sc.nextLine();
    }


    /**
     * Captura as informações necessárias para consultar uma única conta.
     * Os dados são passados e validados em outro método.
     */
    private static void consultarContaPorNumero(){
        System.out.println("Digite o número da conta:");
        Integer numeroDaConta = Integer.parseInt(sc.nextLine());

        System.out.println(contaService.listaContaPorNumero(numeroDaConta).toString());

        System.out.println("\n" + "Tecle enter para voltar ao menu principal");

        sc.nextLine();
    }
}