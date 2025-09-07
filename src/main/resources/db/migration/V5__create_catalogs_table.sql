
CREATE TABLE catalogs (
    catalog_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    catalog_name VARCHAR(255) NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE DEFAULT NOW()
);
