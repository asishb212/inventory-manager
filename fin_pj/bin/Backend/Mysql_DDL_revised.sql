SET SQL_MODE = NO_AUTO_VALUE_ON_ZERO;

CREATE TABLE cust_addr (
    addr_id      BIGINT NOT NULL COMMENT 'Address ID Number',
    addr_street  VARCHAR(30) NOT NULL COMMENT 'Street name',
    addr_city    VARCHAR(30) NOT NULL COMMENT 'City name',
    addr_state   VARCHAR(30) NOT NULL COMMENT 'State name',
    addr_country VARCHAR(30) NOT NULL COMMENT 'Country name',
    addr_zipcode VARCHAR(10) NOT NULL COMMENT 'Zip code',
    cust_id      BIGINT NOT NULL
);

CREATE UNIQUE INDEX cust_addr__idx ON
    cust_addr (cust_id ASC );

ALTER TABLE cust_addr ADD CONSTRAINT cust_addr_pk PRIMARY KEY ( addr_id ) ;
ALTER TABLE cust_addr MODIFY COLUMN addr_id BIGINT AUTO_INCREMENT FIRST;


CREATE TABLE customer (
    cust_id      BIGINT NOT NULL COMMENT 'Customer ID Number',
    first_name   VARCHAR(30) NOT NULL COMMENT 'First Name of the Customer',
    last_name    VARCHAR(30) NOT NULL COMMENT 'Last Name of the Customer',
    phone_number VARCHAR(10) NOT NULL COMMENT 'Phone number of Customer',
    email        VARCHAR(30) NOT NULL COMMENT 'Email ID of the Customer',
    user_id      BIGINT NOT NULL
);



CREATE UNIQUE INDEX customer__idx ON
    customer (user_id ASC);

ALTER TABLE customer ADD CONSTRAINT customer_pk PRIMARY KEY ( cust_id ) ;
ALTER TABLE customer MODIFY COLUMN cust_id BIGINT AUTO_INCREMENT FIRST;


ALTER TABLE customer ADD CONSTRAINT customer_phone_number_un UNIQUE ( phone_number );


CREATE TABLE item (
    item_id               BIGINT NOT NULL COMMENT 'Item ID Number',
    item_name             VARCHAR(30) NOT NULL COMMENT 'Name of the product Item',
    item_description      VARCHAR(30) NOT NULL COMMENT 'Description of the product Item',
    item_unit_price       DECIMAL(10, 2) NOT NULL COMMENT 'Unit Price of the item',
    item_discount_percent DECIMAL(10, 2) NOT NULL COMMENT 'Discount provided on Item by the Supplier',
    supplier_id           BIGINT NOT NULL
);


ALTER TABLE item ADD CONSTRAINT item_pk PRIMARY KEY ( item_id );
ALTER TABLE item MODIFY COLUMN item_id BIGINT AUTO_INCREMENT FIRST;



CREATE TABLE `ORDER` (
    order_id      BIGINT NOT NULL COMMENT 'Order ID Number',
    order_type    VARCHAR(10) NOT NULL COMMENT 'Order Type could be either Customer Order or Supplier Order',
    order_status  VARCHAR(30) NOT NULL COMMENT 'Order Status could be either NEW or PAID or DELIVERED',
    sub_total     DECIMAL(10, 2) NOT NULL COMMENT 'Subtotal includes sum of price of all items ordered excluding tax amount',
    tax_percent   DECIMAL(5, 2) NOT NULL COMMENT 'Tax Percentage on the Subtotal amount',
    total_amount  DECIMAL(10, 2) NOT NULL COMMENT 'Total Amount includes sum of subtotal + calculated tax amount',
    user_id       BIGINT,
    date_created  DATETIME NOT NULL COMMENT 'Date Created',
    date_modified DATETIME COMMENT 'Date Modified'
);

ALTER TABLE `ORDER` ADD CONSTRAINT order_pk PRIMARY KEY ( order_id );
ALTER TABLE `ORDER` MODIFY COLUMN order_id BIGINT AUTO_INCREMENT FIRST;





CREATE TABLE supplier (
    supplier_id       BIGINT NOT NULL COMMENT 'Supplier ID Number',
    supplier_name     VARCHAR(30) NOT NULL COMMENT 'Name of the Supplier',
    contact_firstname VARCHAR(30) NOT NULL COMMENT 'First name of the Supplier Contact person',
    contact_lastname  VARCHAR(30) NOT NULL COMMENT 'Last name of the Contact person',
    contact_phone     VARCHAR(30) NOT NULL COMMENT 'Phone number of the Contact person',
    user_id           BIGINT NOT NULL
);



