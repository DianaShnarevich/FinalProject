CREATE TABLE IF NOT EXISTS fitness.products
(
    uuid uuid NOT NULL,
    calories integer,
    carbohydrates numeric(10,1),
    dt_create timestamp(6) without time zone,
    dt_update timestamp(6) without time zone,
    fats numeric(10,1),
    proteins numeric(10,1),
    title character varying(255),
    weight integer,
    CONSTRAINT products_pkey PRIMARY KEY (uuid)
)