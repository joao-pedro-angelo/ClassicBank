package org.example.entities;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Classe que representa uma Conta.
 * Uma conta pode ser criada por um cliente do ClassicBank.
 * A conta contém o número da mesma (que a identifica e é único). Contém o saldo e o cliente
 * que a criou.
 *
 * @author carneiroangelojoaopedro@gmail.com
 */
public class Conta {

    private final Integer numeroConta;
    private BigDecimal saldo;
    private final Cliente cliente;

    public Conta(Integer numeroConta, BigDecimal saldo, Cliente cliente){
        this.numeroConta = numeroConta;
        this.saldo = saldo;
        this.cliente = cliente;
    }

    public Conta(Integer numeroConta, Cliente cliente){
        this(numeroConta, BigDecimal.ZERO, cliente);
    }


    /**
     * Verifica se a conta possuiSaldo.
     * @return true - se o saldo for diferente de 0. false, caso contrário.
     */
    public boolean possuiSaldo(){
        return this.saldo.compareTo(BigDecimal.ZERO) != 0;
    }


    /**
     * Método que compara duas contas. O número da conta é o identificador.
     * @param o A outra conta que está sendo comparada
     * @return true - se são a mesma conta. false - caso contrário.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Conta conta = (Conta) o;
        return numeroConta.equals(conta.numeroConta);
    }


    /**
     * A representação de uma conta utiliza o atributo "numeroConta" como ID único
     * @return O valor que identifica esta conta.
     */
    @Override
    public int hashCode() {
        return Objects.hash(numeroConta);
    }


    /**
     * Representação textual de uma conta.
     * @return Representação textual de uma conta.
     */
    @Override
    public String toString() {
        return "Conta{" +
                "numero='" + numeroConta + '\'' +
                ", saldo=" + saldo +
                ", titular=" + cliente +
                '}' + "\n";
    }


    // Métodos acessadores
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