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
alter table item
    add image varchar(200) null;

delete t1
from item t1
join item t2
on t1.productId=t2.productId

;

insert into goods
select distinct productId, image, lprice, title, category1, category2, category3
    from item

;
select productId, image, lprice, title, category1, category2, category3
from item;

#
#
# SELECT * FROM information_schema.TABLE_CONSTRAINTS;
# DROP TABLE user_authorities;
# DROP TABLE contact_image;
# DROP TABLE contact;
# DROP TABLE post_image;
# DROP TABLE contact_image;
# DROP TABLE contact;
# DROP TABLE comment;
# drop table likes;
# DROP TABLE post;
# drop table options;
# drop table purchase_item;
# drop table purchase;
# drop table cart;
# drop table review_image;
# drop table review;
# drop table address;
# drop table recent_item;
# drop table coupon_box;
# drop table coupon;
# DROP TABLE user;
# drop table request_image;
# drop table request;
# drop table goods;
# drop table authority;
# show tables;