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
-- Table structure for table `t_manager`
--

DROP TABLE IF EXISTS `t_manager`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_manager` (
  `manager_seq` bigint(20) NOT NULL AUTO_INCREMENT,
  `manager_del_yn` tinyint(1) DEFAULT 0,
  `manager_email` varchar(50) DEFAULT NULL,
  `manager_id` varchar(25) DEFAULT NULL,
  `manager_image_url` varchar(255) DEFAULT NULL,
  `manager_mod_date` datetime(6) DEFAULT NULL,
  `manager_mod_user` varchar(255) DEFAULT NULL,
  `manager_name` varchar(10) DEFAULT NULL,
  `manager_pass` varchar(100) DEFAULT NULL,
  `manager_reg_date` datetime(6) DEFAULT NULL,
  `manager_reg_user` varchar(255) DEFAULT NULL,
  `manager_tel` varchar(15) DEFAULT NULL,
  `brand_seq` bigint(20) DEFAULT NULL,
  `manager_mod_user_seq` int(11) DEFAULT NULL,
  `manager_reg_user_seq` int(11) DEFAULT NULL,
  PRIMARY KEY (`manager_seq`),
  UNIQUE KEY `UK_a6qvk8fjgyfgn5jhecsvtpu1s` (`manager_id`),
  KEY `FK20uvfgrwtvervjto77l1xpj3r` (`brand_seq`),
  CONSTRAINT `FK20uvfgrwtvervjto77l1xpj3r` FOREIGN KEY (`brand_seq`) REFERENCES `t_brand` (`brand_seq`)
) ENGINE=InnoDB AUTO_INCREMENT=182 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_manager`
--

