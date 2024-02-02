package org.example;

import org.example.entities.conta.ContaDTO;
import org.example.exception.RegraDeNegocioException;
import org.example.services.ContaService;

import java.math.BigDecimal;
import java.util.Scanner;

public class Main {

    private static final Scanner sc = new Scanner(System.in).useDelimiter("\n");
    private static final ContaService contaService = new ContaService();

    public static void main(String[] args){
        int opcao = exibirMenu();
        while (opcao != 7){
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
                }
            } catch (RegraDeNegocioException e){
                System.out.println("Erro: " + e.getMessage());
                System.out.println("Pressione qualquer tecla e dê enter para ir ao menu");
                sc.next();
            }
            opcao = exibirMenu();
        }
        System.out.println("Finalizando a aplicação...");
    }


    private static int exibirMenu(){
        System.out.println("""
                BYTEBANK - ESCOLHA UMA OPÇÃO:
                1 - Listar contas abertas
                2 - Abertura de conta
                3 - Encerramento de conta
                4 - Consultar saldo de uma conta
                5 - Realizar saque em uma conta
                6 - Realizar depósito em uma conta
                7 - Sair
                """);
        return Integer.parseInt(sc.nextLine());
    }


    private static void listarContas(){
        System.out.println("Contas cadastradas: ");
        System.out.println(contaService.listarContas());

        sc.next();
    }


    private static void abrirConta(){
        System.out.println("Digite o número da conta: ");
        Integer numeroConta = Integer.parseInt(sc.nextLine());

        System.out.println("Digite o CPF do cliente: ");
        String cpfTitular = sc.nextLine();

        contaService.abrir(new ContaDTO(numeroConta, cpfTitular));

        System.out.println("Conta aberta com sucesso!");
        System.out.println("Pressione qualquer tecla e dê enter para voltar ao menu principal.");
        sc.next();
    }


    private static void encerrarConta(){
        System.out.println("Digite o número da conta:");
        Integer numeroDaConta = Integer.parseInt(sc.nextLine());

        contaService.encerrar(numeroDaConta);

        System.out.println("Conta encerrada com sucesso!");
        System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu principal");
        sc.next();
    }


    private static void consultarSaldo(){
        System.out.println("Digite o número da conta:");
        Integer numeroDaConta = Integer.parseInt(sc.nextLine());
        BigDecimal saldo = contaService.consultarSaldo(numeroDaConta);
        System.out.println("Saldo da conta: " +saldo);

        System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu principal");
        sc.next();
    }


    private static void realizarSaque(){
        System.out.println("Digite o número da conta:");
        Integer numeroDaConta = Integer.parseInt(sc.nextLine());

        System.out.println("Digite o valor do saque:");
        BigDecimal valor = sc.nextBigDecimal();

        contaService.realizarSaque(numeroDaConta, valor);

        System.out.println("Saque realizado com sucesso!");
        System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu principal");
        sc.next();
    }


    private static void realizarDeposito(){
        System.out.println("Digite o número da conta:");
        Integer numeroDaConta = Integer.parseInt(sc.nextLine());

        System.out.println("Digite o valor do depósito:");
        var valor = sc.nextBigDecimal();

        contaService.realizarDeposito(numeroDaConta, valor);

        System.out.println("Depósito realizado com sucesso!");
        System.out.println("Pressione qualquer tecla e de ENTER para voltar ao menu principal");
        sc.next();
    }


}