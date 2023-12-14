# Oracle Spatial

# O que é o projeto
    Consiste em medir a distância entre dois pontos geográficos ou localizar pontos próximos, 
    a partir da longitude e latitude de ambos

## Requisitos:
- [x] Java 17

## Procedimentos para Execução:
> **Instanciar o container do oracle:**
> ```shell
> docker compose up
> ```
> **Os serviços estaram operacionais quando houver a seguinte saída no console:**
> ```
> oracle  | create tablespace bancoespacial datafile '/opt/oracle/oradata/bancoespacial01.dbf' size 100M online
> oracle  | Completed: create tablespace bancoespacial datafile '/opt/oracle/oradata/bancoespacial01.dbf' size 100M online
> oracle  | create tablespace idx_bancoespacial datafile '/opt/oracle/oradata/idx_bancoespacial01.dbf' size 100M
> oracle  | Completed: create tablespace idx_bancoespacial datafile '/opt/oracle/oradata/idx_bancoespacial01.dbf' size 100M

>>### Para execução da Aplicação em ambiente local
>>**Executando aplicação Spring-boot:**
>>```shell
>>mvn spring-boot:run
>>```


 

