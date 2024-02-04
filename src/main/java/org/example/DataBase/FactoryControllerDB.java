package org.example.DataBase;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FactoryControllerDB {

    private FactoryConexaoDB conexaoDB;

    public FactoryControllerDB(){
        this.conexaoDB = new FactoryConexaoDB();
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
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

}
