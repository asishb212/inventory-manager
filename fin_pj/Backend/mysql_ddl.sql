-- SQLINES DEMO *** le SQL Developer Data Modeler 23.1.0.087.0806
-- SQLINES DEMO *** -04-27 22:32:53 EDT
-- SQLINES DEMO *** le Database 21c
-- SQLINES DEMO *** le Database 21c



-- SQLINES DEMO *** no DDL - MDSYS.SDO_GEOMETRY

-- SQLINES DEMO *** no DDL - XMLTYPE

-- SQLINES LICENSE FOR EVALUATION USE ONLY
CREATE TABLE cust_addr (
    addr_id      BIGINT NOT NULL COMMENT 'Address ID Number',
    addr_street  VARCHAR(30) NOT NULL COMMENT 'Street name',
    addr_city    VARCHAR(30) NOT NULL COMMENT 'City name',
    addr_state   VARCHAR(30) NOT NULL COMMENT 'State name',
    addr_country VARCHAR(30) NOT NULL COMMENT 'Country name',
    addr_zipcode VARCHAR(10) NOT NULL COMMENT 'Zip code
',
    cust_id      BIGINT NOT NULL
);

/* Moved to CREATE TABLE
COMMENT ON COLUMN cust_addr.addr_id IS
    'Address ID Number'; */

/* Moved to CREATE TABLE
COMMENT ON COLUMN cust_addr.addr_street IS
    'Street name'; */

/* Moved to CREATE TABLE
COMMENT ON COLUMN cust_addr.addr_city IS
    'City name'; */

/* Moved to CREATE TABLE
COMMENT ON COLUMN cust_addr.addr_state IS
    'State name'; */

/* Moved to CREATE TABLE
COMMENT ON COLUMN cust_addr.addr_country IS
    'Country name'; */

/* Moved to CREATE TABLE
COMMENT ON COLUMN cust_addr.addr_zipcode IS
    'Zip code
'; */

-- SQLINES LICENSE FOR EVALUATION USE ONLY
CREATE UNIQUE INDEX cust_addr__idx ON
    cust_addr (
        cust_id
    ASC );

ALTER TABLE cust_addr ADD CONSTRAINT cust_addr_pk PRIMARY KEY ( addr_id );

-- SQLINES LICENSE FOR EVALUATION USE ONLY
CREATE TABLE customer (
    cust_id      BIGINT NOT NULL COMMENT 'Customer ID Number',
    first_name   VARCHAR(30) NOT NULL COMMENT 'First Name of the Customer',
    last_name    VARCHAR(30) NOT NULL COMMENT 'Last Name of the Customer',
    phone_number VARCHAR(10) NOT NULL COMMENT 'Phone number of Customer',
    email        VARCHAR(30) NOT NULL COMMENT 'Email ID of the Customer',
    user_id      BIGINT NOT NULL
);

/* Moved to CREATE TABLE
COMMENT ON COLUMN customer.cust_id IS
    'Customer ID Number'; */

/* Moved to CREATE TABLE
COMMENT ON COLUMN customer.first_name IS
    'First Name of the Customer'; */

/* Moved to CREATE TABLE
COMMENT ON COLUMN customer.last_name IS
    'Last Name of the Customer'; */

/* Moved to CREATE TABLE
COMMENT ON COLUMN customer.phone_number IS
    'Phone number of Customer'; */

/* Moved to CREATE TABLE
COMMENT ON COLUMN customer.email IS
    'Email ID of the Customer'; */

-- SQLINES LICENSE FOR EVALUATION USE ONLY
CREATE UNIQUE INDEX customer__idx ON
    customer (
        user_id
    ASC );

ALTER TABLE customer ADD CONSTRAINT customer_pk PRIMARY KEY ( cust_id );

ALTER TABLE customer ADD CONSTRAINT customer_phone_number_un UNIQUE ( phone_number );

-- SQLINES LICENSE FOR EVALUATION USE ONLY
CREATE TABLE item (
    item_id               BIGINT NOT NULL COMMENT 'Item ID Number',
    item_name             VARCHAR(30) NOT NULL COMMENT 'Name of the product Item',
    item_description      VARCHAR(30) NOT NULL COMMENT 'Description of the product Item',
    item_unit_price       DECIMAL(10, 2) NOT NULL COMMENT 'Unit Price of the item',
    item_discount_percent DECIMAL(10, 2) NOT NULL COMMENT 'Discount provided on Item by the Supplier',
    supplier_id           BIGINT NOT NULL
);

/* Moved to CREATE TABLE
COMMENT ON COLUMN item.item_id IS
    'Item ID Number'; */

