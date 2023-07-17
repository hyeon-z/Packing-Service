CREATE TABLE packing_list
(
    id             BIGINT PRIMARY KEY AUTO_INCREMENT,
    title          VARCHAR(12) NOT NULL,
    description    VARCHAR(30)          DEFAULT NULL,
    departure_date DATE        NOT NULL,
    created_at     DATETIME    NOT NULL DEFAULT NOW(),
    updated_at     DATETIME    NOT NULL DEFAULT NOW()
)
