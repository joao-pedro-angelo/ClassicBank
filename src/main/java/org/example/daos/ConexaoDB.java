package org.example.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDB {

    public Connection recuperaConexao(){
        try{
            String password = "senha-de-root";

            return DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/byte_bank?user=root&password=" + password);
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

}
