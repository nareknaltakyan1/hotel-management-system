create table additional_service
(
    id    bigserial PRIMARY KEY NOT NULL,
    name  varchar(255)          NOT NULL,
    price double PRECISION      NOT NULL
);
