CREATE TABLE IF NOT EXISTS fitness.recipes
(
    uuid uuid NOT NULL,
    dt_create timestamp(6) without time zone,
    dt_update timestamp(6) without time zone,
    title character varying(255),
    CONSTRAINT recipes_pkey PRIMARY KEY (uuid)
)