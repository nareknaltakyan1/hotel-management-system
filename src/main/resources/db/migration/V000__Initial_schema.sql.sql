create table room
(
    id      bigserial    not null,
    type    varchar(255) not null,
    created timestamp    not null,
    updated timestamp    not null,
    deleted timestamp,
    primary key (id)
);

create index idx_room_type on room (type);

create table guest
(
    id       bigserial    not null,
    passport varchar(255) not null UNIQUE,
    name     varchar(255) not null,
    phone    varchar(255) not null UNIQUE,
    email    varchar(255),
    created  timestamp    not null,
    updated  timestamp    not null,
    deleted  timestamp,
    primary key (id)
);

create index idx_guest_email on guest (email);
create index idx_guest_name on guest (name);

create table employee
(
    id       bigserial    not null,
    role     varchar(255) not null,
    passport varchar(255) not null UNIQUE,
    name     varchar(255) not null,
    phone    varchar(255) not null UNIQUE,
    email    varchar(255),
    created  timestamp    not null,
    updated  timestamp    not null,
    deleted  timestamp,
    primary key (id)
);

create index idx_employee_email on employee (email);
create index idx_employee_name on employee (name);
create index idx_employee_role on employee (role);



CREATE TABLE worker_type
(
    "id" bigserial    NOT NULL,
    type varchar(255) NOT NULL,
    CONSTRAINT PK_21 PRIMARY KEY ("id")
);



CREATE TABLE guest
(
    "id"       bigserial    NOT NULL,
    first_name varchar(255) NOT NULL,
    last_name  varchar(255) NULL,
    passport   varchar(255) NULL,
    created    date         NOT NULL,
    deleted    date         NULL,
    CONSTRAINT PK_34 PRIMARY KEY ("id")
);



CREATE TABLE reservation
(
    "id"        bigserial NOT NULL,
    from_date timestamp NOT NULL,
    status_id bigserial NOT NULL,
    guest_id  bigserial NOT NULL,
    to_date   timestamp NULL,
    CONSTRAINT PK_53 PRIMARY KEY ( "id" ),
    CONSTRAINT FK_56 FOREIGN KEY ( guest_id ) REFERENCES guest ( "id" ),
    CONSTRAINT FK_59 FOREIGN KEY ( status_id ) REFERENCES reservation_status ( "id" )
);

CREATE INDEX FK_58 ON reservation
    (
     guest_id
        );

CREATE INDEX FK_61 ON reservation
    (
     status_id
        );




CREATE TABLE reservation_additional_services
(
    "id"           bigserial NOT NULL,
    "count"        int       NOT NULL,
    service_id     bigserial NOT NULL,
    reservation_id bigserial NOT NULL,
    CONSTRAINT PK_67 PRIMARY KEY ("id"),
    CONSTRAINT FK_69 FOREIGN KEY (reservation_id) REFERENCES reservation ("id"),
    CONSTRAINT FK_72 FOREIGN KEY (service_id) REFERENCES additional_service ("id")
);

CREATE INDEX FK_71 ON reservation_additional_services
    (
     reservation_id
    );

CREATE INDEX FK_74 ON reservation_additional_services
    (
     service_id
    );


CREATE TABLE reservation_status
(
    "id"   bigserial   NOT NULL,
    status varchar(50) NOT NULL,
    CONSTRAINT PK_49 PRIMARY KEY ("id")
);



CREATE TABLE room
(
    "id"          bigserial        NOT NULL,
    area          double precision NOT NULL,
    room_type_id  bigserial        NOT NULL,
    bed_numbers   int              NOT NULL,
    price_per_day money            NOT NULL,
    CONSTRAINT PK_5 PRIMARY KEY ("id"),
    CONSTRAINT FK_13 FOREIGN KEY (room_type_id) REFERENCES room_type ("id")
);

CREATE INDEX FK_15 ON room
    (
     room_type_id
    );

CREATE TABLE room_type
(
    "id" bigserial    NOT NULL,
    type varchar(255) NOT NULL,
    CONSTRAINT PK_10 PRIMARY KEY ("id")
);


CREATE TABLE worker
(
    "id"                 NOT NULL,
    worker_type_id    bigserial NOT NULL,
    first_name        varchar(255) NOT NULL,
    last_name         varchar(255) NOT NULL,
    passport          varchar(255) NOT NULL,
    profile_image_url varchar(255) NULL,
    created           date NOT NULL,
    deleted           date NULL,
    CONSTRAINT PK_18 PRIMARY KEY ( "id" ),
    CONSTRAINT FK_23 FOREIGN KEY ( worker_type_id ) REFERENCES worker_type ( "id" )
);

CREATE INDEX FK_25 ON worker
    (
     worker_type_id
        );


CREATE TABLE worker_type
(
    "id" bigserial    NOT NULL,
    type varchar(255) NOT NULL,
    CONSTRAINT PK_21 PRIMARY KEY ("id")
);








