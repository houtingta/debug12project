--Create airline table
CREATE TABLE airlines(
    id          BIGSERIAL NOT NULL,
    name        VARCHAR(20) NOT NULL,
    tail_number VARCHAR(10) NOT NULL UNIQUE,
    CONSTRAINT  airlines_aid_pk PRIMARY KEY (id)
);
