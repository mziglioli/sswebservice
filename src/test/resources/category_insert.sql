INSERT INTO `sswebservicetest`.`helparticles`(article_id,`action`,action_name,answer,title) values(1,'/action1','action name','answer1','title1');
INSERT INTO `sswebservicetest`.`helparticles`(article_id,`action`,action_name,answer,title) values(2,'/action2','action name','answer2','title2');
INSERT INTO `sswebservicetest`.`helparticles`(article_id,`action`,action_name,answer,title) values(3,'/action3','action name','answer3','title3');
INSERT INTO `sswebservicetest`.`helparticles`(article_id,`action`,action_name,answer,title) values(4,'/action4','action name','answer4','title4');

INSERT INTO `sswebservicetest`.`helparticle_category` VALUES (1,'icon1','Category1');
INSERT INTO `sswebservicetest`.`helparticle_category` VALUES (2,'icon2','Category2');

INSERT INTO `sswebservicetest`.`helparticle_category_category`(category_id,article_id) VALUES (1,1);
INSERT INTO `sswebservicetest`.`helparticle_category_category`(category_id,article_id) VALUES (1,2);
INSERT INTO `sswebservicetest`.`helparticle_category_category`(category_id,article_id) VALUES (1,3);
INSERT INTO `sswebservicetest`.`helparticle_category_category`(category_id,article_id) VALUES (2,3);
INSERT INTO `sswebservicetest`.`helparticle_category_category`(category_id,article_id) VALUES (2,4);