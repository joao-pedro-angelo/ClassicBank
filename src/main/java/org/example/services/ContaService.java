package org.example.services;

import org.example.daos.ControllerDB;
import org.example.entities.cliente.Cliente;
import org.example.entities.conta.Conta;
import org.example.entities.conta.ContaDTO;
import org.example.exception.RegraDeNegocioException;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        this.recuperaContas();
        StringBuilder result = new StringBuilder();

        for (Conta conta : this.contas) result.append(conta.toString());
        return result.toString();
    }

    private void recuperaContas(){
        try{
            ResultSet resultSet = this.controllerDB.listarTodasContas();

            while (resultSet.next()){
                Integer numeroConta = resultSet.getInt(1);
                BigDecimal saldo = resultSet.getBigDecimal(2);
                String cpf = resultSet.getString(3);

                Cliente cliente = new Cliente(cpf);
                ContaDTO contaDTO = new ContaDTO(numeroConta, cliente);
                Conta conta = new Conta(contaDTO);
                conta.depositar(saldo);

                this.contas.add(conta);
            }

            resultSet.close();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public BigDecimal consultarSaldo(Integer numeroConta){
        Conta conta = buscarContaPorNumero(numeroConta);
        return conta.getSaldo();
    }

    public void abrir(ContaDTO contaDTO){
        this.controllerDB.abrirConta(contaDTO.numeroConta(), contaDTO.cliente());
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