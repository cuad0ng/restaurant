use restaurant_db;
alter table employee add column img nvarchar(255);
alter table customer add column img nvarchar(255);
alter table account add column id int NOT NULL AUTO_INCREMENT primary key;
alter table account add column customer_id int;
alter table invoice modify column total_cost double; 
