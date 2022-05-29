-- create database hotel_management_system;
-- -- ************************************** worker_type

CREATE TABLE worker_type
(
    "id" bigserial    NOT NULL,
    type varchar(255) NOT NULL,
    CONSTRAINT PK_21 PRIMARY KEY ("id")
);

-- ************************************** reservation_status

CREATE TABLE reservation_status
(
    "id"   bigserial   NOT NULL,
    status varchar(50) NOT NULL,
    CONSTRAINT PK_49 PRIMARY KEY ("id")
);

-- ************************************** room_type

CREATE TABLE room_type
(
    "id" bigserial    NOT NULL,
    type varchar(255) NOT NULL,
    CONSTRAINT PK_10 PRIMARY KEY ("id")
);
-- ************************************** additional_service

CREATE TABLE additional_service
(
    "id"  bigserial    NOT NULL,
    name  varchar(255) NOT NULL,
    price money        NOT NULL,
    CONSTRAINT PK_42 PRIMARY KEY ("id")
);

-- ************************************** guest

CREATE TABLE guest
(
    "id"       bigserial    NOT NULL,
    first_name varchar(255) NOT NULL,
    last_name  varchar(255) NULL,
    passport   varchar(255) NULL,
    created    date         NOT NULL,
    deleted    date         NULL,
    phone      varchar(255) NOT NULL,
    email      varchar(255),
    CONSTRAINT PK_34 PRIMARY KEY ("id")
);

-- ************************************** room

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

-- ************************************** reservation

CREATE TABLE reservation
(
    "id"      bigserial NOT NULL,
    from_date timestamp NOT NULL,
    room_id   bigserial NOT NULL,
    status_id bigserial NOT NULL,
    guest_id  bigserial NOT NULL,
    to_date   timestamp NULL,
    price     money     NOT NULL,
    created   timestamp NOT NULL,
    CONSTRAINT PK_53 PRIMARY KEY ("id"),
    CONSTRAINT FK_56 FOREIGN KEY (guest_id) REFERENCES guest ("id"),
    CONSTRAINT FK_59 FOREIGN KEY (status_id) REFERENCES reservation_status ("id"),
    CONSTRAINT FK_80 FOREIGN KEY (room_id) REFERENCES room ("id")
);

CREATE INDEX FK_58 ON reservation
    (
     guest_id
        );

CREATE INDEX FK_61 ON reservation
    (
     status_id
        );

CREATE INDEX FK_82 ON reservation
    (
     room_id
        );

-- ************************************** reservation_additional_services

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


-- ************************************** worker

CREATE TABLE worker
(
    "id"              bigserial    NOT NULL,
    worker_type_id    bigserial    NOT NULL,
    first_name        varchar(255) NOT NULL,
    last_name         varchar(255) NOT NULL,
    passport          varchar(255) NOT NULL,
    profile_image_url varchar(255) NULL,
    created           date         NOT NULL,
    deleted           date         NULL,
    phone             varchar(255) NOT NULL,
    email             varchar(255) NOT NULL,
    CONSTRAINT PK_18 PRIMARY KEY ("id"),
    CONSTRAINT FK_23 FOREIGN KEY (worker_type_id) REFERENCES worker_type ("id")
);

CREATE INDEX FK_25 ON worker
    (
     worker_type_id
        );