/* Moved to CREATE TABLE
COMMENT ON COLUMN item.item_name IS
    'Name of the product Item'; */

/* Moved to CREATE TABLE
COMMENT ON COLUMN item.item_description IS
    'Description of the product Item'; */

/* Moved to CREATE TABLE
COMMENT ON COLUMN item.item_unit_price IS
    'Unit Price of the item'; */

/* Moved to CREATE TABLE
COMMENT ON COLUMN item.item_discount_percent IS
    'Discount provided on Item by the Supplier'; */

ALTER TABLE item ADD CONSTRAINT item_pk PRIMARY KEY ( item_id );

-- SQLINES LICENSE FOR EVALUATION USE ONLY
CREATE TABLE item_stock (
    stock_id            BIGINT NOT NULL COMMENT 'Stock ID Number',
    total_qty_purchased BIGINT NOT NULL COMMENT 'Total Quantity Purchased',
    total_qty_sold      BIGINT NOT NULL COMMENT 'Total Quantity Sold',
    total_qty_available BIGINT NOT NULL COMMENT 'Total Quantity Available',
    stock_status        VARCHAR(30) NOT NULL COMMENT 'Stock Status is either item Available or Unavailable or Suspended',
    item_id             BIGINT NOT NULL,
    date_created        DATETIME NOT NULL COMMENT 'Date Created',
    date_modified       DATETIME COMMENT 'Date Modified'
);

/* Moved to CREATE TABLE
COMMENT ON COLUMN item_stock.stock_id IS
    'Stock ID Number'; */

/* Moved to CREATE TABLE
COMMENT ON COLUMN item_stock.total_qty_purchased IS
    'Total Quantity Purchased'; */

/* Moved to CREATE TABLE
COMMENT ON COLUMN item_stock.total_qty_sold IS
    'Total Quantity Sold'; */

/* Moved to CREATE TABLE
COMMENT ON COLUMN item_stock.total_qty_available IS
    'Total Quantity Available'; */

/* Moved to CREATE TABLE
COMMENT ON COLUMN item_stock.stock_status IS
    'Stock Status is either item Available or Unavailable or Suspended'; */

/* Moved to CREATE TABLE
COMMENT ON COLUMN item_stock.date_created IS
    'Date Created'; */

/* Moved to CREATE TABLE
COMMENT ON COLUMN item_stock.date_modified IS
    'Date Modified'; */

-- SQLINES LICENSE FOR EVALUATION USE ONLY
CREATE UNIQUE INDEX item_stock__idx ON
    item_stock (
        item_id
    ASC );

ALTER TABLE item_stock ADD CONSTRAINT item_stock_pk PRIMARY KEY ( stock_id );

-- SQLINES LICENSE FOR EVALUATION USE ONLY
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

/* Moved to CREATE TABLE
COMMENT ON COLUMN `ORDER`.order_i` IS
    'Order ID Number'; */

/* Moved to CREATE TABLE
COMMENT ON COLUMN `ORDER`.order_typ` IS
    'Order Type could be either Customer Order or Supplier Order'; */

/* Moved to CREATE TABLE
COMMENT ON COLUMN `ORDER`.order_statu` IS
    'Order Status could be either NEW or PAID or DELIVERED'; */

/* Moved to CREATE TABLE
COMMENT ON COLUMN `ORDER`.sub_tota` IS
    'Subtotal includes sum of price of all items ordered excluding tax amount'; */

/* Moved to CREATE TABLE
COMMENT ON COLUMN `ORDER`.tax_percen` IS
    'Tax Percentage on the Subtotal amount'; */

/* Moved to CREATE TABLE
COMMENT ON COLUMN `ORDER`.total_amoun` IS
    'Total Amount includes sum of subtotal + calculated tax amount'; */

/* Moved to CREATE TABLE
COMMENT ON COLUMN `ORDER`.date_create` IS
    'Date Created'; */

/* Moved to CREATE TABLE
COMMENT ON COLUMN `ORDER`.date_modifie` IS
    'Date Modified'; */

ALTER TABLE `ORDER` ADD CONSTRAINT order_pk PRIMARY KEY ( order_id );

