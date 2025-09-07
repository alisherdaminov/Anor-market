
CREATE TABLE categories (
    category_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    category_name VARCHAR(255) NOT NULL,
    local_date_time TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW(),
    catalog_id UUID,
    CONSTRAINT fk_catalog FOREIGN KEY (catalog_id) REFERENCES catalogs(catalog_id) ON DELETE CASCADE
);