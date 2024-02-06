package org.example.services;

import org.example.daos.ControllerDB;
import org.example.entities.Cliente;
import org.example.entities.Conta;
import org.example.exception.RegraDeNegocioException;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

public class ContaService {

    private Set<Conta> contas;
    private ControllerDB controllerDB;

    public ContaService(){
        this.contas =  new HashSet<>();
        this.controllerDB = new ControllerDB();
    }

    public String listarContas(){
        this.contas = this.controllerDB.listarTodasContas();
        if (this.contas == null) throw new RegraDeNegocioException("Não há contas cadastradas");
        StringBuilder result = new StringBuilder();

        for (Conta conta : this.contas) result.append(conta.toString());
        return result.toString();
    }

    public Conta listaContaPorNumero(Integer numeroConta){
        Conta conta = this.controllerDB.listaContaPorNumero(numeroConta);
        if (conta == null) throw new RegraDeNegocioException("Não há conta com este número");
        return conta;
    }


    public void abrir(Integer numeroConta, String cpfCliente){
        if (numeroConta <= 0 || cpfCliente.isBlank()) throw new RegraDeNegocioException("Valores inválidos!");
        this.controllerDB.abrirConta(numeroConta, new Cliente(cpfCliente));
    }

    public BigDecimal consultarSaldo(Integer numeroConta){
        Conta conta = listaContaPorNumero(numeroConta);
        if (conta == null) throw new RegraDeNegocioException("Não há conta com este número");
        return conta.getSaldo();
    }

    public void realizarSaque(Integer numeroDaConta, BigDecimal valor){
        Conta conta = this.listaContaPorNumero(numeroDaConta);
        BigDecimal valorAtual = conta.getSaldo();
        if (valor.compareTo(valorAtual) > 0) throw new RegraDeNegocioException("Saldo insuficiente.");

        this.controllerDB.alteraSaldo(numeroDaConta, valor);
    }

    public void realizarDeposito(Integer numeroDaConta, BigDecimal valor) {
        Conta conta = this.listaContaPorNumero(numeroDaConta);

        this.controllerDB.alteraSaldo(numeroDaConta, valor);
    }

    public void encerrar(Integer numeroDaConta) {

    }
}