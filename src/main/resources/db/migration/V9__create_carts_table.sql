
CREATE TABLE carts (
    cart_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    quantity_of_product INT NOT NULL,
    local_date_time TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW(),
    products_cart_id UUID UNIQUE,
    CONSTRAINT fk_cart_product FOREIGN KEY (products_cart_id) REFERENCES products(product_id) ON DELETE CASCADE
);
