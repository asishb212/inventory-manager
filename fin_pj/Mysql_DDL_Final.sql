-- SQLINES DEMO *** le SQL Developer Data Modeler 23.1.0.087.0806
-- SQLINES DEMO *** -04-27 22:32:53 EDT
-- SQLINES DEMO *** le Database 21c

SET SQL_MODE = NO_AUTO_VALUE_ON_ZERO;

-- SQLINES LICENSE FOR EVALUATION USE ONLY
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


-- SQLINES LICENSE FOR EVALUATION USE ONLY
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

-- SQLINES LICENSE FOR EVALUATION USE ONLY
CREATE TABLE item (
    item_id               BIGINT NOT NULL COMMENT 'Item ID Number',
    item_name             VARCHAR(30) NOT NULL COMMENT 'Name of the product Item',
    item_description      VARCHAR(30) NOT NULL COMMENT 'Description of the product Item',
    item_unit_price       DECIMAL(10, 2) NOT NULL COMMENT 'Unit Price of the item',
    item_discount_percent DECIMAL(10, 2) NOT NULL COMMENT 'Discount provided on Item by the Supplier',
    total_qty_purchased   BIGINT NOT NULL COMMENT 'Total Quantity Purchased',
    total_qty_sold   	    BIGINT NOT NULL COMMENT 'Total Quantity sold',
    stock_status          VARCHAR(30) NOT NULL COMMENT 'Stock Status is either item Available or Unavailable or Suspended',
    category              VARCHAR(30) NOT NULL,
    supplier_id           BIGINT NOT NULL
);


ALTER TABLE item ADD CONSTRAINT item_pk PRIMARY KEY ( item_id );
ALTER TABLE item MODIFY COLUMN item_id BIGINT AUTO_INCREMENT FIRST;



-- SQLINES LICENSE FOR EVALUATION USE ONLY
CREATE TABLE ORDERS (
    order_id      BIGINT NOT NULL COMMENT 'Order ID Number',
    order_type    VARCHAR(10) NOT NULL COMMENT 'Order Type could be either Customer Order or Supplier Order',
    order_status  VARCHAR(30) NOT NULL COMMENT 'Order Status could be either NEW or PAID or DELIVERED',
    tax_percent   DECIMAL(5, 2) NOT NULL COMMENT 'Tax Percentage on the total amount',
    items         VARCHAR(500),
    quantities    VARCHAR(500),
    user_id       BIGINT,
    date_created  DATETIME NOT NULL COMMENT 'Date Created',
    date_modified DATETIME COMMENT 'Date Modified'
);

ALTER TABLE ORDERS ADD CONSTRAINT order_pk PRIMARY KEY ( order_id );
ALTER TABLE ORDERS MODIFY COLUMN order_id BIGINT AUTO_INCREMENT FIRST;


-- SQLINES LICENSE FOR EVALUATION USE ONLY
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

-- SQLINES LICENSE FOR EVALUATION USE ONLY
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


