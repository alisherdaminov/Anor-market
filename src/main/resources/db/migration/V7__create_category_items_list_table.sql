
CREATE TABLE category_items_list (
    category_item_list_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    category_item_list_name VARCHAR(255) NOT NULL,
    local_date_time TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW(),
    category_id UUID,
    CONSTRAINT fk_category FOREIGN KEY (category_id) REFERENCES categories(category_id) ON DELETE CASCADE
);
