create table guest
(
    id   bigserial PRIMARY KEY NOT NULL,
    first_name varchar(255)          not null,
    last_name  varchar(255),
    passport   varchar(255),
    created    timestamp             not null,
    deleted    timestamp,
    phone      varchar(255)          not null,
    email      varchar(255)
)


 CREATE INDEX FK_1 ON guest
     (
      passport
         );



SELECT
    DISTINCT passport
FROM
    guest;