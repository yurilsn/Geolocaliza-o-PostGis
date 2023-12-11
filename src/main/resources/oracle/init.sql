connect system/oracle
alter session set "_ORACLE_SCRIPT"=true;

--**********************************
--Esquema bancoespacial
--**********************************

create tablespace bancoespacial datafile '/opt/oracle/oradata/bancoespacial01.dbf' size 100M online;
create tablespace idx_bancoespacial datafile '/opt/oracle/oradata/idx_bancoespacial01.dbf' size 100M;
create user bancoespacial identified by bancoespacial default tablespace bancoespacial temporary tablespace temp;
grant resource to bancoespacial;
grant connect to bancoespacial;
grant create view to bancoespacial;
grant create procedure to bancoespacial;
grant create job to bancoespacial;
grant create materialized view to bancoespacial;
alter user bancoespacial default role connect, resource;
