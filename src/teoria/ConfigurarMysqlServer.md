# MySQL Server e Linux

> Este sistema utiliza o MySQL Server como SGBD<br>
> Neste arquivo, você terá informações sobre como instalar e configurar o MySQL.


---
## Instalação no Linux

Para instalar o MySQL Server no Linux, siga os passos abaixo:

```shell
sudo apt-get update
```

```shell
sudo apt install mysql-server
```


---
## Configurar senha

Para acessar o mysql, será necessário ter uma senha.
Seguindo os passos abaixo, você terá como cadastrar a sua primeira senha.

```shell
sudo mysql -u root
```

```shell
ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'senha-de-root';
```

```shell
FLUSH PRIVILEGES;
```

```shell
\q
```


---
## Configurar nossa tabela de dados

Agora que já foi instalado o mysql server e a configuração da senha foi feita,
será preciso criar a base de dados que será usada para armazenar os dados desse sistema bancário.

Precisamos de uma tabela para as contas criadas.

Siga os passos abaixo:

```shell
mysql -u root -p

create database classic-bank;

use classic-bank
```

A base de dados foi criada, agora vamos
configurar a tabela, colocando as colunas e definindo restrições.

```shell
CREATE TABLE `conta` (
  `numero` int NOT NULL,
  `saldo` decimal(10,0) DEFAULT NULL,
  `cliente_nome` varchar(50) DEFAULT NULL,
  `cliente_cpf` varchar(11) DEFAULT NULL,
  `cliente_email` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`numero`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
```


---
## Conexão JDBC

A string de conexão com a base de dados, deve ter o seguinte formato:

```txt
"jdbc:mysql://localhost:3306/<nomeDaTabela>?user=<nomeDoSeuUsuário>&password=<suaSenhaDoBancoDeDados>”
```


---
Feito isso, o SGBD e o banco de dados (tabela) estarão configurados!