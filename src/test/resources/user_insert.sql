LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user`(`id`,`description`,`name`, `password`,`status`,`username`,`active`,`created_by`,`created_date`) VALUES (1,'System Administrator','admin','$2a$10$HqidyHh3WogLEDe5vWOfsOtfLL1AKMBqkp78mqVAeJ4kNYtZp.fJe','ACTIVE','admin@admin.com',1,1,"2017-01-01 00:00:000");
INSERT INTO `user`(`id`,`description`,`name`, `password`,`status`,`username`,`active`,`created_by`,`created_date`) VALUES (2,'System common user','user','$2a$10$HqidyHh3WogLEDe5vWOfsOtfLL1AKMBqkp78mqVAeJ4kNYtZp.fJe','ACTIVE','user@user.com',1,1,"2017-01-01 00:00:000");
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

LOCK TABLES `user_roles` WRITE;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
INSERT INTO `user_roles` VALUES (1,'USER'),(1,'ADMIN'),(2,'USER');
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
UNLOCK TABLES;