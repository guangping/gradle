/**基础公用字段*/
ALTER TABLE table_name
  ADD COLUMN is_deleted VARCHAR(2) NOT NULL DEFAULT '0'
COMMENT '是否删除 0 正常 1 删除';
ALTER TABLE table_name
  ADD COLUMN create_time DATETIME NOT NULL DEFAULT current_timestamp
COMMENT '创建时间';
ALTER TABLE table_name
  ADD COLUMN update_time DATETIME NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp
COMMENT '操作时间';
ALTER TABLE table_name
  ADD COLUMN remark VARCHAR(2048) COMMENT '备注';

/**权限基础字段**/
ALTER TABLE table_name
  ADD COLUMN create_user_id INTEGER NOT NULL DEFAULT '-1'
COMMENT '创建人编码';
ALTER TABLE table_name
  ADD COLUMN update_user_id INTEGER NOT NULL DEFAULT '-1'
COMMENT '修改人编码';

ALTER TABLE table_name
  ADD COLUMN create_org_id INTEGER NOT NULL DEFAULT '-1'
COMMENT '创建人组织机构编码';
ALTER TABLE table_name
  ADD COLUMN create_depart_id INTEGER NOT NULL DEFAULT '-1'
COMMENT '创建人部门编码';


/**基础配置表*/
CREATE TABLE sys_config (
  id           INT(11)              AUTO_INCREMENT,
  config_name  VARCHAR(128) COMMENT '参数名称',
  config_key   VARCHAR(64) NOT NULL
  COMMENT 'key值',
  config_value VARCHAR(1024) COMMENT '数据值',
  is_deleted   VARCHAR(2)  NOT NULL DEFAULT '0'
  COMMENT '是否删除 0 正常 1 删除',
  create_time  DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP
  COMMENT '创建时间',
  update_time  DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
  COMMENT '操作时间',
  PRIMARY KEY (id),
  UNIQUE (config_key)
)
  ENGINE = INNODB
  DEFAULT CHARSET = utf8
  COMMENT '基础配置表';


/**值集**/
CREATE TABLE sys_value_set (
  id          INT(11)               AUTO_INCREMENT,
  group_code  VARCHAR(256) NOT NULL
  COMMENT '分组code',
  group_name  VARCHAR(256) NOT NULL
  COMMENT '分组名称',
  is_deleted  VARCHAR(2)   NOT NULL DEFAULT '0'
  COMMENT '是否删除 0 正常 1 删除',
  create_time DATETIME     NOT NULL DEFAULT current_timestamp
  COMMENT '创建时间',
  update_time DATETIME     NOT NULL DEFAULT current_timestamp ON UPDATE current_timestamp
  COMMENT '操作时间',
  PRIMARY KEY (id),
  UNIQUE (group_code)
)
  ENGINE = innodb
  DEFAULT CHARSET = utf8
  COMMENT '值集分组表';

CREATE TABLE sys_value_data (
  id              INT(11)                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             AUTO_INCREMENT,
  group_id        INT        NOT NULL                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        DEFAULT '-1'
  COMMENT '分组ID',
  value_set_key   VARCHAR(32) COMMENT '数据key',
  value_set_value VARCHAR(256) COMMENT '数据名、显示使用',
  is_default      VARCHAR(2) NOT NULL                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 DEFAULT '0'
  COMMENT '是否默认 0 不是 1 是',
  order           INT        NOT NULL                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 DEFAULT 0
  COMMENT '排序',
  is_deleted      VARCHAR(2) NOT NULL                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 DEFAULT '0'
  COMMENT '是否删除 0 正常 1 删除',
  create_time     DATETIME   NOT NULL                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 DEFAULT current_timestamp
  COMMENT '创建时间',
  update_time     DATETIME   NOT NULL                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 DEFAULT current_timestamp ON UPDATE current_timestamp
  COMMENT '操作时间',
  PRIMARY KEY (id)
)
  ENGINE = innodb
  DEFAULT CHARSET = utf8
  COMMENT '值集数据表';


/**文件记录*/
CREATE TABLE sys_doc_file (
  id          INT(11)                AUTO_INCREMENT,
  file_code VARCHAR(32)   NOT NULL
  COMMENT '编码',
  file_name VARCHAR(1024) NOT NULL
  COMMENT '文件名称',
  file_size INT(11)       NOT NULL DEFAULT 0
  COMMENT '文件大小',
  file_path VARCHAR(1024) COMMENT '文件路径',
  file_type VARCHAR(32) COMMENT '文件类型',
  is_deleted VARCHAR(2)   NOT NULL  DEFAULT '0'
  COMMENT '是否删除 0 正常 1 删除',
  create_time DATETIME    NOT NULL   DEFAULT current_timestamp
  COMMENT '创建时间',
  update_time DATETIME    NOT NULL   DEFAULT current_timestamp ON UPDATE current_timestamp
  COMMENT '操作时间',
  type VARCHAR(2)  NOT NULL   DEFAULT '0'
  COMMENT '0 普通文件 1 模板文件',
  PRIMARY KEY (id),
  UNIQUE (file_code)
)
  ENGINE = innodb
  DEFAULT CHARSET = utf8
  COMMENT '文件记录表';


/**操作日志*/







