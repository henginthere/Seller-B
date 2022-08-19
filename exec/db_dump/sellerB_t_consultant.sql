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
-- Table structure for table `t_consultant`
--

DROP TABLE IF EXISTS `t_consultant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_consultant` (
  `consultant_seq` bigint(20) NOT NULL AUTO_INCREMENT,
  `consultant_del_yn` tinyint(1) DEFAULT 0,
  `consultant_email` varchar(50) DEFAULT NULL,
  `consultant_id` varchar(25) DEFAULT NULL,
  `consultant_image_url` varchar(255) DEFAULT NULL,
  `consultant_mod_date` datetime(6) DEFAULT NULL,
  `consultant_mod_user` varchar(255) DEFAULT NULL,
  `consultant_name` varchar(10) DEFAULT NULL,
  `consultant_pass` varchar(100) DEFAULT NULL,
  `consultant_reg_date` datetime(6) DEFAULT NULL,
  `consultant_reg_user` varchar(255) DEFAULT NULL,
  `consultant_tel` varchar(15) DEFAULT NULL,
  `product_group_seq` bigint(20) DEFAULT NULL,
  `consultant_mod_user_seq` int(11) DEFAULT NULL,
  `consultant_reg_user_seq` int(11) DEFAULT NULL,
  PRIMARY KEY (`consultant_seq`),
  UNIQUE KEY `UK_cnyl2jqxi1os2wtkpwwpejfiv` (`consultant_id`),
  KEY `FKlegbftotwwt6al3jeraxaq40c` (`product_group_seq`),
  CONSTRAINT `FKlegbftotwwt6al3jeraxaq40c` FOREIGN KEY (`product_group_seq`) REFERENCES `t_product_group` (`product_group_seq`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_consultant`
--

LOCK TABLES `t_consultant` WRITE;
/*!40000 ALTER TABLE `t_consultant` DISABLE KEYS */;
INSERT INTO `t_consultant` VALUES
(1,0,'park@consultant.com','s_consultant1','https://sellerb.s3.ap-northeast-2.amazonaws.com/consultant/%EC%A6%9D%EB%AA%85%EC%82%AC%EC%A7%841.jpg','2022-08-16 08:05:36.355382','비회원','박상담','$2a$10$Nbi4N4YI7Eg4QUPvmYdM/OoyclKk/6ljwi18ETygwf4Ghj.wvjYcO','2022-08-11 01:12:58.206183','비회원','010-2222-3333',1,NULL,NULL),
(2,0,'kim@samsung.com','s_consultant2','https://sellerb.s3.ap-northeast-2.amazonaws.com/consultant/KakaoTalk_20220818_134201606.jpg','2022-08-11 01:14:39.322930','비회원','김상담','$2a$10$7h0fWTXxkBakxSo5AUazputfpyqodlGXLMFakJ7g1KHHa1RYzTHN6','2022-08-11 01:14:39.322930','비회원','010-3333-4444',8,NULL,NULL),
(3,0,'choi@apple.com','a_consultant','https://sellerb.s3.ap-northeast-2.amazonaws.com/consultant/2600191+(1).jpg','2022-08-11 01:15:42.751003','비회원','최상담','$2a$10$Pv3tNS3rO81/jra2fuiQleEwbzQyQd363JfF0f.h/uZw9RBn4Tzeq','2022-08-11 01:15:42.751003','비회원','010-4444-5555',12,NULL,NULL),
(4,0,'le2@lg.com','l_consultant','https://sellerb.s3.ap-northeast-2.amazonaws.com/consultant/2600191+(1).jpg','2022-08-11 01:16:34.022198','비회원','이상담','$2a$10$VDiAmIe5ddCjBC89RHeYuOaegcWRu0Rv9.7dJI52hc79zSJWhAdzC','2022-08-11 01:16:34.022198','비회원','010-2222-5555',14,NULL,NULL),
(5,0,'s_consultant3@sam.com','s_consultant3','https://sellerb.s3.ap-northeast-2.amazonaws.com/consultant/2600191+(1).jpg','2022-08-10 16:35:52.123562','비회원','삼상담','$2a$10$INZl3pga/Gq77.uyGXj6NOQzWA1CnXUMwQYBBdnukVx9J60tx/b7S','2022-08-10 16:35:52.123562','비회원','010-0000-0000',7,NULL,NULL),
(6,0,'test1@test1.com','테스트상담사1','https://sellerb.s3.ap-northeast-2.amazonaws.com/consultant/2600191+(1).jpg','2022-08-11 05:42:26.814165','s_manager','테스트상담사1','$2a$10$NyLMjpbsiGPFPfZkKjmyj.8Jm1sn9uZfnwtNY8mCwBLv9B9U4eLRC','2022-08-11 05:42:26.814165','s_manager','010-0000-0000',6,NULL,NULL),
(7,0,'test2@test2.com','테스트상담사2','https://sellerb.s3.ap-northeast-2.amazonaws.com/consultant/2600191+(1).jpg','2022-08-11 05:43:52.362540','s_manager','테스트상담사2','$2a$10$l7DkNuklSRE2OqZRJK6wFOv/t9W/arP.UZ2XRVFtpC01drF.nXZ/G','2022-08-11 05:43:52.362540','s_manager','010-0000-0000',1,NULL,NULL),
(8,0,'33@3.com','테스트상담사3','https://sellerb.s3.ap-northeast-2.amazonaws.com/consultant/2600191+(1).jpg','2022-08-11 11:12:34.532783','s_manager','테스트상담사3','$2a$10$g.xVdTLatkEOtv.mQH/6yeJf48Hiql7GQmxC4VVRbwefnE5vRTwzC','2022-08-11 11:12:34.532783','s_manager','010-0000-0011',6,NULL,NULL),
(9,0,'11@1.com','111','https://sellerb.s3.ap-northeast-2.amazonaws.com/consultant/2600191+(1).jpg','2022-08-11 13:43:34.761604','비회원','111','$2a$10$wJIob6OuBSp4Zfu4Hvan/ecCPVCbT3TqZDOKo5.NYMFDmjr0N1e2i','2022-08-11 13:43:34.761604','비회원','010-1111-1111',1,NULL,NULL),
(10,0,'hoho@ho.com','백호상담사','https://sellerb.s3.ap-northeast-2.amazonaws.com/consultant/2600191+(1).jpg','2022-08-12 06:55:18.835545','비회원','백호상담사','$2a$10$N97HBu83kzPpafs7K0k.CePatTk1ZIH.miF1W2p/kKYeNbbhHvRWC','2022-08-12 06:55:18.835545','비회원','010-0000-0000',6,NULL,NULL),
(11,0,'hohho','호호','https://sellerb.s3.ap-northeast-2.amazonaws.com/consultant/2600191+(1).jpg','2022-08-12 07:12:21.410582','비회원','호호','$2a$10$BD/lM9buyOHskQSwpGSBuuWl1ZGanesD001c68Wjngi.eD4vrpFlG','2022-08-12 07:12:21.410582','비회원','010-0000-0000',6,NULL,NULL),
(12,0,'honghong','홍홍','https://sellerb.s3.ap-northeast-2.amazonaws.com/consultant/2600191+(1).jpg','2022-08-12 07:13:39.356583','비회원','홍홍','$2a$10$1ve5qVuv.BjhWidAo.0LYe0li.aGlc6zrHE/MBsAr/0kpApBLDfbu','2022-08-12 07:13:39.356583','비회원','010-1111-1111',2,NULL,NULL),
(13,0,'haha','하하','https://sellerb.s3.ap-northeast-2.amazonaws.com/consultant/2600191+(1).jpg','2022-08-12 07:15:24.172679','비회원','하하','$2a$10$gFpumALi2Hmwoya0AJJJrO3IDcE7DPe0nubn.yC2V3LeXXoGIr/.O','2022-08-12 07:15:24.172679','비회원','010-0000-0011',7,NULL,NULL),
(14,0,'hi@hi.com','히히','https://sellerb.s3.ap-northeast-2.amazonaws.com/consultant/2600191+(1).jpg','2022-08-12 07:24:56.708638','s_manager','히히','$2a$10$49PGMma4kPtc2.1RU/GjMunjS.ohmZviSRUSOcvzCgR82g.aU5vNW','2022-08-12 07:24:56.708638','s_manager','010-0000-0000',6,NULL,NULL),
(15,0,'nana@na.com','나나','https://sellerb.s3.ap-northeast-2.amazonaws.com/consultant/2600191+(1).jpg','2022-08-12 07:34:55.872385','s_manager','나나','$2a$10$SsZ9vw.4jPs2vqA/brI5VOyqSJsVI2IjExH1qvWuT9oAyCN02E7A6','2022-08-12 07:34:55.872385','s_manager','010-0000-0000',8,NULL,NULL),
(16,0,'ga@ga.com','가가','https://sellerb.s3.ap-northeast-2.amazonaws.com/consultant/2600191+(1).jpg','2022-08-12 07:37:51.284582','s_manager','가가','$2a$10$TqyzgKMAbV3A9GKbN/7y/eGK3P8on.xqqWkTkdgNlDsR.xISnH9Yu','2022-08-12 07:37:51.284582','s_manager','010-1234-1234',6,NULL,NULL),
(17,0,'dada@da.com','다다','https://sellerb.s3.ap-northeast-2.amazonaws.com/consultant/image.%EA%B3%A0%EC%96%91%EC%9D%B4%EC%9D%B4%EB%AF%B8%EC%A7%80_1660290278818.jpg','2022-08-12 07:44:40.983925','s_manager','다다','$2a$10$9I.y6BAxDtRDRaOuKqIL3OC/ahb3/3UzH/YFYlRVlsC7wcM8i21L2','2022-08-12 07:44:40.983925','s_manager','010-1234-1234',6,NULL,NULL),
(18,0,'s@s.com','에스','https://sellerb.s3.ap-northeast-2.amazonaws.com/consultant/image.%EB%B0%B1%ED%98%B8%EB%B0%B1%ED%98%B8_1660290693872.jpg','2022-08-12 07:51:36.301885','s_manager','에스','$2a$10$qewmXD.lo0lma6Y0lO.kcOLw8uxUriiDxIvuPN/OIN92/NQyxEP36','2022-08-12 07:51:36.301885','s_manager','010-1234-1234',7,NULL,NULL),
(19,1,NULL,'고양사원','https://sellerb.s3.ap-northeast-2.amazonaws.com/consultant/2600191+(1).jpg','2022-08-16 15:37:05.045363','비회원',NULL,NULL,'2022-08-14 16:52:31.389445','비회원',NULL,2,NULL,NULL),
(20,0,'test@test.com','테스트','https://sellerb.s3.ap-northeast-2.amazonaws.com/consultant/image.%EA%B3%A0%EC%96%91%EC%9D%B4%EC%9D%B4%EB%AF%B8%EC%A7%80_1660641167004.jpg','2022-08-16 09:13:04.057759','s_manager','테스트','$2a$10$Y9QDt7Yf8459AF7lKDFcqega3yW0au/RYmnGmyfVLi3zwBUeYpKDO','2022-08-16 09:13:04.057759','s_manager','010-2222-2222',2,NULL,NULL),
(21,0,'dlacogus5239@gamil.com','a_consultant1','https://sellerb.s3.ap-northeast-2.amazonaws.com/consultant/2600191+(1).jpg','2022-08-17 00:43:08.390887','a_manager','애상담','$2a$10$mMrhHCld19mX0Az71TxprerzEyz.N9vN1IZscTVh0jLsg9coTWKN2','2022-08-17 00:43:08.390887','a_manager','010-1111-1111',9,NULL,NULL),
(22,0,'0817@0817.com','0817','https://sellerb.s3.ap-northeast-2.amazonaws.com/consultant/image.%EA%B3%A0%EC%96%91%EC%9D%B4%EC%9D%B4%EB%AF%B8%EC%A7%80_1660721708449.jpg','2022-08-17 07:35:40.637330','sss333','0817사원','$2a$10$7H.dz7pKtvxWc1WvL1eb8.94RWECirFYEnQO5G0LBVI5ExKYqOE5i','2022-08-17 07:35:40.637330','sss333','010-0000-0000',2,NULL,NULL),
(23,0,'dd@d.com','마지막상담원','https://sellerb.s3.ap-northeast-2.amazonaws.com/consultant/image.%EA%B3%A0%EC%B0%BD%EC%84%9D%EA%B3%A0%EC%96%91%EC%9D%B4_1660788146407.jpg','2022-08-18 02:16:28.892115','비회원','마지막상담원','$2a$10$VW71OFABaHprTVIn1FbjiO5tWOf.nQonkP/9kN1quro8lfTElg/5y','2022-08-18 02:02:47.618615','비회원','010-0000-0000',2,NULL,NULL);
/*!40000 ALTER TABLE `t_consultant` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-08-19 11:21:18
