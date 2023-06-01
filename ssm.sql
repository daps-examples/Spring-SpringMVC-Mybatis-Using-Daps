

CREATE DATABASE  IF NOT EXISTS `ssm`;
use `ssm`;

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
                         `id` int NOT NULL AUTO_INCREMENT COMMENT '用户编码',
                         `user_name` varchar(255) DEFAULT NULL COMMENT '用户名称',
                         `user_pwd` varchar(255) DEFAULT NULL COMMENT '用户密码',
                         `user_phone` varchar(255) DEFAULT NULL COMMENT '用户手机号',
                         `user_email` varchar(255) DEFAULT NULL COMMENT '用户邮箱',
                         `user_note` varchar(255) DEFAULT NULL COMMENT '用户备注',
                         PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;
INSERT INTO `users` VALUES (1,'admin','admin','15512345678','123@126.com','管理员');

create user ssm identified by 'ssm';
grant all privileges on ssm.* to 'ssm';