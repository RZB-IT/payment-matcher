create table public.matching_target
(
    id              integer not null
        constraint matching_target_pk
            primary key,
    first_name      varchar(250),
    last_name       varchar(250),
    address         text,
    iban            varchar(50),
    variable_symbol varchar(10),
    specific_symbol varchar(10),
    constant_symbol varchar(10),
    reference       varchar(250)
);