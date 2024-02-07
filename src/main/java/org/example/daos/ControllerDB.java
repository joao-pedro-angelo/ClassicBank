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

/**
 * A classe {@code ControllerDB} fornece métodos para interagir com o banco de dados relacionados às contas bancárias.
 * Ela realiza operações de CRUD (criação, leitura, atualização e exclusão) sobre as contas armazenadas no banco de dados.
 * <p>
 * Esta classe utiliza a classe {@code ConexaoDB} para obter conexão com o banco de dados.
 * </p>
 *
 * @author carneiroangelojoaopedro@gmail.com
 * @version 1.0
 */
public class ControllerDB {

    private ConexaoDB conexaoDB;

    /**
     * Construtor padrão da classe {@code ControllerDB}.
     * Inicializa a conexão com o banco de dados.
     */
    public ControllerDB(){
        this.conexaoDB = new ConexaoDB();
    }

    /**
     * Abre uma nova conta no banco de dados.
     *
     * @param numeroConta o número da conta a ser aberta
     * @param cliente o cliente associado à conta
     */
    public void abrirConta(Integer numeroConta, Cliente cliente){
        String sqlQuery = "INSERT INTO conta (numeroConta, saldo, cpf) " +
                "VALUES (?, ?, ?)";

        Connection connection = this.conexaoDB.recuperaConexao();

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

            preparedStatement.setInt(1, numeroConta);
            preparedStatement.setBigDecimal(2, BigDecimal.ZERO);
            preparedStatement.setString(3, cliente.cpf());

            this.encerraConexoes(connection, preparedStatement);
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    /**
     * Lista todas as contas cadastradas no banco de dados.
     *
     * @return um conjunto de contas cadastradas
     */
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

    /**
     * Retorna uma conta do banco de dados com base no número da conta fornecido.
     *
     * @param numeroConta o número da conta a ser pesquisada
     * @return a conta correspondente ao número fornecido
     */
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
                conta = new Conta(numeroRecuperado, saldo, cliente);
            }
            this.encerraConexoes(connection, resultSet, preparedStatement);
        } catch (SQLException e){
            throw new RuntimeException(e);
        } return conta;
    }

    /**
     * Altera o saldo de uma conta no banco de dados com base no número da conta fornecido.
     *
     * @param numeroConta o número da conta cujo saldo será alterado
     * @param saldo o novo saldo da conta
     */
    public void alteraSaldo(Integer numeroConta, BigDecimal saldo){
        String sqlQuery = "UPDATE conta SET saldo = ? WHERE numeroConta = ?";
        Connection connection = this.conexaoDB.recuperaConexao();

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setBigDecimal(1, saldo);
            preparedStatement.setInt(2, numeroConta);
            preparedStatement.execute();

            this.encerraConexoes(connection, preparedStatement);
        } catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    /**
     * Remove uma conta do banco de dados com base no número da conta fornecido.
     *
     * @param numeroConta o número da conta a ser removida
     */
    public void removeConta(Integer numeroConta){
        String sqlQuery = "DELETE FROM conta WHERE numeroConta = ?";
        Connection connection = this.conexaoDB.recuperaConexao();

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, numeroConta);
            preparedStatement.execute();

            this.encerraConexoes(connection, preparedStatement);
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    /**
     * Fecha a conexão com o banco de dados.
     *
     * @param connection a conexão a ser fechada
     * @param preparedStatement o statement preparado a ser fechado
     */
    private void encerraConexoes(Connection connection, PreparedStatement preparedStatement){
        try{
            connection.close();
            preparedStatement.close();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    /**
     * Fecha a conexão com o banco de dados.
     *
     * @param connection a conexão a ser fechada
     * @param resultSet o conjunto de resultados a ser fechado
     * @param preparedStatement o statement preparado a ser fechado
     */
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
