CREATE DATABASE metadata;
USE metadata;

CREATE TABLE customers (
  id                INT   NOT NULL   AUTO_INCREMENT   PRIMARY KEY,
  customerName      VARCHAR(50),
  customerMiddle    VARCHAR(1),
  customerSurname   VARCHAR(50),
  customerAge       TINYINT,
  customerBirthdate DATETIME);

CREATE TABLE stores (
  id             INT   NOT NULL   AUTO_INCREMENT   PRIMARY KEY,
  storeCode      INT,
  storeName      VARCHAR(100),
  storeAvgIncome DECIMAL(26,4));

CREATE TABLE products (
  id           INT   NOT NULL   AUTO_INCREMENT   PRIMARY KEY,
  productCode  INT,
  productName  VARCHAR(100),
  productPrice FLOAT(8,2));

CREATE TABLE stock (
  id          INT   NOT NULL   AUTO_INCREMENT   PRIMARY KEY,
  productCode INT,
  storeCode   INT,
  stock       SMALLINT UNSIGNED);

CREATE VIEW v_Stock AS
SELECT stores.storeCode,stores.storeName,products.productCode,products.productName,stock.stock
FROM stock stock
JOIN stores stores
ON stock.storeCode = stores.storeCode
JOIN products products
ON stock.productCode = products.productCode; 

INSERT INTO stores (storeCode,storeName,storeAvgIncome) VALUES (001,'STAMFORD',5000);
INSERT INTO stores (storeCode,storeName,storeAvgIncome) VALUES (002,'NORWALK',4000);
INSERT INTO stores (storeCode,storeName,storeAvgIncome) VALUES (003,'GREENWICH',4500);

INSERT INTO products (productCode, productName, productPrice) VALUES (001,'SUGAR',1.75);
INSERT INTO products (productCode, productName, productPrice) VALUES (002,'SALT',1.15);
INSERT INTO products (productCode, productName, productPrice) VALUES (003,'PEPPER',1.35);

INSERT INTO stock (productCode,storeCode,stock) VALUES (1,1,100);
INSERT INTO stock (productCode,storeCode,stock) VALUES (1,2,200);
INSERT INTO stock (productCode,storeCode,stock) VALUES (1,3,300);
INSERT INTO stock (productCode,storeCode,stock) VALUES (2,1,100);
INSERT INTO stock (productCode,storeCode,stock) VALUES (2,2,200);
INSERT INTO stock (productCode,storeCode,stock) VALUES (2,3,300);
INSERT INTO stock (productCode,storeCode,stock) VALUES (3,1,100);
INSERT INTO stock (productCode,storeCode,stock) VALUES (3,2,200);
INSERT INTO stock (productCode,storeCode,stock) VALUES (3,3,300);

-- DB 2

INSERT INTO stores (storeCode,storeName,storeAvgIncome) VALUES (001,'STAMFORD',4000);
INSERT INTO stores (storeCode,storeName,storeAvgIncome) VALUES (002,'NORWALK',3000);
INSERT INTO stores (storeCode,storeName,storeAvgIncome) VALUES (003,'GREENWICH',3500);

INSERT INTO products (productCode, productName, productPrice) VALUES (001,'SUGAR',1.65);
INSERT INTO products (productCode, productName, productPrice) VALUES (002,'SALT',1.15);
INSERT INTO products (productCode, productName, productPrice) VALUES (003,'PEPPER',1.25);

INSERT INTO stock (productCode,storeCode,stock) VALUES (1,1,100);
INSERT INTO stock (productCode,storeCode,stock) VALUES (1,2,200);
INSERT INTO stock (productCode,storeCode,stock) VALUES (1,3,300);
INSERT INTO stock (productCode,storeCode,stock) VALUES (2,1,300);
INSERT INTO stock (productCode,storeCode,stock) VALUES (2,2,200);
INSERT INTO stock (productCode,storeCode,stock) VALUES (2,3,100);
INSERT INTO stock (productCode,storeCode,stock) VALUES (3,1,200);
INSERT INTO stock (productCode,storeCode,stock) VALUES (3,2,200);
INSERT INTO stock (productCode,storeCode,stock) VALUES (3,3,200);

