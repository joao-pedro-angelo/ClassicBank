# Factory Pattern

> Os padrões factory têm como principal objetivo nos auxiliar a reduzir acoplamento em nosso software, ou seja, manter dependências flexíveis. Dessa forma as dependências deixam de ser explícitas.

Podemos definir o padrão Factory pensando justamente na sua tradução: significa “Fábrica”. Dessa forma, criamos uma classe que terá um método que retorna um objeto.

Assim, ao invés de usar o new a todo momento, fazemos isso apenas uma vez, quando estamos criando o método na classe Factory. Depois, para criar os objetos, apenas chamamos esse método de criação.


---
## Contexto

No sistema **ClassicBank**, sempre que precisarmos realizar alguma operação no banco de dados,
precisaremos de uma conexão com o DB.

A classe **ConexaoDB** é a responsável por realizar esta conexão com o banco de dados.

Sem o padrão factory, precisaremos de várias instâncias da classe **ConexaoDB**, o que gera alto acoplamento
e repetição de código.


---
## Utilizando o padrão

Sem o padrão, a classe ConexaoDB está escrita da seguinte forma:

```java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public static void main(String... args) {
    try {
        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/byte-bank?user=root&password=password");

        System.out.println("Conectado!");

        connection.close();
    } catch (SQLException e){
        System.out.println(e.getMessage());
    }
}
```

Com o padrão, a ideia é que a conexão não esteja em um método main. 
Mas sim em um método que possa retornar a conexão sempre que for invocado.


### Refatorando

```java
import java.sql.Connection;

public Connection recuperaConexao(String password){
    try {
        return DriverManager
                .getConnection("jdbc:mysql://localhost:3306/byte-bank?user=root&password="+password);
    } catch (SQLException e){
        throw new RuntimeException(e);
    }
}
```

---


### Conclusão

Agora não é preciso diversas instâncias da classe de conexão. Basta uma instância e a conexão pode ser recuperada
por meio do método **recuperaConexao()**, que pode ter ou não parâmetros.
