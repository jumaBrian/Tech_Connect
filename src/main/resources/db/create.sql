SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS professionals (
 id int PRIMARY KEY auto_increment,
 professional_name VARCHAR,
 qualification VARCHAR,
 bio VARCHAR,
 email VARCHAR,
 service_id INTEGER
);