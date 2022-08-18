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
-- Table structure for table `t_product_group`
--

DROP TABLE IF EXISTS `t_product_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_product_group` (
  `product_group_seq` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_group_code` varchar(10) DEFAULT NULL,
  `product_group_del_yn` tinyint(1) DEFAULT 0,
  `product_group_mod` varchar(255) DEFAULT NULL,
  `product_group_mod_date` datetime(6) DEFAULT NULL,
  `product_group_name` varchar(25) DEFAULT NULL,
  `product_group_reg_date` datetime(6) DEFAULT NULL,
  `product_group_reg_user` varchar(255) DEFAULT NULL,
  `brand_seq` bigint(20) DEFAULT NULL,
  `product_group_mod_seq` int(11) DEFAULT NULL,
  `product_group_reg_user_seq` int(11) DEFAULT NULL,
  PRIMARY KEY (`product_group_seq`),
  KEY `FK8qop7f0f7pgqtl9h5k6lppt0n` (`brand_seq`),
  CONSTRAINT `FK8qop7f0f7pgqtl9h5k6lppt0n` FOREIGN KEY (`brand_seq`) REFERENCES `t_brand` (`brand_seq`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_product_group`
--

LOCK TABLES `t_product_group` WRITE;
/*!40000 ALTER TABLE `t_product_group` DISABLE KEYS */;
INSERT INTO `t_product_group` VALUES
(1,'S001',0,'samsung','2022-08-11 00:52:23.000000','TV','2022-08-11 00:52:23.000000','samsung',1,NULL,NULL),
(2,'S002',0,'samsung','2022-08-11 00:53:02.000000','냉장고','2022-08-11 00:53:02.000000','samsung',1,NULL,NULL),
(6,'S004',0,'samsung','2022-08-11 01:08:11.000000','세탁기','2022-08-11 01:08:11.000000','samsung',1,NULL,NULL),
(7,'S005',0,'samsung','2022-08-11 01:08:17.000000','건조기','2022-08-11 01:08:17.000000','samsung',1,NULL,NULL),
(8,'S003',0,'samsung','2022-08-11 01:08:32.000000','스마트폰','2022-08-11 01:08:32.000000','samsung',1,NULL,NULL),
(9,'A001',0,'apple','2022-08-11 01:09:22.000000','스마트폰','2022-08-11 01:09:22.000000','apple',2,NULL,NULL),
(10,'A002',0,'apple','2022-08-11 01:09:52.000000','태블릿','2022-08-11 01:09:52.000000','apple',2,NULL,NULL),
(11,'A003',0,'apple','2022-08-11 01:10:01.000000','워치','2022-08-11 01:10:01.000000','apple',2,NULL,NULL),
(12,'A004',0,'apple','2022-08-11 01:10:36.000000','노트북','2022-08-11 01:10:36.000000','apple',2,NULL,NULL),
(13,'L004',0,'lg','2022-08-11 01:11:13.000000','TV','2022-08-11 01:11:13.000000','lg',3,NULL,NULL),
(14,'L001',0,'lg','2022-08-11 01:11:21.000000','냉장고','2022-08-11 01:11:21.000000','lg',3,NULL,NULL),
(15,'L002',0,'lg','2022-08-11 01:11:27.000000','에어컨','2022-08-11 01:11:27.000000','lg',3,NULL,NULL),
(16,'L003',0,'lg','2022-08-11 01:11:33.000000','정수기','2022-08-11 01:11:33.000000','lg',3,NULL,NULL),
(17,'L005',0,'lg','2022-08-11 01:11:52.000000','노트북','2022-08-11 01:11:52.000000','lg',3,NULL,NULL);
/*!40000 ALTER TABLE `t_product_group` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-08-17 23:43:39
