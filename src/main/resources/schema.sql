CREATE TABLE IF NOT EXISTS student (
    id                  INT                 NOT NULL AUTO_INCREMENT,
    names               VARCHAR(64)         NOT NULL,
    surnames            VARCHAR(64)         NOT NULL,
    email               VARCHAR(128)        NOT NULL,
    phone_number        VARCHAR(16)         NOT NULL,
    active              BOOLEAN             DEFAULT true,
    created_at          TIMESTAMP           DEFAULT NULL,
    latest_update       TIMESTAMP           DEFAULT NULL,

    PRIMARY KEY (id)
);