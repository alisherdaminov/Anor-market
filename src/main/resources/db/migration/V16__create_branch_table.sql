CREATE TABLE branch (
    branches_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    branch_title VARCHAR(255) NOT NULL,
    city_name VARCHAR(255) NOT NULL,
    local_date_time TIMESTAMP NOT NULL DEFAULT NOW()
);