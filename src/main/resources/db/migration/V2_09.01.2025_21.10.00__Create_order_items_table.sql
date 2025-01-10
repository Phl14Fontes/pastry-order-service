CREATE TABLE IF NOT EXISTS order_items (
    id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    correlation_id VARCHAR(26) NOT NULL,
    flavor VARCHAR(50) NOT NULL,
    quantity INT NOT NULL,
    amount FLOAT NOT NULL,
    observations VARCHAR(100)
);
