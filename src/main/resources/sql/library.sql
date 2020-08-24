DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(32) NOT NULL COMMENT '用户名',
  `pwd` varchar(64) NOT NULL COMMENT '密码',
  `status` int DEFAULT NULL,
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_name` (`name`) USING BTREE COMMENT '用户名唯一'
) ENGINE=InnoDB CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户表';

DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`(
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(200) NOT NULL COMMENT '角色名',
  `status` int DEFAULT NULL,
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=INNODB CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='角色表';

DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`(
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(200) NOT NULL COMMENT '权限名',
  `description` VARCHAR(200) DEFAULT NULL COMMENT '描述',
  `url` VARCHAR(200) NOT NULL COMMENT '路径',
  `pid` BIGINT DEFAULT NULL COMMENT '',
  `status` int DEFAULT NULL,
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=INNODB CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='权限表';


DROP TABLE IF EXISTS `role_user`;
CREATE TABLE `role_user`(
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT UNSIGNED NOT NULL COMMENT '用户id',
  `role_id` BIGINT UNSIGNED NOT NULL COMMENT '角色id',
  `status` int DEFAULT NULL,
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_uid_rid` (`user_id`, `role_id`) USING BTREE COMMENT '用户角色唯一'
) ENGINE=INNODB CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='书籍类型';


DROP TABLE IF EXISTS `permission_role`;
CREATE TABLE `permission_role`(
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `role_id` BIGINT UNSIGNED NOT NULL COMMENT '角色id',
  `permission_id` BIGINT UNSIGNED NOT NULL COMMENT '用户id',
  `status` int DEFAULT NULL,
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_pid_rid` (`permission_id`, `role_id`) USING BTREE COMMENT '权限角色唯一'
) ENGINE=INNODB CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='权限角色表';

DROP TABLE IF EXISTS `lib_book_type`;
CREATE TABLE IF NOT EXISTS `lib_book_type` (
  `id` int AUTO_INCREMENT NOT NULL comment '主键',
  `type_name` varchar(50) comment '种类名称',
  `type_intro` varchar(200) comment '种类简介',
  `status` int DEFAULT NULL,
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT='书籍类型';

DROP TABLE IF EXISTS `lib_book`;
CREATE TABLE IF NOT EXISTS `lib_book` (
  `id` int AUTO_INCREMENT NOT NULL comment '主键',
  `book_name` varchar(50) comment '书名称' ,
  `book_intro` varchar(200) comment '书简介',
  `book_price` bigint(20) comment '单价',
  `type_id` int not null comment '种类',
  `pub_id` int not null comment '出版社',
  `image_url` varchar(200) comment '缩略图url',
  `author` varchar(200) comment '作者',
  `repertory_size` int comment '库存数量',
  `status` int DEFAULT NULL,
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_name_author` (`book_name`, `author`) USING BTREE COMMENT '书名作者唯一',
  KEY index_author (`author`) USING BTREE COMMENT '作者索引'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT='书籍';

DROP TABLE IF EXISTS `lib_book_borrow`;
CREATE TABLE IF NOT EXISTS `lib_book_borrow` (
  `id` int AUTO_INCREMENT NOT NULL comment '书名称',
  `book_id` int not null comment '书id',
  `user_id` int not null comment '用户id',
  `status` int DEFAULT NULL,
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY index_uid_bid (`user_id`, `book_id`) USING BTREE COMMENT '用户id与书籍id索引'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT='书的借出归还记录';


insert into USER (id, name, pwd, status, create_time, update_time) values (1, 'admin', '$2a$10$oRW15xDfepZR3HWjJ.fwNOvs2H3Fwt.4yN4cvkJhBCzjWjifCkqCi', 1, now(), now());
insert into USER (id, name, pwd, status, create_time, update_time) values (2,'abel', '$2a$10$32fpuIiMEHaijsOyxKxyIeMW1gqWKWfi7cFi155wYMkqUW4g084h6', 1, now(), now());

insert into ROLE(id, name, status, create_time, update_time) values(1,'ROLE_ADMIN', 1, now(), now());
insert into ROLE(id, name, status, create_time, update_time) values(2,'ROLE_USER', 1, now(), now());

insert into ROLE_USER(USER_ID, role_id, status, create_time, update_time) values(1, 1, 1, now(), now());
insert into ROLE_USER(USER_ID, role_id, status, create_time, update_time) values(2, 2, 1, now(), now();

INSERT INTO `permission` VALUES ('1', 'ROLE_HOME', 'home', '/', null, 1, now(), now()), ('2', 'ROLE_ADMIN', 'ABel', '/admin', null, 1, now(), now());
INSERT INTO `permission_role` VALUES ('1', '1', '1', 1, now(), now()), ('2', '1', '2', 1, now(), now()), ('3', '2', '1', 1, now(), now());

INSERT INTO `library`.`lib_book`(`id`, `book_name`, `book_intro`, `book_price`, `type_id`, `pub_id`, `image_url`, `author`, `repertory_size`, `status`, `create_time`, `update_time`) VALUES (1, 'test', 'test', 11111, 1, 1, '1', '1', 0, 1, '2020-08-23 19:52:54', '2020-08-23 19:52:58');
 