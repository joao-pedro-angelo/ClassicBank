# MySQL e Terminal do Linux

> Neste arquivo, você verá como manipular a sua base de dados por meio do terminal.


---
### Acessando a base de dados:

```shell
mysql -u root -p

# Terá que inserir a senha do mysql após isso
```


---
### Determinando qual base de dados usar:

```shell
# use <nomeDaBase de Dados>
use classic_bank
```


---
### Mostrando as tabelas criadas na base de dados:

```shell
show tables;
```


---
### Selecionar todos os dados de determinada tabela:

```shell
# select * from <nomeDaTabela>;

select * from conta;
```


---
### Criar um novo usuário no MySQL:

```shell
CREATE USER 'nome_usuario'@'localhost' IDENTIFIED BY 'senha';
```


---
### Exibir informações sobre uma tabela:

```shell
DESCRIBE nome_da_tabela;
```
Este comando mostra informações sobre a estrutura da tabela, incluindo os nomes das colunas, tipos de dados e muito mais.


---
### Realizar backup de uma base de dados:

```shell
mysqldump -u nome_usuario -p nome_da_base_de_dados > nome_do_arquivo_backup.sql
```
Este comando cria um backup completo da base de dados especificada em um arquivo SQL.


---
### Restaurar uma base de dados a partir de um backup:

```shell
mysql -u nome_usuario -p nome_da_base_de_dados < nome_do_arquivo_backup.sql
```
Este comando restaura a base de dados a partir do arquivo de backup SQL fornecido.
