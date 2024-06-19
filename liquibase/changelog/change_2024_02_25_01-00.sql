create table public.matching_target
(
    id              serial not null
        constraint matching_target_pk
            primary key,
    identifier      varchar(250) NOT NULL ,
    first_name      varchar(250),
    last_name       varchar(250),
    address         text,
    iban            varchar(50),
    variable_symbol varchar(10),
    specific_symbol varchar(10),
    constant_symbol varchar(10),
    reference       varchar(250)
);

create table matched_transaction
(

    id             serial
        primary key,
    target_id      integer,
    created        timestamp(6),
    transaction_id integer
);

create table transaction
(
    id                  serial
        primary key,
    transaction_id      varchar   not null  unique ,
    date                timestamp not null,
    constant_symbol     varchar,
    variable_symbol     varchar,
    account_number_full varchar,
    amount              numeric   not null,
    comment             varchar,
    currency            varchar   not null,
    created_at          timestamp not null
);

do $$
    begin
        for r in 1..800 loop
                insert into public.matching_target(id, identifier,variable_symbol) values(r,r,r);
            end loop;
    end;
$$;