-- SQLINES LICENSE FOR EVALUATION USE ONLY
CREATE TABLE user (
    user_id       BIGINT NOT NULL COMMENT 'User ID Number',
    username      VARCHAR(30) NOT NULL COMMENT 'Username used for Login to Application',
    salt            VARCHAR(255) NOT NULL,
    hashed_password VARCHAR(255) NOT NULL COMMENT 'Hashed Login Password',
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

ALTER TABLE item
    ADD CONSTRAINT item_supplier_fk FOREIGN KEY ( supplier_id )
        REFERENCES supplier ( supplier_id )
            ON DELETE CASCADE;

ALTER TABLE ORDERS 
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
                     
INSERT INTO user (user_id, username, salt, hashed_password, user_role, user_type, date_created) VALUES
(1, 'JohnD', 'IFXI34wROR0UN6R+C69slg==', 'BIi8Zh8lxluuVVpw47NunU+ElpzMz1xiHEg39W40IjA=', 'Norm', 'S', '2024-05-09 00:00:00'),
(2, 'DarthVader', 'MOmnumpyR61wjBgB/pwP6w==', '9DCtGj0ovMac2dffDDK/Z1q+vBI87TRFgmx1KVtsXes=', 'Norm', 'S', '2024-05-09 00:00:00'),
(3, 'IronMan', 'WMUn3u1MD/ubv1jKebmuIA==', 'go2CHc5fO0F8oCg6t0E524PwFqGqVm7GoH7WDZws2UI=', 'Norm', 'C', '2024-05-09 00:00:00'),
(4, 'BatMan', 'y/jBPQOU86GTMyfByB3R1w==', 'ToktsGekh3gVHTXdfk528UW74ae8iUfDhWodILQB6VE=', 'Norm', 'C', '2024-05-09 00:00:00');

INSERT INTO supplier (supplier_id, supplier_name, contact_firstname, contact_lastname, contact_phone, user_id) VALUES
(1, 'john enterprises', 'Jhon', 'Doe', '9922110011', 1),
(2, 'The empire', 'Han', 'Solo', '9299233433', 2);

INSERT INTO customer (cust_id, first_name, last_name, phone_number, email, user_id) VALUES
(1, 'Tony', 'Stark', '9992220000', 'tony.stark@starkindustries.com', 3),
(2, 'Bruce', 'Wayne', '667123456', 'bruce.wayne@waynecorp.com', 4);

INSERT INTO cust_addr (addr_id, addr_street, addr_city, addr_state, addr_country, addr_zipcode, cust_id) VALUES
(1, 'stark tower', 'Manhatten', 'NY', 'USA', '10001', 1),
(2, 'bat cave', 'Gotham', 'Gotham', 'USA', '99999', 2);

INSERT INTO supplier_addr (addr_id, addr_street, addr_city, addr_state, addr_country, addr_zipcode, supplier_id) VALUES
(1, '991 ray pkwy', 'brooklyn', 'NY', 'USA', '11202', 1),
(2, 'Fortress Vader', 'Mustafar', 'Mustafar', 'world of Mustafar', '00000', 2);

-- Insert items from Supplier 1
INSERT INTO item (item_id, item_name, item_description, item_unit_price, item_discount_percent, total_qty_purchased, total_qty_sold, stock_status, category, supplier_id) VALUES
(1, 'Golden Apple', 'Organic apples from sunny orchards', 3.50, 5.0, 200, 50, 'A', 'Food', 1),
(2, 'Summer Tee', 'Lightweight cotton t-shirt for summer', 20.00, 10.0, 150, 40, 'A', 'Clothing', 1),
(3, 'Eco Sofa', 'Sustainable couch made from recycled materials', 299.99, 15.0, 30, 5, 'A', 'Furniture', 1),
(4, 'Tech Watch', 'Smartwatch with multiple health monitoring features', 199.50, 20.0, 100, 20, 'NA', 'Electronics', 1),
(5, 'Blush Beauty', 'Natural cheek blush in rose pink', 25.00, 8.0, 120, 30, 'A', 'Cosmetics', 1),
(6, 'Pain Relief', 'Fast-acting pain relief for headaches and muscle aches', 10.00, 10.0, 300, 100, 'A', 'Health', 1),
(7, 'Glitter Nails', 'Quick-dry nail polish in shimmering shades', 8.99, 5.0, 200, 75, 'A', 'Cosmetics', 1),
(8, 'Running Sneakers', 'High-performance sneakers for professional running', 120.00, 15.0, 100, 25, 'A', 'Clothing', 1),
(9, 'LED Lamp', 'Energy-efficient LED lamp with adjustable brightness', 45.00, 20.0, 150, 50, 'NA', 'Electronics', 1),
(10, 'Bamboo Bowl Set', 'Set of four bamboo bowls, perfect for salads', 35.00, 10.0, 50, 15, 'A', 'Furniture', 1);

-- Insert items from Supplier 2
INSERT INTO item (item_id, item_name, item_description, item_unit_price, item_discount_percent, total_qty_purchased, total_qty_sold, stock_status, category, supplier_id) VALUES
(11, 'Space Heater', 'Portable heater with three heat settings', 50.00, 10.0, 100, 20, 'A', 'Electronics', 2),
(12, 'Hiking Backpack', 'Durable backpack for all your hiking gear', 75.00, 5.0, 100, 30, 'NA', 'Clothing', 2),
(13, 'Mint Toothpaste', 'Natural toothpaste with refreshing mint', 4.99, 10.0, 250, 150, 'A', 'Health', 2),
(14, 'Kitchen Mixer', 'Multipurpose mixer for all your cooking needs', 120.00, 20.0, 60, 10, 'NA', 'Others', 2),
(15, 'Moisturizing Cream', '24-hour skin moisturizing cream', 22.00, 8.0, 150, 45, 'A', 'Cosmetics', 2),
(16, 'Office Chair', 'Ergonomic office chair with lumbar support', 150.00, 15.0, 40, 10, 'NA', 'Furniture', 2),
(17, 'Herbal Tea', 'Soothing herbal tea blend with chamomile and mint', 10.00, 5.0, 200, 100, 'A', 'Food', 2),
(18, 'Workout Dumbbells', 'Adjustable weight dumbbells for home workouts', 60.00, 10.0, 100, 25, 'A', 'Health', 2),
(19, 'Gaming Monitor', 'Ultra-wide monitor for professional gaming', 299.99, 20.0, 80, 20, 'NA', 'Electronics', 2),
(20, 'Silk Pillowcase', 'Luxury silk pillowcase for skin and hair health', 45.00, 15.0, 150, 40, 'A', 'Others', 2);


-- Insert orders
INSERT INTO orders (order_id, order_type, order_status, tax_percent, items, quantities, user_id, date_created) VALUES
(1, 'Customer', 'NEW', 7.50, '1;3;5', '2;1;4', 3, '2023-05-10'),
(2, 'Supplier', 'PAID', 5.00, '2;4', '3;2', 1, '2023-06-15'),
(3, 'Customer', 'DELIVERED', 8.25, '5;6;7', '5;3;1', 4, '2023-07-20'),
(4, 'Supplier', 'NEW', 6.00, '8;10', '2;3', 2, '2023-08-25'),
(5, 'Customer', 'PAID', 7.00, '9;11;12', '4;2;1', 3, '2023-09-30'),
(6, 'Customer', 'DELIVERED', 9.00, '13;14', '3;1', 4, '2023-10-05'),
(7, 'Supplier', 'NEW', 4.50, '15;16', '5;2', 1, '2023-11-10'),
(8, 'Customer', 'PAID', 5.75, '17;18', '1;2', 3, '2023-12-15'),
(9, 'Customer', 'DELIVERED', 7.25, '19;20', '2;3', 4, '2024-01-20'),
(10, 'Supplier', 'NEW', 8.50, '1;2', '4;1', 2, '2024-02-25'),
(11, 'Customer', 'PAID', 6.75, '3;4', '1;2', 3, '2024-03-30'),
(12, 'Supplier', 'DELIVERED', 5.50, '5;6', '3;4', 1, '2024-04-04');


