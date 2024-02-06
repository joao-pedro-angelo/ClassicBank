package org.example.entities;

/**
 * Record que representa uma pessoa física
 *
 * @param cpf Código de Pessoa Física - Identificador de uma pessoa
 * @author carneiro.angelo.joao.pedro@gmail.com
 */
public record Cliente(String cpf) {

    /**
     * Método que compara duas pessoas. Caso tenham o mesmo CPF, então são a mesma pessoa.
     *
     * @param o Outro objeto do tipo Cliente
     * @return true - se são iguais. false - se não são iguais.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return cpf.equals(cliente.cpf);
    }

    /**
     * Representação textual de um cliente do sistema.
     * @return Representação textual de um cliente do sistema.
     */
    @Override
    public String toString() {
        return "Cliente{" +
                "cpf='" + cpf + '\'' +
                '}';
    }

}