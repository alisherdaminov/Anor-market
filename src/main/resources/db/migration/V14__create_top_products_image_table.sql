
CREATE TABLE top_products_image (
    top_product_image_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    origenName VARCHAR(255),
    extension VARCHAR(50),
    path TEXT,
    size BIGINT,
    url TEXT,
    createdAt TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW(),
    top_products_id UUID,
    CONSTRAINT fk_top_products_image FOREIGN KEY (top_products_id) REFERENCES top_products(top_products_id) ON DELETE CASCADE
);
