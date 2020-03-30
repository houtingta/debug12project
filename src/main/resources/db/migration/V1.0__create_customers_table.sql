--Create customer tables
CREATE TABLE customers(
    id              BIGSERIAL NOT NULL,
    name            VARCHAR(30) NOT NULL UNIQUE,
    first_name    	VARCHAR(30),
    last_name     	VARCHAR(30),
	telephone		VARCHAR(15),
    email           VARCHAR(50),
    address        	VARCHAR(50),
	city			VARCHAR(25),
	state			VARCHAR(2),
	zipcode			VARCHAR(10),
	CONSTRAINT  customers_cid_pk PRIMARY KEY (id)
);