-- MySQL dump 10.13  Distrib 9.6.0, for macos14.8 (arm64)
--
-- Host: localhost    Database: lsu_scheduler
-- ------------------------------------------------------
-- Server version	9.6.0

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

SET @@GLOBAL.GTID_PURGED=/*!80000 '+'*/ '6a68f696-329d-11f1-b95c-76c473f57054:1-295';

--
-- Table structure for table `course_prerequisites`
--

DROP TABLE IF EXISTS `course_prerequisites`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course_prerequisites` (
  `course_id` varchar(255) NOT NULL,
  `prerequisite_id` varchar(255) DEFAULT NULL,
  KEY `FKhh4f1avebuvlv54m3j3l3pp36` (`course_id`),
  CONSTRAINT `FKhh4f1avebuvlv54m3j3l3pp36` FOREIGN KEY (`course_id`) REFERENCES `courses` (`course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_prerequisites`
--

LOCK TABLES `course_prerequisites` WRITE;
/*!40000 ALTER TABLE `course_prerequisites` DISABLE KEYS */;
INSERT INTO `course_prerequisites` VALUES ('CSC 4330','CSC 3102'),('CSC 4103','CSC 3102'),('CSC 4351','CSC 3102'),('CSC 4402','CSC 2050'),('CSC 4380','CSC 3102'),('CSC 1351','CSC 1350'),('CSC 2259','CSC 1351'),('CSC 2262','CSC 1351'),('CSC 2362','CSC 1351'),('CSC 2610','CSC 1351'),('CSC 2730','CSC 1351'),('CSC 3200','CSC 2259'),('CSC 3304','CSC 2259'),('CSC 3380','CSC 3102'),('CSC 3501','CSC 2259'),('CSC 3730','CSC 2730'),('CSC 4101','CSC 3380'),('CSC 4332','CSC 4330'),('CSC 4343','CSC 3730'),('CSC 4360','CSC 4103'),('CSC 4362','CSC 4360'),('CSC 4501','CSC 4103'),('CSC 4562','CSC 4103'),('CSC 4610','CSC 4501'),('CSC 4740','CSC 4402'),('MATH 1552','MATH 1550'),('MATH 2090','MATH 1552'),('IE 3302','MATH 1552'),('ENGL 2000','ENGL 1001');
/*!40000 ALTER TABLE `course_prerequisites` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `courses`
--

DROP TABLE IF EXISTS `courses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `courses` (
  `course_id` varchar(255) NOT NULL,
  `course_name` varchar(255) DEFAULT NULL,
  `credit_hours` int NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `required` bit(1) NOT NULL,
  `semester_offered` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `courses`
--

LOCK TABLES `courses` WRITE;
/*!40000 ALTER TABLE `courses` DISABLE KEYS */;
INSERT INTO `courses` VALUES ('CSC 1350','Computer Science I for Majors',4,NULL,_binary '','Spring','CS_CORE'),('CSC 1351','Computer Science II for Majors',4,NULL,_binary '','Spring','CS_CORE'),('CSC 2259','Discrete Structures',3,NULL,_binary '','Spring','CS_CORE'),('CSC 2262','Numerical Methods',3,NULL,_binary '\0','Spring','CS_CORE'),('CSC 2362','Introduction to Cybersecurity and Cyber Defense',3,NULL,_binary '\0','Spring','CS_CORE'),('CSC 2610','Cloud Fundamentals and Web Programming',3,NULL,_binary '\0','Spring','CS_CORE'),('CSC 2730','Data Science and Analytics',3,NULL,_binary '\0','Spring','CS_CORE'),('CSC 3200','Ethics in Computing',1,NULL,_binary '\0','Spring','CS_CORE'),('CSC 3304','Introduction to Systems Programming',3,NULL,_binary '\0','Spring','CS_CORE'),('CSC 3380','Object Oriented Design',3,NULL,_binary '','Spring','CS_CORE'),('CSC 3501','Computer Organization and Design',3,NULL,_binary '\0','Spring','CS_CORE'),('CSC 3730','Machine Learning and Data Analytics',3,NULL,_binary '\0','SPRING','CS_CORE'),('CSC 4101','Programming Languages',3,NULL,_binary '\0','Spring','CS_CORE'),('CSC 4103','Operating Systems',3,'Fundamentals of operating system design.',_binary '','Spring','CS_CORE'),('CSC 4330','Software Systems Dev',3,'Software development methodologies and practices.',_binary '','Spring','CS_CORE'),('CSC 4332','Software Quality and Testing',3,NULL,_binary '\0','Spring','CS_CORE'),('CSC 4343','Applied Deep Learning',3,NULL,_binary '\0','Spring','CS_CORE'),('CSC 4351','Compiler Construction',3,'Design and implementation of compilers.',_binary '\0','SPRING','CS_CORE'),('CSC 4360','Malware Analysis and Reverse Engineering',3,NULL,_binary '\0','Spring','CS_CORE'),('CSC 4362','Software Vulnerabilities and Exploitation',3,NULL,_binary '\0','Spring','CS_CORE'),('CSC 4380','Cyber Security',3,'Principles of computer and network security.',_binary '\0','Spring','CS_CORE'),('CSC 4402','Database Management',3,'Relational database design and SQL.',_binary '\0','Spring','CS_CORE'),('CSC 4501','Computer Networks',3,NULL,_binary '\0','Spring','CS_CORE'),('CSC 4562','Mobile Security and Applied Cryptography',3,NULL,_binary '\0','Spring','CS_CORE'),('CSC 4610','Cloud Systems and Virtualization',3,NULL,_binary '\0','Spring','CS_CORE'),('CSC 4740','Big Data Technologies',3,NULL,_binary '\0','Spring','CS_CORE'),('ENGL 1001','English Composition I',3,NULL,_binary '','Spring','GENERAL_ED'),('ENGL 2000','English Composition II',3,NULL,_binary '\0','Spring','GENERAL_ED'),('IE 3302','Engineering Statistics',3,NULL,_binary '\0','Spring','MATH'),('MATH 1550','Differential and Integral Calculus',5,NULL,_binary '','Spring','MATH'),('MATH 1552','Analytic Geometry and Calculus II',4,NULL,_binary '','Spring','MATH'),('MATH 2090','Elementary Differential Equations and Linear Algebra',4,NULL,_binary '','Spring','MATH');
/*!40000 ALTER TABLE `courses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `degree_plan`
--

DROP TABLE IF EXISTS `degree_plan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `degree_plan` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `course_id` varchar(255) DEFAULT NULL,
  `course_name` varchar(255) DEFAULT NULL,
  `credit_hours` int DEFAULT NULL,
  `recommended_semester` int DEFAULT NULL,
  `recommended_year` int DEFAULT NULL,
  `category` varchar(255) DEFAULT NULL,
  `concentration` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=90 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `degree_plan`
--

LOCK TABLES `degree_plan` WRITE;
/*!40000 ALTER TABLE `degree_plan` DISABLE KEYS */;
INSERT INTO `degree_plan` VALUES (1,'CSC 1350','Computer Science I for Majors',4,1,1,'CS Core','Software Engineering'),(2,'ENGL 1001','English Composition I',3,1,1,'General Ed','Software Engineering'),(3,'MATH 1550','Differential and Integral Calculus',5,1,1,'Math','Software Engineering'),(4,'CSC 1351','Computer Science II for Majors',4,2,1,'CS Core','Software Engineering'),(5,'MATH 1552','Analytic Geometry and Calculus II',4,2,1,'Math','Software Engineering'),(6,'CSC 2259','Discrete Structures',3,3,2,'CS Core','Software Engineering'),(7,'CSC 3102','Advanced Data Structures and Algorithm Analysis',3,3,2,'CS Core','Software Engineering'),(8,'MATH 2090','Elementary Differential Equations and Linear Algebra',4,3,2,'Math','Software Engineering'),(9,'CSC 2262','Numerical Methods',3,4,2,'CS Core','Software Engineering'),(10,'CSC 3304','Introduction to Systems Programming',3,4,2,'CS Core','Software Engineering'),(11,'CSC 3501','Computer Organization and Design',3,4,2,'CS Core','Software Engineering'),(12,'ENGL 2000','English Composition II',3,4,2,'General Ed','Software Engineering'),(13,'CSC 3380','Object Oriented Design',3,5,3,'CS Core','Software Engineering'),(14,'CSC 4101','Programming Languages',3,5,3,'CS Core','Software Engineering'),(15,'CSC 3200','Ethics in Computing',1,5,3,'CS Core','Software Engineering'),(16,'IE 3302','Engineering Statistics',3,5,3,'Math','Software Engineering'),(17,'CSC 4103','Operating Systems',3,6,3,'CS Core','Software Engineering'),(18,'CSC 4402','Database Systems',3,6,3,'CS Core','Software Engineering'),(19,'CSC 4351','Compiler Construction',3,6,3,'CS Core','Software Engineering'),(20,'CSC 4330','Software Systems Development',3,7,4,'CS Core','Software Engineering'),(21,'CSC 4332','Software Quality and Testing',3,8,4,'CS Core','Software Engineering'),(22,'CSC 1350','Computer Science I for Majors',4,1,1,'CS Core','Cybersecurity'),(23,'ENGL 1001','English Composition I',3,1,1,'General Ed','Cybersecurity'),(24,'MATH 1550','Differential and Integral Calculus',5,1,1,'Math','Cybersecurity'),(25,'CSC 1351','Computer Science II for Majors',4,2,1,'CS Core','Cybersecurity'),(26,'MATH 1552','Analytic Geometry and Calculus II',4,2,1,'Math','Cybersecurity'),(27,'CSC 3102','Advanced Data Structures and Algorithm Analysis',3,3,2,'CS Core','Cybersecurity'),(28,'CSC 2259','Discrete Structures',3,3,2,'CS Core','Cybersecurity'),(29,'MATH 2090','Elementary Differential Equations and Linear Algebra',4,3,2,'Math','Cybersecurity'),(30,'CSC 3380','Object Oriented Design',3,4,2,'CS Core','Cybersecurity'),(31,'CSC 3304','Introduction to Systems Programming',3,4,2,'CS Core','Cybersecurity'),(32,'CSC 2262','Numerical Methods',3,4,2,'CS Core','Cybersecurity'),(33,'ENGL 2000','English Composition II',3,4,2,'General Ed','Cybersecurity'),(34,'CSC 3501','Computer Organization and Design',3,5,3,'CS Core','Cybersecurity'),(35,'CSC 4103','Operating Systems',3,5,3,'CS Core','Cybersecurity'),(36,'CSC 2362','Introduction to Cybersecurity and Cyber Defense',3,5,3,'CS Core','Cybersecurity'),(37,'CSC 3200','Ethics in Computing',1,5,3,'CS Core','Cybersecurity'),(38,'CSC 4330','Software Systems Development',3,6,3,'CS Core','Cybersecurity'),(39,'CSC 4360','Malware Analysis and Reverse Engineering',3,6,3,'CS Core','Cybersecurity'),(40,'CSC 4402','Database Systems',3,6,3,'CS Core','Cybersecurity'),(41,'CSC 4101','Programming Languages',3,7,4,'CS Core','Cybersecurity'),(42,'CSC 4501','Computer Networks',3,7,4,'CS Core','Cybersecurity'),(43,'CSC 4362','Software Vulnerabilities and Exploitation',3,7,4,'CS Core','Cybersecurity'),(44,'IE 3302','Engineering Statistics',3,7,4,'Math','Cybersecurity'),(45,'CSC 4562','Mobile Security and Applied Cryptography',3,8,4,'CS Core','Cybersecurity'),(46,'CSC 1350','Computer Science I for Majors',4,1,1,'CS Core','Data Science'),(47,'ENGL 1001','English Composition I',3,1,1,'General Ed','Data Science'),(48,'MATH 1550','Differential and Integral Calculus',5,1,1,'Math','Data Science'),(49,'CSC 1351','Computer Science II for Majors',4,2,1,'CS Core','Data Science'),(50,'MATH 1552','Analytic Geometry and Calculus II',4,2,1,'Math','Data Science'),(51,'CSC 2259','Discrete Structures',3,3,2,'CS Core','Data Science'),(52,'CSC 3102','Advanced Data Structures and Algorithm Analysis',3,3,2,'CS Core','Data Science'),(53,'MATH 2090','Elementary Differential Equations and Linear Algebra',4,3,2,'Math','Data Science'),(54,'CSC 2262','Numerical Methods',3,4,2,'CS Core','Data Science'),(55,'CSC 3380','Object Oriented Design',3,4,2,'CS Core','Data Science'),(56,'CSC 3501','Computer Organization and Design',3,4,2,'CS Core','Data Science'),(57,'ENGL 2000','English Composition II',3,4,2,'General Ed','Data Science'),(58,'CSC 4402','Database Systems',3,5,3,'CS Core','Data Science'),(59,'CSC 2730','Data Science and Analytics',3,5,3,'CS Core','Data Science'),(60,'IE 3302','Engineering Statistics',3,5,3,'Math','Data Science'),(61,'CSC 4103','Operating Systems',3,6,3,'CS Core','Data Science'),(62,'CSC 4330','Software Systems Development',3,6,3,'CS Core','Data Science'),(63,'CSC 4740','Big Data Technologies',3,6,3,'CS Core','Data Science'),(64,'CSC 3200','Ethics in Computing',1,7,4,'CS Core','Data Science'),(65,'CSC 4101','Programming Languages',3,7,4,'CS Core','Data Science'),(66,'CSC 3730','Machine Learning and Data Analytics',3,7,4,'CS Core','Data Science'),(67,'CSC 4343','Applied Deep Learning',3,8,4,'CS Core','Data Science'),(68,'CSC 1350','Computer Science I for Majors',4,1,1,'CS Core','Cloud Computing'),(69,'ENGL 1001','English Composition I',3,1,1,'General Ed','Cloud Computing'),(70,'MATH 1550','Differential and Integral Calculus',5,1,1,'Math','Cloud Computing'),(71,'CSC 1351','Computer Science II for Majors',4,2,1,'CS Core','Cloud Computing'),(72,'MATH 1552','Analytic Geometry and Calculus II',4,2,1,'Math','Cloud Computing'),(73,'CSC 2259','Discrete Structures',3,3,2,'CS Core','Cloud Computing'),(74,'CSC 3102','Advanced Data Structures and Algorithm Analysis',3,3,2,'CS Core','Cloud Computing'),(75,'MATH 2090','Elementary Differential Equations and Linear Algebra',4,3,2,'Math','Cloud Computing'),(76,'CSC 2262','Numerical Methods',3,4,2,'CS Core','Cloud Computing'),(77,'CSC 2610','Cloud Fundamentals and Web Programming',3,4,2,'CS Core','Cloud Computing'),(78,'CSC 3380','Object Oriented Design',3,4,2,'CS Core','Cloud Computing'),(79,'ENGL 2000','English Composition II',3,4,2,'General Ed','Cloud Computing'),(80,'CSC 4402','Database Systems',3,5,3,'CS Core','Cloud Computing'),(81,'CSC 4501','Computer Networks',3,5,3,'CS Core','Cloud Computing'),(82,'IE 3302','Engineering Statistics',3,5,3,'Math','Cloud Computing'),(83,'CSC 3200','Ethics in Computing',1,6,3,'CS Core','Cloud Computing'),(84,'CSC 3501','Computer Organization and Design',3,6,3,'CS Core','Cloud Computing'),(85,'CSC 4103','Operating Systems',3,6,3,'CS Core','Cloud Computing'),(86,'CSC 4330','Software Systems Development',3,6,3,'CS Core','Cloud Computing'),(87,'CSC 4101','Programming Languages',3,7,4,'CS Core','Cloud Computing'),(88,'CSC 4610','Cloud Systems and Virtualization',3,7,4,'CS Core','Cloud Computing'),(89,'CSC 4562','Mobile Security and Applied Cryptography',3,8,4,'CS Core','Cloud Computing');
/*!40000 ALTER TABLE `degree_plan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `enrollments`
--

DROP TABLE IF EXISTS `enrollments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `enrollments` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `student_id` bigint DEFAULT NULL,
  `course_id` varchar(255) DEFAULT NULL,
  `section_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKho8mcicp4196ebpltdn9wl6co` (`course_id`),
  KEY `FK8kf1u1857xgo56xbfmnif2c51` (`student_id`),
  CONSTRAINT `FK8kf1u1857xgo56xbfmnif2c51` FOREIGN KEY (`student_id`) REFERENCES `students` (`id`),
  CONSTRAINT `FKho8mcicp4196ebpltdn9wl6co` FOREIGN KEY (`course_id`) REFERENCES `courses` (`course_id`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `enrollments`
--

LOCK TABLES `enrollments` WRITE;
/*!40000 ALTER TABLE `enrollments` DISABLE KEYS */;
/*!40000 ALTER TABLE `enrollments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sections`
--

DROP TABLE IF EXISTS `sections`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sections` (
  `section_id` bigint NOT NULL AUTO_INCREMENT,
  `building` varchar(255) DEFAULT NULL,
  `capacity` int NOT NULL,
  `days` varchar(255) DEFAULT NULL,
  `end_time` varchar(255) DEFAULT NULL,
  `enrolled_count` int NOT NULL,
  `instructor` varchar(255) DEFAULT NULL,
  `rmp_link` varchar(255) DEFAULT NULL,
  `room` varchar(255) DEFAULT NULL,
  `section_number` varchar(255) DEFAULT NULL,
  `start_time` varchar(255) DEFAULT NULL,
  `course_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`section_id`),
  KEY `FK7ty9cevpq04d90ohtso1q8312` (`course_id`),
  CONSTRAINT `FK7ty9cevpq04d90ohtso1q8312` FOREIGN KEY (`course_id`) REFERENCES `courses` (`course_id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sections`
--

LOCK TABLES `sections` WRITE;
/*!40000 ALTER TABLE `sections` DISABLE KEYS */;
INSERT INTO `sections` VALUES (1,'Patrick Taylor',30,'MWF','10:20 AM',22,'Gerald Baumgartner',NULL,'1100','001','9:30 AM','CSC 4103'),(2,'Tureaud Hall',30,'TTh','2:50 PM',18,'David Trammell',NULL,'215','002','1:30 PM','CSC 4103'),(3,'Patrick Taylor',30,'MWF','11:20 AM',25,'William Duncan',NULL,'1100','001','10:30 AM','CSC 4330'),(4,'Johnston Hall',30,'TTh','4:20 PM',20,'William Duncan',NULL,'338','002','3:00 PM','CSC 4330'),(5,'Tureaud Hall',25,'TTh','11:50 AM',15,'Gerald Baumgartner',NULL,'215','001','10:30 AM','CSC 4351'),(6,'Patrick Taylor',30,'MWF','12:20 PM',20,'Abeer Eldewahi',NULL,'1100','001','11:30 AM','CSC 4380'),(7,'Johnston Hall',30,'TTh','10:20 AM',15,'Josephy Khoury',NULL,'338','002','9:00 AM','CSC 4380'),(8,'Johnston Hall',30,'MWF','9:20 AM',25,'David Trammell',NULL,'338','001','8:30 AM','CSC 4402'),(9,'Coates Hall',30,'TTh','11:50 AM',12,'Abeer Eldewahi',NULL,'145','002','10:30 AM','CSC 4402'),(10,'Patrick Taylor',30,'MWF','10:20 AM',20,'Abeer Eldewahi',NULL,'1100','001','9:30 AM','CSC 1351'),(11,'Johnston Hall',30,'TTh','11:50 AM',25,'Gerald Baumgartner',NULL,'338','001','10:30 AM','CSC 2259'),(12,'Tureaud Hall',30,'MWF','12:20 PM',18,'David Trammell',NULL,'215','001','11:30 AM','CSC 2262'),(13,'Johnston Hall',30,'TTh','2:50 PM',15,'Josephy Khoury',NULL,'338','001','1:30 PM','CSC 2362'),(14,'Coates Hall',30,'MWF','9:20 AM',22,'Abeer Eldewahi',NULL,'145','001','8:30 AM','CSC 2610'),(15,'Patrick Taylor',30,'TTh','10:20 AM',20,'David Trammell',NULL,'1100','001','9:00 AM','CSC 2730'),(16,'Johnston Hall',35,'MWF','1:20 PM',28,'William Duncan',NULL,'338','001','12:30 PM','CSC 3200'),(17,'Tureaud Hall',30,'TTh','4:20 PM',16,'Gerald Baumgartner',NULL,'215','001','3:00 PM','CSC 3304'),(18,'Johnston Hall',30,'MWF','11:20 AM',24,'William Duncan',NULL,'338','001','10:30 AM','CSC 3380'),(19,'Patrick Taylor',30,'TTh','2:50 PM',20,'William Duncan',NULL,'1100','002','1:30 PM','CSC 3380'),(20,'Coates Hall',30,'MWF','10:20 AM',22,'Josephy Khoury',NULL,'145','001','9:30 AM','CSC 3501'),(21,'Patrick Taylor',25,'TTh','11:50 AM',18,'David Trammell',NULL,'1100','001','10:30 AM','CSC 3730'),(22,'Johnston Hall',30,'MWF','9:20 AM',20,'Gerald Baumgartner',NULL,'338','001','8:30 AM','CSC 4101'),(23,'Tureaud Hall',25,'TTh','10:20 AM',15,'William Duncan',NULL,'215','001','9:00 AM','CSC 4332'),(24,'Coates Hall',25,'MWF','2:20 PM',12,'David Trammell',NULL,'145','001','1:30 PM','CSC 4343'),(25,'Johnston Hall',25,'TTh','4:20 PM',14,'Josephy Khoury',NULL,'338','001','3:00 PM','CSC 4360'),(26,'Tureaud Hall',25,'MWF','3:20 PM',10,'Josephy Khoury',NULL,'215','001','2:30 PM','CSC 4362'),(27,'Coates Hall',30,'TTh','2:50 PM',20,'Abeer Eldewahi',NULL,'145','001','1:30 PM','CSC 4501'),(28,'Patrick Taylor',30,'MWF','4:20 PM',15,'Abeer Eldewahi',NULL,'1100','002','3:30 PM','CSC 4501'),(29,'Johnston Hall',25,'TTh','11:50 AM',12,'Josephy Khoury',NULL,'338','001','10:30 AM','CSC 4562'),(30,'Coates Hall',30,'MWF','12:20 PM',18,'Abeer Eldewahi',NULL,'145','001','11:30 AM','CSC 4610'),(31,'Patrick Taylor',25,'TTh','4:20 PM',16,'David Trammell',NULL,'1100','001','3:00 PM','CSC 4740'),(32,'Lockett Hall',35,'MWF','9:20 AM',30,'Staff',NULL,'9','001','8:30 AM','MATH 1550'),(33,'Lockett Hall',35,'MWF','10:20 AM',28,'Staff',NULL,'9','001','9:30 AM','MATH 1552'),(34,'Lockett Hall',35,'MWF','11:20 AM',25,'Staff',NULL,'9','001','10:30 AM','MATH 2090'),(35,'Patrick Taylor',30,'TTh','10:20 AM',22,'Staff',NULL,'1100','001','9:00 AM','IE 3302'),(36,'Allen Hall',25,'MWF','12:20 PM',20,'Staff',NULL,'101','001','11:30 AM','ENGL 1001'),(37,'Allen Hall',25,'TTh','2:50 PM',18,'Staff',NULL,'101','001','1:30 PM','ENGL 2000'),(38,'Patrick Taylor',30,'MWF','9:20 AM',18,'Abeer Eldewahi',NULL,'1100','001','8:30 AM','CSC 1350'),(39,'Johnston Hall',30,'TTh','11:50 AM',20,'David Trammell',NULL,'338','002','10:30 AM','CSC 1350');
/*!40000 ALTER TABLE `sections` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_completed_courses`
--

DROP TABLE IF EXISTS `student_completed_courses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student_completed_courses` (
  `student_id` bigint DEFAULT NULL,
  `course_id` varchar(255) DEFAULT NULL,
  KEY `FK63d64enrk275lq0memeawe6tj` (`student_id`),
  CONSTRAINT `FK63d64enrk275lq0memeawe6tj` FOREIGN KEY (`student_id`) REFERENCES `students` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_completed_courses`
--

LOCK TABLES `student_completed_courses` WRITE;
/*!40000 ALTER TABLE `student_completed_courses` DISABLE KEYS */;
INSERT INTO `student_completed_courses` VALUES (1,'CSC 1350'),(1,'CSC 1351'),(1,'CSC 2050'),(1,'CSC 3102'),(2,'CSC 1350'),(2,'CSC 1351'),(2,'CSC 2050'),(2,'CSC 3102'),(2,'MATH 1550'),(2,'MATH 1552'),(2,'MATH 2090'),(2,'ENGL 1001'),(2,'ENGL 2000'),(2,'CSC 2259'),(2,'CSC 2262'),(2,'CSC 3200'),(2,'CSC 3380'),(2,'CSC 3501'),(4,'CSC 1350');
/*!40000 ALTER TABLE `student_completed_courses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `students`
--

DROP TABLE IF EXISTS `students`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `students` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `credits_completed` int NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `major` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `year` varchar(255) DEFAULT NULL,
  `student_id` varchar(255) DEFAULT NULL,
  `concentration` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `students`
--

LOCK TABLES `students` WRITE;
/*!40000 ALTER TABLE `students` DISABLE KEYS */;
INSERT INTO `students` VALUES (1,89,'mnguyen@lsu.edu','Computer Science','Minh Nguyen','Junior','89012345',NULL),(2,50,'mnguye3@lsu.edu','Computer Science','minh nguyen','Junior','899649604','Data Science'),(3,0,'Minh@lsu.edu','Computer Science','Minh Nguyen','Junior','123','Data Science'),(4,0,'cameron@lsu.edu','Computer Science','cameron guidry','Freshman','1234','Software Engineering');
/*!40000 ALTER TABLE `students` ENABLE KEYS */;
UNLOCK TABLES;
SET @@SESSION.SQL_LOG_BIN = @MYSQLDUMP_TEMP_LOG_BIN;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-04-29 11:43:19
