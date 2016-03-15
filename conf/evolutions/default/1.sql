# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table roles (
  id                        bigint not null,
  name                      varchar(255),
  description               varchar(255),
  constraint pk_roles primary key (id))
;

create table users (
  id                        bigint not null,
  name                      varchar(255),
  email                     varchar(255),
  password                  varchar(255),
  remember_me               boolean,
  role_id                   bigint,
  constraint uq_users_email unique (email),
  constraint uq_users_role_id unique (role_id),
  constraint pk_users primary key (id))
;

create sequence roles_seq;

create sequence users_seq;

alter table users add constraint fk_users_role_1 foreign key (role_id) references roles (id) on delete restrict on update restrict;
create index ix_users_role_1 on users (role_id);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists roles;

drop table if exists users;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists roles_seq;

drop sequence if exists users_seq;

