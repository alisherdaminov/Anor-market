
CREATE TABLE refresh_token (
    refresh_token_id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    refresh_token TEXT NOT NULL UNIQUE,
    expiry_date TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    user_id INT NOT NULL,
    CONSTRAINT fk_refresh_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);
