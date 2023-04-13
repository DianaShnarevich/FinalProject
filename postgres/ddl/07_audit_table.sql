create TABLE IF NOT EXISTS fitness.audit
(
    uuid uuid NOT NULL,
    dt_create timestamp(6) without time zone,
    fio character varying(255),
    mail character varying(255),
    entity character varying(255),
    text character varying(255),
    type character varying(255),
    uuid_service character varying(255),
    uuid_user uuid,
    CONSTRAINT audit_pkey PRIMARY KEY (uuid)
)


