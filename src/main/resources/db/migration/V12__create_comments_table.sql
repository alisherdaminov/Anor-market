
CREATE TABLE comments (
    comments_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    commenters_name VARCHAR(255),
    product_description TEXT,
    advantage_of_product TEXT,
    disadvantage_of_product TEXT,
    comments TEXT,
    local_date DATE DEFAULT CURRENT_DATE,
    products_comment_id UUID,
    CONSTRAINT fk_comment_product FOREIGN KEY (products_comment_id) REFERENCES products(product_id) ON DELETE CASCADE
);
