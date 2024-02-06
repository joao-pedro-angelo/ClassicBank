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

