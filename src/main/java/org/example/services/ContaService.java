package org.example.services;

import org.example.DataBase.ControllerDB;
import org.example.entities.cliente.Cliente;
import org.example.entities.cliente.ClienteDTO;
import org.example.entities.conta.Conta;
import org.example.entities.conta.ContaDTO;
import org.example.exception.RegraDeNegocioException;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class ContaService {

    private Set<Conta> contas;
    private ControllerDB controllerDB;

    public ContaService(){
        this.contas =  new HashSet<>();
        this.controllerDB = new ControllerDB();
    }

    public String listarContas(){
        StringBuilder result = new StringBuilder();
        for (Conta conta : contas){
            result.append(conta.toString());
        } return result.toString();
    }

    public BigDecimal consultarSaldo(Integer numeroConta){
        Conta conta = buscarContaPorNumero(numeroConta);
        return conta.getSaldo();
    }

    public void abrir(ContaDTO contaDTO){
        Cliente cliente = new Cliente(new ClienteDTO(contaDTO.cliente()));
        Conta conta = new Conta(contaDTO, cliente);
        if (contas.contains(conta)){
            throw new RegraDeNegocioException("Já existe conta cadastrada com esse número.");
        } contas.add(conta);

        this.controllerDB.abrirConta(conta.getNumero(), cliente.getCpf());
    }

    public void realizarSaque(Integer numeroDaConta, BigDecimal valor){
        Conta conta = buscarContaPorNumero(numeroDaConta);
        if (valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RegraDeNegocioException("Valor do saque deve ser superior a zero!");
        }

        if (valor.compareTo(conta.getSaldo()) > 0) {
            throw new RegraDeNegocioException("Saldo insuficiente!");
        }

        conta.sacar(valor);
    }

    public void realizarDeposito(Integer numeroDaConta, BigDecimal valor) {
        Conta conta = buscarContaPorNumero(numeroDaConta);
        if (valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RegraDeNegocioException("Valor do deposito deve ser superior a zero!");
        }

        conta.depositar(valor);
    }

    public void encerrar(Integer numeroDaConta) {
        Conta conta = buscarContaPorNumero(numeroDaConta);
        if (conta.possuiSaldo()) {
            throw new RegraDeNegocioException("Conta não pode ser encerrada pois ainda possui saldo!");
        }

        contas.remove(conta);
    }

    private Conta buscarContaPorNumero(Integer numero) {
        return contas
                .stream()
                .filter(c -> Objects.equals(c.getNumero(), numero))
                .findFirst()
                .orElseThrow(() -> new RegraDeNegocioException("Não existe conta cadastrada com esse número!"));
    }
}