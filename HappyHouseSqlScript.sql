CREATE DATABASE `ssafyweb` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
use ssafyweb;

CREATE TABLE `board` (
  `BOARD_ID` int NOT NULL AUTO_INCREMENT,
  `userid` varchar(30) NOT NULL,
  `TITLE` varchar(500) DEFAULT NULL,
  `CONTENT` text,
  `REG_DT` datetime DEFAULT NULL,
  `READ_COUNT` int DEFAULT '0',
  PRIMARY KEY (`BOARD_ID`),
  KEY `FK_USER_idx` (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `board_file` (
  `FILE_ID` int NOT NULL AUTO_INCREMENT,
  `BOARD_ID` int NOT NULL,
  `FILE_NAME` varchar(500) NOT NULL,
  `FILE_SIZE` int NOT NULL,
  `FILE_CONTENT_TYPE` varchar(500) NOT NULL,
  `FILE_URL` varchar(500) NOT NULL,
  `REG_DT` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`FILE_ID`),
  KEY `BOARD_FILE_FK_idx` (`BOARD_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `board_user_read` (
  `board_id` int NOT NULL,
  `userid` varchar(30) NOT NULL,
  PRIMARY KEY (`board_id`,`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `code` (
  `group_code` char(3) NOT NULL,
  `code` char(3) NOT NULL,
  `code_name` varchar(50) DEFAULT NULL,
  `use_yn` char(1) DEFAULT NULL,
  PRIMARY KEY (`group_code`,`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `group_code` (
  `group_code` char(3) NOT NULL,
  `group_code_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`group_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `guestbook` (
  `articleno` int NOT NULL AUTO_INCREMENT,
  `userid` varchar(16) NOT NULL,
  `subject` varchar(100) NOT NULL,
  `content` varchar(2000) NOT NULL,
  `regtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`articleno`),
  KEY `guestbook_userid_FK_idx` (`userid`),
  CONSTRAINT `guestbook_userid_FK` FOREIGN KEY (`userid`) REFERENCES `ssafy_member` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `housedeal` (
  `no` int NOT NULL AUTO_INCREMENT,
  `dong` varchar(30) NOT NULL,
  `AptName` varchar(50) NOT NULL,
  `code` varchar(30) NOT NULL,
  `dealAmount` varchar(50) NOT NULL,
  `buildYear` varchar(30) DEFAULT NULL,
  `dealYear` varchar(30) DEFAULT NULL,
  `dealMonth` varchar(30) DEFAULT NULL,
  `dealDay` varchar(30) DEFAULT NULL,
  `area` varchar(30) DEFAULT NULL,
  `floor` varchar(30) DEFAULT NULL,
  `jibun` varchar(30) DEFAULT NULL,
  `type` varchar(30) DEFAULT NULL,
  `rentMoney` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`no`)
) ENGINE=InnoDB AUTO_INCREMENT=68865 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `houseinfo` (
  `no` int NOT NULL AUTO_INCREMENT,
  `dong` varchar(30) NOT NULL,
  `AptName` varchar(50) NOT NULL,
  `code` varchar(30) NOT NULL,
  `buildYear` varchar(30) DEFAULT NULL,
  `jibun` varchar(30) DEFAULT NULL,
  `lat` varchar(30) DEFAULT NULL,
  `lng` varchar(30) DEFAULT NULL,
  `img` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`no`)
) ENGINE=InnoDB AUTO_INCREMENT=5990 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `ssafy_member` (
  `userid` varchar(16) NOT NULL,
  `username` varchar(20) NOT NULL,
  `userpwd` varchar(30) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  `address_gu` varchar(100) DEFAULT NULL,
  `joindate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `user_clsf` char(3) DEFAULT NULL,
  `address_dong` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



