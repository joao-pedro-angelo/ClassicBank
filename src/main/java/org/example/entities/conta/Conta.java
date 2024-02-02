package org.example.entities.conta;

import java.math.BigDecimal;
import java.util.Objects;

public class Conta {

    private Integer numeroConta;
    private BigDecimal saldo;
    private String cpfTitular;

    public Conta(ContaDTO contaDTO){
        this.numeroConta = contaDTO.numeroConta();
        this.cpfTitular = contaDTO.cpfTitular();
        this.saldo = BigDecimal.ZERO;
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
                ", titular=" + cpfTitular +
                '}';
    }

    public Integer getNumero() {
        return numeroConta;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public String getTitular() {
        return cpfTitular;
    }
}