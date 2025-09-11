CREATE TABLE cards (
    cards_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    card_name VARCHAR(255) NOT NULL,
    local_date_time TIMESTAMP NOT NULL DEFAULT NOW()
);
