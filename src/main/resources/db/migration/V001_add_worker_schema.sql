CREATE TABLE worker
(
    "id"              bigserial  PRIMARY KEY  NOT NULL,
    first_name        varchar(255) NOT NULL,
    last_name         varchar(255) NOT NULL,
    passport          varchar(255) NOT NULL UNIQUE,
    phone             varchar(255) NOT NULL,
    email             varchar(255) NOT NULL UNIQUE,
    profile_image_url varchar(255),
    created           timestamp    NOT NULL,
    deleted           timestamp,
    updated            timestamp    NOT NULL,
);

create index idx_worker_passport on worker (passport);
create index idx_worker_phone on worker (phone);
create index idx_worker_email on worker (email);