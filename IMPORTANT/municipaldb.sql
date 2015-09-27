/*
SQLyog Ultimate v10.42 
MySQL - 5.6.20 : Database - municipaldb
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`municipaldb` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `municipaldb`;

/*Table structure for table `accounts` */

DROP TABLE IF EXISTS `accounts`;

CREATE TABLE `accounts` (
  `ACCOUNTID` int(11) NOT NULL AUTO_INCREMENT,
  `CREATED_ON` date DEFAULT NULL,
  `ISDISABLED` tinyint(1) DEFAULT '0',
  `PASSWORD` varchar(255) DEFAULT NULL,
  `UPDATED_ON` date DEFAULT NULL,
  `USERNAME` varchar(255) DEFAULT NULL,
  `USERTYPE_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ACCOUNTID`),
  KEY `FK_ACCOUNTS_USERTYPE_ID` (`USERTYPE_ID`),
  CONSTRAINT `FK_ACCOUNTS_USERTYPE_ID` FOREIGN KEY (`USERTYPE_ID`) REFERENCES `usertype` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

/*Data for the table `accounts` */

insert  into `accounts`(`ACCOUNTID`,`CREATED_ON`,`ISDISABLED`,`PASSWORD`,`UPDATED_ON`,`USERNAME`,`USERTYPE_ID`) values (1,'1969-12-31',0,'losewin',NULL,'losewin4',3),(2,'1969-12-31',0,'losewin',NULL,'losewin3',4),(3,'1969-12-31',0,'losewin',NULL,'losewin6',2),(4,'1969-12-31',0,'losewin',NULL,'losewin2',1),(5,'1969-12-31',0,'losewin',NULL,'losewin1',1),(6,'1969-12-31',0,'losewin',NULL,'losewin5',3);

/*Table structure for table `barangay` */

DROP TABLE IF EXISTS `barangay`;

