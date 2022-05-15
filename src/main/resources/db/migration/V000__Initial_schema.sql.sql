create table contact
(
    id bigserial not null,
    name varchar(255) not null,
    phone varchar(255) not null,
    email varchar(255) not null,
    address1 varchar(255) not null,
    address2 varchar(255) not null,
    address3 varchar(255) not null,
    postalCode varchar(255) not null,
    note varchar(255) not null,
    primary key(id)
);

create index idx_contact_name on contact (name);