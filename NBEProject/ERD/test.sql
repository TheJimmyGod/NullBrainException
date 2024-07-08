
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

alter table goods modify contents LONGTEXT;
alter table goods modify keywords LONGTEXT;
alter table goods modify image_1 LONGTEXT;
alter table goods modify image_2 LONGTEXT;
alter table goods modify image_3 LONGTEXT;
alter table goods modify image_4 LONGTEXT;
alter table goods modify image_5 LONGTEXT;
alter table goods modify image_6 LONGTEXT;
alter table goods modify name TEXT;

alter table contact ADD COLUMN type VARCHAR(20);

alter table user ADD COLUMN status boolean default true;

delete from goods;
select * from goods;

desc goods;

select * from user;
select * from contact;
select * from contact_image;
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
where user_id = 23;

INSERT INTO purchase ( user_id, goods_id, pay_id, opt,merchant_id, amount,  regdate, status)
VALUES (23, '105218', 190, '블랙+FREE','dab92edfa3a441a18c9d68187c63ac33', 15950, now(), 'OK');

select * from user;

select * from goods;

select * from user;

select * from user_authorities;

desc pay;

desc pay;

UPDATE pay SET status = 'CANCEL_OK' WHERE status = 'CANCEL';




desc purchase;
ALTER TABLE purchase
MODIFY COLUMN status VARCHAR(50) DEFAULT 'OK';

ALTER TABLE pay
MODIFY COLUMN status VARCHAR(30) DEFAULT '';

select * from purchase;

select * from purchase;