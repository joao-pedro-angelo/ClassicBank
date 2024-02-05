package org.example.daos;

import org.example.entities.Cliente;
import org.example.entities.Conta;

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

            this.encerraConexoes(connection, preparedStatement);
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
                Conta conta = new Conta(numeroConta, saldo, cliente);

                contas.add(conta);
            }
            this.encerraConexoes(connection, resultSet, preparedStatement);
        } catch (SQLException e){
            throw new RuntimeException(e);
        }

        return contas;
    }

    public Conta listaContaPorNumero(Integer numeroConta){
        String sql = "SELECT * FROM conta WHERE numeroConta = ?";
        Connection connection = this.conexaoDB.recuperaConexao();
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        Conta conta = null;

        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, numeroConta);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Integer numeroRecuperado = resultSet.getInt(1);
                BigDecimal saldo = resultSet.getBigDecimal(2);
                String cpf = resultSet.getString(3);

                Cliente cliente = new Cliente(cpf);
                conta = new Conta(numeroConta, saldo, cliente);
            }
            this.encerraConexoes(connection, resultSet, preparedStatement);
        } catch (SQLException e){
            throw new RuntimeException(e);
        } return conta;
    }

    private void encerraConexoes(Connection connection, PreparedStatement preparedStatement){
        try{
            connection.close();
            preparedStatement.close();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    private void encerraConexoes(Connection connection, ResultSet resultSet, PreparedStatement preparedStatement){
        try{
            connection.close();
            resultSet.close();
            preparedStatement.close();
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
}
