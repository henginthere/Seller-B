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
-- Table structure for table `t_notice`
--

DROP TABLE IF EXISTS `t_notice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_notice` (
  `notice_seq` bigint(20) NOT NULL AUTO_INCREMENT,
  `notice_content` varchar(500) DEFAULT NULL,
  `notice_del_yn` tinyint(1) DEFAULT 0,
  `notice_mod_date` datetime(6) DEFAULT NULL,
  `notice_mod_user` varchar(255) DEFAULT NULL,
  `notice_reg_date` datetime(6) DEFAULT NULL,
  `notice_reg_user` varchar(255) DEFAULT NULL,
  `notice_title` varchar(100) DEFAULT NULL,
  `brand_seq` bigint(20) DEFAULT NULL,
  `notice_mod_user_seq` varchar(255) DEFAULT NULL,
  `notice_reg_user_seq` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`notice_seq`),
  KEY `FK2obeh5qjjv99f38n955wvle8f` (`brand_seq`),
  CONSTRAINT `FK2obeh5qjjv99f38n955wvle8f` FOREIGN KEY (`brand_seq`) REFERENCES `t_brand` (`brand_seq`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_notice`
--

LOCK TABLES `t_notice` WRITE;
/*!40000 ALTER TABLE `t_notice` DISABLE KEYS */;
INSERT INTO `t_notice` VALUES
(1,'공지사항1내용입니다.',1,'2022-08-11 00:32:18.000000','s_manager','2022-08-11 00:32:18.000000','s_manager','공지사항1',1,NULL,NULL),
(2,'내용2 스웨거 수정',1,'2022-08-15 12:16:50.439693','비회원','2022-08-11 00:32:18.000000','s_manager','내용2 스웨거 수정',1,NULL,NULL),
(3,'공지사항3내용입니다.',0,'2022-08-11 00:32:18.000000','s_manager','2022-08-11 00:32:18.000000','s_manager','공지사항3',1,NULL,NULL),
(4,'포스트맨으로 수정',0,'2022-08-15 12:30:38.223349','비회원','2022-08-11 00:32:18.000000','s_manager','포스트맨으로 수정',1,NULL,NULL),
(5,'공지사항5내용입니다.',0,'2022-08-11 00:32:18.000000','s_manager','2022-08-11 00:32:18.000000','s_manager','공지사항5',1,NULL,NULL),
(6,'공지사항6내용입니다.',0,'2022-08-11 00:32:18.000000','s_manager','2022-08-11 00:32:18.000000','s_manager','공지사항6',1,NULL,NULL),
(7,'공지사항7내용입니다.',0,'2022-08-11 00:32:18.000000','s_manager','2022-08-11 00:32:18.000000','s_manager','공지사항7',1,NULL,NULL),
(8,'공지사항8내용입니다.',0,'2022-08-11 00:32:18.000000','s_manager','2022-08-11 00:32:18.000000','s_manager','공지사항8',1,NULL,NULL),
(9,'공지사항9내용입니다.',0,'2022-08-11 00:32:18.000000','s_manager','2022-08-11 00:32:18.000000','s_manager','공지사항9',1,NULL,NULL),
(10,'공지사항10내용입니다.',0,'2022-08-11 00:32:18.000000','s_manager','2022-08-11 00:32:18.000000','s_manager','공지사항10',1,NULL,NULL),
(11,'공지사항1 내용입니다.',0,'2022-08-11 00:33:01.000000','a_manager','2022-08-11 00:33:01.000000','a_manager','공지사항1',2,NULL,NULL),
(12,'공지사항2 내용입니다.',0,'2022-08-11 00:33:01.000000','a_manager','2022-08-11 00:33:01.000000','a_manager','공지사항2',2,NULL,NULL),
(13,'공지사항3 내용입니다.',0,'2022-08-11 00:33:01.000000','a_manager','2022-08-11 00:33:01.000000','a_manager','공지사항3',2,NULL,NULL),
(14,'공지사항4 내용입니다.',0,'2022-08-11 00:33:01.000000','a_manager','2022-08-11 00:33:01.000000','a_manager','공지사항4',2,NULL,NULL),
(15,'공지사항5 내용입니다.',0,'2022-08-11 00:33:01.000000','a_manager','2022-08-11 00:33:01.000000','a_manager','공지사항5',2,NULL,NULL),
(16,'공지사항6 내용입니다.',0,'2022-08-11 00:33:01.000000','a_manager','2022-08-11 00:33:01.000000','a_manager','공지사항6',2,NULL,NULL),
(17,'공지사항7 내용입니다.',0,'2022-08-11 00:33:01.000000','a_manager','2022-08-11 00:33:01.000000','a_manager','공지사항7',2,NULL,NULL),
(18,'공지사항8 내용입니다.',0,'2022-08-11 00:33:01.000000','a_manager','2022-08-11 00:33:01.000000','a_manager','공지사항8',2,NULL,NULL),
(19,'공지사항9 내용입니다.',0,'2022-08-11 00:33:01.000000','a_manager','2022-08-11 00:33:01.000000','a_manager','공지사항9',2,NULL,NULL),
(20,'공지사항10 내용입니다.',0,'2022-08-11 00:33:01.000000','a_manager','2022-08-11 00:33:01.000000','a_manager','공지사항10',2,NULL,NULL),
(21,'공지사항1 내용입니다.',0,'2022-08-11 00:33:40.000000','l_manager','2022-08-11 00:33:40.000000','l_manager','공지사항1',3,NULL,NULL),
(22,'공지사항2 내용입니다.',0,'2022-08-11 00:33:40.000000','l_manager','2022-08-11 00:33:40.000000','l_manager','공지사항2',3,NULL,NULL),
(23,'공지사항3 내용입니다.',0,'2022-08-11 00:33:40.000000','l_manager','2022-08-11 00:33:40.000000','l_manager','공지사항3',3,NULL,NULL),
(24,'공지사항4 내용입니다.',0,'2022-08-11 00:33:40.000000','l_manager','2022-08-11 00:33:40.000000','l_manager','공지사항4',3,NULL,NULL),
(25,'공지사항5 내용입니다.',0,'2022-08-11 00:33:40.000000','l_manager','2022-08-11 00:33:40.000000','l_manager','공지사항5',3,NULL,NULL),
(26,'공지사항6 내용입니다.444dd',1,'2022-08-17 02:03:21.151541','비회원','2022-08-11 00:33:40.000000','l_manager','공지사항644dd',3,NULL,NULL),
(27,'공지사항7 내용입니다.',1,'2022-08-11 00:33:40.000000','l_manager','2022-08-11 00:33:40.000000','l_manager','공지사항7',3,NULL,NULL),
(28,'ccccccccccccc',1,'2022-08-17 02:04:34.062262','비회원','2022-08-11 00:33:40.000000','l_manager','cccccccccccc',3,NULL,NULL),
(29,'공지사항 수정 최종 테스트',0,'2022-08-17 10:24:09.079815','비회원','2022-08-11 00:33:40.000000','l_manager','공지사항 수정 최종 테스트',3,NULL,NULL),
(30,'공지사항10 내용입니다.555',1,'2022-08-17 01:23:22.310830','비회원','2022-08-11 00:33:40.000000','l_manager','공지사항555',3,NULL,NULL),
(31,'새글',1,'2022-08-13 07:59:30.018662','s_manager','2022-08-13 07:59:30.018662','s_manager','새굴',NULL,NULL,NULL),
(32,'새글3',1,'2022-08-13 08:00:56.227801','s_manager','2022-08-13 08:00:56.227801','s_manager','새글3',NULL,NULL,NULL),
(33,'<p>에디터테스트 내용111</p>',1,'2022-08-15 07:31:11.684818','비회원','2022-08-15 07:31:11.684818','비회원','에디터테스트111',NULL,NULL,NULL),
(34,'<p>작성 테스트 2번째 내용임</p>',1,'2022-08-15 08:41:20.942929','s_manager','2022-08-15 08:41:20.942929','s_manager','작성 테스트 2번째',NULL,NULL,NULL),
(35,'<p>오후 5시46분 공지사항 등록 테스트중&nbsp;</p>',1,'2022-08-15 08:46:44.836856','s_manager','2022-08-15 08:46:44.836856','s_manager','오후 5시46분 공지사항 등록 테스트중 ',NULL,NULL,NULL),
(36,'<p>테스트중</p><figure class=\"table\"><table><tbody><tr><td>&nbsp;</td></tr></tbody></table></figure><figure class=\"media\"><oembed url=\"https://www.youtube.com/watch?v=ZBNCG3Sbgag\"></oembed></figure>',1,'2022-08-15 08:51:14.300229','s_manager','2022-08-15 08:51:14.300229','s_manager','테스트중',NULL,NULL,NULL),
(37,'swagger에서 테스틎',1,'2022-08-15 09:08:10.390065','비회원','2022-08-15 09:02:21.603481','s_manager','swagger에서 테스틎',NULL,NULL,NULL),
(38,'<p>zzz</p>',1,'2022-08-15 09:16:07.809842','비회원','2022-08-15 09:10:18.163628','s_manager','swagger에서 테스틎22',NULL,NULL,NULL),
(39,'<p>develop에서 테스트</p>',1,'2022-08-15 10:07:24.825650','s_manager','2022-08-15 10:07:24.825650','s_manager','develop에서 테스트',NULL,NULL,NULL),
(40,'브랜드 공지사항 테스트입니다',1,'2022-08-15 10:27:09.155385','비회원','2022-08-15 10:27:09.155385','비회원','삼성전자 공지사항',1,NULL,NULL),
(41,'ㅁㅇㅇㄻㅇㄹㄴㄹ',1,'2022-08-16 17:40:17.904796','비회원','2022-08-16 04:36:34.319849','s_manager','API테스트',1,NULL,NULL),
(42,'<p>ㅇㅇㅇ</p>',1,'2022-08-16 17:41:40.229251','s_manager','2022-08-16 17:41:40.229251','s_manager','ㅇㅇㅇ',1,NULL,NULL),
(43,'<p>내용을 작성해주세요!teststestaetaseetssetteteetaetaetaestaestaetaet</p>',1,'2022-08-17 07:30:58.004749','s_manager','2022-08-17 07:30:58.004749','s_manager','test',1,NULL,NULL),
(44,'수정 두번째 테스트dd',0,'2022-08-17 11:44:14.742841','비회원','2022-08-17 17:25:46.759600','비회원','수정 두번째 테스트dd',1,NULL,NULL),
(45,'새글새글',0,'2022-08-17 14:24:34.896761','비회원','2022-08-17 14:24:25.695990','sss333','새글',1,NULL,NULL),
(46,'<p>dd</p>',0,'2022-08-18 02:36:55.214537','sss333','2022-08-18 02:36:55.214537','sss333','d',1,NULL,NULL),
(47,'<p>오늘의 공지사항입니다.</p>',1,'2022-08-18 08:33:34.789007','s_manager','2022-08-18 08:33:34.789007','s_manager','08/19 공지사항',1,NULL,NULL),
(48,'<p>공지사항입니다.</p>',0,'2022-08-18 08:37:12.726460','s_manager','2022-08-18 08:37:12.726460','s_manager','공지사항입니다.',1,NULL,NULL),
(49,'<p>오늘의 공지사항 입니다.</p>',0,'2022-08-18 08:54:20.369749','s_manager','2022-08-18 08:54:20.369749','s_manager','공지사항 입니다.',1,NULL,NULL);
/*!40000 ALTER TABLE `t_notice` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-08-19 11:21:12
