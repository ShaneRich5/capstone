# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table role (
  id                        bigint not null,
  name                      varchar(255),
  description               varchar(255),
  constraint pk_role primary key (id))
;

create table user (
  id                        bigint not null,
  username                  varchar(255),
  password                  varchar(255),
  constraint pk_user primary key (id))
;

create sequence role_seq;

create sequence user_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists role;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists role_seq;

drop sequence if exists user_seq;

