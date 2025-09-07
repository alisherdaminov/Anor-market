
CREATE TABLE favorites (
    favorite_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    local_date_time TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW(),
    products_favorite_id UUID UNIQUE,
    CONSTRAINT fk_favorite_product FOREIGN KEY (products_favorite_id) REFERENCES products(product_id) ON DELETE CASCADE
);
