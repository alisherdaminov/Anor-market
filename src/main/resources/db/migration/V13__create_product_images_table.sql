
CREATE TABLE product_images (
    image_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    origenName VARCHAR(255),
    extension VARCHAR(50),
    path TEXT,
    size BIGINT,
    url TEXT,
    createdAt TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW(),
    product_id UUID,
    top_products_id UUID,
    CONSTRAINT fk_product_image FOREIGN KEY (product_id) REFERENCES products(product_id) ON DELETE CASCADE,
    CONSTRAINT fk_top_product_image FOREIGN KEY (top_products_id) REFERENCES top_products(top_products_id) ON DELETE CASCADE
);