-- SQLINES LICENSE FOR EVALUATION USE ONLY
CREATE TABLE order_items (
    order_item_id        BIGINT NOT NULL COMMENT 'Order Item ID Number',
    item_ordered_qty     BIGINT NOT NULL COMMENT 'Total Quantity of the item that is Ordered',
    item_order_subtotal  DECIMAL(10, 2) NOT NULL COMMENT 'Total Amount of the ordered Item quantity',
    item_discount_amount DECIMAL(10, 2) NOT NULL COMMENT 'Calculated Discount Amount for that Order Item',
    item_order_total     DECIMAL(10, 2) NOT NULL COMMENT 'Item Order Total amount is calculated as subtotal - discount amount',
    item_order_type      VARCHAR(3) NOT NULL COMMENT 'Item-Order-Type is either IN or OUT which is dependant on the Order-Type as Supplier or Customer',
    order_id             BIGINT NOT NULL,
    item_id              BIGINT NOT NULL,
    stock_id             BIGINT NOT NULL
);

/* Moved to CREATE TABLE
COMMENT ON COLUMN order_items.order_item_id IS
    'Order Item ID Number'; */

/* Moved to CREATE TABLE
COMMENT ON COLUMN order_items.item_ordered_qty IS
    'Total Quantity of the item that is Ordered'; */

/* Moved to CREATE TABLE
COMMENT ON COLUMN order_items.item_order_subtotal IS
    'Total Amount of the ordered Item quantity'; */

/* Moved to CREATE TABLE
COMMENT ON COLUMN order_items.item_discount_amount IS
    'Calculated Discount Amount for that Order Item'; */

/* Moved to CREATE TABLE
COMMENT ON COLUMN order_items.item_order_total IS
    'Item Order Total amount is calculated as subtotal - discount amount'; */

/* Moved to CREATE TABLE
COMMENT ON COLUMN order_items.item_order_type IS
    'Item-Order-Type is either IN or OUT which is dependant on the Order-Type as Supplier or Customer'; */

ALTER TABLE order_items ADD CONSTRAINT order_items_pk PRIMARY KEY ( order_item_id );

-- SQLINES LICENSE FOR EVALUATION USE ONLY
CREATE TABLE supplier (
    supplier_id       BIGINT NOT NULL COMMENT 'Supplier ID Number',
    supplier_name     VARCHAR(30) NOT NULL COMMENT 'Name of the Supplier',
    contact_firstname VARCHAR(30) NOT NULL COMMENT 'First name of the Supplier Contact person',
    contact_lastname  VARCHAR(30) NOT NULL COMMENT 'Last name of the Contact person',
    contact_phone     VARCHAR(30) NOT NULL COMMENT 'Phone number of the Contact person',
    user_id           BIGINT NOT NULL
);

/* Moved to CREATE TABLE
COMMENT ON COLUMN supplier.supplier_id IS
    'Supplier ID Number'; */

/* Moved to CREATE TABLE
COMMENT ON COLUMN supplier.supplier_name IS
    'Name of the Supplier'; */

/* Moved to CREATE TABLE
COMMENT ON COLUMN supplier.contact_firstname IS
    'First name of the Supplier Contact person'; */

/* Moved to CREATE TABLE
COMMENT ON COLUMN supplier.contact_lastname IS
    'Last name of the Contact person'; */

/* Moved to CREATE TABLE
COMMENT ON COLUMN supplier.contact_phone IS
    'Phone number of the Contact person'; */

-- SQLINES LICENSE FOR EVALUATION USE ONLY
CREATE UNIQUE INDEX supplier__idx ON
    supplier (
        user_id
    ASC );

ALTER TABLE supplier ADD CONSTRAINT supplier_pk PRIMARY KEY ( supplier_id );

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

/* Moved to CREATE TABLE
COMMENT ON COLUMN supplier_addr.addr_id IS
    'Address ID Number'; */

/* Moved to CREATE TABLE
COMMENT ON COLUMN supplier_addr.addr_street IS
    'Street name'; */

/* Moved to CREATE TABLE
COMMENT ON COLUMN supplier_addr.addr_city IS
    'City name'; */

/* Moved to CREATE TABLE
COMMENT ON COLUMN supplier_addr.addr_state IS
    'State name'; */

/* Moved to CREATE TABLE
COMMENT ON COLUMN supplier_addr.addr_country IS
    'Country name'; */

/* Moved to CREATE TABLE
COMMENT ON COLUMN supplier_addr.addr_zipcode IS
    'Zip code'; */

-- SQLINES LICENSE FOR EVALUATION USE ONLY
CREATE UNIQUE INDEX supplier_addr__idx ON
    supplier_addr (
        supplier_id
    ASC );

ALTER TABLE supplier_addr ADD CONSTRAINT supplier_addr_pk PRIMARY KEY ( addr_id );

