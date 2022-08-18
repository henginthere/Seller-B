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
-- Table structure for table `t_manager_consultant`
--

DROP TABLE IF EXISTS `t_manager_consultant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_manager_consultant` (
  `manager_consultant_seq` bigint(20) NOT NULL AUTO_INCREMENT,
  `consultant_del_yn` tinyint(1) DEFAULT 0,
  `manager_consultant_mod_date` datetime(6) DEFAULT NULL,
  `manager_consultant_mod_user` varchar(255) DEFAULT NULL,
  `manager_consultant_reg_date` datetime(6) DEFAULT NULL,
  `manager_consultant_reg_user` varchar(255) DEFAULT NULL,
  `consultant_seq` bigint(20) DEFAULT NULL,
  `manager_seq` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`manager_consultant_seq`),
  KEY `FKle00vuhqufwlhdyj7rn2h2low` (`consultant_seq`),
  KEY `FKn1loqm2phpv9bdnwf4wv3t6ox` (`manager_seq`),
  CONSTRAINT `FKle00vuhqufwlhdyj7rn2h2low` FOREIGN KEY (`consultant_seq`) REFERENCES `t_consultant` (`consultant_seq`),
  CONSTRAINT `FKn1loqm2phpv9bdnwf4wv3t6ox` FOREIGN KEY (`manager_seq`) REFERENCES `t_manager` (`manager_seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_manager_consultant`
--

LOCK TABLES `t_manager_consultant` WRITE;
/*!40000 ALTER TABLE `t_manager_consultant` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_manager_consultant` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-08-17 23:43:42
