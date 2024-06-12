
CREATE TABLE authority
(
  id   INT          NOT NULL AUTO_INCREMENT COMMENT 'AuthorityID',
  name VARCHAR(100) NOT NULL COMMENT 'AuthorityName',
  PRIMARY KEY (id)
) COMMENT '권한';

ALTER TABLE authority
  ADD CONSTRAINT UQ_name UNIQUE (name);

CREATE TABLE basket
(
  id      INT      NOT NULL AUTO_INCREMENT COMMENT 'BasketID',
  amount  INT      NULL     DEFAULT 1 COMMENT 'Amount',
  regDate DATETIME NULL     DEFAULT now() COMMENT 'RegDate',
  user_id INT      NOT NULL COMMENT 'UserID',
  PRIMARY KEY (id)
) COMMENT '장바구니';

CREATE TABLE comment
(
  id            INT          NOT NULL AUTO_INCREMENT COMMENT 'CommentID',
  content       VARCHAR(200) NOT NULL COMMENT 'CommentContent',
  regdate       DATETIME     NULL     DEFAULT now() COMMENT 'CommentRegDate',
  comment_order INT          NULL     DEFAULT 1 COMMENT 'CommentOrder',
  reply_order   INT          NULL     DEFAULT 0 COMMENT 'ReplyOrder',
  reply_depth   INT          NULL     DEFAULT 0 COMMENT 'ReplyDepth',
  post_id       INT          NOT NULL COMMENT 'PostID',
  PRIMARY KEY (id)
) COMMENT '댓글';

CREATE TABLE contact
(
  id       INT          NOT NULL AUTO_INCREMENT COMMENT 'ContactID',
  title    VARCHAR(100) NOT NULL COMMENT 'ContactTitle',
  content  VARCHAR(300) NOT NULL COMMENT 'ContactContent',
  regdate  DATETIME     NULL     DEFAULT now() COMMENT 'ContactRegDate',
  status   BOOLEAN      NULL     DEFAULT false COMMENT 'Status',
  user_id  INT          NOT NULL COMMENT 'UserID',
  goods_id INT          NOT NULL COMMENT 'GoodsID',
  PRIMARY KEY (id)
) COMMENT '문의사항';

CREATE TABLE coupon
(
  id       INT         NOT NULL AUTO_INCREMENT COMMENT 'CouponID',
  name     VARCHAR(50) NOT NULL COMMENT 'CouponName',
  code     VARCHAR(20) NOT NULL COMMENT 'CouponCode',
  user_id  INT         NOT NULL COMMENT 'UserID',
  goods_id INT         NOT NULL COMMENT 'GoodsID',
  PRIMARY KEY (id)
) COMMENT '쿠폰';

CREATE TABLE goods
(
  id        INT          NOT NULL AUTO_INCREMENT COMMENT 'GoodsID',
  title     VARCHAR(100) NOT NULL COMMENT 'GoodsTitle',
  price     INT          NOT NULL DEFAULT 0 COMMENT 'GoodsPrice',
  image     VARCHAR(600) NOT NULL COMMENT 'GoodsImage',
  content   VARCHAR(500) NULL     COMMENT 'GoodsContent',
  amount    INT          NOT NULL COMMENT 'GoodsAmount',
  regDate   DATETIME     NULL     DEFAULT now() COMMENT 'GoodsRegDate',
  category1 VARCHAR(20)  NULL     COMMENT 'Category1',
  category2 VARCHAR(20)  NULL     COMMENT 'Category2',
  category3 VARCHAR(20)  NULL     COMMENT 'Category3',
  basket_id INT          NOT NULL COMMENT 'BasketID',
  PRIMARY KEY (id)
) COMMENT '제품';

CREATE TABLE interest
(
  id      INT         NOT NULL AUTO_INCREMENT COMMENT 'InterestID',
  user_id INT         NOT NULL COMMENT 'UserID',
  name    VARCHAR(50) NULL     COMMENT 'InterestName',
  PRIMARY KEY (id)
) COMMENT '관심사';

