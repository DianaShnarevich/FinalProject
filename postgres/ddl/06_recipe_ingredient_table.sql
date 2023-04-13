CREATE TABLE IF NOT EXISTS fitness.recipe_ingredient
(
    recipe_uuid uuid NOT NULL,
    ingredient_uuid uuid NOT NULL,
    CONSTRAINT recipe_ingredient_pkey PRIMARY KEY (recipe_uuid, ingredient_uuid),
    CONSTRAINT fkey_recipe FOREIGN KEY (recipe_uuid)
        REFERENCES fitness.recipes (uuid) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkey_ingredient FOREIGN KEY (ingredient_uuid)
        REFERENCES fitness.ingredients (uuid) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)