-- SQLINES LICENSE FOR EVALUATION USE ONLY
CREATE TABLE `USER` (
    user_id       BIGINT NOT NULL COMMENT 'User ID Number',
    username      VARCHAR(30) NOT NULL COMMENT 'Username used for Login to Application',
    password      VARCHAR(30) NOT NULL COMMENT 'Login Password',
    user_role     VARCHAR(10) NOT NULL COMMENT 'User role is either Admin or Supplier or Customer',
    user_type     VARCHAR(1) NOT NULL COMMENT 'User Type is either "A" or "C" or "S" i.e. Admin or Customer or Supplier',
    date_created  DATETIME NOT NULL COMMENT 'Date Created',
    date_modified DATETIME COMMENT 'Date Modified'
);

/* Moved to CREATE TABLE
COMMENT ON COLUMN `USER`.user_i` IS
    'User ID Number'; */

/* Moved to CREATE TABLE
COMMENT ON COLUMN `USER`.usernam` IS
    'Username used for Login to Application'; */

/* Moved to CREATE TABLE
COMMENT ON COLUMN `USER`.passwor` IS
    'Login Password'; */

/* Moved to CREATE TABLE
COMMENT ON COLUMN `USER`.user_rol` IS
    'User role is either Admin or Supplier or Customer'; */

/* Moved to CREATE TABLE
COMMENT ON COLUMN `USER`.user_typ` IS
    'User Type is either "A" or "C" or "S" i.e. Admin or Customer or Supplier'; */

/* Moved to CREATE TABLE
COMMENT ON COLUMN `USER`.date_create` IS
    'Date Created'; */

/* Moved to CREATE TABLE
COMMENT ON COLUMN `USER`.date_modifie` IS
    'Date Modified'; */

ALTER TABLE `USER` ADD CONSTRAINT user_pk PRIMARY KEY ( user_id );

ALTER TABLE `USER` ADD CONSTRAINT user_username_un UNIQUE ( username );

ALTER TABLE cust_addr
    ADD CONSTRAINT cust_addr_customer_fk FOREIGN KEY ( cust_id )
        REFERENCES customer ( cust_id )
            ON DELETE CASCADE;

ALTER TABLE customer
    ADD CONSTRAINT customer_user_fk FOREIGN KEY ( user_id )
        REFERENCES `USER` ( user_id )
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
        REFERENCES `ORDER` ( order_id )
            ON DELETE CASCADE;

ALTER TABLE `ORDER`
    ADD CONSTRAINT order_user_fk FOREIGN KEY ( user_id )
        REFERENCES `USER` ( user_id )
            ON DELETE CASCADE;

ALTER TABLE supplier_addr
    ADD CONSTRAINT supplier_addr_supplier_fk FOREIGN KEY ( supplier_id )
        REFERENCES supplier ( supplier_id )
            ON DELETE CASCADE;

ALTER TABLE supplier
    ADD CONSTRAINT supplier_user_fk FOREIGN KEY ( user_id )
        REFERENCES `USER` ( user_id )
            ON DELETE CASCADE;

CALL CreateSequence('cust_addr_addr_id_seq', 1, 1);

-- SQLINES LICENSE FOR EVALUATION USE ONLY
DROP TRIGGER IF EXISTS cust_addr_addr_id_trg;

DELIMITER //

CREATE TRIGGER cust_addr_addr_id_trg BEFORE
    INSERT ON cust_addr
    FOR EACH ROW
    WHEN ( new.addr_id IS NULL )
BEGIN
    set new.addr_id = nextval('cust_addr_addr_id_seq');
END;
//

DELIMITER ;



CALL CreateSequence('customer_cust_id_seq', 1, 1);

-- SQLINES LICENSE FOR EVALUATION USE ONLY
DROP TRIGGER IF EXISTS customer_cust_id_trg;

DELIMITER //

CREATE TRIGGER customer_cust_id_trg BEFORE
    INSERT ON customer
    FOR EACH ROW
    WHEN ( new.cust_id IS NULL )
BEGIN
    set new.cust_id = nextval('customer_cust_id_seq');
END;
//

DELIMITER ;



CALL CreateSequence('item_item_id_seq', 1, 1);

-- SQLINES LICENSE FOR EVALUATION USE ONLY
DROP TRIGGER IF EXISTS item_item_id_trg;

DELIMITER //

CREATE TRIGGER item_item_id_trg BEFORE
    INSERT ON item
    FOR EACH ROW
    WHEN ( new.item_id IS NULL )
BEGIN
    set new.item_id = nextval('item_item_id_seq');
END;
//

DELIMITER ;



CALL CreateSequence('item_stock_stock_id_seq', 1, 1);

-- SQLINES LICENSE FOR EVALUATION USE ONLY
DROP TRIGGER IF EXISTS item_stock_stock_id_trg;

DELIMITER //

