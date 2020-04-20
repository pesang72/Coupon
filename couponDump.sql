CREATE DATABASE  IF NOT EXISTS `kakao_coupon` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `kakao_coupon`;
-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: kakao_coupon
-- ------------------------------------------------------
-- Server version	8.0.19

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
-- Table structure for table `batch_job_execution`
--

DROP TABLE IF EXISTS `batch_job_execution`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `batch_job_execution` (
  `JOB_EXECUTION_ID` bigint NOT NULL,
  `VERSION` bigint DEFAULT NULL,
  `JOB_INSTANCE_ID` bigint NOT NULL,
  `CREATE_TIME` datetime NOT NULL,
  `START_TIME` datetime DEFAULT NULL,
  `END_TIME` datetime DEFAULT NULL,
  `STATUS` varchar(10) DEFAULT NULL,
  `EXIT_CODE` varchar(2500) DEFAULT NULL,
  `EXIT_MESSAGE` varchar(2500) DEFAULT NULL,
  `LAST_UPDATED` datetime DEFAULT NULL,
  `JOB_CONFIGURATION_LOCATION` varchar(2500) DEFAULT NULL,
  PRIMARY KEY (`JOB_EXECUTION_ID`),
  KEY `JOB_INST_EXEC_FK` (`JOB_INSTANCE_ID`),
  CONSTRAINT `JOB_INST_EXEC_FK` FOREIGN KEY (`JOB_INSTANCE_ID`) REFERENCES `batch_job_instance` (`JOB_INSTANCE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `batch_job_execution_context`
--

DROP TABLE IF EXISTS `batch_job_execution_context`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `batch_job_execution_context` (
  `JOB_EXECUTION_ID` bigint NOT NULL,
  `SHORT_CONTEXT` varchar(2500) NOT NULL,
  `SERIALIZED_CONTEXT` text,
  PRIMARY KEY (`JOB_EXECUTION_ID`),
  CONSTRAINT `JOB_EXEC_CTX_FK` FOREIGN KEY (`JOB_EXECUTION_ID`) REFERENCES `batch_job_execution` (`JOB_EXECUTION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `batch_job_execution_params`
--

DROP TABLE IF EXISTS `batch_job_execution_params`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `batch_job_execution_params` (
  `JOB_EXECUTION_ID` bigint NOT NULL,
  `TYPE_CD` varchar(6) NOT NULL,
  `KEY_NAME` varchar(100) NOT NULL,
  `STRING_VAL` varchar(250) DEFAULT NULL,
  `DATE_VAL` datetime DEFAULT NULL,
  `LONG_VAL` bigint DEFAULT NULL,
  `DOUBLE_VAL` double DEFAULT NULL,
  `IDENTIFYING` char(1) NOT NULL,
  KEY `JOB_EXEC_PARAMS_FK` (`JOB_EXECUTION_ID`),
  CONSTRAINT `JOB_EXEC_PARAMS_FK` FOREIGN KEY (`JOB_EXECUTION_ID`) REFERENCES `batch_job_execution` (`JOB_EXECUTION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `batch_job_execution_seq`
--

DROP TABLE IF EXISTS `batch_job_execution_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `batch_job_execution_seq` (
  `ID` bigint NOT NULL,
  `UNIQUE_KEY` char(1) NOT NULL,
  UNIQUE KEY `UNIQUE_KEY_UN` (`UNIQUE_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `batch_job_instance`
--

DROP TABLE IF EXISTS `batch_job_instance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `batch_job_instance` (
  `JOB_INSTANCE_ID` bigint NOT NULL,
  `VERSION` bigint DEFAULT NULL,
  `JOB_NAME` varchar(100) NOT NULL,
  `JOB_KEY` varchar(32) NOT NULL,
  PRIMARY KEY (`JOB_INSTANCE_ID`),
  UNIQUE KEY `JOB_INST_UN` (`JOB_NAME`,`JOB_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `batch_job_seq`
--

DROP TABLE IF EXISTS `batch_job_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `batch_job_seq` (
  `ID` bigint NOT NULL,
  `UNIQUE_KEY` char(1) NOT NULL,
  UNIQUE KEY `UNIQUE_KEY_UN` (`UNIQUE_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `batch_step_execution`
--

DROP TABLE IF EXISTS `batch_step_execution`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `batch_step_execution` (
  `STEP_EXECUTION_ID` bigint NOT NULL,
  `VERSION` bigint NOT NULL,
  `STEP_NAME` varchar(100) NOT NULL,
  `JOB_EXECUTION_ID` bigint NOT NULL,
  `START_TIME` datetime NOT NULL,
  `END_TIME` datetime DEFAULT NULL,
  `STATUS` varchar(10) DEFAULT NULL,
  `COMMIT_COUNT` bigint DEFAULT NULL,
  `READ_COUNT` bigint DEFAULT NULL,
  `FILTER_COUNT` bigint DEFAULT NULL,
  `WRITE_COUNT` bigint DEFAULT NULL,
  `READ_SKIP_COUNT` bigint DEFAULT NULL,
  `WRITE_SKIP_COUNT` bigint DEFAULT NULL,
  `PROCESS_SKIP_COUNT` bigint DEFAULT NULL,
  `ROLLBACK_COUNT` bigint DEFAULT NULL,
  `EXIT_CODE` varchar(2500) DEFAULT NULL,
  `EXIT_MESSAGE` varchar(2500) DEFAULT NULL,
  `LAST_UPDATED` datetime DEFAULT NULL,
  PRIMARY KEY (`STEP_EXECUTION_ID`),
  KEY `JOB_EXEC_STEP_FK` (`JOB_EXECUTION_ID`),
  CONSTRAINT `JOB_EXEC_STEP_FK` FOREIGN KEY (`JOB_EXECUTION_ID`) REFERENCES `batch_job_execution` (`JOB_EXECUTION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `batch_step_execution_context`
--

DROP TABLE IF EXISTS `batch_step_execution_context`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `batch_step_execution_context` (
  `STEP_EXECUTION_ID` bigint NOT NULL,
  `SHORT_CONTEXT` varchar(2500) NOT NULL,
  `SERIALIZED_CONTEXT` text,
  PRIMARY KEY (`STEP_EXECUTION_ID`),
  CONSTRAINT `STEP_EXEC_CTX_FK` FOREIGN KEY (`STEP_EXECUTION_ID`) REFERENCES `batch_step_execution` (`STEP_EXECUTION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `batch_step_execution_seq`
--

DROP TABLE IF EXISTS `batch_step_execution_seq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `batch_step_execution_seq` (
  `ID` bigint NOT NULL,
  `UNIQUE_KEY` char(1) NOT NULL,
  UNIQUE KEY `UNIQUE_KEY_UN` (`UNIQUE_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_coupon_code_1`
--

DROP TABLE IF EXISTS `tb_coupon_code_1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_coupon_code_1` (
  `SEQ` int NOT NULL AUTO_INCREMENT,
  `COUPON_SEQ` mediumint DEFAULT NULL,
  `COUPON_CODE` varchar(20) DEFAULT NULL,
  `TARGET_USER_ID` varchar(70) DEFAULT NULL,
  `IS_USED` varchar(1) DEFAULT 'N',
  `REG_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `MOD_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`SEQ`)
) ENGINE=InnoDB AUTO_INCREMENT=15601 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_coupon_count`
--

DROP TABLE IF EXISTS `tb_coupon_count`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_coupon_count` (
  `COUPON_SEQ` int NOT NULL,
  `COUNT` int DEFAULT '0',
  PRIMARY KEY (`COUPON_SEQ`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_coupon_info`
--

DROP TABLE IF EXISTS `tb_coupon_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_coupon_info` (
  `SEQ` int NOT NULL AUTO_INCREMENT,
  `COUPON_SEQ` int DEFAULT NULL,
  `TITLE` varchar(100) DEFAULT NULL,
  `THUMBNAIL` varchar(70) DEFAULT NULL,
  `START_DATE` timestamp NULL DEFAULT NULL,
  `END_DATE` timestamp NULL DEFAULT NULL,
  `REG_DATE` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `COUPON_COUNT` bigint DEFAULT '0',
  `IS_SERVICE` varchar(1) DEFAULT 'N',
  PRIMARY KEY (`SEQ`),
  UNIQUE KEY `COUPON_IDX` (`COUPON_SEQ`)
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_coupon_prefix`
--

DROP TABLE IF EXISTS `tb_coupon_prefix`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_coupon_prefix` (
  `COUPON_SEQ` int NOT NULL,
  `COUPON_PREFIX` varchar(6) DEFAULT NULL,
  PRIMARY KEY (`COUPON_SEQ`),
  UNIQUE KEY `prefix_uk` (`COUPON_PREFIX`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_coupon_user_info`
--

DROP TABLE IF EXISTS `tb_coupon_user_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_coupon_user_info` (
  `SEQ` int NOT NULL AUTO_INCREMENT,
  `USER_SEQ` int DEFAULT NULL,
  `COUPON_SEQ` int DEFAULT NULL,
  `COUPON_CODE_SEQ` int DEFAULT NULL,
  `IS_USED` varchar(1) DEFAULT NULL,
  `REG_DATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `UPD_DATE` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`SEQ`),
  UNIQUE KEY `USER_SEQ` (`USER_SEQ`,`COUPON_SEQ`),
  KEY `user_info` (`USER_SEQ`,`COUPON_SEQ`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tb_user_mapping_info`
--

DROP TABLE IF EXISTS `tb_user_mapping_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tb_user_mapping_info` (
  `USER_SEQ` int NOT NULL,
  `EMAIL` varchar(50) DEFAULT NULL,
  `PASSWORD` varchar(200) DEFAULT NULL,
  `TOKEN` varchar(500) DEFAULT NULL,
  `REG_DATE` datetime DEFAULT CURRENT_TIMESTAMP,
  `UPD_DATE` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`USER_SEQ`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping routines for database 'kakao_coupon'
--
/*!50003 DROP PROCEDURE IF EXISTS `SP_PROVIDE_COUPON` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_PROVIDE_COUPON`(
IN IN_USER_SEQ INT,
IN IN_COUPON_SEQ INT
)
BEGIN
	/* 가격을 변경할 변수를 선언한다. */
	DECLARE COUPON_CODE_SEQ INT;
	DECLARE RESULT INT;
	/* 만약 SQL에러라면 ROLLBACK 처리한다.*/
	DECLARE exit handler for SQLEXCEPTION
	  BEGIN
		ROLLBACK;        
		SET RESULT = CONCAT('UPDATE tb_coupon_code_',IN_COUPON_SEQ);  
        SELECT -1;
	END;
	    
    
    START TRANSACTION;
    
		SELECT COUPON_SEQ INTO RESULT from tb_coupon_user_info A where USER_SEQ = IN_USER_SEQ AND COUPON_SEQ = IN_COUPON_SEQ;
		IF RESULT IS NULL THEN

            SELECT COUNT into COUPON_CODE_SEQ  FROM tb_coupon_count WHERE COUPON_SEQ = IN_COUPON_SEQ FOR UPDATE;
            SET COUPON_CODE_SEQ = COUPON_CODE_SEQ + 1;
            
			IF COUPON_CODE_SEQ >= (SELECT COUPON_COUNT FROM tb_coupon_info where COUPON_SEQ = IN_COUPON_SEQ) THEN
				SET RESULT = -3;    
			ELSE
				SET @GetName = CONCAT('UPDATE tb_coupon_code_',IN_COUPON_SEQ,' SET TARGET_USER_ID = ',IN_USER_SEQ,' WHERE SEQ = ',COUPON_CODE_SEQ);
				PREPARE stmt FROM @GetName;
				EXECUTE stmt;
				
				INSERT INTO tb_coupon_user_info (USER_SEQ,COUPON_SEQ,COUPON_CODE_SEQ,IS_USED) VALUES (IN_USER_SEQ,IN_COUPON_SEQ,COUPON_CODE_SEQ,'N');
				
				UPDATE tb_coupon_count SET COUNT = COUPON_CODE_SEQ WHERE COUPON_SEQ = IN_COUPON_SEQ;
				SET RESULT = COUPON_CODE_SEQ;    
            END IF;
		
		ELSE
			# 이미 등록된 값.
			SET RESULT = -2;
		END IF;
    COMMIT;
	SELECT RESULT;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `SP_REGISTER_COUPON` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `SP_REGISTER_COUPON`(
IN IN_TITLE VARCHAR(100),
IN IN_THUMBNAIL VARCHAR(70),
IN IN_START_TIME TIMESTAMP,
IN IN_END_TIME TIMESTAMP,
IN IN_COUPON_PREFIX VARCHAR(10)
)
BEGIN
	DECLARE RESULT INT;
    DECLARE MAX_COUPON_SEQ INT;
	DECLARE VAL_TABLE_NAME VARCHAR(50);
    
    
    SELECT MAX(COUPON_SEQ) INTO MAX_COUPON_SEQ FROM tb_coupon_info;
    IF MAX_COUPON_SEQ IS NULL THEN
		SET MAX_COUPON_SEQ = 0;
    END IF;
    
    SET MAX_COUPON_SEQ = MAX_COUPON_SEQ + 1;
    
    SET VAL_TABLE_NAME = CONCAT('tb_coupon_code_',MAX_COUPON_SEQ);
    
    
    IF  (SELECT COUNT(1) FROM information_schema.tables WHERE table_name = VAL_TABLE_NAME) = 0 THEN
		
        INSERT INTO tb_coupon_info (COUPON_SEQ,TITLE,THUMBNAIL,START_DATE,END_DATE) VALUES (MAX_COUPON_SEQ,IN_TITLE,IN_THUMBNAIL,IN_START_TIME,IN_END_TIME);
        INSERT INTO tb_coupon_prefix (COUPON_SEQ, COUPON_PREFIX) VALUES ( MAX_COUPON_SEQ, IN_COUPON_PREFIX);
        
        SET @sqlStr = CONCAT('CREATE TABLE ',VAL_TABLE_NAME,' ');
        SET @sqlStr = CONCAT(@sqlStr,'(SEQ int NOT NULL AUTO_INCREMENT,');
        SET @sqlStr = CONCAT(@sqlStr,'COUPON_SEQ mediumint DEFAULT NULL,');
        SET @sqlStr = CONCAT(@sqlStr,'COUPON_CODE varchar(20) DEFAULT NULL,');
        SET @sqlStr = CONCAT(@sqlStr,'TARGET_USER_ID varchar(70) DEFAULT NULL,');
        SET @sqlStr = CONCAT(@sqlStr,'IS_USED varchar(1) DEFAULT \'N\' , ');
        SET @sqlStr = CONCAT(@sqlStr,'REG_DATE timestamp DEFAULT CURRENT_TIMESTAMP,');
        SET @sqlStr = CONCAT(@sqlStr,'MOD_DATE timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,');
        SET @sqlStr = CONCAT(@sqlStr,'PRIMARY KEY (SEQ)) ENGINE=InnoDB AUTO_INCREMENT=0');
        
		PREPARE stmt FROM @sqlStr;
		EXECUTE stmt;

        IF(SELECT COUNT(1) FROM tb_coupon_count WHERE COUPON_SEQ = MAX_COUPON_SEQ) = 0 THEN
			INSERT INTO tb_coupon_count (COUPON_SEQ) VALUES (MAX_COUPON_SEQ);
            SET RESULT = MAX_COUPON_SEQ;
        ELSE
			SET RESULT = 3;
        END IF;
	
	ELSE	
		SET RESULT = 2;
    END IF;
    
	SELECT RESULT;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-04-21  4:09:01
