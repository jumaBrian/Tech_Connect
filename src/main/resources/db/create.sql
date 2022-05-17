SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS services(
id SERIAL PRIMARY KEY,
 service VARCHAR,
 hours VARCHAR,
 hourly_price VARCHAR,
 user_id INTEGER
);