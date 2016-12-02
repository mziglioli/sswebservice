LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'System Administrator','admin','$2a$10$HqidyHh3WogLEDe5vWOfsOtfLL1AKMBqkp78mqVAeJ4kNYtZp.fJe','ACTIVE','admin@admin.com');
INSERT INTO `user` VALUES (2,'System common user','user','$2a$10$HqidyHh3WogLEDe5vWOfsOtfLL1AKMBqkp78mqVAeJ4kNYtZp.fJe','ACTIVE','user@user.com');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

LOCK TABLES `user_roles` WRITE;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
INSERT INTO `user_roles` VALUES (1,'USER'),(1,'ADMIN'),(2,'USER');
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
UNLOCK TABLES;