# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table assignments (
  id                        bigint not null,
  name                      varchar(255),
  language_name             varchar(255),
  description               varchar(255),
  lecturer_id_num           varchar(255),
  course_code               varchar(255),
  path                      varchar(255) not null,
  constraint pk_assignments primary key (id))
;

create table courses (
  code                      varchar(255) not null,
  name                      varchar(255),
  description               varchar(255),
  lecturer_id_num           varchar(255),
  constraint uq_courses_name unique (name),
  constraint uq_courses_lecturer_id_num unique (lecturer_id_num),
  constraint pk_courses primary key (code))
;

create table languages (
  name                      varchar(255) not null,
  constraint pk_languages primary key (name))
;

create table roles (
  id                        integer not null,
  name                      varchar(255),
  description               varchar(255),
  constraint pk_roles primary key (id))
;

create table submissions (
  id                        bigint not null,
  grade                     double,
  path                      varchar(255),
  course_code               varchar(255),
  assignment_id             bigint,
  student_id_num            varchar(255),
  constraint pk_submissions primary key (id))
;

create table tests (
  id                        bigint not null,
  full_test                 varchar(255),
  assignment_id             bigint,
  constraint pk_tests primary key (id))
;

create table testcases (
  id                        bigint not null,
  name                      varchar(255),
  markscheme_description    varchar(255),
  mark                      integer,
  test_id                   bigint,
  code                      varchar(255),
  constraint pk_testcases primary key (id))
;

create table tokens (
  id                        integer not null,
  value                     varchar(255),
  description               varchar(255),
  user_id_num               varchar(255),
  constraint pk_tokens primary key (id))
;

create table users (
  id_num                    varchar(255) not null,
  name                      varchar(255),
  email                     varchar(255),
  remember_me               boolean,
  role_id                   integer,
  constraint pk_users primary key (id_num))
;


create table courses_users (
  courses_code                   varchar(255) not null,
  users_id_num                   varchar(255) not null,
  constraint pk_courses_users primary key (courses_code, users_id_num))
;

create table users_courses (
  users_id_num                   varchar(255) not null,
  courses_code                   varchar(255) not null,
  constraint pk_users_courses primary key (users_id_num, courses_code))
;
create sequence AS_SEQ;

create sequence courses_seq;

create sequence languages_seq;

create sequence roles_seq;

create sequence submissions_seq;

create sequence CUST_SEQ;

create sequence CASE_SEQ;

create sequence tokens_seq;

create sequence users_seq;

alter table assignments add constraint fk_assignments_language_1 foreign key (language_name) references languages (name) on delete restrict on update restrict;
create index ix_assignments_language_1 on assignments (language_name);
alter table assignments add constraint fk_assignments_lecturer_2 foreign key (lecturer_id_num) references users (id_num) on delete restrict on update restrict;
create index ix_assignments_lecturer_2 on assignments (lecturer_id_num);
alter table assignments add constraint fk_assignments_course_3 foreign key (course_code) references courses (code) on delete restrict on update restrict;
create index ix_assignments_course_3 on assignments (course_code);
alter table courses add constraint fk_courses_lecturer_4 foreign key (lecturer_id_num) references users (id_num) on delete restrict on update restrict;
create index ix_courses_lecturer_4 on courses (lecturer_id_num);
alter table submissions add constraint fk_submissions_course_5 foreign key (course_code) references courses (code) on delete restrict on update restrict;
create index ix_submissions_course_5 on submissions (course_code);
alter table submissions add constraint fk_submissions_assignment_6 foreign key (assignment_id) references assignments (id) on delete restrict on update restrict;
create index ix_submissions_assignment_6 on submissions (assignment_id);
alter table submissions add constraint fk_submissions_student_7 foreign key (student_id_num) references users (id_num) on delete restrict on update restrict;
create index ix_submissions_student_7 on submissions (student_id_num);
alter table tests add constraint fk_tests_assignment_8 foreign key (assignment_id) references assignments (id) on delete restrict on update restrict;
create index ix_tests_assignment_8 on tests (assignment_id);
alter table testcases add constraint fk_testcases_test_9 foreign key (test_id) references tests (id) on delete restrict on update restrict;
create index ix_testcases_test_9 on testcases (test_id);
alter table tokens add constraint fk_tokens_user_10 foreign key (user_id_num) references users (id_num) on delete restrict on update restrict;
create index ix_tokens_user_10 on tokens (user_id_num);
alter table users add constraint fk_users_role_11 foreign key (role_id) references roles (id) on delete restrict on update restrict;
create index ix_users_role_11 on users (role_id);



alter table courses_users add constraint fk_courses_users_courses_01 foreign key (courses_code) references courses (code) on delete restrict on update restrict;

alter table courses_users add constraint fk_courses_users_users_02 foreign key (users_id_num) references users (id_num) on delete restrict on update restrict;

alter table users_courses add constraint fk_users_courses_users_01 foreign key (users_id_num) references users (id_num) on delete restrict on update restrict;

alter table users_courses add constraint fk_users_courses_courses_02 foreign key (courses_code) references courses (code) on delete restrict on update restrict;

# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists assignments;

drop table if exists courses;

drop table if exists courses_users;

drop table if exists languages;

drop table if exists roles;

drop table if exists submissions;

drop table if exists tests;

drop table if exists testcases;

drop table if exists tokens;

drop table if exists users;

drop table if exists users_courses;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists AS_SEQ;

drop sequence if exists courses_seq;

drop sequence if exists languages_seq;

drop sequence if exists roles_seq;

drop sequence if exists submissions_seq;

drop sequence if exists CUST_SEQ;

drop sequence if exists CASE_SEQ;

drop sequence if exists tokens_seq;

drop sequence if exists users_seq;

