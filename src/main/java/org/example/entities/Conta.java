package org.example.entities;

import java.math.BigDecimal;
import java.util.Objects;

public class Conta {

    private Integer numeroConta;
    private BigDecimal saldo;
    private Cliente cliente;

    public Conta(Integer numeroConta, BigDecimal saldo, Cliente cliente){
        this.numeroConta = numeroConta;
        this.saldo = saldo;
        this.cliente = cliente;
    }

    public Conta(Integer numeroConta, Cliente cliente){
        this(numeroConta, BigDecimal.ZERO, cliente);
    }

    public boolean possuiSaldo(){
        return this.saldo.compareTo(BigDecimal.ZERO) != 0;
    }

    public void sacar(BigDecimal valor){
        this.saldo = this.saldo.subtract(valor);
    }

    public void depositar(BigDecimal valor){
        this.saldo = this.saldo.add(valor);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Conta conta = (Conta) o;
        return numeroConta.equals(conta.numeroConta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numeroConta);
    }

    @Override
    public String toString() {
        return "Conta{" +
                "numero='" + numeroConta + '\'' +
                ", saldo=" + saldo +
                ", titular=" + cliente +
                '}' + "\n";
    }

    public Integer getNumero() {
        return numeroConta;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public Cliente getTitular() {
        return cliente;
    }
}