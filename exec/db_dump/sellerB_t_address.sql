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
-- Table structure for table `t_address`
--

DROP TABLE IF EXISTS `t_address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_address` (
  `addr_seq` bigint(20) NOT NULL AUTO_INCREMENT,
  `addr` varchar(100) DEFAULT NULL,
  `addr_del_yn` tinyint(1) DEFAULT 0,
  `addr_mod_date` datetime(6) DEFAULT NULL,
  `addr_mod_user` varchar(255) DEFAULT NULL,
  `addr_reg_date` datetime(6) DEFAULT NULL,
  `addr_reg_user` varchar(255) DEFAULT NULL,
  `addr_requests` varchar(100) DEFAULT NULL,
  `customer_seq` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`addr_seq`),
  KEY `FKsprqbf91k3y39c0ndg4ajos2d` (`customer_seq`),
  CONSTRAINT `FKsprqbf91k3y39c0ndg4ajos2d` FOREIGN KEY (`customer_seq`) REFERENCES `t_customer` (`customer_seq`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_address`
--

LOCK TABLES `t_address` WRITE;
/*!40000 ALTER TABLE `t_address` DISABLE KEYS */;
INSERT INTO `t_address` VALUES
(1,'우리집',0,'2022-08-18 08:31:13.997771','비회원','2022-08-18 08:31:13.997771','비회원','문앞에 두고 가주세요.!',6),
(2,'경북 구미시 3공단 3로 302 삼성전자 구미 사업장',0,'2022-08-18 08:32:57.989782','비회원','2022-08-18 08:32:57.989782','비회원','부재 시 게이트에 맡겨주세요!',6);
/*!40000 ALTER TABLE `t_address` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-08-19 11:21:19
