package org.example.DataBase;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ControllerDB {

    private ConexaoDB conexaoDB;

    public ControllerDB(){
        this.conexaoDB = new ConexaoDB();
    }

    public void abrirConta(Integer numeroConta, String cpfCliente){
        String sqlQuery = "INSERT INTO conta (numeroConta, saldo, cpf) " +
                "VALUES (?, ?, ?)";

        Connection connection = this.conexaoDB.recuperaConexao();

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

            preparedStatement.setInt(1, numeroConta);
            preparedStatement.setBigDecimal(2, BigDecimal.ZERO);
            preparedStatement.setString(3, cpfCliente);

            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

}
