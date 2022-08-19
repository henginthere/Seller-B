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
-- Table structure for table `t_customer`
--

DROP TABLE IF EXISTS `t_customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_customer` (
  `customer_seq` bigint(20) NOT NULL AUTO_INCREMENT,
  `customer_birth` varchar(10) DEFAULT NULL,
  `customer_del_yn` tinyint(1) DEFAULT 0,
  `customer_email` varchar(50) DEFAULT NULL,
  `customer_id` varchar(25) DEFAULT NULL,
  `customer_mod_date` datetime(6) DEFAULT NULL,
  `customer_mod_user` varchar(255) DEFAULT NULL,
  `customer_name` varchar(10) DEFAULT NULL,
  `customer_pass` varchar(100) DEFAULT NULL,
  `customer_reg_date` datetime(6) DEFAULT NULL,
  `customer_reg_user` varchar(255) DEFAULT NULL,
  `customer_token` varchar(255) DEFAULT NULL,
  `customer_addr` varchar(255) DEFAULT NULL,
  `customer_mod_user_seq` int(11) DEFAULT NULL,
  `customer_reg_user_seq` int(11) DEFAULT NULL,
  `customer_tel` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`customer_seq`),
  UNIQUE KEY `UK_lsdk4dnu2fnhnxq6pn1muglf9` (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_customer`
--

LOCK TABLES `t_customer` WRITE;
/*!40000 ALTER TABLE `t_customer` DISABLE KEYS */;
INSERT INTO `t_customer` VALUES
(1,'19990909',0,'123@naver.com','custom1','2022-08-11 01:20:25.811910','비회원','김손님','$2a$10$i8cOYPCv4pANkx5EStAZmeTtzXBilmnOmmk4giUrH2bclbBn0HblG','2022-08-11 01:20:25.811910','비회원','dkfjsldirjlsdk',NULL,NULL,NULL,NULL),
(2,'19990909',0,'cndehdrnao@naver.com','custom2','2022-08-11 01:20:49.754311','비회원','박손님','$2a$10$E2L6.qVPyKxQ8TErktZ2W.35g1aNeoV2IeYRKgt4l0jqZr/Sjoom.','2022-08-11 01:20:49.754311','비회원','dkfjsldirjlsdk',NULL,NULL,NULL,NULL),
(3,NULL,0,NULL,'1234','2022-08-11 08:08:19.001283','비회원','kiddo','$2a$10$qyxG2FP2uisXGfDwRlXvk.d1FuHEkYhCoBPpJvuxtZUHu8A9zo6Ve','2022-08-11 08:08:19.001283','비회원',NULL,NULL,NULL,NULL,NULL),
(4,NULL,0,NULL,'kiddo2','2022-08-11 08:20:03.258589','비회원','kiddo2','$2a$10$mwAlwcU8yImvJb7ivzWcAuL/LOD2J5CUe.rtV.qMjqSfWyO4fS1Ba','2022-08-11 08:20:03.258589','비회원',NULL,NULL,NULL,NULL,NULL),
(5,'19870423',0,'123@123','custom3','2022-08-11 08:20:27.229914','비회원','최손님','$2a$10$wIUPTi1ttPEhI8HPW/NbPudQVu8Ag0FVQyKztmIlfoSMbQdn9PWtm','2022-08-11 08:20:27.229914','비회원','dkfjsldirjlsdk',NULL,NULL,NULL,NULL),
(6,NULL,0,NULL,'kiddo','2022-08-17 06:05:17.626683','비회원','kiddo','$2a$10$8ZkkHQWxwR8kjknq01LZ6uNdV0CpTSGGZt.snosdnEV7tH2qODm6C','2022-08-11 08:23:34.996725','비회원','c92EqmV8Rp2urs-cRiGgo0:APA91bELE5TpWZP0rYcCVm6OHdIReVmaPlHlQNHyE94fjCSbRUc24_fSAChCEofKEVRE2ujtZ-DIi7GO48quGSTlTkYdbZWLzRSHdvsoIPgV9aKvfzRQFdfIS8UfPg1jrBDNEa7rvZh_',NULL,NULL,NULL,NULL),
(18,NULL,0,NULL,'kiddo3','2022-08-16 07:41:43.352352','비회원','이기덕','$2a$10$0ZXIFHiTUb.9hZ8eIMy.d.scz.oUcfwiYUjJtiD32tR.fWxa40JX.','2022-08-16 07:41:43.352352','비회원',NULL,NULL,NULL,NULL,NULL),
(19,'',0,'','apple','2022-08-16 16:41:49.181882','비회원','이기덕','$2a$10$Hi8y9hpY9xOo2tsjinWU9uhKtlpyJpjzW03hKTVfaXyl.h8QaQSZW','2022-08-16 16:41:49.181882','비회원','',NULL,NULL,NULL,NULL),
(24,NULL,0,NULL,'kiddo3173@gmail.com','2022-08-17 00:59:47.467076','비회원','이기덕','$2a$10$uzHVT2HEoxetwcOb9G8e5ui1dqTgWC0y.0RnLd7a6bndakatflOwq','2022-08-16 08:01:41.242391','비회원','c9Q5McqqSNeMEzFAbMK3bo:APA91bFPDe-GsJ4WaczvRmXiymyAKpFMbBO08BU85Evn3N2U19TLuBeUKCtd-tmNO60qfiq5P7U0n1o4kElMwBu2OSLomdkJRLjWJl7pFVhWXQDT6z6VN7hp69n5WsgCwxk_jS8gZ33G',NULL,NULL,NULL,NULL),
(25,NULL,0,NULL,'tg_04@naver.com','2022-08-16 08:02:18.346712','비회원','이기덕','$2a$10$z.TeoM8VA4agNJoEd6CJs.Atw2ZRaPKEVabfNySEc.jjLpecRsnJa','2022-08-16 08:02:18.346712','비회원',NULL,NULL,NULL,NULL,NULL),
(26,NULL,0,NULL,'test@naver.com','2022-08-16 17:06:49.786581','비회원','네이버테스트','$2a$10$azJjaUK3y5someJEADDaAe212N1Vh6pYY2zFVXMlGU1vheaTevTBa','2022-08-16 17:06:49.786581','비회원',NULL,NULL,NULL,NULL,NULL),
(27,NULL,0,NULL,'test@kakao.com','2022-08-16 17:07:23.496479','비회원','카카오테스트','$2a$10$ePhX/sMiCZYBrVZ/dktZL..L/XK1/vSJGYbDt3lgHBevULyOgr1EC','2022-08-16 17:07:23.496479','비회원',NULL,NULL,NULL,NULL,NULL),
(29,NULL,0,NULL,'Ggoogle','2022-08-17 11:52:47.758154','비회원','구글테스트','$2a$10$C4h0PNk094Gv40uM4KrA8ucg/lezwtDnDI1i1BjgWnVB72jHw86fq','2022-08-17 11:52:47.758154','비회원',NULL,NULL,NULL,NULL,NULL),
(32,NULL,0,NULL,'Nnaver','2022-08-17 11:56:56.877481','비회원','네이버테스트','$2a$10$6u8Qu1P6ggJ/tBDTEV0QE.qzNR6zfDXxkjt/19im10NUEamw0rvZq','2022-08-17 11:56:56.877481','비회원',NULL,NULL,NULL,NULL,NULL),
(33,NULL,0,NULL,'Kkakao','2022-08-17 11:57:20.361233','비회원','카카오테스트','$2a$10$IwMGdCw1YS8ei6HTCyCo1O.FN3ll6jiCSjvUUnCqlOtSdQi9ctc0G','2022-08-17 11:57:20.361233','비회원',NULL,NULL,NULL,NULL,NULL),
(34,NULL,0,NULL,'Gkiddo3173','2022-08-17 20:55:07.700789','비회원','이기덕','$2a$10$Q9Ose.2b/wwwTQtGCzEU7u/ZwYwaChZ7Uj5Rqs2qxoJ1Qkz/sWmqu','2022-08-17 03:32:08.194782','비회원','eBh4TtjtQXuzf_V1g3YTTH:APA91bGxBloxxD2wxW05-tKG4ugIWv97KBcI7a1cHnc6A7xi6CzyBXkI4m0mtM6mGhKtPkRAEwvwfbwrHLtFmFdfZAymMwOTHPcA_WiLfB3lN-h8bWvjKTUfI0d8p93ZPZsxkEQOTuax',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `t_customer` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-08-19 11:21:13
