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
-- Table structure for table `t_brand`
--

DROP TABLE IF EXISTS `t_brand`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_brand` (
  `brand_seq` bigint(20) NOT NULL AUTO_INCREMENT,
  `brand_del_yn` tinyint(1) DEFAULT 0,
  `brand_logo` varchar(255) DEFAULT NULL,
  `brand_mod_date` datetime(6) DEFAULT NULL,
  `brand_mod_user` varchar(255) DEFAULT NULL,
  `brand_name_eng` varchar(25) DEFAULT NULL,
  `brand_name_kor` varchar(25) DEFAULT NULL,
  `brand_reg_date` datetime(6) DEFAULT NULL,
  `brand_reg_user` varchar(255) DEFAULT NULL,
  `brand_mod_user_seq` int(11) DEFAULT NULL,
  `brand_reg_user_seq` int(11) DEFAULT NULL,
  PRIMARY KEY (`brand_seq`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_brand`
--

LOCK TABLES `t_brand` WRITE;
/*!40000 ALTER TABLE `t_brand` DISABLE KEYS */;
INSERT INTO `t_brand` VALUES
(1,0,'이미지경로','2022-08-10 23:55:11.000000','root','Samsung','삼성','2022-08-10 23:55:11.000000','root',NULL,NULL),
(2,0,'이미지경로','2022-08-10 23:55:14.000000','root','Apple','애플','2022-08-10 23:55:14.000000','root',NULL,NULL),
(3,0,'이미지경로','2022-08-10 23:55:15.000000','root','LG','엘지','2022-08-10 23:55:15.000000','root',NULL,NULL);
/*!40000 ALTER TABLE `t_brand` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-08-17 23:43:40
