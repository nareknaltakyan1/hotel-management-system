CREATE TABLE contact
(
    id bigserial PRIMARY KEY NOT NULL,
    name varchar(255) NOT NULL,
    phone varchar(255) NOT NULL,
    email varchar(255) NOT NULL,
    address1 varchar(255) NOT NULL,
    address2 varchar(255) NOT NULL,
    address3 varchar(255) NOT NULL,
    postalCode varchar(255) NOT NULL,
    note varchar(255) NOT NULL
);