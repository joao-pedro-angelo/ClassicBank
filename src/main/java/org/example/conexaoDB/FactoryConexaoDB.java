package org.example.conexaoDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FactoryConexaoDB {

    public Connection recuperaConexao(String password){
        try{
            return DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/byte_bank?user=root&password=" + password);
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

}
