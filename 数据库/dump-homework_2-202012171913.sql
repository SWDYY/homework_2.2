-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: cdb-fugoxito.cd.tencentcdb.com    Database: homework_2
-- ------------------------------------------------------
-- Server version	5.7.18-20170830-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
SET @MYSQLDUMP_TEMP_LOG_BIN = @@SESSION.SQL_LOG_BIN;
SET @@SESSION.SQL_LOG_BIN= 0;

--
-- GTID state at the beginning of the backup 
--

SET @@GLOBAL.GTID_PURGED=/*!80000 '+'*/ 'b7a545c5-33ec-11eb-9a2b-5254007b86b3:1-473';

--
-- Table structure for table `customermanager`
--

DROP TABLE IF EXISTS `customermanager`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customermanager` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `phonenum` varchar(100) NOT NULL,
  `classification` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `newtable_un` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customermanager`
--

LOCK TABLES `customermanager` WRITE;
/*!40000 ALTER TABLE `customermanager` DISABLE KEYS */;
INSERT INTO `customermanager` VALUES (1,'wkr','123','retail'),(2,'dzh','369','wholesaler'),(3,'werf','1324','retail'),(4,'yyq','1234','retail'),(5,'sxz','233','wholesaler'),(6,'rgy','34','wholesaler'),(7,'yzj','666','retail'),(8,'hhh','123456','retail'),(9,'www','111','retail'),(10,'miao','233','wholesaler'),(11,'zzz','444','wholesaler');
/*!40000 ALTER TABLE `customermanager` ENABLE KEYS */;
UNLOCK TABLES;