CREATE TABLE order
(
  id            INT          NOT NULL AUTO_INCREMENT COMMENT 'OrderID',
  price         INT          NOT NULL DEFAULT 0 COMMENT 'OrderPrice',
  date          DATETIME     NULL     DEFAULT now() COMMENT 'OrderDate',
  status        ENUM('Paid','Ready','Shipping', 'Complete')         NULL     DEFAULT 'Paid' COMMENT 'OrderStatus',
  address       VARCHAR(200) NOT NULL COMMENT 'OrderAddress',
  zipcode       VARCHAR(6)   NOT NULL COMMENT 'OrderZipCode',
  phone         VARCHAR(20)  NOT NULL COMMENT 'OrderPhone',
  request       VARCHAR(200) NULL     COMMENT 'OrderRequest',
  supplier_name VARCHAR(20)  NOT NULL COMMENT 'OrderSuppilerName',
  goods_amount  INT          NULL     DEFAULT 0 COMMENT 'OrderGoodsAmount',
  goods_name    VARCHAR(100) NULL     COMMENT 'OrderGoodsName',
  basket_id     INT          NOT NULL COMMENT 'BasketID',
  PRIMARY KEY (id)
) COMMENT '주문';

CREATE TABLE post
(
  id      INT          NOT NULL AUTO_INCREMENT COMMENT 'PostID',
  title   VARCHAR(100) NOT NULL COMMENT 'PostTitle',
  content VARCHAR(300) NOT NULL COMMENT 'PostContent',
  image   VARCHAR(200) NULL     COMMENT 'PostImage',
  regdate DATETIME     NULL     DEFAULT now() COMMENT 'PostRegDate',
  user_id INT          NOT NULL COMMENT 'UserID',
  PRIMARY KEY (id)
) COMMENT '게시글';

CREATE TABLE review
(
  id       INT          NOT NULL AUTO_INCREMENT COMMENT 'ReviewID',
  title    VARCHAR(100) NOT NULL COMMENT 'ReviewTitle',
  content  VARCHAR(200) NOT NULL COMMENT 'ReviewContent',
  rate     INT          NULL     DEFAULT -1 COMMENT 'ReviewRate',
  regdate  DATETIME     NULL     DEFAULT now() COMMENT 'ReviewRegDate',
  image    VARCHAR(200) NULL     COMMENT 'ReviewImage',
  goods_id INT          NOT NULL COMMENT 'GoodsID',
  user_id  INT          NOT NULL COMMENT 'UserID',
  PRIMARY KEY (id)
) COMMENT '상품리뷰';

CREATE TABLE search
(
  id           INT          NOT NULL AUTO_INCREMENT COMMENT 'SearchID',
  title        VARCHAR(200) NULL     COMMENT 'Title',
  season       ENUM('None','Spring','Summer','Fall','Winter')         NULL     DEFAULT 'None' COMMENT 'Season',
  expired_date DATETIME     NULL     COMMENT 'ExpiredDate',
  user_id      INT          NOT NULL COMMENT 'UserID',
  PRIMARY KEY (id)
) COMMENT '검색';

CREATE TABLE user
(
  id       INT          NOT NULL AUTO_INCREMENT COMMENT 'UserID',
  username VARCHAR(100) NOT NULL COMMENT 'UserName',
  password VARCHAR(200) NOT NULL COMMENT 'Password',
  name     VARCHAR(50)  NOT NULL COMMENT 'Name',
  phone    VARCHAR(20)  NOT NULL COMMENT 'Phone',
  birth    VARCHAR(20)  NOT NULL COMMENT 'Birthday',
  email    VARCHAR(50)  NOT NULL COMMENT 'Email',
  gender   INT          NULL     DEFAULT 0 COMMENT 'Gender',
  regdate  DATETIME     NULL     DEFAULT now() COMMENT 'RegDate',
  grade    ENUM('Bronze','Sliver','Gold','Diamond')         NULL     DEFAULT 'Bronze' COMMENT 'Grade',
  point    INT          NOT NULL DEFAULT 0 COMMENT 'Point',
  PRIMARY KEY (id)
) COMMENT '유저';

