CREATE TABLE employee
(
    id                bigserial PRIMARY KEY NOT NULL,
    role              varchar(255) NOT NULL,
    first_name        varchar(255) NOT NULL,
    last_name         varchar(255) NOT NULL,
    passport          varchar(255) UNIQUE NOT NULL,
    phone             varchar(255) UNIQUE NOT NULL,
    email             varchar(255) UNIQUE NOT NULL,
    profile_image_url varchar(255),
    created           timestamp NOT NULL,
    deleted           timestamp,
    updated           timestamp NOT NULL
);

create index idx_employee_passport on employee (passport);
create index idx_employee_phone on employee (phone);
create index idx_employee_email on employee (email);