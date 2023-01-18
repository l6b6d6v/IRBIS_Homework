create table news (
    id serial not null,
    news varchar(255),
    source_news varchar(255),
    subject_news varchar(255),
    primary key (id));

create table users (
    id serial not null,
    login varchar(255),
    password varchar(255),
    role varchar(255),
    primary key (id));