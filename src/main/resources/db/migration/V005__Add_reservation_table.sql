CREATE TABLE reservation
(
    id bigserial PRIMARY KEY NOT NULL,
    from_date timestamp NOT NULL,
    to_date   timestamp,
    status varchar(255) NOT NULL,
    room_id   bigserial NOT NULL,
    status_id bigserial NOT NULL,
    guest_id  bigserial NOT NULL,
    price double precision NOT NULL,
    created timestamp NOT NULL,
    deleted timestamp,
    updated timestamp NOT NULL,
    CONSTRAINT fk_reservation_guest FOREIGN KEY (guest_id) REFERENCES guest (id),
    CONSTRAINT fk_reservation_romm  FOREIGN KEY (room_id) REFERENCES room (id)
);