
CREATE TABLE address
(
  id          INT         NOT NULL AUTO_INCREMENT,
  user_id     INT         NOT NULL COMMENT 'UserID',
  isDefault   BOOLEAN     NOT NULL DEFAULT false,
  street_addr VARCHAR(50) NOT NULL,
  detail_addr VARCHAR(50) NOT NULL,
  name        VARCHAR(50) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE authority
(
  id   INT         NOT NULL AUTO_INCREMENT COMMENT 'AuthorityID',
  auth VARCHAR(30) NOT NULL COMMENT 'AuthorityName',
  PRIMARY KEY (id)
) COMMENT '권한';

ALTER TABLE authority
  ADD CONSTRAINT UQ_auth UNIQUE (auth);

CREATE TABLE cart
(
  user_id INT      NOT NULL COMMENT 'UserID',
  item_id INT      NOT NULL,
  regdate DATETIME NULL    ,
  PRIMARY KEY (user_id, item_id)
);

CREATE TABLE comment
(
  id      INT          NOT NULL AUTO_INCREMENT COMMENT 'CommentID',
  user_id INT          NOT NULL COMMENT 'UserID',
  post_id INT          NOT NULL COMMENT 'PostID',
  content VARCHAR(200) NOT NULL COMMENT 'CommentContent',
  regdate DATETIME     NULL     DEFAULT now() COMMENT 'CommentRegDate',
  PRIMARY KEY (id)
) COMMENT '댓글';

CREATE TABLE contact
(
  id       INT          NOT NULL AUTO_INCREMENT COMMENT 'ContactID',
  user_id  INT          NOT NULL COMMENT 'UserID',
  goods_no VARCHAR(50)  NOT NULL COMMENT 'GoodsNO',
  regdate  DATETIME     NULL     DEFAULT now() COMMENT 'ContactRegDate',
  title    VARCHAR(100) NOT NULL COMMENT 'ContactTitle',
  content  VARCHAR(300) NOT NULL COMMENT 'ContactContent',
  status   VARCHAR(50)  NULL     DEFAULT false COMMENT 'Status',
  answer   VARCHAR(300) NULL    ,
  PRIMARY KEY (id)
) COMMENT '문의사항';

CREATE TABLE contact_image
(
  id         INT          NOT NULL AUTO_INCREMENT,
  contact_id INT          NOT NULL COMMENT 'ContactID',
  file_name  VARCHAR(100) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE coupon
(
  id   INT         NOT NULL AUTO_INCREMENT,
  rate DOUBLE      NOT NULL,
  name VARCHAR(50) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE coupon_box
(
  id        INT         NOT NULL AUTO_INCREMENT COMMENT 'Coupon_BoxId',
  user_id   INT         NOT NULL COMMENT 'UserID',
  coupon_id INT         NOT NULL,
  code      VARCHAR(20) NOT NULL COMMENT 'CouponCode',
  expiry    DATETIME    NOT NULL COMMENT 'CouponExpiry',
  status    BOOLEAN     NOT NULL DEFAULT false,
  PRIMARY KEY (id)
) COMMENT '쿠폰';

CREATE TABLE goods
(
  goods_no      VARCHAR(50)  NOT NULL,
  category_code VARCHAR(200) NOT NULL,
  name          text         NULL    ,
  keywords      longtext     NULL    ,
  brand_name    VARCHAR(20)  NULL    ,
  maker         VARCHAR(20)  NULL    ,
  price         VARCHAR(20)  NULL    ,
  image_1       longtext     NULL    ,
  image_2       longtext     NULL    ,
  image_3       longtext     NULL    ,
  image_4       longtext     NULL    ,
  image_5       longtext     NULL    ,
  image_6       longtext     NULL    ,
  contents      longtext     NULL    ,
  PRIMARY KEY (goods_no)
) COMMENT '제품';

CREATE TABLE likes
(
  user_id INT NOT NULL COMMENT 'UserID',
  post_id INT NOT NULL COMMENT 'PostID',
  PRIMARY KEY (user_id, post_id)
);

CREATE TABLE opt
(
  id       int         NOT NULL AUTO_INCREMENT,
  goods_no VARCHAR(50) NOT NULL,
  color    varchar(20) NULL    ,
  size     varchar(20) NULL    ,
  PRIMARY KEY (id)
) COMMENT '각제품이 가질수 있는 옵션';

CREATE TABLE post
(
  id      INT          NOT NULL AUTO_INCREMENT COMMENT 'PostID',
  user_id INT          NOT NULL COMMENT 'UserID',
  regdate DATETIME     NULL     DEFAULT now() COMMENT 'PostRegDate',
  content VARCHAR(300) NOT NULL COMMENT 'PostContent',
  PRIMARY KEY (id)
) COMMENT '게시글';

CREATE TABLE post_image
(
  id        INT          NOT NULL AUTO_INCREMENT,
  file_name VARCHAR(200) NULL    ,
  post_id   INT          NOT NULL COMMENT 'PostID',
  PRIMARY KEY (id)
);

CREATE TABLE purchase
(
  id             INT          NOT NULL AUTO_INCREMENT COMMENT 'OrderID',
  user_id        INT          NOT NULL COMMENT 'UserID',
  item_id        INT          NOT NULL,
  regdate        DATETIME     NULL     DEFAULT now() COMMENT 'OrderDate',
  street_addr    VARCHAR(200) NOT NULL,
  detail_address VARCHAR(200) NOT NULL COMMENT 'OrderAddress',
  phone          VARCHAR(20)  NOT NULL COMMENT 'OrderPhone',
  request        VARCHAR(200) NULL     COMMENT 'OrderRequest',
  PRIMARY KEY (id)
) COMMENT '주문';

CREATE TABLE purchase_item
(
  id       INT         NOT NULL,
  goods_id VARCHAR(50) NOT NULL COMMENT 'GoodsID',
  amount   INT         NOT NULL DEFAULT 0,
  color    varchar(30) NULL    ,
  size     varchar(30) NULL    ,
  PRIMARY KEY (id)
);

CREATE TABLE recent_item
(
  id       INT         NOT NULL AUTO_INCREMENT,
  user_id  INT         NOT NULL COMMENT 'UserID',
  goods_no VARCHAR(50) NOT NULL COMMENT 'GoodsID',
  regdate  DATETIME    NOT NULL DEFAULT now(),
  PRIMARY KEY (id)
);

CREATE TABLE request
(
  user_id INT         NOT NULL COMMENT 'UserID',
  item_id INT         NOT NULL,
  type    VARCHAR(20) NOT NULL DEFAULT 'paid',
  result  VARCHAR(50) NOT NULL DEFAULT 'processing',
  PRIMARY KEY (user_id, item_id)
);

CREATE TABLE request_image
(
  id        INT          NOT NULL AUTO_INCREMENT,
  file_name VARCHAR(200) NOT NULL,
  user_id   INT          NOT NULL COMMENT 'UserID',
  item_id   INT          NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE review
(
  id       INT          NOT NULL AUTO_INCREMENT COMMENT 'ReviewID',
  user_id  INT          NOT NULL COMMENT 'UserID',
  goods_no VARCHAR(50)  NOT NULL COMMENT 'GoodsNO',
  regdate  DATETIME     NULL     DEFAULT now() COMMENT 'ReviewRegDate',
  title    VARCHAR(100) NOT NULL COMMENT 'ReviewTitle',
  content  VARCHAR(200) NOT NULL COMMENT 'ReviewContent',
  rate     DOUBLE       NULL     DEFAULT 0 COMMENT 'ReviewRate',
  PRIMARY KEY (id)
) COMMENT '상품리뷰';

CREATE TABLE review_image
(
  id        INT          NOT NULL AUTO_INCREMENT,
  review_id INT          NOT NULL COMMENT 'ReviewID',
  file_name VARCHAR(100) NOT NULL,
  PRIMARY KEY (id)
);

ALTER TABLE review_image
  ADD CONSTRAINT UQ_file_name UNIQUE (file_name);

CREATE TABLE user
(
  id           INT          NOT NULL AUTO_INCREMENT COMMENT 'UserID',
  username     VARCHAR(100) NOT NULL COMMENT 'UserName',
  password     VARCHAR(200) NOT NULL COMMENT 'Password',
  regdate      DATETIME     NULL     DEFAULT now() COMMENT 'RegDate',
  name         VARCHAR(50)  NOT NULL COMMENT 'Name',
  phone        VARCHAR(20)  NOT NULL COMMENT 'Phone',
  birth        VARCHAR(20)  NOT NULL COMMENT 'Birthday',
  email        VARCHAR(50)  NOT NULL COMMENT 'Email',
  gender       INT          NULL     DEFAULT 0 COMMENT 'Gender',
  profileimage VARCHAR(600) NULL     COMMENT 'ProfileImage',
  grade        ENUM('Bronze','Sliver','Gold','Diamond') DEFAULT 'Bronze' COMMENT 'Grade',
  total_price  INT          NULL     DEFAULT 0,
  point        INT          NOT NULL DEFAULT 0 COMMENT 'Point',
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

ALTER TABLE user_authorities
  ADD CONSTRAINT FK_user_TO_user_authorities
    FOREIGN KEY (user_id)
    REFERENCES user (id);

ALTER TABLE user_authorities
  ADD CONSTRAINT FK_authority_TO_user_authorities
    FOREIGN KEY (authority_id)
    REFERENCES authority (id);

ALTER TABLE contact
  ADD CONSTRAINT FK_user_TO_contact
    FOREIGN KEY (user_id)
    REFERENCES user (id);

ALTER TABLE contact
  ADD CONSTRAINT FK_goods_TO_contact
    FOREIGN KEY (goods_no)
    REFERENCES goods (goods_no);

ALTER TABLE comment
  ADD CONSTRAINT FK_post_TO_comment
    FOREIGN KEY (post_id)
    REFERENCES post (id);

ALTER TABLE review
  ADD CONSTRAINT FK_goods_TO_review
    FOREIGN KEY (goods_no)
    REFERENCES goods (goods_no);

ALTER TABLE coupon_box
  ADD CONSTRAINT FK_user_TO_coupon_box
    FOREIGN KEY (user_id)
    REFERENCES user (id);

ALTER TABLE post
  ADD CONSTRAINT FK_user_TO_post
    FOREIGN KEY (user_id)
    REFERENCES user (id);

ALTER TABLE review
  ADD CONSTRAINT FK_user_TO_review
    FOREIGN KEY (user_id)
    REFERENCES user (id);

ALTER TABLE address
  ADD CONSTRAINT FK_user_TO_address
    FOREIGN KEY (user_id)
    REFERENCES user (id);

ALTER TABLE comment
  ADD CONSTRAINT FK_user_TO_comment
    FOREIGN KEY (user_id)
    REFERENCES user (id);

ALTER TABLE recent_item
  ADD CONSTRAINT FK_user_TO_recent_item
    FOREIGN KEY (user_id)
    REFERENCES user (id);

ALTER TABLE recent_item
  ADD CONSTRAINT FK_goods_TO_recent_item
    FOREIGN KEY (goods_no)
    REFERENCES goods (goods_no);

ALTER TABLE coupon_box
  ADD CONSTRAINT FK_coupon_TO_coupon_box
    FOREIGN KEY (coupon_id)
    REFERENCES coupon (id);

ALTER TABLE review_image
  ADD CONSTRAINT FK_review_TO_review_image
    FOREIGN KEY (review_id)
    REFERENCES review (id);

ALTER TABLE contact_image
  ADD CONSTRAINT FK_contact_TO_contact_image
    FOREIGN KEY (contact_id)
    REFERENCES contact (id);

ALTER TABLE likes
  ADD CONSTRAINT FK_user_TO_likes
    FOREIGN KEY (user_id)
    REFERENCES user (id);

ALTER TABLE likes
  ADD CONSTRAINT FK_post_TO_likes
    FOREIGN KEY (post_id)
    REFERENCES post (id);

ALTER TABLE purchase_item
  ADD CONSTRAINT FK_goods_TO_purchase_item
    FOREIGN KEY (goods_id)
    REFERENCES goods (goods_no);

ALTER TABLE request
  ADD CONSTRAINT FK_user_TO_request
    FOREIGN KEY (user_id)
    REFERENCES user (id);

ALTER TABLE request
  ADD CONSTRAINT FK_purchase_item_TO_request
    FOREIGN KEY (item_id)
    REFERENCES purchase_item (id);

ALTER TABLE post_image
  ADD CONSTRAINT FK_post_TO_post_image
    FOREIGN KEY (post_id)
    REFERENCES post (id);

ALTER TABLE purchase
  ADD CONSTRAINT FK_user_TO_purchase
    FOREIGN KEY (user_id)
    REFERENCES user (id);

ALTER TABLE request_image
  ADD CONSTRAINT FK_request_TO_request_image
    FOREIGN KEY (user_id, item_id)
    REFERENCES request (user_id, item_id);

ALTER TABLE cart
  ADD CONSTRAINT FK_user_TO_cart
    FOREIGN KEY (user_id)
    REFERENCES user (id);

ALTER TABLE cart
  ADD CONSTRAINT FK_purchase_item_TO_cart
    FOREIGN KEY (item_id)
    REFERENCES purchase_item (id);

ALTER TABLE purchase
  ADD CONSTRAINT FK_purchase_item_TO_purchase
    FOREIGN KEY (item_id)
    REFERENCES purchase_item (id);

ALTER TABLE opt
  ADD CONSTRAINT FK_goods_TO_opt
    FOREIGN KEY (goods_no)
    REFERENCES goods (goods_no);
