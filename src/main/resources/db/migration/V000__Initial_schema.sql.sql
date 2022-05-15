create table room(
    id bigserial not null,
    type varchar(255) not null,
    created timestamp not null,
    updated timestamp not null,
    deleted timestamp,
    primary key(id)
);

create index idx_room_type on room (type);

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

create index idx_guest_email on guest (email);
create index idx_guest_name on guest (name);

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

create index idx_employee_email on employee (email);
create index idx_employee_name on employee (name);
create index idx_employee_role on employee (role);