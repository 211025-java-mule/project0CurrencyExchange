create table if not exists currency(
    id serial primary key,
    success boolean,
    timestamp timestamp,
    base text,
    date text
);

create table if not exists rate(
    id serial primary key,
    currencyID int,
    name text,
    value decimal,
    constraint fk_currency foreign key(currencyID) references currency(id)
);


select * from currency;
insert into currency(success, timestamp, base, date) values (true, '2021-11-26T11:11:23+02:00', 'EUR', '2021-11-26');