CREATE TABLE `barangay` (
  `BARANGAYID` int(11) NOT NULL AUTO_INCREMENT,
  `DATE_REGISTERED` date DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `MUNICIPAL_MUNICIPALID` int(11) DEFAULT NULL,
  `DISTRICK` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`BARANGAYID`),
  KEY `FK_BARANGAY_MUNICIPAL_MUNICIPALID` (`MUNICIPAL_MUNICIPALID`),
  CONSTRAINT `FK_BARANGAY_MUNICIPAL_MUNICIPALID` FOREIGN KEY (`MUNICIPAL_MUNICIPALID`) REFERENCES `municipal` (`MUNICIPALID`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=latin1;

/*Data for the table `barangay` */

insert  into `barangay`(`BARANGAYID`,`DATE_REGISTERED`,`NAME`,`MUNICIPAL_MUNICIPALID`,`DISTRICK`) values (1,'1969-12-31','Carmen',2,NULL),(2,'1969-12-31','Pagadian',1,NULL),(3,'1969-12-31','Kauswagan',2,NULL),(4,'1969-12-31','Bugo',2,NULL),(5,'1969-12-31','Parangka',1,NULL),(6,'1969-12-31','qwewqewqe',4,0),(7,'1969-12-31','Carment',5,2),(8,'1969-12-31','qqqqq',6,3),(9,'1969-12-31','adasdasdsad',6,3),(10,'1969-12-31','qweqweqwe',6,5),(11,'1969-12-31','awdawdwd',9,0),(12,'1969-12-31','qwewqe',9,0),(13,'1969-12-31','ssss',9,0),(14,'1969-12-31','bbbb',9,0),(15,'1969-12-31','zzzzz',9,0),(16,'1969-12-31','qeqweqwe',10,4),(17,'1969-12-31','qqqqqq',11,2),(18,'1969-12-31','qqqqqq',12,3),(19,'1969-12-31','qweqwe',12,3),(20,'1969-12-31','qweqweqwe',13,2),(21,'1969-12-31','qweqwewqe',14,2),(22,'1969-12-31','qweqwewqe',15,4),(23,'1969-12-31','qwewqewq',16,2),(24,'1969-12-31','qweqwe',16,0),(25,'1969-12-31','qweqwe',16,0),(26,'1969-12-31','qweqweqw',16,2),(27,'1969-12-31','wqeqwewqe',16,2),(28,'1969-12-31','qweqw',16,2),(29,'1969-12-31','qwewqewqe',17,4),(30,'1969-12-31','qwewqewqe',18,0),(31,'1969-12-31','qweqwe',18,0),(32,'1969-12-31','qwewqe',18,0),(33,'1969-12-31','qwewqeqwe',19,0),(34,'1969-12-31','qweqwew',20,2),(35,'1969-12-31','qwewqewqe',21,2),(36,'1969-12-31','qwqweqwe',21,2),(37,'1969-12-31','qwewqeqw',22,2);

/*Table structure for table `information` */

DROP TABLE IF EXISTS `information`;

CREATE TABLE `information` (
  `INFOID` int(11) NOT NULL AUTO_INCREMENT,
  `ADDRESS` varchar(255) DEFAULT NULL,
  `BIRTHDATE` date DEFAULT NULL,
  `CONTACT` varchar(255) DEFAULT NULL,
  `EMAIL` varchar(255) DEFAULT NULL,
  `FNAME` varchar(255) DEFAULT NULL,
  `GENDER` varchar(255) DEFAULT NULL,
  `LNAME` varchar(255) DEFAULT NULL,
  `MNAME` varchar(255) DEFAULT NULL,
  `ACCOUNT_ACCOUNTID` int(11) DEFAULT NULL,
  PRIMARY KEY (`INFOID`),
  KEY `FK_INFORMATION_ACCOUNT_ACCOUNTID` (`ACCOUNT_ACCOUNTID`),
  CONSTRAINT `FK_INFORMATION_ACCOUNT_ACCOUNTID` FOREIGN KEY (`ACCOUNT_ACCOUNTID`) REFERENCES `accounts` (`ACCOUNTID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

/*Data for the table `information` */

insert  into `information`(`INFOID`,`ADDRESS`,`BIRTHDATE`,`CONTACT`,`EMAIL`,`FNAME`,`GENDER`,`LNAME`,`MNAME`,`ACCOUNT_ACCOUNTID`) values (1,'Talakag','1969-12-31','09265634331','dongskay@gmail.com','Jerome','Male','Pacana','Secret',1),(2,'Upper Carmen','1969-12-31','09265634331','dongskay@gmail.com','Al Lestaire','Male','Acasio','Gilig',4),(3,'Kauswagan','1969-12-31','09265634331','dongskay@gmail.com','Mary Ciolina','Female','Branzuela','Pepe',6),(4,'Butuan','1969-12-31','09265634331','dongskay@gmail.com','John Alfred','Male','Catampo','Ruiz',2),(5,'Kauswagan','1969-12-31','09265634331','dongskay@gmail.com','Super Admin','Male','Super Admin','Super Admin',3),(6,'Bugo','1969-12-31','09265634331','dongskay@gmail.com','April Marie','Female','Bandivas','Dalogdog',5);

/*Table structure for table `municipal` */

DROP TABLE IF EXISTS `municipal`;

CREATE TABLE `municipal` (
  `MUNICIPALID` int(11) NOT NULL AUTO_INCREMENT,
  `HASDISTRICK` tinyint(1) DEFAULT '0',
  `NAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`MUNICIPALID`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=latin1;

/*Data for the table `municipal` */

insert  into `municipal`(`MUNICIPALID`,`HASDISTRICK`,`NAME`) values (1,0,'Zamboanga City'),(2,1,'Cagayan De Oro City'),(3,1,'qwewqe'),(4,0,'qwewqewq'),(5,1,'qweqeqwe'),(6,1,'qweqweqwe'),(7,1,'qwewqe'),(8,0,'qwewqewqs'),(9,0,'qwewqewqsd'),(10,1,'qwewqewqe'),(11,1,'bahhhh'),(12,1,'qweqwewqewqeqw'),(13,1,'example'),(14,1,'example1'),(15,1,'qeqweqweqwe'),(16,1,'qwewqeqwe'),(17,1,'qwewqewqewww'),(18,0,'qwewqeqwewwww'),(19,0,'qwewqeqwevvvv'),(20,1,'qwewqewqewq'),(21,1,'qweqweqweqwe'),(22,1,'qwewqeqwewwwwwwww');

/*Table structure for table `user_barangay` */

DROP TABLE IF EXISTS `user_barangay`;

CREATE TABLE `user_barangay` (
  `USERBARANGAYID` int(11) NOT NULL AUTO_INCREMENT,
  `POSITION` varchar(255) DEFAULT NULL,
  `BARANGAY_BARANGAYID` int(11) DEFAULT NULL,
  `INFORMATION_INFOID` int(11) DEFAULT NULL,
  PRIMARY KEY (`USERBARANGAYID`),
  KEY `FK_USER_BARANGAY_INFORMATION_INFOID` (`INFORMATION_INFOID`),
  KEY `FK_USER_BARANGAY_BARANGAY_BARANGAYID` (`BARANGAY_BARANGAYID`),
  CONSTRAINT `FK_USER_BARANGAY_BARANGAY_BARANGAYID` FOREIGN KEY (`BARANGAY_BARANGAYID`) REFERENCES `barangay` (`BARANGAYID`),
  CONSTRAINT `FK_USER_BARANGAY_INFORMATION_INFOID` FOREIGN KEY (`INFORMATION_INFOID`) REFERENCES `information` (`INFOID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `user_barangay` */

insert  into `user_barangay`(`USERBARANGAYID`,`POSITION`,`BARANGAY_BARANGAYID`,`INFORMATION_INFOID`) values (1,'Encoder',2,3),(2,'Encoder',1,1);

/*Table structure for table `user_municipal` */

DROP TABLE IF EXISTS `user_municipal`;

CREATE TABLE `user_municipal` (
  `USERMUNICIPALID` int(11) NOT NULL AUTO_INCREMENT,
  `EXPERTISE` varchar(255) DEFAULT NULL,
  `POSITION` varchar(255) DEFAULT NULL,
  `INFORMATION_INFOID` int(11) DEFAULT NULL,
  `MUNICIPAL_MUNICIPALID` int(11) DEFAULT NULL,
  PRIMARY KEY (`USERMUNICIPALID`),
  KEY `FK_USER_MUNICIPAL_INFORMATION_INFOID` (`INFORMATION_INFOID`),
  KEY `FK_USER_MUNICIPAL_MUNICIPAL_MUNICIPALID` (`MUNICIPAL_MUNICIPALID`),
  CONSTRAINT `FK_USER_MUNICIPAL_INFORMATION_INFOID` FOREIGN KEY (`INFORMATION_INFOID`) REFERENCES `information` (`INFOID`),
  CONSTRAINT `FK_USER_MUNICIPAL_MUNICIPAL_MUNICIPALID` FOREIGN KEY (`MUNICIPAL_MUNICIPALID`) REFERENCES `municipal` (`MUNICIPALID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `user_municipal` */

insert  into `user_municipal`(`USERMUNICIPALID`,`EXPERTISE`,`POSITION`,`INFORMATION_INFOID`,`MUNICIPAL_MUNICIPALID`) values (1,'Programming','Developer',6,2),(2,'Designer','Web Developer',2,1);

/*Table structure for table `user_voter` */

DROP TABLE IF EXISTS `user_voter`;

CREATE TABLE `user_voter` (
  `USERVOTERID` int(11) NOT NULL AUTO_INCREMENT,
  `ISVOTED` tinyint(1) DEFAULT '0',
  `WORK` varchar(255) DEFAULT NULL,
  `BARANGAY_BARANGAYID` int(11) DEFAULT NULL,
  `INFORMATION_INFOID` int(11) DEFAULT NULL,
  PRIMARY KEY (`USERVOTERID`),
  KEY `FK_USER_VOTER_INFORMATION_INFOID` (`INFORMATION_INFOID`),
  KEY `FK_USER_VOTER_BARANGAY_BARANGAYID` (`BARANGAY_BARANGAYID`),
  CONSTRAINT `FK_USER_VOTER_BARANGAY_BARANGAYID` FOREIGN KEY (`BARANGAY_BARANGAYID`) REFERENCES `barangay` (`BARANGAYID`),
  CONSTRAINT `FK_USER_VOTER_INFORMATION_INFOID` FOREIGN KEY (`INFORMATION_INFOID`) REFERENCES `information` (`INFOID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `user_voter` */

insert  into `user_voter`(`USERVOTERID`,`ISVOTED`,`WORK`,`BARANGAY_BARANGAYID`,`INFORMATION_INFOID`) values (1,0,'Teacher',3,4);

/*Table structure for table `usertype` */

DROP TABLE IF EXISTS `usertype`;

CREATE TABLE `usertype` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `usertype` */

insert  into `usertype`(`ID`,`NAME`) values (1,'Municipal'),(2,'Administrator'),(3,'Barangay'),(4,'Voter');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
