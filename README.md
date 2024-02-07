# ClassicBank

> CRUD - Sistema Bancário - API JDBC - MySQL Server<br><br>
> Integração de um sistema Java com banco de dados utilizando a API JDBC

---

## O que há no projeto:

- Simulação de Operações Bancárias
- Integração com Banco de Dados
- Factory Pattern
- Reaproveitamento de Conexões com Pool de Conexões

---

## Configuração do Ambiente

Antes de executar o projeto, certifique-se de realizar a seguinte etapa:

1. **Configuração do MySQL Server e JDBC:** Siga as instruções detalhadas no guia [Configurar MySQL e JDBC no Linux](/teoria/ConfigurarMysqlServer.md) para configurar o ambiente de desenvolvimento

---

## Teoria

1. [Sobre o Factory Pattern](/teoria/SobrePadrãoFactory.md)
2. [Comandos do MySQL Server no Terminal Linux](/teoria/MysqlServer-TerminalLinux.md)
3. [Pacote que contém as classes que interagem com o DB](https://github.com/joao-pedro-angelo/ClassicBank/tree/master/src/main/java/org/example/daos)

---

## Uso da API JDBC

Neste sistema, a API JDBC foi utilizada para realizar operações de CRUD (Create, Read, Update, Delete) no banco de dados MySQL Server. Abaixo estão alguns trechos de código da classe `ControllerDB` que ilustram como a JDBC foi utilizada:

```java
// Método para abrir uma nova conta no banco de dados
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
```
