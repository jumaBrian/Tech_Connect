SET MODE PostgreSQL;

CREATE TABLE IF NOT EXISTS clients(
id int PRIMARY KEY auto_increment,
name VARCHAR,
email VARCHAR,
contact INTEGER
);