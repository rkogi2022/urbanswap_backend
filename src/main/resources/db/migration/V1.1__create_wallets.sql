CREATE TABLE IF NOT EXISTS wallets (
    id UUID PRIMARY KEY NOT NULL UNIQUE,
    balance NUMERIC(19, 2) NOT NULL,
    user_id VARCHAR(36) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
    );

-- Create a function to update the updated_at column
CREATE OR REPLACE FUNCTION update_wallet_updated_at_column()
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = NOW();
RETURN NEW;
END;
$$ LANGUAGE plpgsql;
-- Create a trigger to call the function on updates
CREATE TRIGGER update_wallet_updated_at
    BEFORE UPDATE ON wallets
    FOR EACH ROW
    EXECUTE FUNCTION update_wallet_updated_at_column();


