CREATE TABLE reservation
(
    id bigserial PRIMARY KEY NOT NULL,
    from_date timestamp NOT NULL,
    to_date   timestamp,
    status varchar(255) NOT NULL,
    room_id   bigserial NOT NULL,
    guest_id  bigserial NOT NULL,
    price double precision NOT NULL,
    created timestamp NOT NULL,
    deleted timestamp,
    updated timestamp NOT NULL,
    CONSTRAINT fk_reservation_guest FOREIGN KEY (guest_id) REFERENCES guest (id),
    CONSTRAINT fk_reservation_room  FOREIGN KEY (room_id) REFERENCES room (id)
);

CREATE INDEX idx_reservation_from_date ON reservation (from_date);
CREATE INDEX idx_reservation_to_date ON reservation (to_date);
CREATE INDEX idx_reservation_status ON reservation (status);