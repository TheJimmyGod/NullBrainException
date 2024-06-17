
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

ALTER TABLE address
  ADD CONSTRAINT UQ_isDefault UNIQUE (isDefault);

CREATE TABLE authority
(
  id   INT         NOT NULL AUTO_INCREMENT COMMENT 'AuthorityID',
  auth VARCHAR(30) NOT NULL COMMENT 'AuthorityName',
  PRIMARY KEY (id)
) COMMENT '권한';

ALTER TABLE authority
  ADD CONSTRAINT UQ_auth UNIQUE (auth);

CREATE TABLE basket
(
  id          INT      NOT NULL AUTO_INCREMENT COMMENT 'BasketID',
  user_id     INT      NOT NULL COMMENT 'UserID',
  regdate     DATETIME NULL     DEFAULT now() COMMENT 'RegDate',
  total_price INT      NOT NULL DEFAULT 0,
  PRIMARY KEY (id)
) COMMENT '장바구니';

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
  goods_id VARCHAR(50)  NOT NULL COMMENT 'GoodsID',
  regdate  DATETIME     NULL     DEFAULT now() COMMENT 'ContactRegDate',
  title    VARCHAR(100) NOT NULL COMMENT 'ContactTitle',
  content  VARCHAR(300) NOT NULL COMMENT 'ContactContent',
  status   BOOLEAN      NULL     DEFAULT false COMMENT 'Status',
  answer   VARCHAR(300) NULL    ,
  PRIMARY KEY (id)
) COMMENT '문의사항';

CREATE TABLE contact_image
(
  id         INT          NOT NULL,
  contact_id INT          NOT NULL COMMENT 'ContactID',
  filen_ame  VARCHAR(100) NOT NULL,
  PRIMARY KEY (id)
);

ALTER TABLE contact_image
  ADD CONSTRAINT UQ_filen_ame UNIQUE (filen_ame);

CREATE TABLE coupon
(
  id   INT         NOT NULL,
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
  productId VARCHAR(50)  NOT NULL COMMENT 'GoodsID',
  title     VARCHAR(100) NOT NULL COMMENT 'GoodsTitle',
  image     VARCHAR(200) NULL    ,
  price     INT          NOT NULL DEFAULT 0 COMMENT 'GoodsPrice',
  content   VARCHAR(500) NULL     COMMENT 'GoodsContent',
  category1 VARCHAR(20)  NULL     COMMENT 'Category1',
  category2 VARCHAR(20)  NULL     COMMENT 'Category2',
  category3 VARCHAR(20)  NULL     COMMENT 'Category3',
  PRIMARY KEY (productId)
) COMMENT '제품';

CREATE TABLE likes
(
  user_id INT NOT NULL COMMENT 'UserID',
  post_id INT NOT NULL COMMENT 'PostID',
  PRIMARY KEY (user_id, post_id)
);

CREATE TABLE options
(
  id               INT         NOT NULL AUTO_INCREMENT,
  purchase_item_id INT         NOT NULL,
  name             VARCHAR(10) NULL    ,
  value            VARCHAR(10) NULL    ,
  PRIMARY KEY (id)
);

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
  id        INT          NOT NULL,
  file_name VARCHAR(200) NULL    ,
  post_id   INT          NOT NULL COMMENT 'PostID',
  PRIMARY KEY (id)
);

CREATE TABLE purchase
(
  id             INT          NOT NULL AUTO_INCREMENT COMMENT 'OrderID',
  user_id        INT          NOT NULL COMMENT 'UserID',
  regdate        DATETIME     NULL     DEFAULT now() COMMENT 'OrderDate',
  street_addr    VARCHAR(200) NOT NULL,
  detail_address VARCHAR(200) NOT NULL COMMENT 'OrderAddress',
  phone          VARCHAR(20)  NOT NULL COMMENT 'OrderPhone',
  request        VARCHAR(200) NULL     COMMENT 'OrderRequest',
  PRIMARY KEY (id)
) COMMENT '주문';

CREATE TABLE purchase_item
(
  id          INT         NOT NULL,
  basket_id   INT         NULL     COMMENT 'BasketID',
  purchase_id INT         NULL     COMMENT 'OrderID',
  goods_id    VARCHAR(50) NOT NULL COMMENT 'GoodsID',
  amount      INT         NOT NULL DEFAULT 0,
  PRIMARY KEY (id)
);

