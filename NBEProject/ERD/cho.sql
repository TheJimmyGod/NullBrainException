CREATE TABLE goods(
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100) NOT NULL ,
    price INT NOT NULL ,
    image VARCHAR(600),
    content VARCHAR(500),
    amount INT,
    regDate DATETIME default now(),
    category1 VARCHAR(20),
    category2 VARCHAR(20),
    category3 VARCHAR(20),
    basket_id INT REFERENCES basket (id)
);

CREATE TABLE basket (
    id INT AUTO_INCREMENT PRIMARY KEY
);
DROP TABLE basket;
