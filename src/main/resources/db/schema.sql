create table if not exists types
(
    id   serial primary key,
    name varchar(255)
    );

create table if not exists rules
(
    id   serial primary key,
    name varchar(255)
    );

create table if not exists accidents
(
    id      serial primary key,
    address varchar(255),
    name    varchar(255),
    text    varchar(255),
    type_id INTEGER REFERENCES types (id)
    );

create table if not exists accident_rule
(
    accident_id INTEGER REFERENCES types (id),
    rule_id    INTEGER REFERENCES rules (id)
    );

CREATE TABLE if not exists users
(
    id      serial primary key,
    username VARCHAR(50)  NOT NULL,
    password VARCHAR(100) NOT NULL,
    status  VARCHAR(20),
    role  VARCHAR(20)
    );

CREATE TABLE if not exists photos
(
    id      serial primary key,
    photo bit,
    accident_id INTEGER REFERENCES types (id)
    );