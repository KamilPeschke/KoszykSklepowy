DROP TABLE IF EXISTS item;
DROP TABLE IF EXISTS basket;

CREATE TABLE basket (
  basket_id int NOT NULL,
  item_id int,
  status varchar(250),
  sum float,
  PRIMARY KEY (basket_id)
);

CREATE TABLE item (
  item_id int NOT NULL,
  name varchar(250) NOT NULL,
  price float,
  PRIMARY KEY (item_id)
);