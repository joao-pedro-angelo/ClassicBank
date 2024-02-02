package org.example.conexaoDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class ConexaoDB {

    public static void main(String... x){

        Scanner sc = new Scanner(System.in);

        try{

            System.out.println("Insira a senha do banco de dados: ");
            String password = sc.nextLine();

            Connection conn = DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/byte_bank?user=root&password=" + password);

            System.out.println("Conexão feita!");

            conn.close();
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

}
