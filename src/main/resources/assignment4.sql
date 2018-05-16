-- MySQL dump 10.13  Distrib 5.7.22, for Linux (x86_64)
--
-- Host: localhost    Database: assignment4
-- ------------------------------------------------------
-- Server version	5.7.21-0ubuntu0.16.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `book` (
  `id_book` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 NOT NULL,
  `author` varchar(255) CHARACTER SET utf8 NOT NULL,
  `description` longtext CHARACTER SET utf8,
  `created_at` date NOT NULL,
  `updated_at` date NOT NULL,
  `image` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `enabled` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  PRIMARY KEY (`id_book`),
  KEY `fk_Book_User_idx` (`id_user`),
  CONSTRAINT `fk_Book_User` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (6,'The Railway Children','Illustrated','Case Closed (US title), also known worldwide as Detective Conan (名探偵コナン Meitantei Konan), is an ongoing Japanese detective manga series written and illustrated by Gosho Aoyama. ','2018-10-10','2018-04-26',NULL,1,1),(10,'Wonder','R.J.Palacio','It was serialized in Shogakukan\'s Weekly Shōnen Sunday on January 19, 1994, and has been collected into 94 tankōbon volumes as of December 18, 2017. ','2018-04-23','2018-04-26',NULL,1,1),(11,'television',' special titled','In 2009, a television special titled Lupin the 3rd vs. Detective Conan was aired featuring characters from Lupin III.','2018-04-23','2018-04-26',NULL,1,1),(16,'Americanized','Funimation','Funimation licensed the anime series for North American broadcast in 2003 under the name Case Closed with the characters given Americanized names','2018-04-24','2018-04-26',NULL,1,1),(17,'The anime','Cartoon','The anime premiered on Cartoon Network as part of their Adult Swim programming block but was discontinued due to low ratings.','2018-04-24','2018-04-26',NULL,1,1),(18,'Crunchyroll','Funimation','In March 2013, Funimation began streaming their licensed episodes of Case Closed; Crunchyroll simulcast them in 2014. ','2018-04-24','2018-04-26',NULL,1,1),(19,'Shogakukan ',' Manga Award','The tankōbon volumes of the manga have sold over 200 million copies worldwide, making it the fourth best-selling manga series. In 2001, the manga was awarded the 46th in the shōnen category. ','2018-04-24','2018-04-26',NULL,1,1),(20,' Animage\'s ','Case Closed','The anime adaptation has been well received and ranked in the top twenty inpolls between 1996 and 2001. In the Japanese anime television ranking,','2018-04-24','2018-04-26',NULL,1,1),(21,'Shinichi Kudo','Jimmy Kudo',' (Japanese: ) is a high school detective who sometimes works with the police to solve cases.[4] During an investigation, he is attacked by members of a crime syndicate known as the Black Organization.','2018-04-24','2018-04-26',NULL,1,1),(22,'transforms','instead','They force him to ingest an experimental poison, but instead of killing him, the poison transforms him into a child.[5]','2018-04-24','2018-04-26',NULL,1,1),(23,'Throughout','Richard\'s','who is a private detective.  the series, he tags along on  cases, but when he is able to solve one, he uses a tranquilizer needle to put Richard to sleep','2018-04-24','2018-04-26',NULL,1,1),(24,'Detective club','Detective Boys','and impersonates his voice using a voice changer to reveal the solution to the case.[6] He also enrolls in a local elementary school where he makes','2018-04-24','2018-04-26',NULL,1,1),(25,'Charlotte\'s Web','E. B. White','The Doraemon manga series was first published in December 1969 in six different magazines. A total of 1,345 stories were created in the original series','2018-04-24','2018-04-26',NULL,1,1),(26,'Black Organization','Dr. Agasa,','While he continues to dig deeper into the , he frequently interacts with a variety of characters, including his professor friend ','2018-04-24','2018-04-26',NULL,1,1),(27,'Fujiko Fujio ','Shogakukan','which are published by Shogakukan. The volumes are collected in the Takaoka Central Library in Toyama, Japan, where Fujiko Fujio was born.','2018-04-24','2018-04-26',NULL,1,1),(28,'Amazon Kindle','Voyager','In July 2013  Japan announced the manga would be released digitally in English via the  e-book service. It is one of the best-selling manga in the world, having sold over','2018-04-24','2018-04-26',NULL,1,1),(29,'Suneo','Doraemon','and the cunning and arrogant  Honekawa. A typical story consists of  using one of his gadgets in order to assist Nobita in various ways, often causing more trouble than he was trying to solve.','2018-04-24','2018-04-26',NULL,1,1),(30,' Doraemon ','English-language','Turner Broadcasting System bought the rights to theanime series in the mid-1980s for an  release in the United States,[1] but cancelled it without explanation before broadcasting any episodes.','2018-04-24','2018-04-26',NULL,1,1),(31,'Cartoonists','Doraemon','Awards for  include the Japan  Association Award for excellence in 1973, the first Shogakukan Manga Award for children\'s manga in 1982','2018-04-24','2018-04-26',NULL,1,1),(32,'Conan 93','Shogakukan','In December 1969 the Doraemon manga appeared in six different children\'s monthly magazines published by . The magazines were aimed at children from','2018-04-24','2018-04-26',NULL,1,1),(33,'CoroCoro','Comic1234','nursery school to fourth grade. In 1977 CoroCoro Comic was launched as the flagship magazine of Doraemon.[4]','2018-04-24','2018-04-26',NULL,1,1),(34,'Comics','Doraemon','Since the debut of  in 1969, the stories have been selectively collected into forty-five tankōbon volumes, which were published under Shogakukan\'s Tentōmushi  imprint','2018-04-24','2018-04-26',NULL,1,1),(35,'Nobita\'s','Takeshi','Nobita\'s closest friend is Shizuka Minamoto, who also serves as his romantic interest and eventually becomes his wife. Nobita is usually tormented by the bullying Takeshi Goda (nicknamed \"Gian\"), ','2018-04-24','2018-04-26',NULL,1,1),(36,'Nobita Nobi','Doraemon',' is a young boy who suffers from poor grades, frequent bullying and negative emotions like sadness and jealousy. Many years in the future, one of his descendants sends the robotic cat ','2018-04-24','2018-04-26',NULL,1,1),(37,'Me Before You','Jojo Moyes','Clock-change time in America adjustment:\nAlthough the clock in Vietnam will remain the same, America time will change at times annually.','2018-04-24','2018-04-26',NULL,1,1),(38,'Ran Mouri','Kogoro Mouri','Adopting the pseudonym Conan Edogawa and keeping his true identity a secret, Kudo lives with his childhood friend Rachel Moore () and her father Richard ()','2018-04-24','2018-04-26',NULL,1,1),(39,'The series','Entertainment.','The series received an anime adaptation by Yomiuri Telecasting Corporation and TMS  The anime resulted in animated feature films, ','2018-04-24','2018-04-26',NULL,1,1),(40,'The Alchemist','Paulo Coelho','The story follows an amateur detective who was transformed into a child while investigating a mysterious organization, and solves a ','2018-04-24','2018-04-26',NULL,1,1);
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book_delete`
--

DROP TABLE IF EXISTS `book_delete`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `book_delete` (
  `id_book` int(11) NOT NULL,
  `title` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `author` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `description` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `created_at` date NOT NULL,
  `updated_at` date NOT NULL,
  `image` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `enabled` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  PRIMARY KEY (`id_book`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book_delete`
--

LOCK TABLES `book_delete` WRITE;
/*!40000 ALTER TABLE `book_delete` DISABLE KEYS */;
INSERT INTO `book_delete` VALUES (2,'Spring','SpringSpring','SpringSpringSpringSpringSpringSpringSpringSpringSpringSpringSpringSpringSpringSpring','2018-10-10','2018-04-21','',1,1),(3,'Spring data JPA 2','Thanh Nhancfcvdcf','Clock-change time in America adjustment','2018-10-10','2018-04-22','',1,54),(5,'Chien tranh','Nguyen Lam','Khong co mo ta nao','2018-10-10','2018-04-23',NULL,1,8),(6,'delete delete','delete delete','delete delete','2018-04-21','2018-04-21',NULL,0,1),(7,'Spring Data JPA','Spring Data','Spring Data JPASpring Data JPASpring Data JPASpring Data JPASpring Data JPA','2018-04-22','2018-04-26',NULL,1,54),(8,'Query','Query Methods','Query MethodsQuery MethodsQuery MethodsQuery MethodsQuery MethodsQuery MethodsQuery MethodsQuery MethodsQuery MethodsQuery MethodsQuery MethodsQuery sec:authorize=\"hasAnyRole(\'ROLE_USER\')\"sec:authori','2018-04-22','2018-04-23',NULL,1,54),(9,'Oxford Learner\'s','Jay Asher','Due to legal considerations with the name Detective Conan, the English language release was renamed to Case Closed.[4] ','2018-04-23','2018-04-26',NULL,1,54),(12,'fdfff','ffffffffff','','2018-04-23','2018-04-23',NULL,0,54),(13,'hhhh3','hhhhhh','hhhhh','2018-04-23','2018-04-23',NULL,0,54),(14,'hhhhhh','hhhhhhhhhhoooo','hhhhhhhhhhhhh','2018-04-23','2018-04-23',NULL,0,54),(15,'ooooo','ooooooooo','ooooooooo','2018-04-23','2018-04-23',NULL,0,54),(41,'da sua333','lllllll','','2018-04-24','2018-04-24',NULL,0,1),(43,'conan92','Aoyama Gōshō','','2018-04-24','2018-04-24',NULL,0,42),(69,'hihi','hihi57',NULL,'2018-10-10','2018-10-10',NULL,1,57),(70,'can xoa','can xoa','can xoa','2018-10-10','2018-10-10',NULL,1,8);
/*!40000 ALTER TABLE `book_delete` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comment` (
  `id_comment` int(11) NOT NULL AUTO_INCREMENT,
  `message` text COLLATE utf8_unicode_ci NOT NULL,
  `id_user` int(11) NOT NULL,
  `id_book` int(11) NOT NULL,
  `created_at` date NOT NULL,
  `updated_at` date NOT NULL,
  PRIMARY KEY (`id_comment`),
  KEY `fk_Comment_User_idx` (`id_user`),
  KEY `fk_Comment_Book_idx` (`id_book`),
  CONSTRAINT `fk_Comment_Book` FOREIGN KEY (`id_book`) REFERENCES `book` (`id_book`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_Comment_User` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment_delete`
--

DROP TABLE IF EXISTS `comment_delete`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comment_delete` (
  `id_comment` int(11) NOT NULL,
  `message` text COLLATE utf8_unicode_ci NOT NULL,
  `id_user` int(11) NOT NULL,
  `id_book` int(11) NOT NULL,
  `created_at` date NOT NULL,
  `updated_at` date NOT NULL,
  PRIMARY KEY (`id_comment`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment_delete`
--

LOCK TABLES `comment_delete` WRITE;
/*!40000 ALTER TABLE `comment_delete` DISABLE KEYS */;
INSERT INTO `comment_delete` VALUES (2,'Also, note that the JavaConfig variant doesn’t configure a package explictly as the package of the annotated class is used by default. To customize the package to scan use one of the basePackage… attribute of the data-store specific repository @Enable…-annotation.',10,2,'2018-10-10','2018-10-10'),(3,'Also, note that the JavaConfig variant doesn’t configure a package explictly as the package of the annotated class is used by default. To customize the package to scan use one of the basePackage… attribute of the data-store specific repository @Enable…-annotation.',10,2,'2018-10-10','2018-10-10'),(4,'dax xoa',57,69,'2018-10-10','2018-10-10'),(5,'xo xox xox xo',8,70,'2018-10-10','2018-10-10'),(6,'ddd ccc vvv',1,70,'2018-10-10','2018-10-10');
/*!40000 ALTER TABLE `comment_delete` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role` (
  `id_role` int(11) NOT NULL AUTO_INCREMENT,
  `name_role` varchar(45) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`id_role`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_USER');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_delete`
--

DROP TABLE IF EXISTS `role_delete`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_delete` (
  `id_role` int(11) NOT NULL AUTO_INCREMENT,
  `name_role` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id_role`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_delete`
--

LOCK TABLES `role_delete` WRITE;
/*!40000 ALTER TABLE `role_delete` DISABLE KEYS */;
INSERT INTO `role_delete` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_USER');
/*!40000 ALTER TABLE `role_delete` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id_user` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(100) CHARACTER SET utf8 NOT NULL,
  `password` varchar(100) CHARACTER SET utf8 NOT NULL,
  `first_name` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `last_name` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `enabled` int(11) NOT NULL,
  `avatar` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `id_role` int(11) NOT NULL,
  PRIMARY KEY (`id_user`),
  UNIQUE KEY `UKob8kqyqqgmefl0aco34akdtpe` (`email`),
  KEY `fk_user_role_idx` (`id_role`),
  CONSTRAINT `fk_user_role` FOREIGN KEY (`id_role`) REFERENCES `role` (`id_role`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'admin@gmail.com','$2a$10$ixzSY.L9zeL2fbE41Qa/GuP4nDUEoBtQgXuoM1aTTglWrQPuyr5pe','Nguyen','Tuyen',1,NULL,1),(10,'as','$2a$10$71v2gy6YBClk.kvDEQ7ZY.DLipJnYILURXWYiJloetOftnrNntWSS','Lam','Hai',1,NULL,2);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_delete`
--

DROP TABLE IF EXISTS `user_delete`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_delete` (
  `id_user` int(11) NOT NULL,
  `email` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `first_name` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `last_name` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `enabled` int(11) NOT NULL,
  `avatar` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `id_role` int(11) NOT NULL,
  PRIMARY KEY (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_delete`
--

LOCK TABLES `user_delete` WRITE;
/*!40000 ALTER TABLE `user_delete` DISABLE KEYS */;
INSERT INTO `user_delete` VALUES (1,'admin@gmail.com','$2a$10$ixzSY.L9zeL2fbE41Qa/GuP4nDUEoBtQgXuoM1aTTglWrQPuyr5pe','Nguyen','Tuyen',0,NULL,1),(8,'d@gmail.com','$2a$04$GYGsaJj9l6kH2GikK6QVzO0v3sOCxt3vdkiA2/tcoSw8erI85ZYDG','s','ddddddddddddddd',0,NULL,1),(42,'d@gmail.com','$2a$10$FoD83MJ/jKC9n0jxQJWyA.R4fiStB4l53HAyfkWkHaR4Hbv0RXaT.','demo','test',0,NULL,2),(46,'admin333@gmail.com','123456','trrr','rrrt',0,NULL,2),(53,'Java12','$2a$10$eQXiF1v/70P.Q2LkuiJ3z.b99yD811Ux685n4mGTbuWqkuddnR4Ci','Java','dddd',0,NULL,2),(54,'a@gmail.com','$2a$10$o06zgXwsPGOtF9LhAwF4rebNW1GhpSKj3ALFiqquLp4gvVBx0rv/y','Ly','Mai 2',0,NULL,2),(56,'admin@gmail.comj','$2a$10$L8tTqQtUrM9.lmqpJt0pruUfAbtRs.5apLsCNlRGsokqxKq/c/yGy','Tinh','van',0,NULL,2),(57,'admindemoccc@gm.com','$2a$10$ixzSY.L9zeL2fbE41Qa/GuP4nDUEoBtQgXuoM1aTTglWrQPuyr5pe','','',0,NULL,2);
/*!40000 ALTER TABLE `user_delete` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-04-26  1:51:06
