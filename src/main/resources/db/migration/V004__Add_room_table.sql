CREATE TABLE room
(
    id bigserial PRIMARY KEY NOT NULL,
    type varchar(255) NOT NULL,
    area double precision NOT NULL,
    bed_numbers int NOT NULL,
    price_per_day double precision NOT NULL,
    created timestamp NOT NULL,
    deleted timestamp,
    updated timestamp NOT NULL
);

CREATE INDEX idx_room_price_per_day ON room (price_per_day);
