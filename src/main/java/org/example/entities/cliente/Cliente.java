package org.example.entities.cliente;

import java.util.Objects;

public class Cliente {
    private String cpf;

    public Cliente(ClienteDTO clienteDTO){
        this.cpf = clienteDTO.cpf();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return cpf.equals(cliente.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "cpf='" + cpf + '\'' +
                '}';
    }

    public String getCpf() {
        return cpf;
    }

}