
CREATE TABLE home_products (
    product_id UUID PRIMARY KEY,
    seller_name VARCHAR(255),
    product_name VARCHAR(255),
    delivery_title VARCHAR(255),
    product_description TEXT,
    product_color VARCHAR(100),
    price INT NOT NULL,
    discount_with_card_percent INT,
    discount_price_with_card INT,
    discount_without_card_percent INT,
    discount_price_without_card INT,
    delivery_date DATE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    -- FK -> homes
    home_id UUID,
    CONSTRAINT fk_home_products_home FOREIGN KEY (home_id) REFERENCES homes(home_id) ON DELETE CASCADE
);
