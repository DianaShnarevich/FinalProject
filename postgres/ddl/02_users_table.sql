CREATE TABLE IF NOT EXISTS fitness.users
(
    uuid uuid NOT NULL,
    code character varying(255),
    dt_create timestamp(6) without time zone,
    dt_update timestamp(6) without time zone,
    fio character varying(255),
    mail character varying(255),
    password character varying(255),
    userRole character varying(255),
    status character varying(255),
    CONSTRAINT users_pkey PRIMARY KEY (uuid),
    CONSTRAINT unique_mail UNIQUE (mail)
)