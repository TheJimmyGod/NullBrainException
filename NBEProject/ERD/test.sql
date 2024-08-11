
create table goods(
    goods_no varchar(20),
    category_code varchar(20),
    name varchar(50),
    keywords varchar(200),
    brand_name varchar(20),
    maker varchar(20),
    price varchar(10),
    image_1 varchar(200),
    image_2 varchar(200),
    image_3 varchar(200),
    image_4 varchar(200),
    image_5 varchar(200),
    image_6 varchar(200),
    contents varchar(200)
);

create table opt (
    id int primary key auto_increment,
    goods_id varchar(20) references goods (goods_no),
    name varchar(10),
    value varchar(10)
);
SELECT * FROM goods;

Truncate post;
Truncate comment;
Truncate post_image;
Truncate likes;

DROP TABLE IF EXISTS user;
DROP TABLE  IF EXISTS  post;
DROP TABLE IF EXISTS likes;
DROP TABLE IF EXISTS purchase;
DROP TABLE IF EXISTS cart;
DROP TABLE IF EXISTS request;
DROP TABLE IF EXISTS request_image;
DROP TABLE IF EXISTS coupon_box;
DROP TABLE IF EXISTS recent_item;
DROP TABLE IF EXISTS address;
DROP TABLE IF EXISTS review;
DROP TABLE IF EXISTS review_image;
DROP TABLE  IF EXISTS  post_image;
DROP TABLE  IF EXISTS  comment;
DROP TABLE  IF EXISTS  likes;
DROP TABLE IF EXISTS authority;
DROP TABLE  IF EXISTS  contact;
DROP TABLE  IF EXISTS  contact_image;
DROP TABLE IF EXISTS user_authorities;

alter table goods modify contents LONGTEXT;
alter table goods modify keywords LONGTEXT;
alter table goods modify image_1 LONGTEXT;
alter table goods modify image_2 LONGTEXT;
alter table goods modify image_3 LONGTEXT;
alter table goods modify image_4 LONGTEXT;
alter table goods modify image_5 LONGTEXT;
alter table goods modify image_6 LONGTEXT;
alter table goods modify name TEXT;

alter table user ADD COLUMN status boolean default true;

delete from goods;
delete from post_image;
delete from post;
delete from comment;
delete from likes;
select * from goods;

desc goods;

INSERT INTO user VALUE (1,'Jimmy12', '1234', null, '진민장', '010-0000-0000', '19921120', 'minjang@mail.com',1, '', 'Bronze',0, '', 1, 0,true);

select * from user;
select * from contact;
select * from contact_image;

drop table if exists user;
drop table if exists user_authorities;
drop table if exists contact;
drop table if exists contact_image;
drop table if exists post;
drop table if exists comment;
drop table if exists post_image;
drop table if exists review;
drop table if exists review_image;
drop table if exists address;
drop table if exists recent_item;
drop table if exists coupon_box;
drop table if exists coupon;
drop table if exists request;
drop table if exists request_image;
drop table if exists cart;
drop table if exists purchase;

delete from contact;
delete from contact_image;

delete from post;
delete from comment;
delete from post_image;

select * from contact;

ALTER TABLE contact
    MODIFY COLUMN status VARCHAR(50) DEFAULT '대기 중';

select * from purchase;

select * from pay;
select * from authority;
select * from user_authorities
where user_id = 1;

INSERT INTO purchase ( user_id, goods_id, pay_id, opt,merchant_id, amount,  regdate, status)
VALUES (23, '105218', 190, '블랙+FREE','dab92edfa3a441a18c9d68187c63ac33', 15950, now(), 'OK');

INSERT INTO user(id, username, password, regdate, name, phone, birth, email, gender, profileimage, grade, total_price, point, provider, providerId, status)
VALUES (1, 'Jimmy112', 12345678, null , '진민장', '010-0000-0000', '19921120', 'minjang@mail.com', 1, null, 'Bronze',0, 0, 'ROLE_MEMBER', 0, true);

INSERT INTO user(id, username, password, regdate, name, phone, birth, email, gender, profileimage, grade, total_price, point)
VALUES (2, 'JimmyGod', 12345678, null , '진민장', '010-0000-0000', '19921120', 'minjang@mail.com', 1, null, 'Bronze',0, 0);

UPDATE user SET password = '12345678' WHERE id = 1;

INSERT INTO authority(id, auth) VALUES (2, 2);

select * from user;

select * from goods;

desc pay;

UPDATE pay SET status = 'CANCEL_OK' WHERE status = 'CANCEL';




desc purchase;
ALTER TABLE purchase
MODIFY COLUMN status VARCHAR(50) DEFAULT 'OK';

select * from purchase;