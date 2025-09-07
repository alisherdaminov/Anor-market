

CREATE TABLE products (
    product_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    seller_name VARCHAR(255),
    product_name VARCHAR(255) NOT NULL,
    delivery_title VARCHAR(255),
    product_description TEXT,
    product_color VARCHAR(100),
    price INT NOT NULL,
    discount_with_card_percent INT,
    discount__price_with_card INT,
    discount_without_card_percent INT,
    discount_price_without_card INT,
    delivery_date DATE,
    local_date_time TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW(),
    category_item_list_id UUID,
    CONSTRAINT fk_category_item_list FOREIGN KEY (category_item_list_id) REFERENCES category_items_list(category_item_list_id) ON DELETE SET NULL
);
