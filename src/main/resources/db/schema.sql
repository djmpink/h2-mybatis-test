CREATE TABLE IF NOT EXISTS
  T_DEMO (
  ID   BIGINT AUTO_INCREMENT PRIMARY KEY,
  NAME VARCHAR(255) NOT NULL
);

-- DROP TABLE IF EXISTS T_LOG_CONFIG;
CREATE TABLE IF NOT EXISTS
  T_LOG_CONFIG (
  ID     BIGINT AUTO_INCREMENT PRIMARY KEY,
  LOG_ID VARCHAR(100),
  IP     VARCHAR(100),
  NAME   VARCHAR(200),
  PATH   VARCHAR(400),
  TYPE   VARCHAR(100),
  TAGS   VARCHAR(200),
  createUserId   VARCHAR(200),
  modifyUserId   VARCHAR(200),
  createTime   datetime,
  modifyTime   datetime,
  isValid   BIT
);