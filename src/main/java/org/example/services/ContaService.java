package org.example.services;

import org.example.daos.ControllerDB;
import org.example.entities.cliente.Cliente;
import org.example.entities.conta.Conta;

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
        StringBuilder result = new StringBuilder();

        for (Conta conta : this.contas) result.append(conta.toString());
        return result.toString();
    }

    public Conta listaContaPorNumero(Integer numeroConta){
        return this.controllerDB.listaContaPorNumero(numeroConta);
    }


    public void abrir(Integer numeroConta, String cpfCliente){
        this.controllerDB.abrirConta(numeroConta, new Cliente(cpfCliente));
    }

    public BigDecimal consultarSaldo(Integer numeroConta){
        Conta conta = listaContaPorNumero(numeroConta);
        return conta.getSaldo();
    }

    public void realizarSaque(Integer numeroDaConta, BigDecimal valor){

    }

    public void realizarDeposito(Integer numeroDaConta, BigDecimal valor) {

    }

    public void encerrar(Integer numeroDaConta) {

    }
}