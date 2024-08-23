SHOW DATABASES;

CREATE DATABASE springboot;

USE springboot;

CREATE TABLE users(

                      username         VARCHAR(100)    NOT NULL,
                      password         VARCHAR(100)    NOT NULL,
                      name             VARCHAR(100)    NOT NULL,
                      token            VARCHAR(100),
                      token_expired_at BIGINT,
    UNIQUE (token),
                      PRIMARY KEY (username)
)ENGINE InnoDB;

CREATE TABLE contacts(
                         id          VARCHAR(100)        NOT NULL,
                         username    VARCHAR(100)        NOT NULL,
                         first_name  VARCHAR(100)        NOT NULL,
                         last_name   VARCHAR(100),
                         email       VARCHAR(100),
                         phone       VARCHAR(100),
                         PRIMARY KEY (id),
                         FOREIGN KEY fk_users_contacts(username) REFERENCES users(username)
)ENGINE InnoDB;

CREATE TABLE addresses(
                          id          VARCHAR(100)        NOT NULL,
                          contact_id  VARCHAR(100)        NOT NULL,
                          city        VARCHAR(100),
                          country     VARCHAR(100)        NOT NULL,
                          postal_code VARCHAR(100),
                          PRIMARY KEY (id),
                          FOREIGN KEY fk_contacts_addresses (contact_id) REFERENCES contacts(id)
)ENGINE InnoDB;

SHOW TABLES;

DESCRIBE users;

SELECT * FROM users;
SELECT * FROM contacts;
SELECT * FROM addresses;
