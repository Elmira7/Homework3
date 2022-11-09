drop table if exists likes;
drop table if exists publication;
drop table if exists users cascade;

create table users (

    id bigserial primary key,
    name varchar(30) unique,
    phone varchar(11),
    mail varchar(50)

);

create table publication (

    id bigserial primary key,
    id_author bigint,
    title varchar(50),
    body text,
    date date,
    foreign key (id_author) references users(id) on update cascade on delete cascade

);

create table likes (

    id_user bigint,
    id_publication bigint,
    date date,
    foreign key (id_user) references users(id) on update cascade on delete cascade,
    foreign key (id_publication) references publication(id) on update cascade on delete cascade,
    primary key (id_user, id_publication)

);


insert into users (name, phone, mail) values ('MihalHalich', '89536764554', 'michael@mail.com'),
                                             ('pro100tas', '5654345', 'stas@mail.com'),
                                             ('MacFlury', '89435464554', 'mac@mail.com');

insert into publication (id_author, title, body, date) values (3, 'How to get rich?', 'be me', now()),
                                                              (3, 'Ate in the local canteen', 'poisoned!! urgently upgrade everything!', now());

insert into likes values (1, 1, '2022-11-01'),
                         (1, 2, '2022-11-02'),
                         (2, 1, '2022-11-01');

