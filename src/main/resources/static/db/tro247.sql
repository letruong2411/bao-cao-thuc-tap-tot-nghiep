CREATE DATABASE  IF NOT EXISTS `timtro247` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `timtro247`;
-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: localhost    Database: timtro247
-- ------------------------------------------------------
-- Server version	8.0.17

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `areas`
--

DROP TABLE IF EXISTS `areas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `areas` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `deleted_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `area_name` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `areas`
--

LOCK TABLES `areas` WRITE;
/*!40000 ALTER TABLE `areas` DISABLE KEYS */;
INSERT INTO `areas` VALUES (1,NULL,NULL,NULL,NULL,NULL,'Liêu Chiểu',1),(2,NULL,NULL,NULL,NULL,NULL,'Hải Châu',1),(3,NULL,NULL,NULL,NULL,NULL,'Thanh Khê',1),(4,NULL,NULL,NULL,NULL,NULL,'Sơn Trà',1);
/*!40000 ALTER TABLE `areas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comments`
--

DROP TABLE IF EXISTS `comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comments` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `deleted_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `post_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKh4c7lvsc298whoyd4w9ta25cr` (`post_id`),
  KEY `FK8omq0tc18jd43bu5tjh6jvraq` (`user_id`),
  CONSTRAINT `FK8omq0tc18jd43bu5tjh6jvraq` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKh4c7lvsc298whoyd4w9ta25cr` FOREIGN KEY (`post_id`) REFERENCES `posts` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comments`
--

LOCK TABLES `comments` WRITE;
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `images`
--

DROP TABLE IF EXISTS `images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `images` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `deleted_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `file_name` varchar(255) DEFAULT NULL,
  `room_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKdeh4h59nedlwji0j8e57hu9mf` (`room_id`),
  CONSTRAINT `FKdeh4h59nedlwji0j8e57hu9mf` FOREIGN KEY (`room_id`) REFERENCES `rooms` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `images`
--

LOCK TABLES `images` WRITE;
/*!40000 ALTER TABLE `images` DISABLE KEYS */;
INSERT INTO `images` VALUES (1,NULL,NULL,NULL,NULL,NULL,'thay.jpg',1),(2,NULL,NULL,NULL,NULL,NULL,'anh12.jpg',2),(3,NULL,NULL,NULL,NULL,NULL,'fwsd.jpg',2),(4,NULL,NULL,NULL,NULL,NULL,'fwsd.jpg',4),(5,NULL,NULL,NULL,NULL,NULL,'thay.jpg',5),(6,NULL,NULL,NULL,NULL,NULL,'thay.jpg',6),(7,NULL,NULL,NULL,NULL,NULL,'thay.jpg',7),(8,NULL,NULL,NULL,NULL,NULL,'thay.jpg',8),(9,NULL,NULL,NULL,NULL,NULL,'thay.jpg',9),(10,NULL,NULL,NULL,NULL,NULL,'anh12.jpg',1),(11,NULL,NULL,NULL,NULL,NULL,'anh12.jpg',2),(12,NULL,NULL,NULL,NULL,NULL,'anh12.jpg',3),(13,NULL,NULL,NULL,NULL,NULL,'anh12.jpg',4),(14,NULL,NULL,NULL,NULL,NULL,'fwsd.jpg',5),(15,NULL,NULL,NULL,NULL,NULL,'fwsd.jpg',6),(16,NULL,NULL,NULL,NULL,NULL,'fwsd.jpg',7),(17,NULL,NULL,NULL,NULL,NULL,'fwsd.jpg',10),(19,NULL,NULL,NULL,NULL,NULL,'fwsd.jpg',11),(20,NULL,NULL,NULL,NULL,NULL,'fwsd.jpg',12),(21,NULL,NULL,NULL,NULL,NULL,'fwsd.jpg',13),(22,NULL,NULL,NULL,NULL,NULL,'fwsd.jpg',1);
/*!40000 ALTER TABLE `images` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `posts`
--

DROP TABLE IF EXISTS `posts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `posts` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `deleted_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `save_post_day_number` int(11) DEFAULT NULL,
  `start_time` date DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `room_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6dg0paba1bfpxg2r67nowod9v` (`room_id`),
  KEY `FK5lidm6cqbc7u4xhqpxm898qme` (`user_id`),
  CONSTRAINT `FK5lidm6cqbc7u4xhqpxm898qme` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FK6dg0paba1bfpxg2r67nowod9v` FOREIGN KEY (`room_id`) REFERENCES `rooms` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `posts`
--

LOCK TABLES `posts` WRITE;
/*!40000 ALTER TABLE `posts` DISABLE KEYS */;
INSERT INTO `posts` VALUES (1,'2020-12-20 11:12:35.187000',NULL,NULL,'2020-12-23 19:55:22.630000','anonymousUser','Nhà trọ sạch sẽ, còn mới, có gác lửng, có chỗ phơi đồ, có sân để xe, toilet riêng trong phòng. Phòng cẩn gạch cao sạch sẽ, có mạng và truyền hình cáp .Gần KCX Q7, bệnh viện, trường học.',3,NULL,'Nhà sạch giá rẻ, thoáng mát, đầy đủ tiện nghi, có sân để xe và sân phơi đồ',2,1),(2,'2020-12-20 11:12:35.187000',NULL,NULL,'2020-12-23 19:57:57.615000','anonymousUser','Nhà trọ sạch sẽ, còn mới, có gác lửng, có chỗ phơi đồ, có sân để xe, toilet riêng trong phòng. Phòng cẩn gạch cao sạch sẽ, có mạng và truyền hình cáp .Gần KCX Q7, bệnh viện, trường học.',3,NULL,'Nhà sạch giá rẻ, thoáng mát, đầy đủ tiện nghi, có sân để xe và sân phơi đồ',4,1),(3,'2020-12-20 11:12:35.187000',NULL,NULL,'2020-12-23 19:58:00.064000','anonymousUser','Nhà trọ sạch sẽ, còn mới, có gác lửng, có chỗ phơi đồ, có sân để xe, toilet riêng trong phòng. Phòng cẩn gạch cao sạch sẽ, có mạng và truyền hình cáp .Gần KCX Q7, bệnh viện, trường học.',3,NULL,'Nhà sạch giá rẻ, thoáng mát, đầy đủ tiện nghi, có sân để xe và sân phơi đồ',1,2),(4,NULL,NULL,NULL,'2020-12-23 19:57:15.848000','anonymousUser','Nhà trọ sạch sẽ, còn mới, có gác lửng, có chỗ phơi đồ, có sân để xe, toilet riêng trong phòng. Phòng cẩn gạch cao sạch sẽ, có mạng và truyền hình cáp .Gần KCX Q7, bệnh viện, trường học.',3,NULL,'Nhà sạch giá rẻ, thoáng mát, đầy đủ tiện nghi, có sân để xe và sân phơi đồ',3,2),(5,NULL,NULL,NULL,'2020-12-23 19:57:15.848000','anonymousUser','Nhà trọ sạch sẽ, còn mới, có gác lửng, có chỗ phơi đồ, có sân để xe, toilet riêng trong phòng. Phòng cẩn gạch cao sạch sẽ, có mạng và truyền hình cáp .Gần KCX Q7, bệnh viện, trường học.',3,NULL,'Nhà sạch giá rẻ, thoáng mát, đầy đủ tiện nghi, có sân để xe và sân phơi đồ',5,2),(6,NULL,NULL,NULL,'2020-12-23 19:57:15.848000','anonymousUser','Nhà trọ sạch sẽ, còn mới, có gác lửng, có chỗ phơi đồ, có sân để xe, toilet riêng trong phòng. Phòng cẩn gạch cao sạch sẽ, có mạng và truyền hình cáp .Gần KCX Q7, bệnh viện, trường học.',3,NULL,'Nhà sạch giá rẻ, thoáng mát, đầy đủ tiện nghi, có sân để xe và sân phơi đồ',6,2),(7,NULL,NULL,NULL,'2020-12-23 19:57:15.848000','anonymousUser','Nhà trọ sạch sẽ, còn mới, có gác lửng, có chỗ phơi đồ, có sân để xe, toilet riêng trong phòng. Phòng cẩn gạch cao sạch sẽ, có mạng và truyền hình cáp .Gần KCX Q7, bệnh viện, trường học.',3,NULL,'Nhà sạch giá rẻ, thoáng mát, đầy đủ tiện nghi, có sân để xe và sân phơi đồ',7,2),(8,NULL,NULL,NULL,'2020-12-23 19:57:15.848000','anonymousUser','Nhà trọ sạch sẽ, còn mới, có gác lửng, có chỗ phơi đồ, có sân để xe, toilet riêng trong phòng. Phòng cẩn gạch cao sạch sẽ, có mạng và truyền hình cáp .Gần KCX Q7, bệnh viện, trường học.',3,NULL,'Nhà sạch giá rẻ, thoáng mát, đầy đủ tiện nghi, có sân để xe và sân phơi đồ',8,2),(9,NULL,NULL,NULL,'2020-12-23 19:57:15.848000','anonymousUser','Nhà trọ sạch sẽ, còn mới, có gác lửng, có chỗ phơi đồ, có sân để xe, toilet riêng trong phòng. Phòng cẩn gạch cao sạch sẽ, có mạng và truyền hình cáp .Gần KCX Q7, bệnh viện, trường học.',3,NULL,'Nhà sạch giá rẻ, thoáng mát, đầy đủ tiện nghi, có sân để xe và sân phơi đồ',9,2),(10,NULL,NULL,NULL,'2020-12-23 19:57:15.848000','anonymousUser','Nhà trọ sạch sẽ, còn mới, có gác lửng, có chỗ phơi đồ, có sân để xe, toilet riêng trong phòng. Phòng cẩn gạch cao sạch sẽ, có mạng và truyền hình cáp .Gần KCX Q7, bệnh viện, trường học.',3,NULL,'Nhà sạch giá rẻ, thoáng mát, đầy đủ tiện nghi, có sân để xe và sân phơi đồ',10,2),(11,NULL,NULL,NULL,'2020-12-23 19:57:15.848000','anonymousUser','Nhà trọ sạch sẽ, còn mới, có gác lửng, có chỗ phơi đồ, có sân để xe, toilet riêng trong phòng. Phòng cẩn gạch cao sạch sẽ, có mạng và truyền hình cáp .Gần KCX Q7, bệnh viện, trường học.',3,NULL,'Nhà sạch giá rẻ, thoáng mát, đầy đủ tiện nghi, có sân để xe và sân phơi đồ',11,2),(12,NULL,NULL,'2020-11-05 00:00:00.000000','2020-12-23 19:57:15.848000','anonymousUser','Nhà trọ sạch sẽ, còn mới, có gác lửng, có chỗ phơi đồ, có sân để xe, toilet riêng trong phòng. Phòng cẩn gạch cao sạch sẽ, có mạng và truyền hình cáp .Gần KCX Q7, bệnh viện, trường học.',3,NULL,'Nhà sạch giá rẻ, thoáng mát, đầy đủ tiện nghi, có sân để xe và sân phơi đồ',12,2),(13,NULL,NULL,'2020-11-05 00:00:00.000000','2020-12-23 19:57:15.848000','anonymousUser','Nhà trọ sạch sẽ, còn mới, có gác lửng, có chỗ phơi đồ, có sân để xe, toilet riêng trong phòng. Phòng cẩn gạch cao sạch sẽ, có mạng và truyền hình cáp .Gần KCX Q7, bệnh viện, trường học.',3,NULL,'Nhà sạch giá rẻ, thoáng mát, đầy đủ tiện nghi, có sân để xe và sân phơi đồ',13,2),(21,'2020-12-27 09:55:09.003000','anonymousUser',NULL,'2020-12-27 09:55:09.003000','anonymousUser','CHUNG CƯ MINI SIÊU RỘNG, HƠN 50m2',6,NULL,'CHUNG CƯ MINI SIÊU RỘNG, HƠN 50m2',2,1);
/*!40000 ALTER TABLE `posts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prices`
--

DROP TABLE IF EXISTS `prices`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prices` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `deleted_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `max_price` int(11) DEFAULT NULL,
  `min_price` int(11) DEFAULT NULL,
  `price_name` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prices`
--

LOCK TABLES `prices` WRITE;
/*!40000 ALTER TABLE `prices` DISABLE KEYS */;
INSERT INTO `prices` VALUES (1,NULL,NULL,NULL,NULL,NULL,1000000,0,'Nhỏ hơn 1000000',NULL),(2,NULL,NULL,NULL,NULL,NULL,2000000,1000000,'Từ 1000000 đến 2000000',NULL),(3,NULL,NULL,NULL,NULL,NULL,3000000,2000000,'Từ 2000000 đến 3000000',NULL),(4,NULL,NULL,NULL,NULL,NULL,50000000,3000000,'Lớn hơn 3000000',NULL);
/*!40000 ALTER TABLE `prices` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `deleted_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'2020-12-20 11:11:32.697000',NULL,NULL,'2020-12-20 11:11:32.697000',NULL,'USER'),(2,'2020-12-20 11:11:32.744000',NULL,NULL,'2020-12-20 11:11:32.744000',NULL,'ADMIN');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rooms`
--

DROP TABLE IF EXISTS `rooms`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rooms` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `deleted_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `acreage` int(11) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `cost` int(11) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `room_name` varchar(255) DEFAULT NULL,
  `max_person_number` int(11) DEFAULT NULL,
  `area_id` bigint(20) DEFAULT NULL,
  `type_room_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `price_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK3kslp3glakym1udtrtrsu2fcq` (`area_id`),
  KEY `FKeidtccnkf386htdl6q9hxnckg` (`type_room_id`),
  KEY `FKa84ab0lpjkgd9beja545d9ysd` (`user_id`),
  KEY `FK7tlh3kyw8gsl9myg2kyrvo2cv` (`price_id`),
  CONSTRAINT `FK3kslp3glakym1udtrtrsu2fcq` FOREIGN KEY (`area_id`) REFERENCES `areas` (`id`),
  CONSTRAINT `FK7tlh3kyw8gsl9myg2kyrvo2cv` FOREIGN KEY (`price_id`) REFERENCES `prices` (`id`),
  CONSTRAINT `FKa84ab0lpjkgd9beja545d9ysd` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKeidtccnkf386htdl6q9hxnckg` FOREIGN KEY (`type_room_id`) REFERENCES `type_rooms` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rooms`
--

LOCK TABLES `rooms` WRITE;
/*!40000 ALTER TABLE `rooms` DISABLE KEYS */;
INSERT INTO `rooms` VALUES (1,NULL,NULL,NULL,'2020-12-23 19:42:33.793000','anonymousUser',100,'100 Phạm Như Xương',1000000,'Nhà sạch giá rẻ, thoáng mát, đầy đủ tiện nghi, có sân để xe và sân phơi đồ','P1',4,2,3,1,2),(2,NULL,NULL,NULL,NULL,'anonymousUser',200,'200 Nam Cao',1000000,'Nhà sạch giá rẻ, thoáng mát, đầy đủ tiện nghi, có sân để xe và sân phơi đồ','P2',4,3,2,1,2),(3,NULL,NULL,NULL,NULL,'anonymousUser',150,'300 Điện Biên Phủ',1000000,'Nhà sạch giá rẻ, thoáng mát, đầy đủ tiện nghi, có sân để xe và sân phơi đồ','P3',4,4,1,1,2),(4,NULL,NULL,NULL,NULL,'anonymousUser',200,'500 Ngô Thị Nhậm',2000000,'Nhà sạch giá rẻ, thoáng mát, đầy đủ tiện nghi, có sân để xe và sân phơi đồ','P4',4,1,3,1,3),(5,NULL,NULL,NULL,'2020-12-23 19:42:33.793000','anonymousUser',100,'100 Phạm Như Xương',1000000,'Nhà sạch giá rẻ, thoáng mát, đầy đủ tiện nghi, có sân để xe và sân phơi đồ','P5',4,2,3,1,2),(6,NULL,NULL,NULL,'2020-12-23 19:42:33.793000','anonymousUser',100,'100 Phạm Như Xương',1000000,'Nhà sạch giá rẻ, thoáng mát, đầy đủ tiện nghi, có sân để xe và sân phơi đồ','P6',4,2,3,1,2),(7,NULL,NULL,NULL,'2020-12-23 19:42:33.793000','anonymousUser',100,'100 Phạm Như Xương',1000000,'Nhà sạch giá rẻ, thoáng mát, đầy đủ tiện nghi, có sân để xe và sân phơi đồ','P7',4,2,3,1,2),(8,NULL,NULL,NULL,'2020-12-23 19:42:33.793000','anonymousUser',100,'100 Phạm Như Xương',1000000,'Nhà sạch giá rẻ, thoáng mát, đầy đủ tiện nghi, có sân để xe và sân phơi đồ','P8',4,2,3,2,2),(9,NULL,NULL,NULL,'2020-12-23 19:42:33.793000','anonymousUser',100,'100 Phạm Như Xương',1000000,'Nhà sạch giá rẻ, thoáng mát, đầy đủ tiện nghi, có sân để xe và sân phơi đồ','P9',4,2,3,2,2),(10,NULL,NULL,NULL,'2020-12-23 19:42:33.793000','anonymousUser',100,'100 Phạm Như Xương',1000000,'Nhà sạch giá rẻ, thoáng mát, đầy đủ tiện nghi, có sân để xe và sân phơi đồ','P10',4,2,3,2,2),(11,NULL,NULL,NULL,'2020-12-23 19:42:33.793000','anonymousUser',100,'100 Phạm Như Xương',1000000,'Nhà sạch giá rẻ, thoáng mát, đầy đủ tiện nghi, có sân để xe và sân phơi đồ','P11',4,2,3,2,2),(12,NULL,NULL,NULL,NULL,'anonymousUser',200,'200 Nam Cao',1000000,'Nhà sạch giá rẻ, thoáng mát, đầy đủ tiện nghi, có sân để xe và sân phơi đồ','P12',4,3,2,2,2),(13,NULL,NULL,NULL,NULL,'anonymousUser',200,'200 Nam Cao',1000000,'Nhà sạch giá rẻ, thoáng mát, đầy đủ tiện nghi, có sân để xe và sân phơi đồ','P13',4,3,2,2,2);
/*!40000 ALTER TABLE `rooms` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transactions`
--

DROP TABLE IF EXISTS `transactions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transactions` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `deleted_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `cost` int(11) DEFAULT NULL,
  `post_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKf9gscwynt3sqs4l53qqmjbuf5` (`post_id`),
  KEY `FKqwv7rmvc8va8rep7piikrojds` (`user_id`),
  CONSTRAINT `FKf9gscwynt3sqs4l53qqmjbuf5` FOREIGN KEY (`post_id`) REFERENCES `posts` (`id`),
  CONSTRAINT `FKqwv7rmvc8va8rep7piikrojds` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transactions`
--

LOCK TABLES `transactions` WRITE;
/*!40000 ALTER TABLE `transactions` DISABLE KEYS */;
/*!40000 ALTER TABLE `transactions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `type_rooms`
--

DROP TABLE IF EXISTS `type_rooms`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `type_rooms` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `deleted_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `type_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `type_rooms`
--

LOCK TABLES `type_rooms` WRITE;
/*!40000 ALTER TABLE `type_rooms` DISABLE KEYS */;
INSERT INTO `type_rooms` VALUES (1,NULL,NULL,NULL,NULL,NULL,'Căn hộ'),(2,NULL,NULL,NULL,NULL,NULL,'Phòng trọ'),(3,NULL,NULL,NULL,NULL,NULL,'Chung cư'),(4,NULL,NULL,NULL,NULL,NULL,'Kí túc xá');
/*!40000 ALTER TABLE `type_rooms` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_roles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKh8ciramu9cc9q3qcqiv4ue8a6` (`role_id`),
  KEY `FKhfh9dx7w3ubf1co1vdev94g3f` (`user_id`),
  CONSTRAINT `FKh8ciramu9cc9q3qcqiv4ue8a6` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
  CONSTRAINT `FKhfh9dx7w3ubf1co1vdev94g3f` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_roles`
--

LOCK TABLES `user_roles` WRITE;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
INSERT INTO `user_roles` VALUES (1,1,1),(2,2,2);
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `deleted_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `active` int(11) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `balance` int(11) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `gender` int(11) DEFAULT NULL,
  `identity_card` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'2020-12-20 11:12:35.187000','anonymousUser',NULL,'2020-12-22 18:10:59.895000','anonymousUser',NULL,'Phạm Như Xương, Đà Nẵng',NULL,'Nguyễn Minh Hiếu',1,'202156453','$2a$12$LtbYtEzOBdnsiD/E9Wtj2O3qeNaeYFP5jOqAmWX/3pUCK2yBn48Ta','1687851644','hieuminh22690@gmail.com'),(2,'2020-12-20 11:22:06.003000','anonymousUser',NULL,'2020-12-20 11:22:06.003000','anonymousUser',NULL,'Hà Văn Tính',NULL,'Nguyễn Thị Thùy Trinh',1,'123456789','$2a$12$LtbYtEzOBdnsiD/E9Wtj2O3qeNaeYFP5jOqAmWX/3pUCK2yBn48Ta','0123456789','hieuminh22490@gmail.com');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wallet_historys`
--

DROP TABLE IF EXISTS `wallet_historys`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `wallet_historys` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `deleted_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `active` int(11) DEFAULT NULL,
  `current_balance` int(11) DEFAULT NULL,
  `deposit` int(11) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6s8loris7p47k073gik0lvhab` (`user_id`),
  CONSTRAINT `FK6s8loris7p47k073gik0lvhab` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wallet_historys`
--

LOCK TABLES `wallet_historys` WRITE;
/*!40000 ALTER TABLE `wallet_historys` DISABLE KEYS */;
/*!40000 ALTER TABLE `wallet_historys` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'timtro247'
--

--
-- Dumping routines for database 'timtro247'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-12-29 14:19:35
