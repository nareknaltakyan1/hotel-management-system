CREATE TABLE worker
(
    "id"              bigserial  PRIMARY KEY  NOT NULL,
    first_name        varchar(255) NOT NULL,
    last_name         varchar(255) NOT NULL,
    passport          varchar(255) NOT NULL,
    phone             varchar(255) NOT NULL,
    email             varchar(255) NOT NULL,
    profile_image_url varchar(255) NULL,
    created           timestamp    NOT NULL,
    deleted           timestamp NULL,
    update            timestamp    NOT NULL,
);
