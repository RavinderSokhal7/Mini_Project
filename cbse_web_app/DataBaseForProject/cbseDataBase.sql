CREATE DATABASE  IF NOT EXISTS `cbse` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `cbse`;
-- MySQL dump 10.13  Distrib 8.0.16, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: cbse
-- ------------------------------------------------------
-- Server version	8.0.16

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `books`
--

DROP TABLE IF EXISTS `books`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `books` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `book_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `book_author` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `total_copies` int(11) NOT NULL,
  `rem_copies` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `books`
--

LOCK TABLES `books` WRITE;
/*!40000 ALTER TABLE `books` DISABLE KEYS */;
INSERT INTO `books` VALUES (1,'Introduction to Algorithms','Gilbert, D.K.',5,5),(2,'Introduction to operations research','Gillett, B.E.',2,2),(3,'Magazine 2','Me',13,13),(4,'Magazine 1','Me',10,9);
/*!40000 ALTER TABLE `books` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `category` (
  `category_id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Library Management System'),(2,'Railway Reservation System'),(3,'Movie Ticket Booking System');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `component`
--

DROP TABLE IF EXISTS `component`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `component` (
  `id` int(11) NOT NULL,
  `category_id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `detail` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `component`
--

LOCK TABLES `component` WRITE;
/*!40000 ALTER TABLE `component` DISABLE KEYS */;
INSERT INTO `component` VALUES (1,1,'SearchComp.jsp','Search Books'),(2,1,'ShowBooksComp.jsp','Show Books'),(3,1,'IssueReturnComp.jsp','Issue/Return Books'),(4,1,'AddBookComp.jsp','Add Books to the Library System'),(5,1,'EditBookComp.jsp','Edit Books in the Library System'),(6,3,'SelectCityComp.jsp','Select city'),(7,2,'FTCityComp.jsp','Select from to city'),(8,2,'GetTrainsComp.jsp','Get trains'),(9,2,'SelectTrainNoComp.jsp','Select Train No.'),(10,2,'SelectDateComp.jsp','Select Boarding Date'),(11,2,'SelectClassComp.jsp','Select class of coach for boarding'),(12,2,'SelectPassengerComp.jsp','Select passengers'),(13,2,'GenerateBillComp.jsp','Generate bill');
/*!40000 ALTER TABLE `component` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `issue_logs`
--

DROP TABLE IF EXISTS `issue_logs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `issue_logs` (
  `issue_id` int(11) NOT NULL AUTO_INCREMENT,
  `id` int(11) NOT NULL,
  `username` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `status` set('issued','returned') CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `issued_by` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `returned_by` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`issue_id`),
  KEY `issue_username` (`username`),
  KEY `issued_by` (`issued_by`),
  KEY `returned_by` (`returned_by`),
  KEY `id` (`id`),
  CONSTRAINT `issue_book_id` FOREIGN KEY (`id`) REFERENCES `books` (`id`),
  CONSTRAINT `issue_username` FOREIGN KEY (`username`) REFERENCES `users` (`username`),
  CONSTRAINT `issued_by` FOREIGN KEY (`issued_by`) REFERENCES `users` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `issue_logs`
--

LOCK TABLES `issue_logs` WRITE;
/*!40000 ALTER TABLE `issue_logs` DISABLE KEYS */;
INSERT INTO `issue_logs` VALUES (13,1,'spydee','returned','2020-04-21 07:20:27','spydee','spydee'),(14,1,'spydee','returned','2020-04-21 07:20:48','spydee','spydee'),(15,2,'spydee','returned','2020-04-21 07:39:45','spydee','spydee'),(16,4,'spydee','returned','2020-04-21 17:33:21','spydee','spydee'),(17,3,'spydee','returned','2020-04-22 06:07:11','spydee','spydee'),(18,1,'spydee','returned','2020-04-22 06:11:51','spydee','spydee'),(19,4,'spydee','returned','2020-04-22 06:12:09','spydee','spydee'),(20,4,'spydee','returned','2020-04-22 06:19:25','spydee','spydee'),(21,4,'spydee','returned','2020-04-22 07:31:32','spydee','spydee');
/*!40000 ALTER TABLE `issue_logs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `login`
--

DROP TABLE IF EXISTS `login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `login` (
  `username` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `password` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`username`),
  CONSTRAINT `username_login` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login`
--

LOCK TABLES `login` WRITE;
/*!40000 ALTER TABLE `login` DISABLE KEYS */;
INSERT INTO `login` VALUES ('admin','21232f297a57a5a743894a0e4a801fc3'),('spydee','5f4dcc3b5aa765d61d8327deb882cf99');
/*!40000 ALTER TABLE `login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `options`
--

DROP TABLE IF EXISTS `options`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `options` (
  `options_id` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `option_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `option_details` text CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `option_status` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`options_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `options`
--

LOCK TABLES `options` WRITE;
/*!40000 ALTER TABLE `options` DISABLE KEYS */;
INSERT INTO `options` VALUES ('issue_return_books','Issue/Return Books','Users can issue books from the library and can return books to the library.',1),('search_book','Search Books','Search for books by their name or author.',1),('update_booklist','Add/Delete Books','You can add and remove books.',1),('update_user_details','Maintain User Details','Users can maintain their details by logging into the website.',1),('view_user_details','View User Details','View User Details like contact and address information, along with books issued.',1);
/*!40000 ALTER TABLE `options` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservations`
--

DROP TABLE IF EXISTS `reservations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `reservations` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `train_no` int(11) NOT NULL,
  `from_station` varchar(45) NOT NULL,
  `to_station` varchar(45) NOT NULL,
  `adult` int(11) NOT NULL,
  `class` varchar(20) NOT NULL,
  `date_of_boarding` varchar(45) NOT NULL,
  `child` int(11) NOT NULL,
  `total_cost` int(11) NOT NULL,
  `train_name` varchar(45) NOT NULL,
  `username` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservations`
--

LOCK TABLES `reservations` WRITE;
/*!40000 ALTER TABLE `reservations` DISABLE KEYS */;
INSERT INTO `reservations` VALUES (5,12417,'prayagraj','new delhi',1,'3rd ac','dummy',1,1980,'Prayag Raj Express','spydee','Spider Man'),(6,12417,'prayagraj','new delhi',1,'3rd ac','dummy',2,2970,'Prayag Raj Express','spydee','Spider Man'),(7,12417,'prayagraj','new delhi',1,'3rd ac','dummy',0,990,'Prayag Raj Express','spydee','Spider Man'),(8,12417,'prayagraj','new delhi',1,'3rd ac','dummy',0,990,'Prayag Raj Express','spydee','Spider Man'),(9,12417,'prayagraj','new delhi',1,'3rd ac','dummy',0,990,'Prayag Raj Express','spydee','Spider Man'),(10,12417,'prayagraj','new delhi',3,'3rd ac','Tue Oct 11 00:00:00 IST 35',0,2970,'Prayag Raj Express','spydee','Spider Man'),(11,12417,'prayagraj','new delhi',1,'sleeper','Tue Apr 04 00:00:00 IST 198',0,400,'Prayag Raj Express','spydee','Spider Man'),(12,12417,'prayagraj','new delhi',1,'3rd ac','2020-06-01',0,990,'Prayag Raj Express','spydee','Spider Man'),(13,12417,'prayagraj','new delhi',1,'3rd ac','10-60-0202',0,990,'Prayag Raj Express','spydee','Spider Man'),(14,12417,'prayagraj','new delhi',1,'3rd ac','Sat Dec 11 00:00:00 IST 6',0,990,'Prayag Raj Express','spydee','Spider Man'),(15,12417,'prayagraj','new delhi',1,'3rd ac','11-12-0006',0,990,'Prayag Raj Express','spydee','Spider Man'),(16,12417,'prayagraj','new delhi',1,'3rd ac','11-12-0006',0,990,'Prayag Raj Express','spydee','Spider Man'),(17,12417,'prayagraj','new delhi',1,'3rd ac','2020-06-01',0,990,'Prayag Raj Express','spydee','Spider Man'),(18,12417,'prayagraj','new delhi',1,'3rd ac','2020-06-01',0,990,'Prayag Raj Express','spydee','Spider Man'),(19,12417,'prayagraj','new delhi',1,'sleeper','2020-04-27',0,400,'Prayag Raj Express','spydee','Spider Man'),(20,12417,'prayagraj','new delhi',2,'sleeper','2020-05-01',0,800,'Prayag Raj Express','spydee','Spider Man'),(21,12417,'prayagraj','ghaziabad',1,'3rd ac','2020-04-23',0,800,'Prayag Raj Express','spydee','Spider Man');
/*!40000 ALTER TABLE `reservations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `train`
--

DROP TABLE IF EXISTS `train`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `train` (
  `train_no` int(11) NOT NULL,
  `train_name` varchar(45) NOT NULL,
  `origin_station` varchar(45) NOT NULL,
  `end_station` varchar(45) NOT NULL,
  `total_seat` int(11) NOT NULL,
  `available_seat` int(11) NOT NULL,
  PRIMARY KEY (`train_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `train`
--

LOCK TABLES `train` WRITE;
/*!40000 ALTER TABLE `train` DISABLE KEYS */;
INSERT INTO `train` VALUES (10101,'dummy','prayagraj','new delhi',500,500),(12417,'Prayag Raj Express','prayagraj','new delhi',1000,1000);
/*!40000 ALTER TABLE `train` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `train_cost`
--

DROP TABLE IF EXISTS `train_cost`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `train_cost` (
  `id` int(11) NOT NULL,
  `train_no` int(11) NOT NULL,
  `from_station_no` int(11) NOT NULL,
  `to_station_no` int(11) NOT NULL,
  `class` varchar(45) NOT NULL,
  `cost` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `train_cost`
--

LOCK TABLES `train_cost` WRITE;
/*!40000 ALTER TABLE `train_cost` DISABLE KEYS */;
INSERT INTO `train_cost` VALUES (1,12417,0,5,'3rd ac',990),(2,12417,0,5,'sleeper',400),(3,12417,0,4,'3rd ac',800);
/*!40000 ALTER TABLE `train_cost` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `train_stations`
--

DROP TABLE IF EXISTS `train_stations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `train_stations` (
  `id` int(11) NOT NULL,
  `train_no` int(11) NOT NULL,
  `station` varchar(45) NOT NULL,
  `station_no` int(11) NOT NULL,
  `arrival_time` varchar(20) NOT NULL,
  `dept_time` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `train_stations`
--

LOCK TABLES `train_stations` WRITE;
/*!40000 ALTER TABLE `train_stations` DISABLE KEYS */;
INSERT INTO `train_stations` VALUES (1,12417,'prayagraj',0,'21:10','21:30'),(2,12417,'fatehpur',1,'22:43','22:48'),(3,12417,'kanpur',2,'00:05','00:25'),(4,12417,'aligarh',3,'04:26','04:35'),(5,12417,'ghaziabad',4,'06:13','06:18'),(6,12417,'new delhi',5,'07:00','07:00'),(7,10101,'prayagraj',0,'11:11','11:30'),(8,10101,'new delhi',1,'18:00','18:00');
/*!40000 ALTER TABLE `train_stations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `users` (
  `username` varchar(40) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `contact` varchar(15) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `address` text CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `usertype` set('user','admin') CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT 'user',
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('admin','Administrator','9876543210','Please Enter Your Address!','admin'),('spydee','Spider Man','09999999999','Queens','user');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-04-24 20:01:00
