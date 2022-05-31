CREATE TABLE reservation
(
    id bigserial PRIMARY KEY NOT NULL,
    from_date timestamp NOT NULL,
    to_date   timestamp,
    type varchar(255) NOT NULL;
    room_id   bigserial NOT NULL,
    status_id bigserial NOT NULL,
    guest_id  bigserial NOT NULL,
    price double precision NOT NULL,
    CONSTRAINT FK_56 FOREIGN KEY (guest_id) REFERENCES guest (id),
    CONSTRAINT FK_80 FOREIGN KEY (room_id) REFERENCES room (id)
);