CREATE TABLE recent_item
(
  id       INT         NOT NULL,
  user_id  INT         NOT NULL COMMENT 'UserID',
  goods_id VARCHAR(50) NOT NULL COMMENT 'GoodsID',
  regdate  DATETIME    NOT NULL DEFAULT now(),
  PRIMARY KEY (id)
);

CREATE TABLE request
(
  user_id          INT         NOT NULL COMMENT 'UserID',
  purchase_item_id INT         NOT NULL,
  type             VARCHAR(20) NOT NULL DEFAULT 'paid',
  result           VARCHAR(50) NOT NULL DEFAULT 'processing',
  PRIMARY KEY (user_id, purchase_item_id)
);

CREATE TABLE request_image
(
  id          INT          NOT NULL,
  file_name   VARCHAR(200) NOT NULL,
  user_id     INT          NOT NULL COMMENT 'UserID',
  purchase_id INT          NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE review
(
  id       INT          NOT NULL AUTO_INCREMENT COMMENT 'ReviewID',
  user_id  INT          NOT NULL COMMENT 'UserID',
  goods_id VARCHAR(50)  NOT NULL COMMENT 'GoodsID',
  regdate  DATETIME     NULL     DEFAULT now() COMMENT 'ReviewRegDate',
  title    VARCHAR(100) NOT NULL COMMENT 'ReviewTitle',
  content  VARCHAR(200) NOT NULL COMMENT 'ReviewContent',
  rate     DOUBLE       NULL     DEFAULT 0 COMMENT 'ReviewRate',
  PRIMARY KEY (id)
) COMMENT '상품리뷰';

CREATE TABLE review_image
(
  review_id INT          NOT NULL COMMENT 'ReviewID',
  id        INT          NOT NULL,
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
  grade        ENUM('Bronze','Silver','Gold','Diamond')        NULL     DEFAULT 'Bronze' COMMENT 'Grade',
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
    FOREIGN KEY (goods_id)
    REFERENCES goods (productId);

ALTER TABLE comment
  ADD CONSTRAINT FK_post_TO_comment
    FOREIGN KEY (post_id)
    REFERENCES post (id);

ALTER TABLE review
  ADD CONSTRAINT FK_goods_TO_review
    FOREIGN KEY (goods_id)
    REFERENCES goods (productId);

ALTER TABLE coupon_box
  ADD CONSTRAINT FK_user_TO_coupon_box
    FOREIGN KEY (user_id)
    REFERENCES user (id);

ALTER TABLE post
  ADD CONSTRAINT FK_user_TO_post
    FOREIGN KEY (user_id)
    REFERENCES user (id);

ALTER TABLE basket
  ADD CONSTRAINT FK_user_TO_basket
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

ALTER TABLE purchase_item
  ADD CONSTRAINT FK_purchase_TO_purchase_item
    FOREIGN KEY (purchase_id)
    REFERENCES purchase (id);

ALTER TABLE purchase_item
  ADD CONSTRAINT FK_basket_TO_purchase_item
    FOREIGN KEY (basket_id)
    REFERENCES basket (id);

ALTER TABLE recent_item
  ADD CONSTRAINT FK_user_TO_recent_item
    FOREIGN KEY (user_id)
    REFERENCES user (id);

ALTER TABLE recent_item
  ADD CONSTRAINT FK_goods_TO_recent_item
    FOREIGN KEY (goods_id)
    REFERENCES goods (productId);

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
    REFERENCES goods (productId);

ALTER TABLE options
  ADD CONSTRAINT FK_purchase_item_TO_options
    FOREIGN KEY (purchase_item_id)
    REFERENCES purchase_item (id);

ALTER TABLE request
  ADD CONSTRAINT FK_user_TO_request
    FOREIGN KEY (user_id)
    REFERENCES user (id);

ALTER TABLE request
  ADD CONSTRAINT FK_purchase_item_TO_request
    FOREIGN KEY (purchase_item_id)
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
    FOREIGN KEY (user_id, purchase_id)
    REFERENCES request (user_id, purchase_item_id);
