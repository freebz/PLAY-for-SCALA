# --- !Ups
CREATE TABLE products (
    id bigint,
    ean bigint,
    name varchar,
    description varchar);

CREATE TABLE warehouses (
    id bigint,
    name varchar);

CREATE TABLE stock_items (
    id bigint,
    product_id bigint,
    warehouse_id bigint,
    quantity bigint);

# --- !Downs
DROP TABLE IF EXISTS products;
DROP TABLE IF EXISTS warehouses;
DROP TABLE IF EXISTS stock_items;
