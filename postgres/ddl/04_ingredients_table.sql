CREATE TABLE IF NOT EXISTS fitness.ingredients
(
    uuid uuid NOT NULL,
    weight integer NOT NULL,
    product_uuid uuid NOT NULL,
    CONSTRAINT ingredients_pkey PRIMARY KEY (uuid),
    CONSTRAINT fkey_product FOREIGN KEY (product_uuid)
        REFERENCES fitness.products (uuid) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)