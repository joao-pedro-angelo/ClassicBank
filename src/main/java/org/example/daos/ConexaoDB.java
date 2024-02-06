package org.example.daos;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe responsável pela conexão com o banco de dados
 *
 * @author carneiro.angelo.joao.pedro@gmail.com
 */
public class ConexaoDB {

    /**
     * Método que realiza a conexão com o banco de dados
     * @return A conexão com banco de dados
     */
    public Connection recuperaConexao(){
        try{
            String password = "senha-de-root";

            return DriverManager
                    .getConnection("jdbc:mysql://localhost:3306/byte_bank?user=root&password=" + password);
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    /**
        Pool de Conexões

        Sem este pool, é inviável que dois ou mais usuários acessem o sistema ao mesmo tempo.
        Com essa ferramente, agora é possível ter um conjunto de conexões.

        Ainda é necessário que as conexões abertas sejam fechadas, porém até 10 usuários podem
        acessar ao mesmo tempo (este número de usuário concorrentes pode ser modificado).
     */
    public HikariDataSource createDataSource(){
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/byte_bank");
        config.setUsername("root");
        config.setPassword("senha-de-root");
        config.setMaximumPoolSize(10);

        return new HikariDataSource(config);
    }

}
