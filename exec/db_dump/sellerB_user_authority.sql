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
-- Table structure for table `user_authority`
--

DROP TABLE IF EXISTS `user_authority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_authority` (
  `id` varchar(25) NOT NULL,
  `authority_name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`,`authority_name`),
  KEY `FK6ktglpl5mjosa283rvken2py5` (`authority_name`),
  CONSTRAINT `FK6ktglpl5mjosa283rvken2py5` FOREIGN KEY (`authority_name`) REFERENCES `authority` (`authority_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_authority`
--

LOCK TABLES `user_authority` WRITE;
/*!40000 ALTER TABLE `user_authority` DISABLE KEYS */;
INSERT INTO `user_authority` VALUES
('0817','ROLE_USER'),
('111','ROLE_USER'),
('1234','ROLE_CUSTOMER'),
('aa','ROLE_ADMIN'),
('apple','ROLE_CUSTOMER'),
('a_consultant','ROLE_USER'),
('a_consultant1','ROLE_USER'),
('a_manager','ROLE_ADMIN'),
('custom1','ROLE_CUSTOMER'),
('custom2','ROLE_CUSTOMER'),
('custom3','ROLE_CUSTOMER'),
('Ggoogle','ROLE_CUSTOMER'),
('Ggooglegmail.com','ROLE_CUSTOMER'),
('Gkiddo3173','ROLE_CUSTOMER'),
('goyang','ROLE_ADMIN'),
('kiddo','ROLE_CUSTOMER'),
('kiddo2','ROLE_CUSTOMER'),
('kiddo3','ROLE_CUSTOMER'),
('Kkakao','ROLE_CUSTOMER'),
('l_consultant','ROLE_USER'),
('l_manager','ROLE_ADMIN'),
('Nnaver','ROLE_CUSTOMER'),
('sam','ROLE_ADMIN'),
('ss','ROLE_ADMIN'),
('sss','ROLE_ADMIN'),
('sss333','ROLE_ADMIN'),
('ssss','ROLE_ADMIN'),
('s_consultant1','ROLE_USER'),
('s_consultant2','ROLE_USER'),
('s_consultant3','ROLE_USER'),
('s_manager','ROLE_ADMIN'),
('s_test','ROLE_ADMIN'),
('s_test_2','ROLE_ADMIN'),
('test@kakao.com','ROLE_CUSTOMER'),
('test@naver.com','ROLE_CUSTOMER'),
('test_0817','ROLE_ADMIN'),
('test_2','ROLE_ADMIN'),
('test_3','ROLE_ADMIN'),
('tg_04@naver.com','ROLE_CUSTOMER'),
('uu','ROLE_ADMIN'),
('zz','ROLE_ADMIN'),
('ㅋㅋ','ROLE_ADMIN'),
('ㅎㅎ','ROLE_ADMIN'),
('가가','ROLE_USER'),
('강호동고양이','ROLE_ADMIN'),
('고양사원','ROLE_USER'),
('고양이테스트','ROLE_ADMIN'),
('나나','ROLE_USER'),
('다다','ROLE_USER'),
('백호상담사','ROLE_USER'),
('삼성고양이','ROLE_ADMIN'),
('삼성이여','ROLE_ADMIN'),
('에스','ROLE_USER'),
('테스트','ROLE_USER'),
('테스트상담사1','ROLE_USER'),
('테스트상담사2','ROLE_USER'),
('테스트상담사3','ROLE_USER'),
('하하','ROLE_USER'),
('호호','ROLE_USER'),
('홍홍','ROLE_USER'),
('히히','ROLE_USER');
/*!40000 ALTER TABLE `user_authority` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-08-17 23:43:45
