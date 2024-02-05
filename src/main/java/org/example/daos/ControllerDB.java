package org.example.daos;

import org.example.entities.cliente.Cliente;
import org.example.entities.conta.Conta;
import org.example.entities.conta.ContaDTO;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class ControllerDB {

    private ConexaoDB conexaoDB;

    public ControllerDB(){
        this.conexaoDB = new ConexaoDB();
    }

    public void abrirConta(Integer numeroConta, Cliente cliente){
        String sqlQuery = "INSERT INTO conta (numeroConta, saldo, cpf) " +
                "VALUES (?, ?, ?)";

        Connection connection = this.conexaoDB.recuperaConexao();

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

            preparedStatement.setInt(1, numeroConta);
            preparedStatement.setBigDecimal(2, BigDecimal.ZERO);
            preparedStatement.setString(3, cliente.getCpf());

            preparedStatement.execute();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public Set<Conta> listarTodasContas(){
        Set<Conta> contas = new HashSet<>();

        String sqlQuery = "SELECT * FROM conta";
        Connection connection = this.conexaoDB.recuperaConexao();

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Integer numeroConta = resultSet.getInt(1);
                BigDecimal saldo = resultSet.getBigDecimal(2);
                String cpf = resultSet.getString(3);

                Cliente cliente = new Cliente(cpf);
                ContaDTO contaDTO = new ContaDTO(numeroConta, cliente);
                Conta conta = new Conta(contaDTO, saldo);

                contas.add(conta);
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }

        return contas;
    }

}
