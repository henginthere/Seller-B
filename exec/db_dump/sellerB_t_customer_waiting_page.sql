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
-- Table structure for table `t_customer_waiting_page`
--

DROP TABLE IF EXISTS `t_customer_waiting_page`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_customer_waiting_page` (
  `customer_waiting_page_seq` bigint(20) NOT NULL AUTO_INCREMENT,
  `customer_waiting_page_del_yn` tinyint(1) DEFAULT 0,
  `customer_waiting_page_image` varchar(255) DEFAULT NULL,
  `customer_waiting_page_ment` varchar(255) DEFAULT NULL,
  `customer_waiting_page_mod_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `customer_waiting_page_mod_user` varchar(25) DEFAULT NULL,
  `customer_waiting_page_reg_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `customer_waiting_page_reg_user` varchar(25) DEFAULT NULL,
  `product_seq` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`customer_waiting_page_seq`),
  KEY `FKk8es51konmrx3tyfe00t528go` (`product_seq`),
  CONSTRAINT `FKk8es51konmrx3tyfe00t528go` FOREIGN KEY (`product_seq`) REFERENCES `t_product` (`product_seq`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_customer_waiting_page`
--

LOCK TABLES `t_customer_waiting_page` WRITE;
/*!40000 ALTER TABLE `t_customer_waiting_page` DISABLE KEYS */;
INSERT INTO `t_customer_waiting_page` VALUES
(6,NULL,'https://sellerb.s3.ap-northeast-2.amazonaws.com/product/image.1_1660279015671.png','현재 판매 1위 상품을 만나보세요 !','2022-08-12 06:27:20','비회원','2022-08-12 06:27:20','비회원',1),
(8,0,'https://sellerb.s3.ap-northeast-2.amazonaws.com/product/image.1_1660279015671.png','updatetest','2022-08-13 08:33:03','비회원','2022-08-12 15:49:42','비회원',2),
(9,0,'https://sellerb.s3.ap-northeast-2.amazonaws.com/waiting-page/image.%EA%B3%A0%EC%96%91%EC%9D%B4%EC%9D%B4%EB%AF%B8%EC%A7%80_1660374086162.jpg','멘트준비중','2022-08-12 22:04:04','비회원','2022-08-12 22:04:04','비회원',41),
(10,0,'https://sellerb.s3.ap-northeast-2.amazonaws.com/waiting-page/image.%EA%B3%A0%EC%96%91%EC%9D%B42_1660374574201.jpg','멘트준비중','2022-08-12 22:09:35','비회원','2022-08-12 22:09:35','비회원',40),
(11,0,'https://sellerb.s3.ap-northeast-2.amazonaws.com/waiting-page/image.%EA%B3%A0%EC%96%91%EC%9D%B4%EC%9D%B4%EB%AF%B8%EC%A7%80_1660374795782.jpg','멘트준비중','2022-08-12 22:13:18','s_manager','2022-08-12 22:13:18','s_manager',49),
(12,0,NULL,'멘트준비중','2022-08-13 21:36:05','s_manager','2022-08-13 21:36:05','s_manager',5),
(13,1,'https://sellerb.s3.ap-northeast-2.amazonaws.com/waiting-page/image.%EB%83%89%EC%9E%A5%EA%B3%A0_%EB%8C%80%EA%B8%B0%ED%99%94%EB%A9%B4_%ED%85%8C%EC%8A%A4%ED%8A%B8%EC%9D%B4%EB%AF%B8%EC%A7%80_1660459703707.jpg','멘트부분','2022-08-15 15:17:11','비회원','2022-08-13 21:57:35','비회원',1),
(14,0,'https://sellerb.s3.ap-northeast-2.amazonaws.com/waiting-page/image.%EC%A0%9C%ED%92%88_%ED%85%8C%EC%8A%A4%ED%8A%B8_%EC%82%BC%EC%84%B1%EB%AA%A8%EB%8B%88%ED%84%B0_1660484858313.jpg','','2022-08-14 04:47:43','s_manager','2022-08-14 04:47:43','s_manager',3),
(15,0,'https://sellerb.s3.ap-northeast-2.amazonaws.com/waiting-page/image.%EA%B3%A0%EC%96%91%EC%9D%B4%EC%9D%B4%EB%AF%B8%EC%A7%80_1660576047846.jpg','','2022-08-15 06:07:34','비회원','2022-08-15 06:07:34','비회원',65),
(16,0,'https://sellerb.s3.ap-northeast-2.amazonaws.com/waiting-page/image.%EA%B3%A0%EC%96%91%EC%9D%B4%EC%9D%B4%EB%AF%B8%EC%A7%80_1660576202521.jpg','','2022-08-15 06:10:07','비회원','2022-08-15 06:10:07','비회원',4),
(17,0,'https://sellerb.s3.ap-northeast-2.amazonaws.com/waiting-page/image.%EA%B3%A0%EC%96%91%EC%9D%B4%EC%9D%B4%EB%AF%B8%EC%A7%80_1660576647250.jpg','','2022-08-15 06:17:32','비회원','2022-08-15 06:17:32','비회원',1),
(18,0,'https://sellerb.s3.ap-northeast-2.amazonaws.com/waiting-page/image.%EA%B3%A0%EC%96%91%EC%9D%B4%EC%9D%B4%EB%AF%B8%EC%A7%80_1660576202521.jpg','스웨거로등록','2022-08-15 06:26:48','비회원','2022-08-15 06:26:48','비회원',10),
(19,0,'https://sellerb.s3.ap-northeast-2.amazonaws.com/waiting-page/image.%EC%A0%9C%ED%92%88_%ED%85%8C%EC%8A%A4%ED%8A%B8_%EC%82%BC%EC%84%B1%EB%AA%A8%EB%8B%88%ED%84%B0_1660577244883.jpg','삼성모니터 대기화면','2022-08-15 06:27:34','비회원','2022-08-15 06:27:34','비회원',55),
(20,0,'https://sellerb.s3.ap-northeast-2.amazonaws.com/waiting-page/image.%EA%B3%A0%EC%96%91%EC%9D%B4%EC%9D%B4%EB%AF%B8%EC%A7%80_1660615523393.jpg','비숑고양이','2022-08-15 17:05:24','s_manager','2022-08-15 17:05:24','s_manager',66),
(21,0,'','','2022-08-15 17:15:25','s_manager','2022-08-15 17:15:25','s_manager',6),
(22,0,'','고양이고양이','2022-08-15 19:09:58','s_manager','2022-08-15 19:09:58','s_manager',68),
(23,0,'https://sellerb.s3.ap-northeast-2.amazonaws.com/waiting-page/image.%EA%B3%A0%EC%96%91%EC%9D%B4%EC%9D%B4%EB%AF%B8%EC%A7%80_1660623322132.jpg','ㅋㅋㅋㅋ','2022-08-15 19:15:27','s_manager','2022-08-15 19:15:27','s_manager',69);
/*!40000 ALTER TABLE `t_customer_waiting_page` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-08-19 11:21:14
