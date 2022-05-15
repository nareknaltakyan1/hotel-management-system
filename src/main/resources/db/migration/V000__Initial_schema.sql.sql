create table room(
    id bigserial not null,
    type varchar(255) not null,
    created timestamp not null,
    updated timestamp not null,
    deleted timestamp,
    primary key(id)
);

-- add index on type

create table guest(
    id bigserial not null,
    passport varchar(255) not null UNIQUE,
    name varchar(255) not null,
    phone varchar(255) not null UNIQUE ,
    email varchar(255),
    created timestamp not null,
    updated timestamp not null,
    deleted timestamp,
    primary key(id)
);

-- add index on email
-- add index on name

create table employee(
    id bigserial not null,
    role varchar(255) not null,
    passport varchar(255) not null UNIQUE,
    name varchar(255) not null,
    phone varchar(255) not null UNIQUE ,
    email varchar(255),
    created timestamp not null,
    updated timestamp not null,
    deleted timestamp,
    primary key(id)
);

-- add index on email
-- add index on name
-- add index on role

-- create index idx_contact_name on contact (name);