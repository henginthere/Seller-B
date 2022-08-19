-- MariaDB dump 10.19  Distrib 10.9.1-MariaDB, for Win64 (AMD64)
--
-- Host: i7d105.p.ssafy.io    Database: sellerB
-- ------------------------------------------------------
-- Server version	10.8.3-MariaDB-1:10.8.3+maria~jammy

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
-- Table structure for table `t_review`
--

DROP TABLE IF EXISTS `t_review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_review` (
  `review_seq` bigint(20) NOT NULL AUTO_INCREMENT,
  `review_content` varchar(255) DEFAULT NULL,
  `review_del_yn` tinyint(1) DEFAULT 0,
  `review_grade` float DEFAULT NULL,
  `review_mod_date` datetime(6) DEFAULT NULL,
  `review_mod_user` varchar(255) DEFAULT NULL,
  `review_reg_date` datetime(6) DEFAULT NULL,
  `review_reg_user` varchar(255) DEFAULT NULL,
  `consulting_seq` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`review_seq`),
  KEY `FKf96vrgacsg8fqwhgh56yvkw0s` (`consulting_seq`),
  CONSTRAINT `FKf96vrgacsg8fqwhgh56yvkw0s` FOREIGN KEY (`consulting_seq`) REFERENCES `t_consulting` (`consulting_seq`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_review`
--

LOCK TABLES `t_review` WRITE;
/*!40000 ALTER TABLE `t_review` DISABLE KEYS */;
INSERT INTO `t_review` VALUES
(1,'친절한 상담 감사합니다.',0,5,'2022-08-12 15:36:08.976569','비회원','2022-08-12 15:36:08.976569','비회원',1),
(2,'궁금함이 해소되었어요.',0,4.5,'2022-08-12 07:24:20.326359','비회원','2022-08-12 07:24:20.326359','비회원',2),
(3,'감사합니다.',0,5,'2022-08-12 07:24:41.755956','비회원','2022-08-12 07:24:41.755956','비회원',4),
(4,'상담사 목소리가 너무 작았어요.',0,3,'2022-08-12 07:25:02.216895','비회원','2022-08-12 07:25:02.216895','비회원',5),
(5,'good~',0,4,'2022-08-12 12:14:56.217331','비회원','2022-08-12 12:14:56.217331','비회원',6);
/*!40000 ALTER TABLE `t_review` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-08-17 23:43:43
