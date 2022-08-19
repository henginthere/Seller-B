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
-- Table structure for table `t_product`
--

DROP TABLE IF EXISTS `t_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_product` (
  `product_seq` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_del_yn` tinyint(1) DEFAULT 0,
  `product_id` varchar(25) DEFAULT NULL,
  `product_manual` varchar(255) DEFAULT NULL,
  `product_mod_date` datetime(6) DEFAULT NULL,
  `product_mod_user` varchar(255) DEFAULT NULL,
  `product_name` varchar(40) DEFAULT NULL,
  `product_price` int(11) DEFAULT NULL,
  `product_reg_date` datetime(6) DEFAULT NULL,
  `product_reg_user` varchar(255) DEFAULT NULL,
  `product_thumbnail` varchar(255) DEFAULT NULL,
  `product_group_seq` bigint(20) DEFAULT NULL,
  `product_mod_user_seq` int(11) DEFAULT NULL,
  `product_reg_user_seq` int(11) DEFAULT NULL,
  PRIMARY KEY (`product_seq`),
  KEY `FKh8xpb634d67lq1y2wwidjqfgt` (`product_group_seq`),
  CONSTRAINT `FKh8xpb634d67lq1y2wwidjqfgt` FOREIGN KEY (`product_group_seq`) REFERENCES `t_product_group` (`product_group_seq`)
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_product`
--

LOCK TABLES `t_product` WRITE;
/*!40000 ALTER TABLE `t_product` DISABLE KEYS */;
INSERT INTO `t_product` VALUES
(1,0,'KU85UA7000FXKR','https://www.samsung.com/sec/tvs/uhd-ua7000fxkr-d2c/KU85UA7000FXKR/','2022-08-11 11:52:14.000000','s_manager','UHD 214 cm 스탠드형',4190000,'2022-08-11 11:52:14.000000','s_manager','https://sellerb.s3.ap-northeast-2.amazonaws.com/product/image.1_1660279015671.png',1,NULL,NULL),
(2,0,'KQ75QB65AFXKR','https://www.samsung.com/sec/tvs/qled-qb65afxkr-d2c/KQ75QB65AFXKR/','2022-08-11 12:03:16.000000','s_manager','2022 QLED 4K 189 cm 스탠드형\n',3690000,'2022-08-11 12:03:16.000000','s_manager','https://sellerb.s3.ap-northeast-2.amazonaws.com/product/image.2_1660279246834.png',1,NULL,NULL),
(3,1,'KQ65QNB700FXKR','https://www.samsung.com/sec/tvs/neo-qled-8k-qnb700fxkr-d2c/KQ65QNB700FXKR/','2022-08-11 13:08:37.000000','s_manager','2022 Neo QLED 8K 163cm 스탠드형',5290000,'2022-08-11 13:08:37.000000','s_manager','https://sellerb.s3.ap-northeast-2.amazonaws.com/product/image.3_1660279285647.png',1,NULL,NULL),
(4,0,'RF85A92413Y','https://www.samsung.com/sec/refrigerators/french-door-rf85a92413y-d2c/RF85A92413Y/','2022-08-11 13:13:05.000000','s_manager','BESPOKE 냉장고 4도어 프리스탠딩 866 L',2299000,'2022-08-11 13:13:05.000000','s_manager','https://sellerb.s3.ap-northeast-2.amazonaws.com/product/image.4_1660823224072.png',2,NULL,NULL),
(5,0,'RS84T50716C','https://www.samsung.com/sec/refrigerators/side-by-side-rs84t5071-d2c/RS84T50716C/','2022-08-17 11:44:31.145603','비회원','양문형 냉장고 846 Ld',2310000,'2022-08-11 13:14:22.000000','s_manager','https://sellerb.s3.ap-northeast-2.amazonaws.com/product/image.5_1660279323596.png',2,NULL,NULL),
(6,0,'WF25B9600KE','https://www.samsung.com/sec/washing-machines/wf25b9600ke-d2c/WF25B9600KE/','2022-08-11 13:17:38.000000','s_manager','BESPOKE 그랑데 세탁기 AI 25 kg [올인원컨트롤]',1949000,'2022-08-11 13:17:38.000000','s_manager','https://sellerb.s3.ap-northeast-2.amazonaws.com/product/image.6_1660279351628.png',6,NULL,NULL),
(7,0,'WF24T8000KP','https://www.samsung.com/sec/washing-machines/grande-wf24t8000kv-d2c3/WF24T8000KP/','2022-08-11 13:27:22.000000','s_manager','그랑데 세탁기 24 kg',910000,'2022-08-11 13:27:22.000000','s_manager','https://sellerb.s3.ap-northeast-2.amazonaws.com/product/image.7_1660279375578.png',6,NULL,NULL),
(8,0,'DV20A9740CE','https://www.samsung.com/sec/business/dryers/dv20a9740ce/DV20A9740CE/','2022-08-11 13:28:36.000000','s_manager','BESPOKE 그랑데 건조기 AI 20 kg [올인원컨트롤]',1899000,'2022-08-11 13:28:36.000000','s_manager','https://sellerb.s3.ap-northeast-2.amazonaws.com/product/image.8_1660279412048.png',7,NULL,NULL),
(10,0,'SM-F721NLVEKOO','https://www.samsung.com/sec/galaxy-z-flip4/preorder/','2022-08-11 13:34:47.117663','s_manager','갤럭시 Z 플립4 자급제',1353000,'2022-08-11 13:34:47.117663','s_manager','https://sellerb.s3.ap-northeast-2.amazonaws.com/product/image.10_1660279430071.png',8,NULL,NULL),
(11,0,'A2638','https://www.apple.com/kr/iphone-13-pro/specs/','2022-08-11 13:43:13.000000','a_manager','iPhone 13 Pro 128G',1350000,'2022-08-11 13:43:13.000000','a_manager','https://sellerb.s3.ap-northeast-2.amazonaws.com/product/image.11_1660281397856.png',9,NULL,NULL),
(12,0,'A2643','https://www.apple.com/kr/iphone-13-pro/specs/','2022-08-11 13:43:47.000000','a_manager','iPhone 13 Pro Max 128G',1490000,'2022-08-11 13:43:47.000000','a_manager','https://sellerb.s3.ap-northeast-2.amazonaws.com/product/image.12_1660281412820.png',9,NULL,NULL),
(13,0,'A2377','https://support.apple.com/kb/SP843?viewlocale=ko_KR&locale=ko_KR','2022-08-11 13:47:00.000000','a_manager','iPad Pro Wi-Fi 256GB',1129000,'2022-08-11 13:47:00.000000','a_manager','https://sellerb.s3.ap-northeast-2.amazonaws.com/product/image.13_1660281426383.png',10,NULL,NULL),
(14,0,'A2604','https://www.apple.com/kr/ipad-10.2/specs/','2022-08-11 13:51:02.000000','a_manager','10.2형 iPad Wi‑Fi + Cellular 256GB',809000,'2022-08-11 13:51:02.000000','a_manager','https://sellerb.s3.ap-northeast-2.amazonaws.com/product/image.14_1660281502098.png',10,NULL,NULL),
(16,0,'MKMY3KH/A','https://www.apple.com/kr/watch/compare/','2022-08-11 13:55:18.000000','a_manager','Apple Watch Series 7',559000,'2022-08-11 13:55:18.000000','a_manager','https://sellerb.s3.ap-northeast-2.amazonaws.com/product/image.16_1660281653958.png',11,NULL,NULL),
(17,0,'M09W3KH/A','https://www.apple.com/kr/apple-watch-nike/','2022-08-11 13:56:07.000000','a_manager','Apple Watch Nike',499000,'2022-08-11 13:56:07.000000','a_manager','https://sellerb.s3.ap-northeast-2.amazonaws.com/product/image.17_1660281775163.png',11,NULL,NULL),
(18,0,'A2337','https://support.apple.com/kb/SP825?locale=ko_KR','2022-08-11 13:59:42.000000','a_manager','MacBook Air(M1)',1390000,'2022-08-11 13:59:42.000000','a_manager','https://sellerb.s3.ap-northeast-2.amazonaws.com/product/image.18_1660281858735.png',12,NULL,NULL),
(20,0,'A2681','https://www.apple.com/kr/macbook-air-m2/','2022-08-11 14:02:45.000000','a_manager','MacBook Air(M2)',1690000,'2022-08-11 14:02:45.000000','a_manager','https://sellerb.s3.ap-northeast-2.amazonaws.com/product/image.20_1660281877624.png',12,NULL,NULL),
(36,1,'ㄷㄷ','준비중','2022-08-12 05:02:32.779976','s_manager','ㄷㄷ',1234,'2022-08-12 05:02:32.779976','s_manager','https://sellerb.s3.ap-northeast-2.amazonaws.com/post/image.%EA%B3%A0%EC%96%91%EC%9D%B42_1660280551879.jpg',7,NULL,NULL),
(37,0,'OLED65B1FNA','https://www.lge.co.kr/tvs/oled65b1fna-stand','2022-08-12 14:37:24.000000','l_manager','LG 올레드 TV (스탠드형)',2152800,'2022-08-12 14:37:24.000000','l_manager','https://sellerb.s3.ap-northeast-2.amazonaws.com/product/image.37_1660282851841.png',13,NULL,NULL),
(38,0,'75NANO75KQA','https://www.lge.co.kr/tvs/75nano75kqa-stand','2022-08-12 14:38:32.000000','l_manager','LG 나노셀 TV (스탠드형)',2890000,'2022-08-12 14:38:32.000000','l_manager','https://sellerb.s3.ap-northeast-2.amazonaws.com/product/image.38_1660282872482.png',13,NULL,NULL),
(39,0,'75NANO75KQA','https://www.lge.co.kr/tvs/75nano75kqa-stand','2022-08-12 14:39:53.000000','l_manager','LG 올레드 오브제컬렉션 Posé',3569000,'2022-08-12 14:39:53.000000','l_manager','https://sellerb.s3.ap-northeast-2.amazonaws.com/product/image.39_1660282884566.png',13,NULL,NULL),
(40,1,'호랑이','준비중','2022-08-12 05:40:43.964364','s_manager','호랑이',1234,'2022-08-12 05:40:43.964364','s_manager','https://sellerb.s3.ap-northeast-2.amazonaws.com/post/image.%EB%B0%B1%ED%98%B8_1660282842793.jpg',8,NULL,NULL),
(41,1,'호랑이','준비중','2022-08-12 05:40:52.463952','s_manager','호랑이',1234,'2022-08-12 05:40:52.463952','s_manager','https://sellerb.s3.ap-northeast-2.amazonaws.com/post/image.%EB%B0%B1%ED%98%B8_1660282842793.jpg',8,NULL,NULL),
(42,0,'W823SGS482','https://www.lge.co.kr/refrigerators/w823sgs482','2022-08-12 14:45:05.000000','l_manager','LG 디오스 오브제컬렉션 얼음정수기냉장고',5750000,'2022-08-12 14:45:05.000000','l_manager','https://sellerb.s3.ap-northeast-2.amazonaws.com/product/image.42_1660283307374.png',14,NULL,NULL),
(43,0,'J814MEE3-F','https://www.lge.co.kr/refrigerators/j814mee3-f','2022-08-12 14:46:07.000000','l_manager','LG 디오스 오브제컬렉션 얼음정수기냉장고',2555000,'2022-08-12 14:46:07.000000','l_manager','https://sellerb.s3.ap-northeast-2.amazonaws.com/product/image.43_1660283320873.png',14,NULL,NULL),
(44,0,'T873MWW312-F','https://www.lge.co.kr/object-collection/t873mww312','2022-08-12 14:47:42.000000','l_manager','LG 디오스 오브제컬렉션 노크온',2600000,'2022-08-12 14:47:42.000000','l_manager','https://sellerb.s3.ap-northeast-2.amazonaws.com/product/image.44_1660283333544.png',14,NULL,NULL),
(45,0,'FQ18PCNRA1M-F','https://www.lge.co.kr/air-conditioners/fq18pcnra1m','2022-08-12 14:50:27.000000','l_manager','LG 휘센 타워에어컨 (프리미엄) 매립배관형',3900000,'2022-08-12 14:50:27.000000','l_manager','https://sellerb.s3.ap-northeast-2.amazonaws.com/product/image.45_1660283587961.png',15,NULL,NULL),
(46,0,'FQ27GASMA1','https://www.lge.co.kr/air-conditioners/fq27gasma1','2022-08-12 14:52:07.000000','l_manager','LG SIGNATURE 에어컨',8330000,'2022-08-12 14:52:07.000000','l_manager','https://sellerb.s3.ap-northeast-2.amazonaws.com/product/image.46_1660283612514.png',15,NULL,NULL),
(47,0,'WD508ACB','https://www.lge.co.kr/water-purifiers/wd508acb','2022-08-12 14:54:59.000000','l_manager','LG 퓨리케어 오브제컬렉션 정수기 (음성인식)',1356000,'2022-08-12 14:54:59.000000','l_manager','https://sellerb.s3.ap-northeast-2.amazonaws.com/product/image.47_1660283784392.png',16,NULL,NULL),
(48,0,'17Z90Q-GA5WK','https://www.lge.co.kr/notebook/17z90q-ga5wk','2022-08-12 14:55:55.000000','l_manager','그램 17',2140000,'2022-08-12 14:55:55.000000','l_manager','https://sellerb.s3.ap-northeast-2.amazonaws.com/product/image.48_1660283798330.png',17,NULL,NULL),
(49,1,'백호','준비중','2022-08-12 06:11:25.841924','s_manager','백호',1234,'2022-08-12 06:11:25.841924','s_manager','https://sellerb.s3.ap-northeast-2.amazonaws.com/post/image.%EB%B0%B1%ED%98%B8%EB%B0%B1%ED%98%B8_1660284683912.jpg',2,NULL,NULL),
(50,1,'ㅋㅋ','준비중','2022-08-13 06:50:20.577034','비회원','ㅋㅋ',1234,'2022-08-13 06:50:20.577034','비회원','https://sellerb.s3.ap-northeast-2.amazonaws.com/product/image.%EA%B3%A0%EC%96%91%EC%9D%B4%EC%9D%B4%EB%AF%B8%EC%A7%80_1660373418804.jpg',6,NULL,NULL),
(51,1,'ㅋㅋ','준비중','2022-08-13 06:50:26.500376','비회원','ㅋㅋ',1234,'2022-08-13 06:50:26.500376','비회원','https://sellerb.s3.ap-northeast-2.amazonaws.com/product/image.%EA%B3%A0%EC%96%91%EC%9D%B4%EC%9D%B4%EB%AF%B8%EC%A7%80_1660373418804.jpg',6,NULL,NULL),
(52,1,'등록디자인테스트','준비중','2022-08-13 10:42:32.947943','비회원','디자인테스트',13000,'2022-08-13 10:42:32.947943','비회원','',1,NULL,NULL),
(53,1,'등록디자인테스트','준비중','2022-08-13 10:42:36.806394','비회원','디자인테스트',13000,'2022-08-13 10:42:36.806394','비회원','https://sellerb.s3.ap-northeast-2.amazonaws.com/product/image.%EB%B0%B1%ED%98%B8%EB%B0%B1%ED%98%B8_1660387355815.jpg',1,NULL,NULL),
(54,1,'Samsung_M','준비중','2022-08-14 08:35:27.624590','s_manager','삼성모니터',100000,'2022-08-14 08:35:27.624590','s_manager','',7,NULL,NULL),
(55,1,'Samsung_M','준비중','2022-08-14 08:35:29.892129','s_manager','삼성모니터',100000,'2022-08-14 08:35:29.892129','s_manager','https://sellerb.s3.ap-northeast-2.amazonaws.com/product/image.%EC%A0%9C%ED%92%88_%ED%85%8C%EC%8A%A4%ED%8A%B8_%EC%82%BC%EC%84%B1%EB%AA%A8%EB%8B%88%ED%84%B0_1660466128805.jpg',7,NULL,NULL),
(56,1,'Samsung_M','준비중','2022-08-14 08:35:38.013138','s_manager','삼성모니터',100000,'2022-08-14 08:35:38.013138','s_manager','https://sellerb.s3.ap-northeast-2.amazonaws.com/product/image.%EC%A0%9C%ED%92%88_%ED%85%8C%EC%8A%A4%ED%8A%B8_%EC%82%BC%EC%84%B1%EB%AA%A8%EB%8B%88%ED%84%B0_1660466136280.jpg',7,NULL,NULL),
(57,1,'백호테스트','준비중','2022-08-14 17:26:23.500055','비회원','백호테스트',100,'2022-08-14 17:26:23.500055','비회원','https://sellerb.s3.ap-northeast-2.amazonaws.com/product/image.%EB%B0%B1%ED%98%B8%EB%B0%B1%ED%98%B8_1660497970272.jpg',1,NULL,NULL),
(58,1,'가격 수정테스트','준비중','2022-08-15 13:10:19.833878','비회원','가격 수정테스트',1000,'2022-08-15 13:10:19.833878','비회원','https://sellerb.s3.ap-northeast-2.amazonaws.com/product/image.%EA%B3%A0%EC%96%91%EC%9D%B4%EC%9D%B4%EB%AF%B8%EC%A7%80_1660569014988.jpg',6,NULL,NULL),
(59,1,'122','준비중','2022-08-15 13:11:32.481156','비회원','122',9999,'2022-08-15 13:11:32.481156','비회원','https://sellerb.s3.ap-northeast-2.amazonaws.com/product/image.%EB%B0%B1%ED%98%B8_1660569045544.jpg',6,NULL,NULL),
(60,1,'찐테스트','준비중','2022-08-15 13:13:01.063724','비회원','찐테스트',100,'2022-08-15 13:13:01.063724','비회원','https://sellerb.s3.ap-northeast-2.amazonaws.com/product/image.%EA%B3%A0%EC%96%91%EC%9D%B4%EC%9D%B4%EB%AF%B8%EC%A7%80_1660569167881.jpg',6,NULL,NULL),
(61,1,'11','준비중','2022-08-15 13:14:10.896657','비회원','11',10,'2022-08-15 13:14:10.896657','비회원','https://sellerb.s3.ap-northeast-2.amazonaws.com/product/image.%EA%B3%A0%EC%96%91%EC%9D%B4%EC%9D%B4%EB%AF%B8%EC%A7%80_1660569247932.jpg',1,NULL,NULL),
(62,1,'ㅇㅇ','준비중','2022-08-15 14:40:15.750501','s_manager','ㅇㅇ',NULL,'2022-08-15 14:40:15.750501','s_manager','',6,NULL,NULL),
(63,1,'1','준비중','2022-08-15 14:40:57.282585','s_manager','1',NULL,'2022-08-15 14:40:57.282585','s_manager','https://sellerb.s3.ap-northeast-2.amazonaws.com/product/image.%EA%B3%A0%EC%96%91%EC%9D%B42_1660574446890.jpg',2,NULL,NULL),
(64,1,'가격 테스트중','준비중','2022-08-15 14:51:45.999096','s_manager','가격 테스트중',NULL,'2022-08-15 14:51:45.999096','s_manager','',1,NULL,NULL),
(65,1,'가격테스트','준비중','2022-08-15 14:54:56.401772','비회원','가격테스트',100000,'2022-08-15 14:54:56.401772','비회원','https://sellerb.s3.ap-northeast-2.amazonaws.com/product/image.%EA%B3%A0%EC%96%91%EC%9D%B4%EC%9D%B4%EB%AF%B8%EC%A7%80_1660575292398.jpg',1,NULL,NULL),
(66,1,'비숑','준비중','2022-08-16 02:04:56.546647','s_manager','비숑',10000,'2022-08-16 02:04:56.546647','s_manager','https://sellerb.s3.ap-northeast-2.amazonaws.com/product/image.%EA%B0%95%EC%95%84%EC%A7%80%EC%9D%B4%EB%AF%B8%EC%A7%80_1660615480518.jpg',1,NULL,NULL),
(67,1,'고양','준비','2022-08-16 03:59:12.687975','비회원','고양99',999999,'2022-08-16 02:39:42.935661','비회원','https://sellerb.s3.ap-northeast-2.amazonaws.com/product/image.%EA%B3%A0%EC%96%91%EC%9D%B42_1660622350702.jpg',1,NULL,NULL),
(68,1,'고창석고양이','준비중','2022-08-16 04:07:11.178848','s_manager','고창석고양이',12341234,'2022-08-16 04:07:11.178848','s_manager','https://sellerb.s3.ap-northeast-2.amazonaws.com/product/image.%EA%B3%A0%EC%B0%BD%EC%84%9D%EA%B3%A0%EC%96%91%EC%9D%B4_1660622823986.jpg',8,NULL,NULL),
(69,1,'고창석고양이','준비중','2022-08-16 04:15:09.434300','s_manager','고창석고양이',12341234,'2022-08-16 04:15:09.434300','s_manager','https://sellerb.s3.ap-northeast-2.amazonaws.com/product/image.%EA%B3%A0%EC%B0%BD%EC%84%9D%EA%B3%A0%EC%96%91%EC%9D%B4_1660623295437.jpg',1,NULL,NULL),
(70,1,'메뉴얼테스트','https://www.inflearn.com/?utm_source=google_brand_search&utm_medium=cpc&utm_campaign=01.brand&utm_content=mainkw&utm_term=%EC%9D%B8%ED%94%84%EB%9F%B0&gclid=CjwKCAjw5s6WBhA4EiwACGncZbue_WG__MoUXkaoBsOShs2WZ_EfBCb_bgKs-BUZe1BkEdu-bJZkWhoCFaMQAvD_BwE','2022-08-16 17:36:02.024373','비회원','제품수정aaa',1234,'2022-08-16 06:40:36.935624','s_test','https://sellerb.s3.ap-northeast-2.amazonaws.com/product/image.%EA%B3%A0%EC%B0%BD%EC%84%9D%EA%B3%A0%EC%96%91%EC%9D%B4_1660671321379.jpg',1,NULL,NULL),
(71,1,'ss','준비중','2022-08-16 07:28:01.791158','s_manager','ss',1234,'2022-08-16 07:28:01.791158','s_manager','https://sellerb.s3.ap-northeast-2.amazonaws.com/product/image.%EB%83%89%EC%9E%A5%EA%B3%A0_%EB%8C%80%EA%B8%B0%ED%99%94%EB%A9%B4_%ED%85%8C%EC%8A%A4%ED%8A%B8%EC%9D%B4%EB%AF%B8%EC%A7%80_1660634853056.jpg',2,NULL,NULL),
(72,1,'ttttt','test','2022-08-17 07:28:55.451773','s_manager','ttttt',11111,'2022-08-17 07:28:55.451773','s_manager','',1,NULL,NULL),
(73,1,'제품 수정 이미지 보존 테스트용','ㅌㅌ','2022-08-17 12:00:49.417354','sss333','제품 수정 이미지 보존 테스트용',1000,'2022-08-17 12:00:49.417354','sss333','',6,NULL,NULL),
(74,1,'제품 수정 이미지 보존 테스트용','000','2022-08-17 12:37:22.126039','비회원','제품 수정 이미지 보존 테스트용',2000,'2022-08-17 12:01:16.546875','sss333','',2,NULL,NULL),
(75,1,'xx','xx','2022-08-17 12:37:44.709181','비회원','xx',10000,'2022-08-17 12:37:44.709181','비회원','',2,NULL,NULL),
(76,1,'고','2222','2022-08-18 02:22:47.685498','비회원','고',2222,'2022-08-18 02:22:31.430891','sss333','https://sellerb.s3.ap-northeast-2.amazonaws.com/product/image.%EA%B0%95%EC%95%84%EC%A7%80%EC%9D%B4%EB%AF%B8%EC%A7%80_1660789362665.jpg',2,NULL,NULL),
(77,0,'NOLARWATHERYO','','2022-08-18 16:43:08.573261','비회원','bonobono',10000,'2022-08-18 16:42:45.663334','siryeong','https://sellerb.s3.ap-northeast-2.amazonaws.com/product/image.%EB%B3%B4%EB%85%B8%EB%B3%B4%EB%85%B8_%EB%AA%A8%EC%98%81_1660840986079.png',8,NULL,NULL);
/*!40000 ALTER TABLE `t_product` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-08-19 11:21:17
