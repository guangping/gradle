/**基础公用字段*/
alter table table_name
    add COLUMN is_deleted varchar(2) not null default '0' comment '是否删除 0 正常 1 删除';
alter table table_name add COLUMN create_time datetime not null default  current_timestamp  comment '创建时间';
alter table table_name  add COLUMN update_time datetime not NULL default current_timestamp on update current_timestamp comment '操作时间';
alter table table_name  add COLUMN remark varchar(2048)  comment '备注';

/**权限基础字段**/
alter table table_name
  add COLUMN create_user_id INTEGER not null default '-1' comment '创建人编码';
alter table table_name
  add COLUMN update_user_id INTEGER not null default '-1' comment '修改人编码';

alter table table_name
  add COLUMN create_org_id INTEGER not null default '-1' comment '创建人组织机构编码';
alter table table_name
  add COLUMN create_depart_id INTEGER not null default '-1' comment '创建人部门编码';


/**基础配置表*/
create table sys_config (
	id int (11) auto_increment,
	config_name varchar (256) comment '参数名称',
	config_key varchar (256) not null comment 'key值',
	config_value varchar (1024) comment '数据值',
	is_deleted varchar(2) not null default '0' comment '是否删除 0 正常 1 删除',
	create_time datetime not null default  current_timestamp  comment '创建时间',
	update_time datetime not NULL default current_timestamp on update current_timestamp comment '操作时间',
	primary key (id),
  UNIQUE (config_key)
) engine=innodb default charset=utf8 COMMENT '基础配置表';


/**值集**/
create table sys_value_set (
  id int (11) auto_increment,
  group_code varchar(256) not NULL comment '分组code',
  group_name varchar (256) not null comment '分组名称',
  is_deleted varchar(2) not null default '0' comment '是否删除 0 正常 1 删除',
  create_time datetime not null default  current_timestamp  comment '创建时间',
  update_time datetime not NULL default current_timestamp on update current_timestamp comment '操作时间',
  primary key (id),
  UNIQUE (group_code)
) engine=innodb default charset=utf8 COMMENT '值集分组表';

create table sys_value_data (
  id int (11) auto_increment,
  group_id int NOT NULL DEFAULT '-1' COMMENT '分组ID',
  value_set_key VARCHAR(32) COMMENT '数据key',
  value_set_value VARCHAR(256) COMMENT '数据名、显示使用',
  is_default varchar(2) not null default '0' comment '是否默认 0 不是 1 是',
  order int not NULL DEFAULT 0 COMMENT '排序',
  is_deleted varchar(2) not null default '0' comment '是否删除 0 正常 1 删除',
  create_time datetime not null default  current_timestamp  comment '创建时间',
  update_time datetime not NULL default current_timestamp on update current_timestamp comment '操作时间',
  primary key (id)
) engine=innodb default charset=utf8 COMMENT '值集数据表';


/**文件记录*/
create table sys_doc_file (
  id int (11) auto_increment,
  file_code varchar (32) not NULL COMMENT '编码',
  file_name varchar (1024) NOT NULL COMMENT '文件名称',
  file_size int (11) NOT NULL DEFAULT 0 COMMENT '文件大小',
  file_path varchar (1024) COMMENT '文件路径',
  file_type varchar (32) COMMENT '文件类型',
  is_deleted varchar(2) not null default '0' comment '是否删除 0 正常 1 删除',
  create_time datetime not null default  current_timestamp  comment '创建时间',
  update_time datetime not NULL default current_timestamp on update current_timestamp comment '操作时间',
  is_template varchar(2) not null default '0' comment '是否模板文件 0 不是 1 是',
  PRIMARY KEY (id),
  UNIQUE (file_code)
)engine=innodb default charset=utf8 COMMENT '文件记录表';


/**操作日志*/







