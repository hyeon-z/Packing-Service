CREATE TABLE packing_list
(
    id             BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    title          VARCHAR(12)        NOT NULL,
    description    VARCHAR(30)                 DEFAULT NULL,
    departure_date DATE               NOT NULL,
    created_at     DATETIME(9)        NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at     DATETIME(9)        NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE pack
(
    id              BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    packing_list_id BIGINT,
    name            VARCHAR(12)        NOT NULL,
    category        VARCHAR(12)        NOT NULL,
    checked         BOOLEAN            NOT NULL DEFAULT FALSE,
    created_at      DATETIME(9)        NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at      DATETIME(9)        NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (packing_list_id) REFERENCES packing_list (id)
);
