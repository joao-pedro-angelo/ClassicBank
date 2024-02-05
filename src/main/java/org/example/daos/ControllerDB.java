package org.example.daos;

import org.example.entities.cliente.Cliente;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

    public ResultSet listarTodasContas(){
        String sqlQuery = "SELECT * FROM conta";
        Connection connection = this.conexaoDB.recuperaConexao();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

            return preparedStatement.executeQuery();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

}
