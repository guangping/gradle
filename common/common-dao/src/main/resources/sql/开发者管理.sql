DROP TABLE IF EXISTS api_user;

CREATE TABLE api_user
(
  id   INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(64) COMMENT '开发者名称',
  PRIMARY KEY (id)
);

ALTER TABLE api_user
  COMMENT '开发者';

ALTER TABLE api_user
  ADD COLUMN is_deleted VARCHAR(2) NOT NULL DEFAULT '0'
COMMENT '是否删除 0 正常 1 删除';
ALTER TABLE api_user
  ADD COLUMN create_time DATETIME NOT NULL DEFAULT current_timestamp
COMMENT '创建时间';
ALTER TABLE api_user
  ADD COLUMN update_time DATETIME NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp
COMMENT '操作时间';
ALTER TABLE api_user
  ADD COLUMN remark VARCHAR(1024) COMMENT '备注';

/****/
DROP TABLE IF EXISTS api_application_category;

CREATE TABLE api_application_category
(
  id   INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(64) COMMENT '类型名称',
  PRIMARY KEY (id)
);

ALTER TABLE api_application_category
  COMMENT '应用类型';

ALTER TABLE api_application_category
  ADD COLUMN is_deleted VARCHAR(2) NOT NULL DEFAULT '0'
COMMENT '是否删除 0 正常 1 删除';
ALTER TABLE api_application_category
  ADD COLUMN create_time DATETIME NOT NULL DEFAULT current_timestamp
COMMENT '创建时间';
ALTER TABLE api_application_category
  ADD COLUMN update_time DATETIME NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp
COMMENT '操作时间';
ALTER TABLE api_application_category
  ADD COLUMN remark VARCHAR(1024) COMMENT '备注';

/****/
DROP TABLE IF EXISTS api_application;

CREATE TABLE api_application
(
  id                      INT         NOT NULL AUTO_INCREMENT,
  code                    VARCHAR(32) NOT NULL
  COMMENT '编号',
  name                    VARCHAR(64) COMMENT '应用名称',
  api_key                 VARCHAR(64) NOT NULL
  COMMENT 'api key',
  secret_key              VARCHAR(64) NOT NULL
  COMMENT 'secret key',
  application_category_id INT COMMENT '应用类型编码',
  application_level       VARCHAR(32) COMMENT '应用等级',
  remark                  VARCHAR(1024) COMMENT '应用描述',
  PRIMARY KEY (id)
);

ALTER TABLE api_application
  COMMENT '应用管理';

ALTER TABLE api_application
  ADD COLUMN user_id INT NOT NULL
COMMENT '开发者';
ALTER TABLE api_application
  ADD COLUMN is_deleted VARCHAR(2) NOT NULL DEFAULT '0'
COMMENT '是否删除 0 正常 1 删除';
ALTER TABLE api_application
  ADD COLUMN create_time DATETIME NOT NULL DEFAULT current_timestamp
COMMENT '创建时间';
ALTER TABLE api_application
  ADD COLUMN update_time DATETIME NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp
COMMENT '操作时间';

/****/
DROP TABLE IF EXISTS api_modules;

CREATE TABLE api_modules
(
  id   INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(64) COMMENT '模块名称',
  PRIMARY KEY (id)
);

ALTER TABLE api_modules
  COMMENT '能力模块';

ALTER TABLE api_modules
  ADD COLUMN is_deleted VARCHAR(2) NOT NULL DEFAULT '0'
COMMENT '是否删除 0 正常 1 删除';
ALTER TABLE api_modules
  ADD COLUMN create_time DATETIME NOT NULL DEFAULT current_timestamp
COMMENT '创建时间';
ALTER TABLE api_modules
  ADD COLUMN update_time DATETIME NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp
COMMENT '操作时间';
ALTER TABLE api_modules
  ADD COLUMN remark VARCHAR(1024) COMMENT '备注';

/****/
DROP TABLE IF EXISTS api_open_capacity;

CREATE TABLE api_open_capacity
(
  id     INT NOT NULL AUTO_INCREMENT,
  name   VARCHAR(128) COMMENT '名称',
  remark VARCHAR(1024) COMMENT '描述',
  url    VARCHAR(256) COMMENT '地址',
  files  VARCHAR(512) COMMENT '附件',
  PRIMARY KEY (id)
);

ALTER TABLE api_open_capacity
  COMMENT '开放能力';

ALTER TABLE api_open_capacity
  ADD COLUMN modules_id INT
COMMENT '所属模块';
ALTER TABLE api_open_capacity
  ADD COLUMN is_deleted VARCHAR(2) NOT NULL DEFAULT '0'
COMMENT '是否删除 0 正常 1 删除';
ALTER TABLE api_open_capacity
  ADD COLUMN create_time DATETIME NOT NULL DEFAULT current_timestamp
COMMENT '创建时间';
ALTER TABLE api_open_capacity
  ADD COLUMN update_time DATETIME NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp
COMMENT '操作时间';

/**应用对应能力*/
DROP TABLE IF EXISTS api_application_capacity;

CREATE TABLE api_application_capacity
(
  id               INT NOT NULL AUTO_INCREMENT,
  application_id   INT COMMENT '应用ID',
  open_capacity_id INT COMMENT '开放能力ID',
  PRIMARY KEY (id)
);
ALTER TABLE api_application_capacity
  COMMENT '应用开放能力';

ALTER TABLE api_application_capacity
  ADD COLUMN create_time DATETIME NOT NULL DEFAULT current_timestamp
COMMENT '创建时间';
ALTER TABLE api_application_capacity
  ADD COLUMN update_time DATETIME NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp
COMMENT '操作时间';















