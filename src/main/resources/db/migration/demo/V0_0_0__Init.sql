CREATE SCHEMA IF NOT EXISTS demo;
REVOKE ALL ON SCHEMA demo FROM PUBLIC;

CREATE SEQUENCE item_id_seq;

CREATE TABLE item
(
    id              INTEGER NOT NULL PRIMARY KEY DEFAULT NEXTVAL('item_id_seq'),
    code            INTEGER NOT NULL,
    name            VARCHAR(50),
    active          boolean
);

ALTER SEQUENCE item_id_seq OWNED BY item.id;

CREATE TABLE reservation
(
    item_id              INTEGER NOT NULL,
    customer_id          INTEGER NOT NULL,
    delivery_description VARCHAR(300),
    PRIMARY KEY (item_id, customer_id),
    CONSTRAINT fk_item_id FOREIGN KEY (item_id) REFERENCES item (id) ON DELETE CASCADE
);