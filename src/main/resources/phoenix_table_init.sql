-- ORDERS 表主键字段使用的自增序列
CREATE SEQUENCE TEST.ORDER_SEQ START WITH 1 INCREMENT BY 1 MINVALUE 1 MAXVALUE 2147483647 CYCLE CACHE 10;

-- ORDERS 表
CREATE TABLE TEST.ORDERS
(
    USER_ID      CHAR(36) NOT NULL,
    CREATE_TIME  DATE     NOT NULL,
    SEQ UNSIGNED_INT NOT NULL,
    PRODUCT_NAME VARCHAR,
    PRICE        FLOAT,
    CONSTRAINT ROWKEY PRIMARY KEY (USER_ID, CREATE_TIME, SEQ)
);

-- ORDERS 表 PRODUCT_NAME 字段二级索引
CREATE INDEX IDX_PRODUCT_NAME ON TEST.ORDERS (PRODUCT_NAME);

-- PERSON 表
CREATE TABLE TEST.PERSON
(
    USER_ID          CHAR(36) NOT NULL PRIMARY KEY,
    NICK_NAME        VARCHAR,
    BIRTHDAY         DATE,
    LEVEL UNSIGNED_INT,
    ORGANIZATION     VARCHAR,
    ITEM             VARCHAR,
    NAME_FIRST_NAME  VARCHAR,
    NAME_MIDDLE_NAME VARCHAR,
    NAME_LAST_NAME   VARCHAR,
    NAME_CLAN_NAME   VARCHAR,
    NAME_CLAN_AREA   VARCHAR
);

-- PERSON 表级联 list 数据
CREATE TABLE TEST.PERSON_ORGANIZATION
(
    USER_ID  CHAR(36) NOT NULL,
    ORG_NAME VARCHAR  NOT NULL,
    CONSTRAINT ROWKEY PRIMARY KEY (USER_ID, ORG_NAME)
);

-- PERSON 表级联合 map 数据
CREATE TABLE TEST.PERSON_ITEM
(
    USER_ID    CHAR(36) NOT NULL,
    ITEM_KEY   VARCHAR  NOT NULL,
    ITEM_VALUE VARCHAR,
    CONSTRAINT ROWKEY PRIMARY KEY (USER_ID, ITEM_KEY)
);

