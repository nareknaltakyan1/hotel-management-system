create table additional_services
(
    id      bigserial PRIMARY KEY NOT NULL,
    name    varchar(255) UNIQUE   NOT NULL,
    price   double PRECISION      NOT NULL,
    created timestamp             NOT NULL,
    deleted timestamp,
    updated timestamp             NOT NULL
);

CREATE INDEX idx_additional_services_name ON additional_services (name);

