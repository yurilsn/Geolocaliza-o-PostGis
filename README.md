# Postgis

# O que é o projeto
    Consiste em medir a distância entre dois pontos geográficos, a partir 
    da longitude e latitude de ambos

## Requisitos:
- [x] Java 17

## Procedimentos para Execução:
> **Instanciar os containers do postgresql e pgadmin:**
> ```shell
> docker compose up
> ```
> **Os serviços estaram operacionais quando houver a seguinte saída no console:**
> ```
> postgres  | PostgreSQL init process complete; ready for start up.
> ```
>### Instalar a extensão postgis
> **Executar comandos no container:**
> ```shell
> docker exec -it postgres bash
> apt update
> apt install postgresql-15-postgis-3
> psql -U nome_de_usuario -d nome_banco_de_dados
> ```
> **Entrar no CLI no postgres:**
> ```shell
> psql -U nome_de_usuario -d nome_banco_de_dados
> ```
> **Executar os seguintes comandos no CLI:**
> ```
> ALTER DATABASE gisdb SET search_path=public,postgis,contrib;
>\connect gisdb;
> CREATE SCHEMA postgis;
> CREATE EXTENSION postgis SCHEMA postgis;
> ```
> **Verificando se o postgis está instalado dentro do CLI:**
> ```
> SELECT postgis_full_version();
> ```
> 
>>### Para execução da Aplicação em ambiente local
>>**Executando aplicação Spring-boot:**
>>```shell
>>mvn spring-boot:run
>>```


 