ALTER TABLE user
  ADD CONSTRAINT UQ_username UNIQUE (username);

CREATE TABLE user_authorities
(
  user_id      INT NOT NULL COMMENT 'UserID',
  authority_id INT NOT NULL COMMENT 'AuthorityID',
  PRIMARY KEY (user_id, authority_id)
) COMMENT '유저권한';

CREATE TABLE wishlist
(
  id       INT NOT NULL AUTO_INCREMENT COMMENT 'WishID',
  user_id  INT NOT NULL COMMENT 'UserID',
  goods_id INT NOT NULL COMMENT 'GoodsID',
  PRIMARY KEY (id, user_id, goods_id)
) COMMENT '좋아요';

ALTER TABLE user_authorities
  ADD CONSTRAINT FK_user_TO_user_authorities
    FOREIGN KEY (user_id)
    REFERENCES user (id)
        ON UPDATE RESTRICT;

ALTER TABLE user_authorities
  ADD CONSTRAINT FK_authority_TO_user_authorities
    FOREIGN KEY (authority_id)
    REFERENCES authority (id)
        ON UPDATE RESTRICT;

ALTER TABLE contact
  ADD CONSTRAINT FK_user_TO_contact
    FOREIGN KEY (user_id)
    REFERENCES user (id)
        ON UPDATE RESTRICT;

ALTER TABLE contact
  ADD CONSTRAINT FK_goods_TO_contact
    FOREIGN KEY (goods_id)
    REFERENCES goods (id)
        ON UPDATE RESTRICT;

ALTER TABLE comment
  ADD CONSTRAINT FK_post_TO_comment
    FOREIGN KEY (post_id)
    REFERENCES post (id)
        ON UPDATE RESTRICT;

ALTER TABLE review
  ADD CONSTRAINT FK_goods_TO_review
    FOREIGN KEY (goods_id)
    REFERENCES goods (id)
        ON UPDATE RESTRICT;

ALTER TABLE coupon
  ADD CONSTRAINT FK_user_TO_coupon
    FOREIGN KEY (user_id)
    REFERENCES user (id)
        ON UPDATE RESTRICT;

ALTER TABLE coupon
  ADD CONSTRAINT FK_goods_TO_coupon
    FOREIGN KEY (goods_id)
    REFERENCES goods (id)
        ON UPDATE RESTRICT;

ALTER TABLE post
  ADD CONSTRAINT FK_user_TO_post
    FOREIGN KEY (user_id)
    REFERENCES user (id)
        ON UPDATE RESTRICT;

ALTER TABLE goods
  ADD CONSTRAINT FK_basket_TO_goods
    FOREIGN KEY (basket_id)
    REFERENCES basket (id)
        ON UPDATE RESTRICT;

ALTER TABLE basket
  ADD CONSTRAINT FK_user_TO_basket
    FOREIGN KEY (user_id)
    REFERENCES user (id)
        ON UPDATE RESTRICT;

ALTER TABLE review
  ADD CONSTRAINT FK_user_TO_review
    FOREIGN KEY (user_id)
    REFERENCES user (id)
        ON UPDATE RESTRICT;

ALTER TABLE wishlist
  ADD CONSTRAINT FK_user_TO_wishlist
    FOREIGN KEY (user_id)
    REFERENCES user (id)
        ON UPDATE RESTRICT;

ALTER TABLE wishlist
  ADD CONSTRAINT FK_goods_TO_wishlist
    FOREIGN KEY (goods_id)
    REFERENCES goods (id)
        ON UPDATE RESTRICT;

ALTER TABLE interest
  ADD CONSTRAINT FK_user_TO_interest
    FOREIGN KEY (user_id)
    REFERENCES user (id)
        ON UPDATE RESTRICT;

ALTER TABLE order
  ADD CONSTRAINT FK_basket_TO_order
    FOREIGN KEY (basket_id)
    REFERENCES basket (id)
        ON UPDATE RESTRICT;

ALTER TABLE search
  ADD CONSTRAINT FK_user_TO_search
    FOREIGN KEY (user_id)
    REFERENCES user (id)
        ON UPDATE RESTRICT;
