CREATE TABLE orders (
    orders_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    branches_id UUID,
    cards_id UUID,
    products_orders_id UUID,
    user_orders_id INT,

    branch_title_orders VARCHAR(255),
    consumer_name_orders VARCHAR(255),
    consumer_phone_number_orders VARCHAR(50),
    delivery_title_orders VARCHAR(255),
    branch_name_orders VARCHAR(255),
    card_name_orders VARCHAR(255),
    product_price_orders INT,
    discount_product_price_orders INT,
    overall_price_orders INT,
    local_date_time_orders TIMESTAMP DEFAULT NOW(),

    CONSTRAINT fk_branch FOREIGN KEY (branches_id) REFERENCES branch(branches_id) ON DELETE SET NULL,
    CONSTRAINT fk_card FOREIGN KEY (cards_id) REFERENCES cards(cards_id) ON DELETE SET NULL,
    CONSTRAINT fk_product_orders FOREIGN KEY (products_orders_id) REFERENCES products(product_id) ON DELETE SET NULL,
    CONSTRAINT fk_user_orders FOREIGN KEY (user_orders_id) REFERENCES users(id) ON DELETE SET NULL
);