CREATE UNIQUE INDEX supplier__idx ON
    supplier (user_id ASC );

ALTER TABLE supplier ADD CONSTRAINT supplier_pk PRIMARY KEY ( supplier_id ) ;
ALTER TABLE supplier MODIFY COLUMN supplier_id BIGINT AUTO_INCREMENT FIRST;


ALTER TABLE supplier ADD CONSTRAINT supplier_supplier_name_un UNIQUE ( supplier_name );


CREATE TABLE supplier_addr (
    addr_id      BIGINT NOT NULL COMMENT 'Address ID Number',
    addr_street  VARCHAR(30) NOT NULL COMMENT 'Street name',
    addr_city    VARCHAR(30) NOT NULL COMMENT 'City name',
    addr_state   VARCHAR(30) NOT NULL COMMENT 'State name',
    addr_country VARCHAR(30) NOT NULL COMMENT 'Country name',
    addr_zipcode VARCHAR(10) NOT NULL COMMENT 'Zip code',
    supplier_id  BIGINT NOT NULL
);



CREATE UNIQUE INDEX supplier_addr__idx ON
    supplier_addr (
        supplier_id ASC );

ALTER TABLE supplier_addr ADD CONSTRAINT supplier_addr_pk PRIMARY KEY ( addr_id );
ALTER TABLE supplier_addr MODIFY COLUMN addr_id BIGINT AUTO_INCREMENT FIRST;



CREATE TABLE user (
    user_id       BIGINT NOT NULL COMMENT 'User ID Number',
    username      VARCHAR(30) NOT NULL COMMENT 'Username used for Login to Application',
    password      VARCHAR(30) NOT NULL COMMENT 'Login Password',
    user_role     VARCHAR(10) NOT NULL COMMENT 'User role is either Admin or Supplier or Customer',
    user_type     VARCHAR(1) NOT NULL COMMENT 'User Type is either "A" or "C" or "S" i.e. Admin or Customer or Supplier',
    date_created  DATETIME NOT NULL COMMENT 'Date Created',
    date_modified DATETIME COMMENT 'Date Modified'
);



ALTER TABLE user ADD CONSTRAINT user_pk PRIMARY KEY ( user_id ) ;
ALTER TABLE user MODIFY COLUMN user_id BIGINT AUTO_INCREMENT FIRST;


ALTER TABLE user ADD CONSTRAINT user_username_un UNIQUE ( username );

ALTER TABLE cust_addr
    ADD CONSTRAINT cust_addr_customer_fk FOREIGN KEY ( cust_id )
        REFERENCES customer ( cust_id )
            ON DELETE CASCADE;

ALTER TABLE customer
    ADD CONSTRAINT customer_user_fk FOREIGN KEY ( user_id )
        REFERENCES user ( user_id )
            ON DELETE CASCADE;

ALTER TABLE item_stock
    ADD CONSTRAINT item_stock_item_fk FOREIGN KEY ( item_id )
        REFERENCES item ( item_id );

ALTER TABLE item
    ADD CONSTRAINT item_supplier_fk FOREIGN KEY ( supplier_id )
        REFERENCES supplier ( supplier_id )
            ON DELETE CASCADE;

ALTER TABLE order_items
    ADD CONSTRAINT order_items_item_fk FOREIGN KEY ( item_id )
        REFERENCES item ( item_id );

ALTER TABLE order_items
    ADD CONSTRAINT order_items_item_stock_fk FOREIGN KEY ( stock_id )
        REFERENCES item_stock ( stock_id );

ALTER TABLE order_items
    ADD CONSTRAINT order_items_order_fk FOREIGN KEY ( order_id )
        REFERENCES `ORDER`  ( order_id )
            ON DELETE CASCADE;

ALTER TABLE `ORDER` 
    ADD CONSTRAINT order_user_fk FOREIGN KEY ( user_id )
        REFERENCES user ( user_id )
            ON DELETE CASCADE;

ALTER TABLE supplier_addr
    ADD CONSTRAINT supplier_addr_supplier_fk FOREIGN KEY ( supplier_id )
        REFERENCES supplier ( supplier_id )
            ON DELETE CASCADE;

ALTER TABLE supplier
    ADD CONSTRAINT supplier_user_fk FOREIGN KEY ( user_id )
        REFERENCES user ( user_id )
            ON DELETE CASCADE;

