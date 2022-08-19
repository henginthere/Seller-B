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
-- Table structure for table `t_consultant_attendance`
--

DROP TABLE IF EXISTS `t_consultant_attendance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_consultant_attendance` (
  `consultant_attendance_seq` bigint(20) NOT NULL AUTO_INCREMENT,
  `consultant_attendance_mod_user` varchar(255) DEFAULT NULL,
  `consultant_attendance_reg_user` varchar(255) DEFAULT NULL,
  `consultant_attendance_state` tinyint(1) DEFAULT 0,
  `login_time` datetime(6) DEFAULT NULL,
  `logout_time` datetime(6) DEFAULT NULL,
  `consultant_seq` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`consultant_attendance_seq`),
  KEY `FKtm2qdpsqfvg1hmr7aqukerkbn` (`consultant_seq`),
  CONSTRAINT `FKtm2qdpsqfvg1hmr7aqukerkbn` FOREIGN KEY (`consultant_seq`) REFERENCES `t_consultant` (`consultant_seq`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_consultant_attendance`
--

LOCK TABLES `t_consultant_attendance` WRITE;
/*!40000 ALTER TABLE `t_consultant_attendance` DISABLE KEYS */;
INSERT INTO `t_consultant_attendance` VALUES
(1,'비회원','비회원',1,'2022-08-11 00:21:34.365252','2022-08-11 00:22:57.957613',1),
(2,'비회원','비회원',1,'2022-08-11 00:46:38.256753','2022-08-11 00:47:58.106658',2),
(3,'비회원','s_manager',1,'2022-08-11 11:40:46.433972','2022-08-17 02:04:46.240078',1),
(4,'비회원','비회원',1,'2022-08-13 16:25:49.140816','2022-08-13 16:25:49.140816',2),
(8,'비회원','비회원',0,'2022-08-16 13:04:44.965854','2022-08-16 13:04:44.965854',19),
(9,'custom2','custom2',0,'2022-08-16 13:36:47.391732','2022-08-16 13:36:47.391732',2),
(10,'custom3','custom3',0,'2022-08-16 13:38:21.034924','2022-08-16 13:38:21.034924',5),
(11,'1234','1234',0,'2022-08-16 13:41:01.636448','2022-08-16 13:41:01.636448',3),
(12,'비회원','비회원',1,'2022-08-17 02:04:46.236729','2022-08-17 10:51:52.876781',1),
(13,'비회원','비회원',0,'2022-08-18 07:57:23.887539','2022-08-18 07:57:23.887539',1);
/*!40000 ALTER TABLE `t_consultant_attendance` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-08-19 11:21:20
