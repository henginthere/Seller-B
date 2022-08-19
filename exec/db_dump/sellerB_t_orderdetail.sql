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
-- Table structure for table `t_orderdetail`
--

DROP TABLE IF EXISTS `t_orderdetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_orderdetail` (
  `order_detail_seq` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_detail_count` int(11) DEFAULT NULL,
  `orderDetail_del_yn` tinyint(1) DEFAULT 0,
  `order_detail_mod_date` datetime(6) DEFAULT NULL,
  `order_detail_mod_user` varchar(255) DEFAULT NULL,
  `order_detail_reg_date` datetime(6) DEFAULT NULL,
  `order_detail_reg_user` varchar(255) DEFAULT NULL,
  `order_seq` bigint(20) DEFAULT NULL,
  `product_seq` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`order_detail_seq`),
  KEY `FK1vuypobad4ngm52klyo3ekvr1` (`order_seq`),
  KEY `FKaqkdh58foqhn5s3fq9ntalc1r` (`product_seq`),
  CONSTRAINT `FK1vuypobad4ngm52klyo3ekvr1` FOREIGN KEY (`order_seq`) REFERENCES `t_order` (`order_seq`),
  CONSTRAINT `FKaqkdh58foqhn5s3fq9ntalc1r` FOREIGN KEY (`product_seq`) REFERENCES `t_product` (`product_seq`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_orderdetail`
--

LOCK TABLES `t_orderdetail` WRITE;
/*!40000 ALTER TABLE `t_orderdetail` DISABLE KEYS */;
INSERT INTO `t_orderdetail` VALUES
(1,1,0,'2022-08-18 08:37:05.651375','비회원','2022-08-18 08:37:05.651375','비회원',2,1),
(2,2,0,'2022-08-18 08:37:05.656187','비회원','2022-08-18 08:37:05.656187','비회원',2,2),
(3,2,0,'2022-08-18 08:38:11.542437','비회원','2022-08-18 08:38:11.542437','비회원',3,1),
(4,1,0,'2022-08-18 08:38:11.546526','비회원','2022-08-18 08:38:11.546526','비회원',3,2),
(5,2,0,'2022-08-18 08:39:09.911305','비회원','2022-08-18 08:39:09.911305','비회원',4,1),
(6,1,0,'2022-08-18 08:39:09.914902','비회원','2022-08-18 08:39:09.914902','비회원',4,2);
/*!40000 ALTER TABLE `t_orderdetail` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-08-19 11:21:11
