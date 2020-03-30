--Create order table
CREATE TABLE orders(
    id          BIGSERIAL NOT NULL,
    order_date  DATE DEFAULT CURRENT_DATE,
    flight_date DATE,
    level       VARCHAR(15),
    seat        VARCHAR(15),
    balance     REAL,
    customer_id BIGINT NOT NULL,
    airline_id  BIGINT NOT NULL,
    CONSTRAINT  orders_oid_pk PRIMARY KEY (id),
    CONSTRAINT  orders_ocid_fk FOREIGN KEY (customer_id) REFERENCES customers (id),
    CONSTRAINT  orders_oaid_fk FOREIGN KEY (airline_id) REFERENCES airlines (id)
);
