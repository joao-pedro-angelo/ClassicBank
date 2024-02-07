package org.example.services;

import org.example.daos.ControllerDB;
import org.example.entities.Cliente;
import org.example.entities.Conta;
import org.example.exception.RegraDeNegocioException;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * A classe {@code ContaService} fornece serviços relacionados à manipulação de contas bancárias.
 * Ela permite abrir, listar, consultar saldo, realizar saques, depósitos e encerrar contas.
 * <p>
 * Esta classe é uma parte do sistema de serviços bancários e utiliza um {@code ControllerDB} para
 * interagir com o banco de dados para operações de CRUD.
 * </p>
 *
 * @author carneiroangelojoaopedro@gmail.com
 * @version 1.0
 */
public class ContaService {

    private Set<Conta> contas;
    private final ControllerDB controllerDB;

    /**
     * Construtor padrão da classe {@code ContaService}.
     * Inicializa o conjunto de contas e o controlador do banco de dados.
     */
    public ContaService(){
        this.contas =  new HashSet<>();
        this.controllerDB = new ControllerDB();
    }

    /**
     * Lista todas as contas cadastradas.
     *
     * @return uma string contendo as informações de todas as contas
     * @throws RegraDeNegocioException se não houver contas cadastradas
     */
    public String listarContas(){
        this.contas = this.controllerDB.listarTodasContas();
        StringBuilder result = new StringBuilder();
        for (Conta conta : this.contas) result.append(conta.toString());
        return result.toString();
    }

    /**
     * Retorna uma conta com base no número da conta fornecido.
     *
     * @param numeroConta o número da conta a ser pesquisada
     * @return a conta correspondente ao número fornecido
     * @throws RegraDeNegocioException se não houver conta com o número fornecido
     */
    public Conta listaContaPorNumero(Integer numeroConta){
        Conta conta = this.controllerDB.listaContaPorNumero(numeroConta);
        if (conta == null) throw new RegraDeNegocioException("Não há conta com este número");
        return conta;
    }

    /**
     * Abre uma nova conta para um cliente com base no número da conta e no CPF fornecidos.
     *
     * @param numeroConta o número da conta a ser aberta
     * @param cpfCliente o CPF do cliente associado à conta
     * @throws RegraDeNegocioException se os valores fornecidos forem inválidos
     */
    public void abrir(Integer numeroConta, String cpfCliente){
        if (numeroConta <= 0 || cpfCliente.isBlank()) throw new RegraDeNegocioException("Valores inválidos!");
        Conta conta = new Conta(numeroConta, BigDecimal.ZERO, new Cliente(cpfCliente));
        this.controllerDB.abrirConta(conta);
    }

    /**
     * Consulta o saldo de uma conta com base no número da conta fornecido.
     *
     * @param numeroConta o número da conta a ser consultada
     * @return o saldo atual da conta
     * @throws RegraDeNegocioException se não houver conta com o número fornecido
     */
    public BigDecimal consultarSaldo(Integer numeroConta){
        Conta conta = listaContaPorNumero(numeroConta);
        if (conta == null) throw new RegraDeNegocioException("Não há conta com este número");
        return conta.saldo();
    }

    /**
     * Realiza um saque em uma conta com base no número da conta e no valor fornecidos.
     *
     * @param numeroDaConta o número da conta de onde o saque será feito
     * @param valor o valor a ser sacado
     * @throws RegraDeNegocioException se não houver saldo suficiente na conta
     */
    public void realizarSaque(Integer numeroDaConta, BigDecimal valor){
        Conta conta = this.listaContaPorNumero(numeroDaConta);
        BigDecimal valorAtual = conta.saldo();
        if (valor.compareTo(valorAtual) > 0) throw new RegraDeNegocioException("Saldo insuficiente.");

        this.controllerDB.alteraSaldo(conta, valor);
    }

    /**
     * Realiza um depósito em uma conta com base no número da conta e no valor fornecidos.
     *
     * @param numeroDaConta o número da conta onde o depósito será feito
     * @param valor o valor a ser depositado
     */
    public void realizarDeposito(Integer numeroDaConta, BigDecimal valor) {
        Conta conta = this.listaContaPorNumero(numeroDaConta);
        this.controllerDB.alteraSaldo(conta, valor);
    }

    /**
     * Encerra uma conta com base no número da conta fornecido.
     *
     * @param numeroDaConta o número da conta a ser encerrada
     */
    public void encerrar(Integer numeroDaConta) {
        Conta conta = this.listaContaPorNumero(numeroDaConta);
        this.controllerDB.removeConta(conta);
    }
}
