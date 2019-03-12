create table if not exists `order`.article
(
  id            varchar(100) null,
  articleId     varchar(100) null,
  articleName   varchar(100) null,
  articleValue  varchar(100) null,
  articleImage  varchar(100) null,
  articleAuthor varchar(100) null,
  articleVistit varchar(100) null
);

create table if not exists `order`.category
(
  id           varchar(100) not null comment '目录id',
  categoryId   varchar(100) null,
  categoryName varchar(100) null
);

create table if not exists `order`.order_department
(
  department_id          varchar(255) not null,
  department_name        varchar(255) null,
  department_description varchar(255) null,
  primary key (department_id)
);

create table if not exists `order`.order_department_level
(
  id            varchar(255) null,
  level         varchar(255) null,
  department_id varchar(255) null,
  parent_id     varchar(255) null
);

create table if not exists `order`.order_resource
(
  resource_id          varchar(255) not null,
  resource_name        varchar(255) null,
  resource_description varchar(255) null,
  primary key (resource_id)
)
  comment '资源表（权限集合）';

create table if not exists `order`.order_role
(
  role_id          varchar(255) not null comment '角色id',
  role_name        varchar(255) null comment '角色名称',
  role_description varchar(255) null comment '角色说明',
  primary key (role_id)
)
  comment '角色表';

create table if not exists `order`.order_role_resource
(
  id          varchar(255) not null,
  role_id     varchar(255) null,
  resource_id varchar(255) null,
  primary key (id)
)
  comment '角色权限表';

create table if not exists `order`.order_user
(
  uuid     varchar(255) not null,
  username varchar(255) null,
  password varchar(255) null,
  birthday date         null,
  email    varchar(255) null,
  mobile   varchar(255) null,
  realname varchar(255) null,
  primary key (uuid)
)
  comment '用户表';

create table if not exists `order`.order_user_department
(
  id            varchar(255) not null,
  uuid          varchar(255) null,
  department_id varchar(255) null,
  primary key (id)
);

create table if not exists `order`.order_user_role
(
  id      varchar(255) not null,
  uuid    varchar(255) null,
  role_id varchar(255) null,
  primary key (id)
)
  comment '用户角色映射表';