LOCK TABLES `t_manager` WRITE;
/*!40000 ALTER TABLE `t_manager` DISABLE KEYS */;
INSERT INTO `t_manager` VALUES
(51,0,'manager1@samsung.com','s_manager1','s_manager_img1','2022-08-11 00:09:28.000000','s_manager1','삼성매니저1','1234','2022-08-11 00:09:28.000000','s_manager1','123-3451',1,NULL,NULL),
(52,0,'manager2@samsung.com','s_manager2','s_manager_img2','2022-08-11 00:09:28.000000','s_manager2','삼성매니저2','1234','2022-08-11 00:09:28.000000','s_manager2','123-3452',1,NULL,NULL),
(53,0,'manager3@samsung.com','s_manager3','s_manager_img3','2022-08-11 00:09:28.000000','s_manager3','삼성매니저3','1234','2022-08-11 00:09:28.000000','s_manager3','123-3453',1,NULL,NULL),
(54,0,'manager4@samsung.com','s_manager4','s_manager_img4','2022-08-11 00:09:28.000000','s_manager4','삼성매니저4','1234','2022-08-11 00:09:28.000000','s_manager4','123-3454',1,NULL,NULL),
(55,0,'manager5@samsung.com','s_manager5','s_manager_img5','2022-08-11 00:09:28.000000','s_manager5','삼성매니저5','1234','2022-08-11 00:09:28.000000','s_manager5','123-3455',1,NULL,NULL),
(56,0,'manager6@samsung.com','s_manager6','s_manager_img6','2022-08-11 00:09:28.000000','s_manager6','삼성매니저6','1234','2022-08-11 00:09:28.000000','s_manager6','123-3456',1,NULL,NULL),
(57,0,'manager7@samsung.com','s_manager7','s_manager_img7','2022-08-11 00:09:28.000000','s_manager7','삼성매니저7','1234','2022-08-11 00:09:28.000000','s_manager7','123-3457',1,NULL,NULL),
(58,0,'manager8@samsung.com','s_manager8','s_manager_img8','2022-08-11 00:09:28.000000','s_manager8','삼성매니저8','1234','2022-08-11 00:09:28.000000','s_manager8','123-3458',1,NULL,NULL),
(59,0,'manager9@samsung.com','s_manager9','s_manager_img9','2022-08-11 00:09:28.000000','s_manager9','삼성매니저9','1234','2022-08-11 00:09:28.000000','s_manager9','123-3459',1,NULL,NULL),
(60,0,'manager10@samsung.com','s_manager10','s_manager_img10','2022-08-11 00:09:28.000000','s_manager10','삼성매니저10','1234','2022-08-11 00:09:28.000000','s_manager10','123-34510',1,NULL,NULL),
(61,0,'manager11@samsung.com','s_manager11','s_manager_img11','2022-08-11 00:09:28.000000','s_manager11','삼성매니저11','1234','2022-08-11 00:09:28.000000','s_manager11','123-34511',1,NULL,NULL),
(62,0,'manager12@samsung.com','s_manager12','s_manager_img12','2022-08-11 00:09:28.000000','s_manager12','삼성매니저12','1234','2022-08-11 00:09:28.000000','s_manager12','123-34512',1,NULL,NULL),
(63,0,'manager13@samsung.com','s_manager13','s_manager_img13','2022-08-11 00:09:28.000000','s_manager13','삼성매니저13','1234','2022-08-11 00:09:28.000000','s_manager13','123-34513',1,NULL,NULL),
(64,0,'manager14@samsung.com','s_manager14','s_manager_img14','2022-08-11 00:09:28.000000','s_manager14','삼성매니저14','1234','2022-08-11 00:09:28.000000','s_manager14','123-34514',1,NULL,NULL),
(65,0,'manager15@samsung.com','s_manager15','s_manager_img15','2022-08-11 00:09:28.000000','s_manager15','삼성매니저15','1234','2022-08-11 00:09:28.000000','s_manager15','123-34515',1,NULL,NULL),
(66,0,'manager16@samsung.com','s_manager16','s_manager_img16','2022-08-11 00:09:28.000000','s_manager16','삼성매니저16','1234','2022-08-11 00:09:28.000000','s_manager16','123-34516',1,NULL,NULL),
(67,0,'manager17@samsung.com','s_manager17','s_manager_img17','2022-08-11 00:09:28.000000','s_manager17','삼성매니저17','1234','2022-08-11 00:09:28.000000','s_manager17','123-34517',1,NULL,NULL),
(68,0,'manager18@samsung.com','s_manager18','s_manager_img18','2022-08-11 00:09:28.000000','s_manager18','삼성매니저18','1234','2022-08-11 00:09:28.000000','s_manager18','123-34518',1,NULL,NULL),
(69,0,'manager19@samsung.com','s_manager19','s_manager_img19','2022-08-11 00:09:28.000000','s_manager19','삼성매니저19','1234','2022-08-11 00:09:28.000000','s_manager19','123-34519',1,NULL,NULL),
(70,0,'manager20@samsung.com','s_manager20','s_manager_img20','2022-08-11 00:09:28.000000','s_manager20','삼성매니저20','1234','2022-08-11 00:09:28.000000','s_manager20','123-34520',1,NULL,NULL),
(71,0,'manager21@samsung.com','s_manager21','s_manager_img21','2022-08-11 00:09:28.000000','s_manager21','삼성매니저21','1234','2022-08-11 00:09:28.000000','s_manager21','123-34521',1,NULL,NULL),
(72,0,'manager22@samsung.com','s_manager22','s_manager_img22','2022-08-11 00:09:28.000000','s_manager22','삼성매니저22','1234','2022-08-11 00:09:28.000000','s_manager22','123-34522',1,NULL,NULL),
(73,0,'manager23@samsung.com','s_manager23','s_manager_img23','2022-08-11 00:09:28.000000','s_manager23','삼성매니저23','1234','2022-08-11 00:09:28.000000','s_manager23','123-34523',1,NULL,NULL),
(74,0,'manager24@samsung.com','s_manager24','s_manager_img24','2022-08-11 00:09:28.000000','s_manager24','삼성매니저24','1234','2022-08-11 00:09:28.000000','s_manager24','123-34524',1,NULL,NULL),
(75,0,'manager25@samsung.com','s_manager25','s_manager_img25','2022-08-11 00:09:28.000000','s_manager25','삼성매니저25','1234','2022-08-11 00:09:28.000000','s_manager25','123-34525',1,NULL,NULL),
(76,0,'manager26@samsung.com','s_manager26','s_manager_img26','2022-08-11 00:09:28.000000','s_manager26','삼성매니저26','1234','2022-08-11 00:09:28.000000','s_manager26','123-34526',1,NULL,NULL),
(77,0,'manager27@samsung.com','s_manager27','s_manager_img27','2022-08-11 00:09:28.000000','s_manager27','삼성매니저27','1234','2022-08-11 00:09:28.000000','s_manager27','123-34527',1,NULL,NULL),
(78,0,'manager28@samsung.com','s_manager28','s_manager_img28','2022-08-11 00:09:28.000000','s_manager28','삼성매니저28','1234','2022-08-11 00:09:28.000000','s_manager28','123-34528',1,NULL,NULL),
(79,0,'manager29@samsung.com','s_manager29','s_manager_img29','2022-08-11 00:09:28.000000','s_manager29','삼성매니저29','1234','2022-08-11 00:09:28.000000','s_manager29','123-34529',1,NULL,NULL),
(80,0,'manager30@samsung.com','s_manager30','s_manager_img30','2022-08-11 00:09:28.000000','s_manager30','삼성매니저30','1234','2022-08-11 00:09:28.000000','s_manager30','123-34530',1,NULL,NULL),
(81,0,'manager31@samsung.com','s_manager31','s_manager_img31','2022-08-11 00:09:28.000000','s_manager31','삼성매니저31','1234','2022-08-11 00:09:28.000000','s_manager31','123-34531',1,NULL,NULL),
(82,0,'manager32@samsung.com','s_manager32','s_manager_img32','2022-08-11 00:09:28.000000','s_manager32','삼성매니저32','1234','2022-08-11 00:09:28.000000','s_manager32','123-34532',1,NULL,NULL),
(83,0,'manager33@samsung.com','s_manager33','s_manager_img33','2022-08-11 00:09:28.000000','s_manager33','삼성매니저33','1234','2022-08-11 00:09:28.000000','s_manager33','123-34533',1,NULL,NULL),
(84,0,'manager34@samsung.com','s_manager34','s_manager_img34','2022-08-11 00:09:28.000000','s_manager34','삼성매니저34','1234','2022-08-11 00:09:28.000000','s_manager34','123-34534',1,NULL,NULL),
(85,0,'manager35@samsung.com','s_manager35','s_manager_img35','2022-08-11 00:09:28.000000','s_manager35','삼성매니저35','1234','2022-08-11 00:09:28.000000','s_manager35','123-34535',1,NULL,NULL),
(86,0,'manager36@samsung.com','s_manager36','s_manager_img36','2022-08-11 00:09:28.000000','s_manager36','삼성매니저36','1234','2022-08-11 00:09:28.000000','s_manager36','123-34536',1,NULL,NULL),
(87,0,'manager37@samsung.com','s_manager37','s_manager_img37','2022-08-11 00:09:28.000000','s_manager37','삼성매니저37','1234','2022-08-11 00:09:28.000000','s_manager37','123-34537',1,NULL,NULL),
(88,0,'manager38@samsung.com','s_manager38','s_manager_img38','2022-08-11 00:09:28.000000','s_manager38','삼성매니저38','1234','2022-08-11 00:09:28.000000','s_manager38','123-34538',1,NULL,NULL),
(89,0,'manager39@samsung.com','s_manager39','s_manager_img39','2022-08-11 00:09:28.000000','s_manager39','삼성매니저39','1234','2022-08-11 00:09:28.000000','s_manager39','123-34539',1,NULL,NULL),
(90,0,'manager40@samsung.com','s_manager40','s_manager_img40','2022-08-11 00:09:28.000000','s_manager40','삼성매니저40','1234','2022-08-11 00:09:28.000000','s_manager40','123-34540',1,NULL,NULL),
(91,0,'manager41@samsung.com','s_manager41','s_manager_img41','2022-08-11 00:09:28.000000','s_manager41','삼성매니저41','1234','2022-08-11 00:09:28.000000','s_manager41','123-34541',1,NULL,NULL),
(92,0,'manager42@samsung.com','s_manager42','s_manager_img42','2022-08-11 00:09:28.000000','s_manager42','삼성매니저42','1234','2022-08-11 00:09:28.000000','s_manager42','123-34542',1,NULL,NULL),
(93,0,'manager43@samsung.com','s_manager43','s_manager_img43','2022-08-11 00:09:28.000000','s_manager43','삼성매니저43','1234','2022-08-11 00:09:28.000000','s_manager43','123-34543',1,NULL,NULL),
(94,0,'manager44@samsung.com','s_manager44','s_manager_img44','2022-08-11 00:09:28.000000','s_manager44','삼성매니저44','1234','2022-08-11 00:09:28.000000','s_manager44','123-34544',1,NULL,NULL),
(95,0,'manager45@samsung.com','s_manager45','s_manager_img45','2022-08-11 00:09:28.000000','s_manager45','삼성매니저45','1234','2022-08-11 00:09:28.000000','s_manager45','123-34545',1,NULL,NULL),
(96,0,'manager46@samsung.com','s_manager46','s_manager_img46','2022-08-11 00:09:28.000000','s_manager46','삼성매니저46','1234','2022-08-11 00:09:28.000000','s_manager46','123-34546',1,NULL,NULL),
(97,0,'manager47@samsung.com','s_manager47','s_manager_img47','2022-08-11 00:09:28.000000','s_manager47','삼성매니저47','1234','2022-08-11 00:09:28.000000','s_manager47','123-34547',1,NULL,NULL),
(98,0,'manager48@samsung.com','s_manager48','s_manager_img48','2022-08-11 00:09:28.000000','s_manager48','삼성매니저48','1234','2022-08-11 00:09:28.000000','s_manager48','123-34548',1,NULL,NULL),
(99,0,'manager49@samsung.com','s_manager49','s_manager_img49','2022-08-11 00:09:28.000000','s_manager49','삼성매니저49','1234','2022-08-11 00:09:28.000000','s_manager49','123-34549',1,NULL,NULL),
(100,0,'manager50@samsung.com','s_manager50','s_manager_img50','2022-08-11 00:09:28.000000','s_manager50','삼성매니저50','1234','2022-08-11 00:09:28.000000','s_manager50','123-34550',1,NULL,NULL),
(101,0,'manager1@apple.com','a_manager1','a_manager_img1','2022-08-11 00:17:34.000000','a_manager1','애플매니저1','1234','2022-08-11 00:17:34.000000','a_manager1','123-3451',2,NULL,NULL),
(102,0,'manager2@apple.com','a_manager2','a_manager_img2','2022-08-11 00:17:34.000000','a_manager2','애플매니저2','1234','2022-08-11 00:17:34.000000','a_manager2','123-3452',2,NULL,NULL),
(103,0,'manager3@apple.com','a_manager3','a_manager_img3','2022-08-11 00:17:34.000000','a_manager3','애플매니저3','1234','2022-08-11 00:17:34.000000','a_manager3','123-3453',2,NULL,NULL),
(104,0,'manager4@apple.com','a_manager4','a_manager_img4','2022-08-11 00:17:34.000000','a_manager4','애플매니저4','1234','2022-08-11 00:17:34.000000','a_manager4','123-3454',2,NULL,NULL),
(105,0,'manager5@apple.com','a_manager5','a_manager_img5','2022-08-11 00:17:34.000000','a_manager5','애플매니저5','1234','2022-08-11 00:17:34.000000','a_manager5','123-3455',2,NULL,NULL),
(106,0,'manager6@apple.com','a_manager6','a_manager_img6','2022-08-11 00:17:34.000000','a_manager6','애플매니저6','1234','2022-08-11 00:17:34.000000','a_manager6','123-3456',2,NULL,NULL),
(107,0,'manager7@apple.com','a_manager7','a_manager_img7','2022-08-11 00:17:34.000000','a_manager7','애플매니저7','1234','2022-08-11 00:17:34.000000','a_manager7','123-3457',2,NULL,NULL),
(108,0,'manager8@apple.com','a_manager8','a_manager_img8','2022-08-11 00:17:34.000000','a_manager8','애플매니저8','1234','2022-08-11 00:17:34.000000','a_manager8','123-3458',2,NULL,NULL),
(109,0,'manager9@apple.com','a_manager9','a_manager_img9','2022-08-11 00:17:34.000000','a_manager9','애플매니저9','1234','2022-08-11 00:17:34.000000','a_manager9','123-3459',2,NULL,NULL),
(110,0,'manager10@apple.com','a_manager10','a_manager_img10','2022-08-11 00:17:34.000000','a_manager10','애플매니저10','1234','2022-08-11 00:17:34.000000','a_manager10','123-34510',2,NULL,NULL),
(111,0,'manager11@apple.com','a_manager11','a_manager_img11','2022-08-11 00:17:34.000000','a_manager11','애플매니저11','1234','2022-08-11 00:17:34.000000','a_manager11','123-34511',2,NULL,NULL),
(112,0,'manager12@apple.com','a_manager12','a_manager_img12','2022-08-11 00:17:34.000000','a_manager12','애플매니저12','1234','2022-08-11 00:17:34.000000','a_manager12','123-34512',2,NULL,NULL),
(113,0,'manager13@apple.com','a_manager13','a_manager_img13','2022-08-11 00:17:34.000000','a_manager13','애플매니저13','1234','2022-08-11 00:17:34.000000','a_manager13','123-34513',2,NULL,NULL),
(114,0,'manager14@apple.com','a_manager14','a_manager_img14','2022-08-11 00:17:34.000000','a_manager14','애플매니저14','1234','2022-08-11 00:17:34.000000','a_manager14','123-34514',2,NULL,NULL),
(115,0,'manager15@apple.com','a_manager15','a_manager_img15','2022-08-11 00:17:34.000000','a_manager15','애플매니저15','1234','2022-08-11 00:17:34.000000','a_manager15','123-34515',2,NULL,NULL),
(116,0,'manager16@apple.com','a_manager16','a_manager_img16','2022-08-11 00:17:34.000000','a_manager16','애플매니저16','1234','2022-08-11 00:17:34.000000','a_manager16','123-34516',2,NULL,NULL),
(117,0,'manager17@apple.com','a_manager17','a_manager_img17','2022-08-11 00:17:34.000000','a_manager17','애플매니저17','1234','2022-08-11 00:17:34.000000','a_manager17','123-34517',2,NULL,NULL),
(118,0,'manager18@apple.com','a_manager18','a_manager_img18','2022-08-11 00:17:34.000000','a_manager18','애플매니저18','1234','2022-08-11 00:17:34.000000','a_manager18','123-34518',2,NULL,NULL),
(119,0,'manager19@apple.com','a_manager19','a_manager_img19','2022-08-11 00:17:34.000000','a_manager19','애플매니저19','1234','2022-08-11 00:17:34.000000','a_manager19','123-34519',2,NULL,NULL),
(120,0,'manager20@apple.com','a_manager20','a_manager_img20','2022-08-11 00:17:34.000000','a_manager20','애플매니저20','1234','2022-08-11 00:17:34.000000','a_manager20','123-34520',2,NULL,NULL),
(121,0,'manager21@apple.com','a_manager21','a_manager_img21','2022-08-11 00:17:34.000000','a_manager21','애플매니저21','1234','2022-08-11 00:17:34.000000','a_manager21','123-34521',2,NULL,NULL),
(122,0,'manager22@apple.com','a_manager22','a_manager_img22','2022-08-11 00:17:34.000000','a_manager22','애플매니저22','1234','2022-08-11 00:17:34.000000','a_manager22','123-34522',2,NULL,NULL),
(123,0,'manager23@apple.com','a_manager23','a_manager_img23','2022-08-11 00:17:34.000000','a_manager23','애플매니저23','1234','2022-08-11 00:17:34.000000','a_manager23','123-34523',2,NULL,NULL),
(124,0,'manager24@apple.com','a_manager24','a_manager_img24','2022-08-11 00:17:34.000000','a_manager24','애플매니저24','1234','2022-08-11 00:17:34.000000','a_manager24','123-34524',2,NULL,NULL),
(125,0,'manager25@apple.com','a_manager25','a_manager_img25','2022-08-11 00:17:34.000000','a_manager25','애플매니저25','1234','2022-08-11 00:17:34.000000','a_manager25','123-34525',2,NULL,NULL),
(126,0,'manager26@apple.com','a_manager26','a_manager_img26','2022-08-11 00:17:34.000000','a_manager26','애플매니저26','1234','2022-08-11 00:17:34.000000','a_manager26','123-34526',2,NULL,NULL),
(127,0,'manager27@apple.com','a_manager27','a_manager_img27','2022-08-11 00:17:34.000000','a_manager27','애플매니저27','1234','2022-08-11 00:17:34.000000','a_manager27','123-34527',2,NULL,NULL),
(128,0,'manager28@apple.com','a_manager28','a_manager_img28','2022-08-11 00:17:34.000000','a_manager28','애플매니저28','1234','2022-08-11 00:17:34.000000','a_manager28','123-34528',2,NULL,NULL),
(129,0,'manager29@apple.com','a_manager29','a_manager_img29','2022-08-11 00:17:34.000000','a_manager29','애플매니저29','1234','2022-08-11 00:17:34.000000','a_manager29','123-34529',2,NULL,NULL),
(130,0,'manager30@apple.com','a_manager30','a_manager_img30','2022-08-11 00:17:34.000000','a_manager30','애플매니저30','1234','2022-08-11 00:17:34.000000','a_manager30','123-34530',2,NULL,NULL),
(131,0,'manager31@apple.com','a_manager31','a_manager_img31','2022-08-11 00:17:34.000000','a_manager31','애플매니저31','1234','2022-08-11 00:17:34.000000','a_manager31','123-34531',2,NULL,NULL),
(132,0,'manager32@apple.com','a_manager32','a_manager_img32','2022-08-11 00:17:34.000000','a_manager32','애플매니저32','1234','2022-08-11 00:17:34.000000','a_manager32','123-34532',2,NULL,NULL),
(133,0,'manager33@apple.com','a_manager33','a_manager_img33','2022-08-11 00:17:34.000000','a_manager33','애플매니저33','1234','2022-08-11 00:17:34.000000','a_manager33','123-34533',2,NULL,NULL),
(134,0,'manager34@apple.com','a_manager34','a_manager_img34','2022-08-11 00:17:34.000000','a_manager34','애플매니저34','1234','2022-08-11 00:17:34.000000','a_manager34','123-34534',2,NULL,NULL),
(135,0,'manager35@apple.com','a_manager35','a_manager_img35','2022-08-11 00:17:34.000000','a_manager35','애플매니저35','1234','2022-08-11 00:17:34.000000','a_manager35','123-34535',2,NULL,NULL),
(136,0,'manager36@apple.com','a_manager36','a_manager_img36','2022-08-11 00:17:34.000000','a_manager36','애플매니저36','1234','2022-08-11 00:17:34.000000','a_manager36','123-34536',2,NULL,NULL),
(137,0,'manager37@apple.com','a_manager37','a_manager_img37','2022-08-11 00:17:34.000000','a_manager37','애플매니저37','1234','2022-08-11 00:17:34.000000','a_manager37','123-34537',2,NULL,NULL),
(138,0,'manager38@apple.com','a_manager38','a_manager_img38','2022-08-11 00:17:34.000000','a_manager38','애플매니저38','1234','2022-08-11 00:17:34.000000','a_manager38','123-34538',2,NULL,NULL),
(139,0,'manager39@apple.com','a_manager39','a_manager_img39','2022-08-11 00:17:34.000000','a_manager39','애플매니저39','1234','2022-08-11 00:17:34.000000','a_manager39','123-34539',2,NULL,NULL),
(140,0,'manager40@apple.com','a_manager40','a_manager_img40','2022-08-11 00:17:34.000000','a_manager40','애플매니저40','1234','2022-08-11 00:17:34.000000','a_manager40','123-34540',2,NULL,NULL),
(141,0,'manager41@apple.com','a_manager41','a_manager_img41','2022-08-11 00:17:34.000000','a_manager41','애플매니저41','1234','2022-08-11 00:17:34.000000','a_manager41','123-34541',2,NULL,NULL),
(142,0,'manager42@apple.com','a_manager42','a_manager_img42','2022-08-11 00:17:34.000000','a_manager42','애플매니저42','1234','2022-08-11 00:17:34.000000','a_manager42','123-34542',2,NULL,NULL),
(143,0,'manager43@apple.com','a_manager43','a_manager_img43','2022-08-11 00:17:34.000000','a_manager43','애플매니저43','1234','2022-08-11 00:17:34.000000','a_manager43','123-34543',2,NULL,NULL),
(144,0,'manager44@apple.com','a_manager44','a_manager_img44','2022-08-11 00:17:34.000000','a_manager44','애플매니저44','1234','2022-08-11 00:17:34.000000','a_manager44','123-34544',2,NULL,NULL),
(145,0,'manager45@apple.com','a_manager45','a_manager_img45','2022-08-11 00:17:34.000000','a_manager45','애플매니저45','1234','2022-08-11 00:17:34.000000','a_manager45','123-34545',2,NULL,NULL),
(146,0,'manager46@apple.com','a_manager46','a_manager_img46','2022-08-11 00:17:34.000000','a_manager46','애플매니저46','1234','2022-08-11 00:17:34.000000','a_manager46','123-34546',2,NULL,NULL),
(147,0,'manager47@apple.com','a_manager47','a_manager_img47','2022-08-11 00:17:34.000000','a_manager47','애플매니저47','1234','2022-08-11 00:17:34.000000','a_manager47','123-34547',2,NULL,NULL),
(148,0,'manager48@apple.com','a_manager48','a_manager_img48','2022-08-11 00:17:34.000000','a_manager48','애플매니저48','1234','2022-08-11 00:17:34.000000','a_manager48','123-34548',2,NULL,NULL),
(149,0,'manager49@apple.com','a_manager49','a_manager_img49','2022-08-11 00:17:34.000000','a_manager49','애플매니저49','1234','2022-08-11 00:17:34.000000','a_manager49','123-34549',2,NULL,NULL),
(150,0,'manager50@apple.com','a_manager50','a_manager_img50','2022-08-11 00:17:34.000000','a_manager50','애플매니저50','1234','2022-08-11 00:17:34.000000','a_manager50','123-34550',2,NULL,NULL),
(151,0,'manager@samsung.com','s_manager','https://sellerb.s3.ap-northeast-2.amazonaws.com/consultant/pcody8dxi.png','2022-08-17 02:07:03.574320','비회원','삼성매니저','$2a$10$Jwrm8/vycaD.e2xP/V/NLu3M9tvT./Vl2.9Prxxy0RZkNelNRLmcu','2022-08-11 00:25:30.191767','비회원','010-1234-3456',1,NULL,NULL),
(152,0,'manager@apple.com','a_manager','https://sellerb.s3.ap-northeast-2.amazonaws.com/consultant/pcody8dxi.png','2022-08-11 00:28:15.914603','비회원','애플매니저','$2a$10$f1UtWtsTnk/xklyIO6U0XOpFvIRUF0ei9AMsE3cLamhipf6F1hllW','2022-08-11 00:28:15.914603','비회원','010-1234-5678',2,NULL,NULL),
(153,0,'manager@lg.com','l_manager','https://sellerb.s3.ap-northeast-2.amazonaws.com/consultant/pcody8dxi.png','2022-08-11 00:28:51.966607','비회원','엘지매니저','$2a$10$l4GWEeH8QKOC8lg1vwF7gObga1PSHiOQhAv49TvsKpwqO/mIj0r7C','2022-08-11 00:28:51.966607','비회원','010-1234-5678',3,NULL,NULL),
(154,0,'sam@sam.com','s_test','https://sellerb.s3.ap-northeast-2.amazonaws.com/product/image.%EA%B3%A0%EC%96%91%EC%9D%B4%EC%9D%B4%EB%AF%B8%EC%A7%80_1660627589033.jpg','2022-08-16 05:27:01.824664','비회원','','$2a$10$/IqoLZcCZwZvoepY9dDFh.qlXQuGuPhHFB5y1qWfikNobeA6GntG.','2022-08-16 05:27:01.824664','비회원','010-0000-0000',1,NULL,NULL),
(155,0,'수정test@test.com','s_test_2','https://sellerb.s3.ap-northeast-2.amazonaws.com/product/image.%EA%B3%A0%EC%B0%BD%EC%84%9D%EA%B3%A0%EC%96%91%EC%9D%B4_1660627789136.jpg','2022-08-16 05:44:53.422972','비회원','박삼성','$2a$10$CcCfE1XJ.m8uAKQ10TWEKuhjT3fVsXq.VOtV9/N2f53PJopwcqble','2022-08-16 05:30:15.343297','비회원','010-3333-3333',1,NULL,NULL),
(180,0,'test1234@gmail.com','test1234','https://sellerb.s3.ap-northeast-2.amazonaws.com/manager/image.%EB%8B%A4%EC%9A%B4%EB%A1%9C%EB%93%9C%20%282%29_1660831115582.jfif','2022-08-18 13:59:03.099525','비회원','구경하러옴','$2a$10$T0g49RdCmm.2/MajZwOg8Oq5FlfnVrculPnJMo3i6WoLNecK4obCe','2022-08-18 13:59:03.099525','비회원','01011111111',1,NULL,NULL),
(181,0,'','siryeong','','2022-08-18 16:36:54.610648','비회원','시령','$2a$10$8Igi/2gQ4feL4IeTbm2K8uNkXYC2hzidZPvqGgwSVo.gY4p///U1G','2022-08-18 16:36:54.610648','비회원','01000000000',1,NULL,NULL);
/*!40000 ALTER TABLE `t_manager` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-08-19 11:21:15
