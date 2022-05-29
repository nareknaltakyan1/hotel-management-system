create table additional_service
(
    id      bigserial PRIMARY KEY NOT NULL,
    name    varchar(255) UNIQUE   NOT NULL,
    price   double PRECISION      NOT NULL,
    created timestamp             NOT NULL,
    deleted timestamp,
    updated timestamp
);

CREATE INDEX idx_additional_service_name ON additional_service (name);

