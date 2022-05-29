CREATE TABLE room
(
    id bigserial PRIMARY KEY NOT NULL,
    type varchar(255) NOT NULL;
    area double precision NOT NULL,
    bed_numbers int NOT NULL,
    price_per_day double precision NOT NULL,
);