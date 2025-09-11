CREATE TABLE top_products (
    top_products_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    seller_name VARCHAR(255),
    product_name VARCHAR(255),
    delivery_title VARCHAR(255),
    product_description TEXT,
    product_color VARCHAR(100),
    price INT,
    discount_with_card_percent INT,
    discount_price_with_card INT,
    discount_without_card_percent INT,
    discount_price_without_card INT,
    delivery_date DATE,
    is_top_product_start_date DATE,
    is_top_product_end_date DATE,
    local_date_time TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW(),

    product_id UUID,  -- foreign key
    CONSTRAINT fk_top_product FOREIGN KEY (product_id) REFERENCES products(product_id) ON DELETE CASCADE
);
