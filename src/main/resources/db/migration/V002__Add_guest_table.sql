create table guest
(
    id         bigserial PRIMARY KEY NOT NULL,
    first_name varchar(255)          NOT NULL,
    last_name  varchar(255),
    passport   varchar(255) UNIQUE   NOT NULL,
    phone      varchar(255) UNIQUE   NOT NULL,
    email      varchar(255) UNIQUE,
    created    timestamp             NOT NULL,
    deleted    timestamp,
    updated    timestamp             NOT NULL
);


CREATE INDEX idx_guest_passport ON guest (passport);
CREATE INDEX idx_guest_email ON guest (email);
CREATE INDEX idx_guest_phone ON guest (phone);