CREATE TRIGGER item_stock_stock_id_trg BEFORE
    INSERT ON item_stock
    FOR EACH ROW
    WHEN ( new.stock_id IS NULL )
BEGIN
    set new.stock_id = nextval('item_stock_stock_id_seq');
END;
//

DELIMITER ;



CALL CreateSequence('order_order_id_seq', 1, 1);

-- SQLINES LICENSE FOR EVALUATION USE ONLY
DROP TRIGGER IF EXISTS order_order_id_trg;

DELIMITER //

CREATE TRIGGER order_order_id_trg BEFORE
    INSERT ON `ORDER`
    FOR EACH ROW
    WHEN ( new.order_id IS NULL )
BEGIN
    set new.order_id = nextval('order_order_id_seq');
END;
//

DELIMITER ;



CALL CreateSequence('order_items_order_item_id_seq', 1, 1);

-- SQLINES LICENSE FOR EVALUATION USE ONLY
DROP TRIGGER IF EXISTS order_items_order_item_id_trg;

DELIMITER //

CREATE TRIGGER order_items_order_item_id_trg BEFORE
    INSERT ON order_items
    FOR EACH ROW
    WHEN ( new.order_item_id IS NULL )
BEGIN
    set new.order_item_id = nextval('order_items_order_item_id_seq');
END;
//

DELIMITER ;



CALL CreateSequence('supplier_supplier_id_seq', 1, 1);

-- SQLINES LICENSE FOR EVALUATION USE ONLY
DROP TRIGGER IF EXISTS supplier_supplier_id_trg;

DELIMITER //

CREATE TRIGGER supplier_supplier_id_trg BEFORE
    INSERT ON supplier
    FOR EACH ROW
    WHEN ( new.supplier_id IS NULL )
BEGIN
    set new.supplier_id = nextval('supplier_supplier_id_seq');
END;
//

DELIMITER ;



CALL CreateSequence('supplier_addr_addr_id_seq', 1, 1);

-- SQLINES LICENSE FOR EVALUATION USE ONLY
DROP TRIGGER IF EXISTS supplier_addr_addr_id_trg;

DELIMITER //

CREATE TRIGGER supplier_addr_addr_id_trg BEFORE
    INSERT ON supplier_addr
    FOR EACH ROW
    WHEN ( new.addr_id IS NULL )
BEGIN
    set new.addr_id = nextval('supplier_addr_addr_id_seq');
END;
//

DELIMITER ;



CALL CreateSequence('user_user_id_seq', 1, 1);

-- SQLINES LICENSE FOR EVALUATION USE ONLY
DROP TRIGGER IF EXISTS user_user_id_trg;

DELIMITER //

CREATE TRIGGER user_user_id_trg BEFORE
    INSERT ON `USER`
    FOR EACH ROW
    WHEN ( new.user_id IS NULL )
BEGIN
    set new.user_id = nextval('user_user_id_seq');
END;
//

DELIMITER ;





-- SQLINES DEMO *** per Data Modeler Summary Report: 
-- 
-- SQLINES DEMO ***                         9
-- SQLINES DEMO ***                         5
-- SQLINES DEMO ***                        22
-- SQLINES DEMO ***                         0
-- SQLINES DEMO ***                         0
-- SQLINES DEMO ***                         0
-- SQLINES DEMO *** DY                      0
-- SQLINES DEMO ***                         0
-- SQLINES DEMO ***                         0
-- SQLINES DEMO ***                         9
-- SQLINES DEMO ***                         0
-- SQLINES DEMO ***  TYPE                   0
-- SQLINES DEMO ***  TYPE                   0
-- SQLINES DEMO ***  TYPE BODY              0
-- SQLINES DEMO ***                         0
-- SQLINES DEMO ***                         0
-- SQLINES DEMO ***                         0
-- SQLINES DEMO ***                         0
-- SQLINES DEMO ***                         0
-- SQLINES DEMO ***                         0
-- SQLINES DEMO ***                         0
-- SQLINES DEMO *** EGMENT                  0
-- SQLINES DEMO ***                         9
-- SQLINES DEMO *** ED VIEW                 0
-- SQLINES DEMO *** ED VIEW LOG             0
-- SQLINES DEMO ***                         0
-- SQLINES DEMO ***                         0
-- SQLINES DEMO ***                         0
-- 
-- SQLINES DEMO ***                         0
-- SQLINES DEMO ***                         0
-- 
-- SQLINES DEMO ***                         0
-- 
-- SQLINES DEMO ***                         0
-- SQLINES DEMO *** A                       0
-- SQLINES DEMO *** T                       0
-- 
-- SQLINES DEMO ***                         0
-- SQLINES DEMO ***                         0
