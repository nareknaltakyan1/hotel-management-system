create  table  guest (
    guest_id bigserial primary  key Not null ,
    first_name varchar(255) not null ,
    last_name varchar(255),
    passport varchar(255),
    created  timestamp not null ,
    deleted timestamp ,
    phone varchar(255) not null ,
    email varchar(255)
)