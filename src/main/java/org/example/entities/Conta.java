package org.example.entities;

import java.math.BigDecimal;
import java.util.Objects;

public record Conta(Integer numeroConta, BigDecimal saldo, Cliente cliente) {

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


    /**
     * Método que compara duas contas. O número da conta é o identificador.
     * @param o A outra conta que está sendo comparada
     * @return true - se são a mesma conta. false - caso contrário.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Conta contaRecord = (Conta) o;
        return numeroConta.equals(contaRecord.numeroConta);
    }


    /**
     * A representação de uma conta utiliza o atributo "numeroConta" como ID único
     * @return O valor que identifica esta conta.
     */
    @Override
    public int hashCode() {
        return Objects.hash(numeroConta);
    }
}
