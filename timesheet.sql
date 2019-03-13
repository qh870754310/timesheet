/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 5.6.23 : Database - timesheet
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`timesheet` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;

USE `timesheet`;

/*Table structure for table `kg_data_sources` */

DROP TABLE IF EXISTS `kg_data_sources`;

CREATE TABLE `kg_data_sources` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `VERSION` int(11) DEFAULT NULL,
  `CONNECT_URL` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `DATA_SOURCE_DESCRIPTION` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `DATA_SOURCE_ID` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `DATA_SOURCE_TYPE` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `JDBC_DRIVER` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PASSWORD` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `USERNAME` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `kg_data_sources` */

/*Table structure for table `kg_general_querys` */

DROP TABLE IF EXISTS `kg_general_querys`;

CREATE TABLE `kg_general_querys` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `VERSION` int(11) DEFAULT NULL,
  `CREATE_DATE` date DEFAULT NULL,
  `DESCRIPTION` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `QUERY_NAME` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TABLE_NAME` varchar(255) COLLATE utf8_bin NOT NULL,
  `DATA_SOURCE_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `QUERY_NAME` (`QUERY_NAME`),
  KEY `FKDD73ED4552701E6F` (`DATA_SOURCE_ID`),
  CONSTRAINT `FKDD73ED4552701E6F` FOREIGN KEY (`DATA_SOURCE_ID`) REFERENCES `kg_data_sources` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `kg_general_querys` */

/*Table structure for table `kgv_dynamic_query_conditions` */

DROP TABLE IF EXISTS `kgv_dynamic_query_conditions`;

CREATE TABLE `kgv_dynamic_query_conditions` (
  `GQ_ID` bigint(20) NOT NULL,
  `LABEL` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `WIDGET_TYPE` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `FIELD_NAME` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `FIELD_TYPE` int(11) DEFAULT NULL,
  `QUERY_OPERATION` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `ORDER_COLUMN` int(11) NOT NULL,
  PRIMARY KEY (`GQ_ID`,`ORDER_COLUMN`),
  KEY `FKC2F84F4662DA1F0` (`GQ_ID`),
  CONSTRAINT `FKC2F84F4662DA1F0` FOREIGN KEY (`GQ_ID`) REFERENCES `kg_general_querys` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `kgv_dynamic_query_conditions` */

/*Table structure for table `kgv_field_details` */

DROP TABLE IF EXISTS `kgv_field_details`;

CREATE TABLE `kgv_field_details` (
  `GQ_ID` bigint(20) NOT NULL,
  `FIELD_NAME` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `LABEL` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `ORDER_COLUMN` int(11) NOT NULL,
  PRIMARY KEY (`GQ_ID`,`ORDER_COLUMN`),
  KEY `FKB98B6AB8662DA1F0` (`GQ_ID`),
  CONSTRAINT `FKB98B6AB8662DA1F0` FOREIGN KEY (`GQ_ID`) REFERENCES `kg_general_querys` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `kgv_field_details` */

/*Table structure for table `kgv_pre_query_conditions` */

DROP TABLE IF EXISTS `kgv_pre_query_conditions`;

CREATE TABLE `kgv_pre_query_conditions` (
  `GQ_ID` bigint(20) NOT NULL,
  `END_VALUE` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `START_VALUE` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `VALUE` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `VISIBLE` tinyint(1) DEFAULT NULL,
  `FIELD_NAME` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `FIELD_TYPE` int(11) DEFAULT NULL,
  `QUERY_OPERATION` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `ORDER_COLUMN` int(11) NOT NULL,
  PRIMARY KEY (`GQ_ID`,`ORDER_COLUMN`),
  KEY `FKC237A990662DA1F0` (`GQ_ID`),
  CONSTRAINT `FKC237A990662DA1F0` FOREIGN KEY (`GQ_ID`) REFERENCES `kg_general_querys` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `kgv_pre_query_conditions` */

/*Table structure for table `kl_bussinesslogs` */

DROP TABLE IF EXISTS `kl_bussinesslogs`;

CREATE TABLE `kl_bussinesslogs` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `LOG_CATEGORY` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `LOG_CONTENT` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `VERSION` int(11) DEFAULT NULL,
  `IP` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `RECORD_TIME` datetime DEFAULT NULL,
  `USERNAME` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `kl_bussinesslogs` */

/*Table structure for table `ko_accountabilities` */

DROP TABLE IF EXISTS `ko_accountabilities`;

CREATE TABLE `ko_accountabilities` (
  `CATEGORY` varchar(31) COLLATE utf8_bin NOT NULL,
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `VERSION` int(11) DEFAULT NULL,
  `from_date` date DEFAULT NULL,
  `to_date` date DEFAULT NULL,
  `IS_PRINCIPAL` tinyint(1) DEFAULT NULL,
  `commissioner_id` bigint(20) DEFAULT NULL,
  `responsible_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK779E2936B4258FA9` (`commissioner_id`),
  KEY `FK779E29366D0466CB` (`responsible_id`),
  CONSTRAINT `FK779E29366D0466CB` FOREIGN KEY (`responsible_id`) REFERENCES `ko_parties` (`ID`),
  CONSTRAINT `FK779E2936B4258FA9` FOREIGN KEY (`commissioner_id`) REFERENCES `ko_parties` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `ko_accountabilities` */

insert  into `ko_accountabilities`(`CATEGORY`,`ID`,`VERSION`,`from_date`,`to_date`,`IS_PRINCIPAL`,`commissioner_id`,`responsible_id`) values ('ORGANIZATIONLINEMANAGEMENT',1,0,'2016-03-23','8888-01-01',NULL,NULL,1),('ORGANIZATIONLINEMANAGEMENT',2,0,'2016-03-23','8888-01-01',NULL,1,2),('ORGANIZATIONLINEMANAGEMENT',3,0,'2016-03-23','8888-01-01',NULL,1,3),('ORGANIZATIONLINEMANAGEMENT',4,0,'2016-03-23','8888-01-01',NULL,1,4),('EMPLOYEEPOSTHOLDING',5,0,'2016-03-23','8888-01-01',1,11,7);

/*Table structure for table `ko_parties` */

DROP TABLE IF EXISTS `ko_parties`;

CREATE TABLE `ko_parties` (
  `CATEGORY` varchar(31) COLLATE utf8_bin NOT NULL,
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `VERSION` int(11) DEFAULT NULL,
  `CREATE_DATE` date DEFAULT NULL,
  `NAME` varchar(30) COLLATE utf8_bin NOT NULL,
  `SN` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `TERMINATE_DATE` date DEFAULT NULL,
  `DESCRIPTION` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `ENTRY_DATE` date DEFAULT NULL,
  `ORG_PRINCIPAL` tinyint(1) DEFAULT NULL,
  `PERSON_ID` bigint(20) DEFAULT NULL,
  `JOB_ID` bigint(20) DEFAULT NULL,
  `ORG_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKDABA62094DBE7988` (`ORG_ID`),
  KEY `FKDABA62098D5C4D9` (`PERSON_ID`),
  KEY `FKDABA62099E874DBB` (`JOB_ID`),
  CONSTRAINT `FKDABA62094DBE7988` FOREIGN KEY (`ORG_ID`) REFERENCES `ko_parties` (`ID`),
  CONSTRAINT `FKDABA62098D5C4D9` FOREIGN KEY (`PERSON_ID`) REFERENCES `ko_persons` (`ID`),
  CONSTRAINT `FKDABA62099E874DBB` FOREIGN KEY (`JOB_ID`) REFERENCES `ko_parties` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `ko_parties` */

insert  into `ko_parties`(`CATEGORY`,`ID`,`VERSION`,`CREATE_DATE`,`NAME`,`SN`,`TERMINATE_DATE`,`DESCRIPTION`,`ENTRY_DATE`,`ORG_PRINCIPAL`,`PERSON_ID`,`JOB_ID`,`ORG_ID`) values ('COMPANY',1,1,'2016-03-23','政务信息化事业部','COM-001','8888-01-01','政务信息化事业部',NULL,NULL,NULL,NULL,NULL),('DEPARTMENT',2,1,'2016-03-23','开发中心','COM-0011','8888-01-01','开发中心',NULL,NULL,NULL,NULL,NULL),('DEPARTMENT',3,1,'2016-03-23','项目管理部','COM-0012','8888-01-01','项目管理部',NULL,NULL,NULL,NULL,NULL),('DEPARTMENT',4,1,'2016-03-23','教育项目部','COM-0013','8888-01-01','教育项目部',NULL,NULL,NULL,NULL,NULL),('EMPLOYEE',6,0,'2016-03-23','高华','0001','8888-01-01',NULL,'2016-03-23',NULL,3,NULL,NULL),('EMPLOYEE',7,0,'2016-03-23','黄静','0002','8888-01-01',NULL,'2016-03-23',NULL,4,NULL,NULL),('EMPLOYEE',8,0,'2016-03-23','李琦','0003','8888-01-01',NULL,'2016-03-23',NULL,5,NULL,NULL),('EMPLOYEE',9,0,'2016-03-23','董岱','0004','8888-01-01',NULL,'2016-03-23',NULL,6,NULL,NULL),('JOB',10,1,'2016-03-23','职位1','JOB-0001','8888-01-01','',NULL,NULL,NULL,NULL,NULL),('POST',11,1,'2016-03-23','JOB1','POS-0001','8888-01-01','',NULL,0,NULL,10,3),('EMPLOYEE',12,0,'2016-03-23','周晓晨','0005','8888-01-01',NULL,'2016-03-23',NULL,7,NULL,NULL);

/*Table structure for table `ko_persons` */

DROP TABLE IF EXISTS `ko_persons`;

CREATE TABLE `ko_persons` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `VERSION` int(11) DEFAULT NULL,
  `EMAIL` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `FAMILY_PHONE` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `GENDER` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `ID_NUMBER` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `MOBILE_PHONE` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `NAME` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID_NUMBER` (`ID_NUMBER`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `ko_persons` */

insert  into `ko_persons`(`ID`,`VERSION`,`EMAIL`,`FAMILY_PHONE`,`GENDER`,`ID_NUMBER`,`MOBILE_PHONE`,`NAME`) values (3,0,NULL,NULL,'MALE',NULL,NULL,'高华'),(4,0,NULL,NULL,'FEMALE',NULL,NULL,'黄静'),(5,0,NULL,NULL,'MALE',NULL,NULL,'李琦'),(6,0,NULL,NULL,'FEMALE',NULL,NULL,'董岱'),(7,0,NULL,NULL,'MALE',NULL,NULL,'周晓晨');

/*Table structure for table `ks_actors` */

DROP TABLE IF EXISTS `ks_actors`;

CREATE TABLE `ks_actors` (
  `CATEGORY` varchar(31) COLLATE utf8_bin NOT NULL,
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `VERSION` int(11) DEFAULT NULL,
  `CREATE_DATE` date DEFAULT NULL,
  `CREATE_OWNER` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `DESCRIPTION` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `LAST_MODIFY_TIME` date DEFAULT NULL,
  `NAME` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `DISABLED` tinyint(1) DEFAULT NULL,
  `EMAIL` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `PASSWORD` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `SALT` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `TELE_PHONE` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `USER_ACCOUNT` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `EMPLOYEE_ID` bigint(20) DEFAULT NULL,
  `OPEN_ID` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK324097D5A70F8679` (`EMPLOYEE_ID`),
  CONSTRAINT `FK324097D5A70F8679` FOREIGN KEY (`EMPLOYEE_ID`) REFERENCES `ko_parties` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `ks_actors` */

insert  into `ks_actors`(`CATEGORY`,`ID`,`VERSION`,`CREATE_DATE`,`CREATE_OWNER`,`DESCRIPTION`,`LAST_MODIFY_TIME`,`NAME`,`DISABLED`,`EMAIL`,`PASSWORD`,`SALT`,`TELE_PHONE`,`USER_ACCOUNT`,`EMPLOYEE_ID`,`OPEN_ID`) values ('EMPLOYEE_USER',1,0,'2016-03-23','system','',NULL,'考拉',0,NULL,'0a7c13f659c2153cfbef2aa571a85481','754d584d-02c4-415a-9619-403b53f212f2',NULL,'koala',NULL,NULL),('EMPLOYEE_USER',2,0,'2016-03-23','koala','',NULL,'周晓晨',0,NULL,'67a735b95b38e5e32a159ceca73580be','086f3350-ea5b-42f0-9f71-84d7807ac1ac',NULL,'zhouxc',12,NULL),('EMPLOYEE_USER',4,0,'2016-03-28','koala','',NULL,'黄静',0,NULL,'18cfd5c640bc18b295ca3c4678ec3d0b','dcba1c1b-4fd6-4c3d-99c8-945f1710ee62',NULL,'huangj',7,NULL);

/*Table structure for table `ks_authorities` */

DROP TABLE IF EXISTS `ks_authorities`;

CREATE TABLE `ks_authorities` (
  `CATEGORY` varchar(31) COLLATE utf8_bin NOT NULL,
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `VERSION` int(11) DEFAULT NULL,
  `DESCRIPTION` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `NAME` varchar(255) COLLATE utf8_bin NOT NULL,
  `IDENTIFIER` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `ks_authorities` */

insert  into `ks_authorities`(`CATEGORY`,`ID`,`VERSION`,`DESCRIPTION`,`NAME`,`IDENTIFIER`) values ('ROLE',1,0,'拥有系统所有权限','超级管理员',NULL),('ROLE',2,1,'项目经理角色','项目经理',NULL),('ROLE',3,1,'管理员角色','管理员',NULL),('ROLE',4,0,'普通员工角色','普通员工',NULL),('ROLE',5,0,'部门经理角色','部门经理',NULL),('ROLE',6,0,'事业部经理角色','事业部经理',NULL);

/*Table structure for table `ks_authorizations` */

DROP TABLE IF EXISTS `ks_authorizations`;

CREATE TABLE `ks_authorizations` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `VERSION` int(11) DEFAULT NULL,
  `ACTOR_ID` bigint(20) DEFAULT NULL,
  `AUTHORITY_ID` bigint(20) DEFAULT NULL,
  `SCOPE_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK9A527AD1B3340F61` (`ACTOR_ID`),
  KEY `FK9A527AD1F986BF01` (`SCOPE_ID`),
  KEY `FK9A527AD1349C2921` (`AUTHORITY_ID`),
  CONSTRAINT `FK9A527AD1349C2921` FOREIGN KEY (`AUTHORITY_ID`) REFERENCES `ks_authorities` (`ID`),
  CONSTRAINT `FK9A527AD1B3340F61` FOREIGN KEY (`ACTOR_ID`) REFERENCES `ks_actors` (`ID`),
  CONSTRAINT `FK9A527AD1F986BF01` FOREIGN KEY (`SCOPE_ID`) REFERENCES `ks_scopes` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `ks_authorizations` */

insert  into `ks_authorizations`(`ID`,`VERSION`,`ACTOR_ID`,`AUTHORITY_ID`,`SCOPE_ID`) values (1,0,1,1,NULL);

/*Table structure for table `ks_menu_resource_relation` */

DROP TABLE IF EXISTS `ks_menu_resource_relation`;

CREATE TABLE `ks_menu_resource_relation` (
  `PARENT_ID` bigint(20) DEFAULT NULL,
  `CHILD_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`CHILD_ID`),
  KEY `FKA4E3D4E4C6FAEA04` (`CHILD_ID`),
  KEY `FKA4E3D4E4DF7783B6` (`PARENT_ID`),
  CONSTRAINT `FKA4E3D4E4C6FAEA04` FOREIGN KEY (`CHILD_ID`) REFERENCES `ks_securityresources` (`ID`),
  CONSTRAINT `FKA4E3D4E4DF7783B6` FOREIGN KEY (`PARENT_ID`) REFERENCES `ks_securityresources` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `ks_menu_resource_relation` */

insert  into `ks_menu_resource_relation`(`PARENT_ID`,`CHILD_ID`) values (1,2),(1,3),(1,4),(5,6),(5,7),(5,8),(9,10),(9,11),(9,12),(9,13),(14,15),(14,16),(186,187),(186,188),(186,189);

/*Table structure for table `ks_resourceassignments` */

DROP TABLE IF EXISTS `ks_resourceassignments`;

CREATE TABLE `ks_resourceassignments` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `VERSION` int(11) DEFAULT NULL,
  `AUTHORITY_ID` bigint(20) DEFAULT NULL,
  `SECURITYRESOURCE_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKA97991013044FAF3` (`SECURITYRESOURCE_ID`),
  KEY `FKA9799101349C2921` (`AUTHORITY_ID`),
  CONSTRAINT `FKA97991013044FAF3` FOREIGN KEY (`SECURITYRESOURCE_ID`) REFERENCES `ks_securityresources` (`ID`),
  CONSTRAINT `FKA9799101349C2921` FOREIGN KEY (`AUTHORITY_ID`) REFERENCES `ks_authorities` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=157 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `ks_resourceassignments` */

insert  into `ks_resourceassignments`(`ID`,`VERSION`,`AUTHORITY_ID`,`SECURITYRESOURCE_ID`) values (1,0,1,1),(2,0,1,2),(3,0,1,3),(4,0,1,5),(5,0,1,6),(6,0,1,7),(7,0,1,8),(8,0,1,9),(9,0,1,10),(10,0,1,11),(11,0,1,12),(12,0,1,13),(13,0,1,14),(14,0,1,15),(15,0,1,16),(16,0,1,17),(17,0,1,18),(18,0,1,19),(19,0,1,20),(20,0,1,22),(21,0,1,23),(22,0,1,24),(23,0,1,25),(24,0,1,26),(25,0,1,27),(26,0,1,28),(27,0,1,29),(28,0,1,30),(29,0,1,31),(30,0,1,33),(31,0,1,38),(32,0,1,39),(33,0,1,40),(34,0,1,42),(35,0,1,43),(36,0,1,44),(37,0,1,46),(38,0,1,47),(39,0,1,48),(40,0,1,49),(41,0,1,51),(42,0,1,52),(43,0,1,53),(44,0,1,54),(45,0,1,55),(46,0,1,56),(47,0,1,57),(48,0,1,58),(49,0,1,59),(50,0,1,60),(51,0,1,61),(52,0,1,62),(53,0,1,63),(54,0,1,64),(55,0,1,65),(56,0,1,66),(57,0,1,67),(58,0,1,68),(59,0,1,69),(60,0,1,70),(61,0,1,71),(62,0,1,72),(63,0,1,73),(64,0,1,74),(65,0,1,75),(66,0,1,76),(67,0,1,77),(68,0,1,78),(69,0,1,83),(70,0,1,84),(71,0,1,85),(72,0,1,86),(73,0,1,87),(74,0,1,88),(75,0,1,89),(76,0,1,92),(77,0,1,93),(78,0,1,94),(79,0,1,95),(80,0,1,96),(81,0,1,97),(82,0,1,98),(83,0,1,99),(84,0,1,100),(85,0,1,101),(86,0,1,102),(87,0,1,103),(88,0,1,104),(89,0,1,105),(90,0,1,106),(91,0,1,110),(92,0,1,111),(93,0,1,112),(94,0,1,113),(95,0,1,120),(96,0,1,121),(97,0,1,122),(98,0,1,123),(99,0,1,124),(100,0,1,125),(101,0,1,126),(102,0,1,130),(103,0,1,131),(104,0,1,132),(105,0,1,133),(106,0,1,134),(107,0,1,135),(108,0,1,136),(109,0,1,140),(110,0,1,141),(111,0,1,142),(112,0,1,143),(113,0,1,144),(114,0,1,145),(115,0,1,149),(116,0,1,150),(117,0,1,151),(118,0,1,152),(119,0,1,153),(120,0,1,154),(121,0,1,155),(122,0,1,156),(123,0,1,157),(124,0,1,158),(125,0,1,159),(126,0,1,160),(127,0,1,161),(128,0,1,162),(129,0,1,163),(130,0,1,164),(131,0,1,165),(132,0,1,166),(133,0,1,167),(134,0,1,168),(135,0,1,169),(136,0,1,170),(137,0,1,171),(138,0,1,172),(139,0,1,173),(140,0,1,174),(141,0,1,175),(142,0,1,176),(143,0,1,177),(144,0,1,178),(145,0,1,179),(146,0,1,180),(147,0,1,181),(148,0,1,182),(149,0,1,183),(150,0,1,184),(151,0,1,185),(152,0,1,186),(153,0,1,187),(154,0,1,4),(155,0,1,188),(156,0,1,189);

/*Table structure for table `ks_role_permission_map` */

DROP TABLE IF EXISTS `ks_role_permission_map`;

CREATE TABLE `ks_role_permission_map` (
  `ROLE_ID` bigint(20) NOT NULL,
  `PERMISSION_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`ROLE_ID`,`PERMISSION_ID`),
  KEY `FK3CF7985EE770E353` (`ROLE_ID`),
  KEY `FK3CF7985E9FB411F3` (`PERMISSION_ID`),
  CONSTRAINT `FK3CF7985E9FB411F3` FOREIGN KEY (`PERMISSION_ID`) REFERENCES `ks_authorities` (`ID`),
  CONSTRAINT `FK3CF7985EE770E353` FOREIGN KEY (`ROLE_ID`) REFERENCES `ks_authorities` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `ks_role_permission_map` */

/*Table structure for table `ks_scopes` */

DROP TABLE IF EXISTS `ks_scopes`;

CREATE TABLE `ks_scopes` (
  `CATEGORY` varchar(31) COLLATE utf8_bin NOT NULL,
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `VERSION` int(11) DEFAULT NULL,
  `NAME` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `ORGANIZATION_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK50F58F56D8EE64D9` (`ORGANIZATION_ID`),
  CONSTRAINT `FK50F58F56D8EE64D9` FOREIGN KEY (`ORGANIZATION_ID`) REFERENCES `ko_parties` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `ks_scopes` */

/*Table structure for table `ks_securityresources` */

DROP TABLE IF EXISTS `ks_securityresources`;

CREATE TABLE `ks_securityresources` (
  `CATEGORY` varchar(31) COLLATE utf8_bin NOT NULL,
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `VERSION` int(11) DEFAULT NULL,
  `DESCRIPTION` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `NAME` varchar(255) COLLATE utf8_bin NOT NULL,
  `LEVEL` int(11) DEFAULT NULL,
  `MENU_ICON` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `POSITION` int(11) DEFAULT NULL,
  `URL` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `IDENTIFIER` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=190 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `ks_securityresources` */

insert  into `ks_securityresources`(`CATEGORY`,`ID`,`VERSION`,`DESCRIPTION`,`NAME`,`LEVEL`,`MENU_ICON`,`POSITION`,`URL`,`IDENTIFIER`) values ('MENU_RESOURCE',1,0,'包含用户、角色管理等','用户角色管理 ',0,'glyphicon  glyphicon-list-alt',0,NULL,NULL),('MENU_RESOURCE',2,1,NULL,'用户管理',1,'glyphicon  glyphicon-list-alt',0,'/pages/auth/user-list.jsp',NULL),('MENU_RESOURCE',3,1,NULL,'角色管理',1,'glyphicon  glyphicon-list-alt',0,'/pages/auth/role-list.jsp',NULL),('MENU_RESOURCE',4,1,NULL,'权限管理',1,'glyphicon  glyphicon-list-alt',0,'/pages/auth/permission-list.jsp',NULL),('MENU_RESOURCE',5,0,'包含菜单、URL、页面元素管理等','资源管理',0,'glyphicon  glyphicon-list-alt',0,NULL,NULL),('MENU_RESOURCE',6,1,NULL,'菜单管理',1,'glyphicon  glyphicon-list-alt',0,'/pages/auth/menu-list.jsp',NULL),('MENU_RESOURCE',7,1,NULL,'URL访问管理',1,'glyphicon  glyphicon-list-alt',0,'/pages/auth/url-list.jsp',NULL),('MENU_RESOURCE',8,1,NULL,'页面元素管理',1,'glyphicon  glyphicon-list-alt',0,'/pages/auth/page-list.jsp',NULL),('MENU_RESOURCE',9,0,'机构管理、职务管理、岗位管理、人员管理','组织机构管理',0,'glyphicon  glyphicon-list-alt',0,NULL,NULL),('MENU_RESOURCE',10,1,NULL,'机构管理',1,'glyphicon  glyphicon-list-alt',0,'/pages/organisation/department-list.jsp',NULL),('MENU_RESOURCE',11,1,NULL,'职务管理',1,'glyphicon  glyphicon-list-alt',0,'/pages/organisation/job-list.jsp',NULL),('MENU_RESOURCE',12,1,NULL,'岗位管理',1,'glyphicon  glyphicon-list-alt',0,'/pages/organisation/position-list.jsp',NULL),('MENU_RESOURCE',13,1,NULL,'人员管理',1,'glyphicon  glyphicon-list-alt',0,'/pages/organisation/employee-list.jsp',NULL),('MENU_RESOURCE',14,0,'通用查询配置、数据源查询配置','通用查询',0,'glyphicon  glyphicon-list-alt',0,NULL,NULL),('MENU_RESOURCE',15,1,NULL,'通用查询配置',1,'glyphicon  glyphicon-list-alt',0,'/pages/gqc/generalQueryList.jsp',NULL),('MENU_RESOURCE',16,1,NULL,'数据源查询配置',1,'glyphicon  glyphicon-list-alt',0,'/pages/gqc/dataSourceList.jsp',NULL),('PAGE_ELEMENT_RESOURCE',17,0,NULL,'用户管理-添加',NULL,NULL,NULL,NULL,'userManagerAdd'),('PAGE_ELEMENT_RESOURCE',18,0,NULL,'用户管理-修改',NULL,NULL,NULL,NULL,'userManagerUpdate'),('PAGE_ELEMENT_RESOURCE',19,0,NULL,'用户管理-撤销',NULL,NULL,NULL,NULL,'userManagerTerminate'),('PAGE_ELEMENT_RESOURCE',20,0,NULL,'用户管理-分配角色',NULL,NULL,NULL,NULL,'userManagerGrantRole'),('PAGE_ELEMENT_RESOURCE',21,0,NULL,'用户管理-分配权限',NULL,NULL,NULL,NULL,'userManagerGrantPermission'),('PAGE_ELEMENT_RESOURCE',22,0,NULL,'用户管理-重置密码',NULL,NULL,NULL,NULL,'userManagerResetPassword'),('PAGE_ELEMENT_RESOURCE',23,0,NULL,'用户管理-挂起',NULL,NULL,NULL,NULL,'userManagerSuspend'),('PAGE_ELEMENT_RESOURCE',24,0,NULL,'用户管理-激活',NULL,NULL,NULL,NULL,'userManagerActivate'),('PAGE_ELEMENT_RESOURCE',25,0,NULL,'用户管理-高级查询',NULL,NULL,NULL,NULL,'userManagerQuery'),('PAGE_ELEMENT_RESOURCE',26,0,NULL,'角色管理-添加',NULL,NULL,NULL,NULL,'roleManagerAdd'),('PAGE_ELEMENT_RESOURCE',27,0,NULL,'角色管理-修改',NULL,NULL,NULL,NULL,'roleManagerUpdate'),('PAGE_ELEMENT_RESOURCE',28,0,NULL,'角色管理-撤销',NULL,NULL,NULL,NULL,'roleManagerTerminate'),('PAGE_ELEMENT_RESOURCE',29,0,NULL,'角色管理-分配路径访问权限资源',NULL,NULL,NULL,NULL,'roleManagerGrantUrlAccessResource'),('PAGE_ELEMENT_RESOURCE',30,0,NULL,'角色管理-分配菜单权限资源',NULL,NULL,NULL,NULL,'roleManagerGrantMenuResource'),('PAGE_ELEMENT_RESOURCE',31,0,NULL,'角色管理-分配页面元素权限资源',NULL,NULL,NULL,NULL,'roleManagerGrantPageElementResource'),('PAGE_ELEMENT_RESOURCE',32,0,NULL,'角色管理-分配权限',NULL,NULL,NULL,NULL,'roleManagerGrantPermission'),('PAGE_ELEMENT_RESOURCE',33,0,NULL,'角色管理-高级查询',NULL,NULL,NULL,NULL,'roleManagerQuery'),('PAGE_ELEMENT_RESOURCE',34,0,NULL,'权限管理-添加',NULL,NULL,NULL,NULL,'permissionManagerAdd'),('PAGE_ELEMENT_RESOURCE',35,0,NULL,'权限管理-修改',NULL,NULL,NULL,NULL,'permissionManagerUpdate'),('PAGE_ELEMENT_RESOURCE',36,0,NULL,'权限管理-撤销',NULL,NULL,NULL,NULL,'permissionManagerTerminate'),('PAGE_ELEMENT_RESOURCE',37,0,NULL,'权限管理-高级查询',NULL,NULL,NULL,NULL,'permissionManagerQuery'),('PAGE_ELEMENT_RESOURCE',38,0,NULL,'菜单资源管理-添加',NULL,NULL,NULL,NULL,'menuResourceManagerAdd'),('PAGE_ELEMENT_RESOURCE',39,0,NULL,'菜单资源管理-修改',NULL,NULL,NULL,NULL,'menuResourceManagerUpdate'),('PAGE_ELEMENT_RESOURCE',40,0,NULL,'菜单资源管理-撤销',NULL,NULL,NULL,NULL,'menuResourceManagerTerminate'),('PAGE_ELEMENT_RESOURCE',41,0,NULL,'菜单资源管理-授权权限',NULL,NULL,NULL,NULL,'menuResourceManagerGrantPermission'),('PAGE_ELEMENT_RESOURCE',42,0,NULL,'路径访问资源管理-添加',NULL,NULL,NULL,NULL,'urlAccessResourceManagerAdd'),('PAGE_ELEMENT_RESOURCE',43,0,NULL,'路径访问资源管理-修改',NULL,NULL,NULL,NULL,'urlAccessResourceManagerUpdate'),('PAGE_ELEMENT_RESOURCE',44,0,NULL,'路径访问资源管理-撤销',NULL,NULL,NULL,NULL,'urlAccessResourceManagerTerminate'),('PAGE_ELEMENT_RESOURCE',45,0,NULL,'路径访问资源管理-授权权限',NULL,NULL,NULL,NULL,'urlAccessResourceManagerGrantPermission'),('PAGE_ELEMENT_RESOURCE',46,0,NULL,'路径访问资源管理-高级查询',NULL,NULL,NULL,NULL,'urlAccessResourceManagerQuery'),('PAGE_ELEMENT_RESOURCE',47,0,NULL,'页面元素资源管理-添加',NULL,NULL,NULL,NULL,'pageElementResourceManagerAdd'),('PAGE_ELEMENT_RESOURCE',48,0,NULL,'页面元素资源管理-修改',NULL,NULL,NULL,NULL,'pageElementResourceManagerUpdate'),('PAGE_ELEMENT_RESOURCE',49,0,NULL,'页面元素资源管理-撤销',NULL,NULL,NULL,NULL,'pageElementResourceManagerTerminate'),('PAGE_ELEMENT_RESOURCE',50,0,NULL,'页面元素资源管理-授权权限',NULL,NULL,NULL,NULL,'pageElementResourceManagerGrantPermission'),('PAGE_ELEMENT_RESOURCE',51,0,NULL,'页面元素资源管理-高级查询',NULL,NULL,NULL,NULL,'pageElementResourceManagerQuery'),('PAGE_ELEMENT_RESOURCE',52,0,NULL,'机构管理-创建分公司',NULL,NULL,NULL,NULL,'organizationManagerAddCompany'),('PAGE_ELEMENT_RESOURCE',53,0,NULL,'机构管理-创建下级部门',NULL,NULL,NULL,NULL,'organizationManagerAddDepartment'),('PAGE_ELEMENT_RESOURCE',54,0,NULL,'机构管理-员工列表',NULL,NULL,NULL,NULL,'organizationManagerEmployeeListBtn'),('PAGE_ELEMENT_RESOURCE',55,0,NULL,'机构管理-修改部门',NULL,NULL,NULL,NULL,'organizationManagerUpdateDepartment'),('PAGE_ELEMENT_RESOURCE',56,0,NULL,'机构管理-修改公司',NULL,NULL,NULL,NULL,'organizationManagerUpdateCompany'),('PAGE_ELEMENT_RESOURCE',57,0,NULL,'机构管理-撤销',NULL,NULL,NULL,NULL,'organizationManagerDelete'),('PAGE_ELEMENT_RESOURCE',58,0,NULL,'职务管理-创建',NULL,NULL,NULL,NULL,'jobManagerCreate'),('PAGE_ELEMENT_RESOURCE',59,0,NULL,'职务管理-修改',NULL,NULL,NULL,NULL,'jobManagerUpdate'),('PAGE_ELEMENT_RESOURCE',60,0,NULL,'职务管理-撤销',NULL,NULL,NULL,NULL,'jobManagerDelete'),('PAGE_ELEMENT_RESOURCE',61,0,NULL,'职务管理-高级搜索',NULL,NULL,NULL,NULL,'jobManagerQuery'),('PAGE_ELEMENT_RESOURCE',62,0,NULL,'岗位管理-创建',NULL,NULL,NULL,NULL,'postManagerCreate'),('PAGE_ELEMENT_RESOURCE',63,0,NULL,'岗位管理-修改',NULL,NULL,NULL,NULL,'postManagerUpdate'),('PAGE_ELEMENT_RESOURCE',64,0,NULL,'岗位管理-撤销',NULL,NULL,NULL,NULL,'postManagerDelete'),('PAGE_ELEMENT_RESOURCE',65,0,NULL,'岗位管理-高级搜索',NULL,NULL,NULL,NULL,'postManagerQuery'),('PAGE_ELEMENT_RESOURCE',66,0,NULL,'人员管理-创建',NULL,NULL,NULL,NULL,'employeeManagerCreate'),('PAGE_ELEMENT_RESOURCE',67,0,NULL,'人员管理-修改',NULL,NULL,NULL,NULL,'employeeManagerUpdate'),('PAGE_ELEMENT_RESOURCE',68,0,NULL,'人员管理-撤销',NULL,NULL,NULL,NULL,'employeeManagerDelete'),('PAGE_ELEMENT_RESOURCE',69,0,NULL,'人员管理-高级搜索',NULL,NULL,NULL,NULL,'employeeManagerQuery'),('URL_ACCESS_RESOURCE',70,0,NULL,'用户管理-所有',NULL,NULL,NULL,'/auth/user/**',NULL),('URL_ACCESS_RESOURCE',71,0,NULL,'用户管理-添加',NULL,NULL,NULL,'/auth/employeeUser/add.koala',NULL),('URL_ACCESS_RESOURCE',72,0,NULL,'用户管理-更新',NULL,NULL,NULL,'/auth/employeeUser/update.koala',NULL),('URL_ACCESS_RESOURCE',73,0,NULL,'用户管理-撤销',NULL,NULL,NULL,'/auth/user/terminate.koala',NULL),('URL_ACCESS_RESOURCE',74,0,NULL,'用户管理-分页查询',NULL,NULL,NULL,'/auth/employeeUser/pagingQuery.koala',NULL),('URL_ACCESS_RESOURCE',75,0,NULL,'用户管理-更新密码',NULL,NULL,NULL,'/auth/user/updatePassword.koala',NULL),('URL_ACCESS_RESOURCE',76,0,NULL,'用户管理-重置密码',NULL,NULL,NULL,'/auth/user/resetPassword.koala',NULL),('URL_ACCESS_RESOURCE',77,0,NULL,'用户管理-授权多个角色',NULL,NULL,NULL,'/auth/user/grantRoles.koala',NULL),('URL_ACCESS_RESOURCE',78,0,NULL,'用户管理-授权一个角色',NULL,NULL,NULL,'/auth/user/grantRole.koala',NULL),('URL_ACCESS_RESOURCE',79,0,NULL,'用户管理-授权多个权限',NULL,NULL,NULL,'/auth/user/grantPermissions.koala',NULL),('URL_ACCESS_RESOURCE',80,0,NULL,'用户管理-授权一个权限',NULL,NULL,NULL,'/auth/user/grantPermission.koala',NULL),('URL_ACCESS_RESOURCE',81,0,NULL,'用户管理-撤销多个权限',NULL,NULL,NULL,'/auth/user/terminatePermissionsByUser.koala',NULL),('URL_ACCESS_RESOURCE',82,0,NULL,'用户管理-撤销一个权限',NULL,NULL,NULL,'/auth/user/terminatePermissionByUser.koala',NULL),('URL_ACCESS_RESOURCE',83,0,NULL,'用户管理-撤销多个角色',NULL,NULL,NULL,'/auth/user/suspend/terminateRolesByUser.koala',NULL),('URL_ACCESS_RESOURCE',84,0,NULL,'用户管理-撤销一个角色',NULL,NULL,NULL,'/auth/user/terminateRoleByUser.koala',NULL),('URL_ACCESS_RESOURCE',85,0,NULL,'用户管理-激活',NULL,NULL,NULL,'/auth/user/activate.koala',NULL),('URL_ACCESS_RESOURCE',86,0,NULL,'用户管理-激动所有',NULL,NULL,NULL,'/auth/user/activates.koala',NULL),('URL_ACCESS_RESOURCE',87,0,NULL,'用户管理-挂起所有',NULL,NULL,NULL,'/auth/user/suspends.koala',NULL),('URL_ACCESS_RESOURCE',88,0,NULL,'用户管理-查找授权的角色',NULL,NULL,NULL,'/auth/employeeUser/pagingQueryGrantRoleByUserId.koala',NULL),('URL_ACCESS_RESOURCE',89,0,NULL,'用户管理-查找没有授权的角色',NULL,NULL,NULL,'/auth/user/pagingQueryNotGrantRoles.koala',NULL),('URL_ACCESS_RESOURCE',90,0,NULL,'用户管理-查找授权的权限',NULL,NULL,NULL,'/auth/employeeUser/pagingQueryGrantPermissionByUserId.koala',NULL),('URL_ACCESS_RESOURCE',91,0,NULL,'用户管理-查找没有授权的权限',NULL,NULL,NULL,'/auth/user/pagingQueryNotGrantPermissions.koala',NULL),('URL_ACCESS_RESOURCE',92,0,NULL,'用户管理-查找用户详细',NULL,NULL,NULL,'/auth/user/findInfoOfUser.koala',NULL),('URL_ACCESS_RESOURCE',93,0,NULL,'用户管理-在某个范围下分配授权',NULL,NULL,NULL,'/auth/employeeUser/grantAuthorityToActorInScope.koala',NULL),('URL_ACCESS_RESOURCE',94,0,NULL,'用户管理-在某个范围下撤销角色',NULL,NULL,NULL,'/auth/employeeUser/terminateUserFromRoleInScope.koala',NULL),('URL_ACCESS_RESOURCE',95,0,NULL,'用户管理-在某个范围下撤销权限',NULL,NULL,NULL,'/auth/employeeUser/terminateUserFromPermissionInScope.koala',NULL),('URL_ACCESS_RESOURCE',96,0,NULL,'角色管理-所有',NULL,NULL,NULL,'/auth/role/**',NULL),('URL_ACCESS_RESOURCE',97,0,NULL,'角色管理-添加',NULL,NULL,NULL,'/auth/role/add.koala',NULL),('URL_ACCESS_RESOURCE',98,0,NULL,'角色管理-修改',NULL,NULL,NULL,'/auth/role/update.koala',NULL),('URL_ACCESS_RESOURCE',99,0,NULL,'角色管理-撤销',NULL,NULL,NULL,'/auth/role/terminate.koala',NULL),('URL_ACCESS_RESOURCE',100,0,NULL,'角色管理-查询',NULL,NULL,NULL,'/auth/role/pagingQuery.koala',NULL),('URL_ACCESS_RESOURCE',101,0,NULL,'角色管理-查询所有带选中的菜单',NULL,NULL,NULL,'/auth/role/findMenuResourceTreeSelectItemByRoleId.koala',NULL),('URL_ACCESS_RESOURCE',102,0,NULL,'角色管理-授权菜单',NULL,NULL,NULL,'/auth/role/grantMenuResourcesToRole.koala',NULL),('URL_ACCESS_RESOURCE',103,0,NULL,'角色管理-授权URL访问权限资源',NULL,NULL,NULL,'/auth/role/grantUrlAccessResourcesToRole.koala',NULL),('URL_ACCESS_RESOURCE',104,0,NULL,'角色管理-撤销URL访问权限资源',NULL,NULL,NULL,'/auth/role/terminateUrlAccessResourcesFromRole.koala',NULL),('URL_ACCESS_RESOURCE',105,0,NULL,'角色管理-查出已经授权的URL访问权限资源',NULL,NULL,NULL,'/auth/role/pagingQueryGrantUrlAccessResourcesByRoleId.koala',NULL),('URL_ACCESS_RESOURCE',106,0,NULL,'角色管理-查出没有授权的URL访问权限资源',NULL,NULL,NULL,'/auth/role/pagingQueryNotGrantUrlAccessResourcesByRoleId.koala',NULL),('URL_ACCESS_RESOURCE',107,0,NULL,'角色管理-撤销权限',NULL,NULL,NULL,'/auth/role/terminatePermissionsFromRole.koala',NULL),('URL_ACCESS_RESOURCE',108,0,NULL,'角色管理-根据角色ID分页查询已经授权的权限',NULL,NULL,NULL,'/auth/role/pagingQueryGrantPermissionsByRoleId.koala',NULL),('URL_ACCESS_RESOURCE',109,0,NULL,'角色管理-根据角色ID分页查询还未授权的权限',NULL,NULL,NULL,'/auth/role/pagingQueryNotGrantPermissionsByRoleId.koala',NULL),('URL_ACCESS_RESOURCE',110,0,NULL,'角色管理-授权页面元素权限资源',NULL,NULL,NULL,'/auth/role/grantPageElementResourcesToRole.koala',NULL),('URL_ACCESS_RESOURCE',111,0,NULL,'角色管理-撤销页面元素权限资源',NULL,NULL,NULL,'/auth/role/terminatePageElementResourcesFromRole.koala',NULL),('URL_ACCESS_RESOURCE',112,0,NULL,'角色管理-根据角色ID分页查询已经授权的页面元素权限资源',NULL,NULL,NULL,'/auth/role/pagingQueryGrantPageElementResourcesByRoleId.koala',NULL),('URL_ACCESS_RESOURCE',113,0,NULL,'角色管理-根据角色ID分页查询还未授权的页面元素权限资源',NULL,NULL,NULL,'/auth/role/pagingQueryNotGrantPageElementResourcesByRoleId.koala',NULL),('URL_ACCESS_RESOURCE',114,0,NULL,'权限管理-所有',NULL,NULL,NULL,'/auth/permission/**',NULL),('URL_ACCESS_RESOURCE',115,0,NULL,'权限管理-添加',NULL,NULL,NULL,'/auth/permission/add.koala',NULL),('URL_ACCESS_RESOURCE',116,0,NULL,'权限管理-修改',NULL,NULL,NULL,'/auth/permission/update.koala',NULL),('URL_ACCESS_RESOURCE',117,0,NULL,'权限管理-撤销',NULL,NULL,NULL,'/auth/permission/terminate.koala',NULL),('URL_ACCESS_RESOURCE',118,0,NULL,'权限管理-查询',NULL,NULL,NULL,'/auth/permission/pagingQuery.koala',NULL),('URL_ACCESS_RESOURCE',119,0,NULL,'权限管理-获取详情',NULL,NULL,NULL,'/auth/permission/findInfoOfPermission.koala',NULL),('URL_ACCESS_RESOURCE',120,0,NULL,'菜单管理-所有',NULL,NULL,NULL,'/auth/menu/**',NULL),('URL_ACCESS_RESOURCE',121,0,NULL,'菜单管理-添加',NULL,NULL,NULL,'/auth/menu/add.koala',NULL),('URL_ACCESS_RESOURCE',122,0,NULL,'菜单管理-添加子菜单',NULL,NULL,NULL,'/auth/menu/addChildToParent.koala',NULL),('URL_ACCESS_RESOURCE',123,0,NULL,'菜单管理-修改',NULL,NULL,NULL,'/auth/menu/update.koala',NULL),('URL_ACCESS_RESOURCE',124,0,NULL,'菜单管理-撤销',NULL,NULL,NULL,'/auth/menu/terminate.koala',NULL),('URL_ACCESS_RESOURCE',125,0,NULL,'菜单管理-查找菜单树',NULL,NULL,NULL,'/auth/menu/findAllMenusTree.koala',NULL),('URL_ACCESS_RESOURCE',126,0,NULL,'菜单管理-授权权限',NULL,NULL,NULL,'/auth/menu/grantPermisssionsToMenuResource.koala',NULL),('URL_ACCESS_RESOURCE',127,0,NULL,'菜单管理-撤销权限',NULL,NULL,NULL,'/auth/menu/terminatePermissionsFromMenuResource.koala',NULL),('URL_ACCESS_RESOURCE',128,0,NULL,'菜单管理-查询已授权的权限',NULL,NULL,NULL,'/auth/menu/pagingQueryGrantPermissionsByMenuResourceId.koala',NULL),('URL_ACCESS_RESOURCE',129,0,NULL,'菜单管理-查询未授权的权限',NULL,NULL,NULL,'/auth/menu/pagingQueryNotGrantPermissionsByMenuResourceId.koala',NULL),('URL_ACCESS_RESOURCE',130,0,NULL,'菜单管理-查询某角色下所有菜单',NULL,NULL,NULL,'/auth/menu/findAllMenusByUserAsRole.koala',NULL),('URL_ACCESS_RESOURCE',131,0,NULL,'URL访问管理-所有',NULL,NULL,NULL,'/auth/url/**',NULL),('URL_ACCESS_RESOURCE',132,0,NULL,'URL访问管理-添加',NULL,NULL,NULL,'/auth/url/add.koala',NULL),('URL_ACCESS_RESOURCE',133,0,NULL,'URL访问管理-更新',NULL,NULL,NULL,'/auth/url/update.koala',NULL),('URL_ACCESS_RESOURCE',134,0,NULL,'URL访问管理-撤销',NULL,NULL,NULL,'/auth/url/terminate.koala',NULL),('URL_ACCESS_RESOURCE',135,0,NULL,'URL访问管理-查询',NULL,NULL,NULL,'/auth/url/pagingQuery.koala',NULL),('URL_ACCESS_RESOURCE',136,0,NULL,'URL访问管理-授权权限',NULL,NULL,NULL,'/auth/url/grantPermisssionsToUrlAccessResource.koala',NULL),('URL_ACCESS_RESOURCE',137,0,NULL,'URL访问管理-撤销权限',NULL,NULL,NULL,'/auth/url/terminatePermissionsFromUrlAccessResource.koala',NULL),('URL_ACCESS_RESOURCE',138,0,NULL,'URL访问管理-查询已授权的权限',NULL,NULL,NULL,'/auth/url/pagingQueryGrantPermissionsByUrlAccessResourceId.koala',NULL),('URL_ACCESS_RESOURCE',139,0,NULL,'URL访问管理-查询未授权的权限',NULL,NULL,NULL,'/auth/url/pagingQueryNotGrantPermissionsByUrlAccessResourceId.koala',NULL),('URL_ACCESS_RESOURCE',140,0,NULL,'页面元素-所有',NULL,NULL,NULL,'/auth/page/**',NULL),('URL_ACCESS_RESOURCE',141,0,NULL,'页面元素-添加',NULL,NULL,NULL,'/auth/page/add.koala',NULL),('URL_ACCESS_RESOURCE',142,0,NULL,'页面元素-修改',NULL,NULL,NULL,'/auth/page/update.koala',NULL),('URL_ACCESS_RESOURCE',143,0,NULL,'页面元素-撤销',NULL,NULL,NULL,'/auth/page/terminate.koala',NULL),('URL_ACCESS_RESOURCE',144,0,NULL,'页面元素-查询',NULL,NULL,NULL,'/auth/page/pagingQuery.koala',NULL),('URL_ACCESS_RESOURCE',145,0,NULL,'页面元素-授予权限',NULL,NULL,NULL,'/auth/page/grantPermisssionsToPageElementResource.koala',NULL),('URL_ACCESS_RESOURCE',146,0,NULL,'页面元素-撤销权限',NULL,NULL,NULL,'/auth/page/terminatePermissionsFromPageElementResource.koala',NULL),('URL_ACCESS_RESOURCE',147,0,NULL,'页面元素-查询已授权的权限',NULL,NULL,NULL,'/auth/page/pagingQueryGrantPermissionsByUrlAccessResourceId.koala',NULL),('URL_ACCESS_RESOURCE',148,0,NULL,'页面元素-查询未授权的权限',NULL,NULL,NULL,'/auth/page/pagingQueryNotGrantPermissionsByPageElementResourceId.koala',NULL),('URL_ACCESS_RESOURCE',149,0,NULL,'组织机构管理-所有',NULL,NULL,NULL,'/organization/**',NULL),('URL_ACCESS_RESOURCE',150,0,NULL,'组织机构管理-在某个公司下创建一个分公司',NULL,NULL,NULL,'/organization/create-company.koala',NULL),('URL_ACCESS_RESOURCE',151,0,NULL,'组织机构管理-在某个机构下创建一个部门',NULL,NULL,NULL,'/organization/create-department.koala',NULL),('URL_ACCESS_RESOURCE',152,0,NULL,'组织机构管理-更新公司信息',NULL,NULL,NULL,'/organization/update-company.koala',NULL),('URL_ACCESS_RESOURCE',153,0,NULL,'组织机构管理-更新部门信息',NULL,NULL,NULL,'/organization/update-department.koala',NULL),('URL_ACCESS_RESOURCE',154,0,NULL,'组织机构管理-获取组织机构树',NULL,NULL,NULL,'/organization/org-tree.koala',NULL),('URL_ACCESS_RESOURCE',155,0,NULL,'组织机构管理-根据ID获得机构',NULL,NULL,NULL,'/organization/get.koala',NULL),('URL_ACCESS_RESOURCE',156,0,NULL,'组织机构管理-撤销某个机构与某些员工的责任关系',NULL,NULL,NULL,'/organization/terminate-eo-relations.koala',NULL),('URL_ACCESS_RESOURCE',157,0,NULL,'组织机构管理-撤销一个公司',NULL,NULL,NULL,'/organization/terminate-company.koala',NULL),('URL_ACCESS_RESOURCE',158,0,NULL,'组织机构管理-撤销一个部门',NULL,NULL,NULL,'/organization/terminate-department.koala',NULL),('URL_ACCESS_RESOURCE',159,0,NULL,'职务管理-所有',NULL,NULL,NULL,'/job/**',NULL),('URL_ACCESS_RESOURCE',160,0,NULL,'职务管理-分页查询职务',NULL,NULL,NULL,'/job/pagingquery.koala',NULL),('URL_ACCESS_RESOURCE',161,0,NULL,'职务管理-查询所有职务',NULL,NULL,NULL,'/job/query-all.koala',NULL),('URL_ACCESS_RESOURCE',162,0,NULL,'职务管理-创建一个职务',NULL,NULL,NULL,'/job/create.koala',NULL),('URL_ACCESS_RESOURCE',163,0,NULL,'职务管理-更新职务信息',NULL,NULL,NULL,'/job/update.koala',NULL),('URL_ACCESS_RESOURCE',164,0,NULL,'职务管理-根据ID获得',NULL,NULL,NULL,'/job/get/{id}.koala',NULL),('URL_ACCESS_RESOURCE',165,0,NULL,'职务管理-撤销某个职务',NULL,NULL,NULL,'/job/terminate.koala',NULL),('URL_ACCESS_RESOURCE',166,0,NULL,'职务管理-撤销多个职务',NULL,NULL,NULL,'/job/terminate-jobs.koala',NULL),('URL_ACCESS_RESOURCE',167,0,NULL,'岗位管理-所有',NULL,NULL,NULL,'/post/**',NULL),('URL_ACCESS_RESOURCE',168,0,NULL,'岗位管理-创建一个岗位',NULL,NULL,NULL,'/post/create.koala',NULL),('URL_ACCESS_RESOURCE',169,0,NULL,'岗位管理-更新岗位信息',NULL,NULL,NULL,'/post/update.koala',NULL),('URL_ACCESS_RESOURCE',170,0,NULL,'岗位管理-根据机构查询岗位',NULL,NULL,NULL,'/post/query-post-by-org.koala',NULL),('URL_ACCESS_RESOURCE',171,0,NULL,'岗位管理-根据机构分页查询岗位',NULL,NULL,NULL,'/post/paging-query-post-by-org.koala',NULL),('URL_ACCESS_RESOURCE',172,0,NULL,'岗位管理-根据ID获得',NULL,NULL,NULL,'/post/get/{id}.koala',NULL),('URL_ACCESS_RESOURCE',173,0,NULL,'岗位管理-撤销某个岗位',NULL,NULL,NULL,'/post/terminate.koala',NULL),('URL_ACCESS_RESOURCE',174,0,NULL,'岗位管理-撤销多个岗位',NULL,NULL,NULL,'/post/terminate-posts.koala',NULL),('URL_ACCESS_RESOURCE',175,0,NULL,'员工管理-所有',NULL,NULL,NULL,'/employee/**',NULL),('URL_ACCESS_RESOURCE',176,0,NULL,'员工管理-分页查询',NULL,NULL,NULL,'/employee/pagingquery.koala',NULL),('URL_ACCESS_RESOURCE',177,0,NULL,'员工管理-分页查询某个机构下的员工',NULL,NULL,NULL,'/employee/pagingquery-by-org.koala',NULL),('URL_ACCESS_RESOURCE',178,0,NULL,'员工管理-调整某个员工的任职信息',NULL,NULL,NULL,'/employee/transform-post.koala',NULL),('URL_ACCESS_RESOURCE',179,0,NULL,'员工管理-创建一个员工',NULL,NULL,NULL,'/employee/create.koala',NULL),('URL_ACCESS_RESOURCE',180,0,NULL,'员工管理-更新某个员工的信息',NULL,NULL,NULL,'/employee/update.koala',NULL),('URL_ACCESS_RESOURCE',181,0,NULL,'员工管理-根据ID获得',NULL,NULL,NULL,'/employee/get/{id}.koala',NULL),('URL_ACCESS_RESOURCE',182,0,NULL,'员工管理-获得性别',NULL,NULL,NULL,'/employee/genders.koala',NULL),('URL_ACCESS_RESOURCE',183,0,NULL,'员工管理-获得某个员工的任职信息',NULL,NULL,NULL,'/employee/get-posts-by-employee.koala',NULL),('URL_ACCESS_RESOURCE',184,0,NULL,'员工管理-解雇员工',NULL,NULL,NULL,'/employee/terminate.koala',NULL),('URL_ACCESS_RESOURCE',185,0,NULL,'员工管理-同时解雇多名员工',NULL,NULL,NULL,'/employee/terminate-employees.koala',NULL),('MENU_RESOURCE',186,0,'','系统设置',0,'glyphicon glyphicon-list-alt',0,'',NULL),('MENU_RESOURCE',187,1,'','工作日设置',1,'glyphicon glyphicon-list-alt',0,'/pages/timesetting/TimeSetting-list.jsp',NULL),('MENU_RESOURCE',188,1,'','数据字典类型',1,'glyphicon glyphicon-list-alt',0,'/pages/sysmgmt/dictionary/Dictionary-list.jsp',NULL),('MENU_RESOURCE',189,1,'','数据字典',1,'glyphicon glyphicon-list-alt',0,'/pages/sysmgmt/dictionary/DictionaryData-list.jsp',NULL);

/*Table structure for table `t_agency` */

DROP TABLE IF EXISTS `t_agency`;

CREATE TABLE `t_agency` (
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  `content` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_user_id` (`user_id`),
  CONSTRAINT `t_agency_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `ks_actors` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `t_agency` */

/*Table structure for table `t_dictionary` */

DROP TABLE IF EXISTS `t_dictionary`;

CREATE TABLE `t_dictionary` (
  `id` bigint(255) NOT NULL AUTO_INCREMENT,
  `create_date` datetime DEFAULT NULL,
  `creator_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `is_available` int(11) NOT NULL,
  `modifier_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `dict_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=843 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `t_dictionary` */

insert  into `t_dictionary`(`id`,`create_date`,`creator_id`,`is_available`,`modifier_id`,`modify_date`,`version`,`dict_name`) values (842,'2016-03-23 16:29:00','koala',1,'koala','2016-03-23 16:29:00',0,'自定义档案');

/*Table structure for table `t_dictionary_data` */

DROP TABLE IF EXISTS `t_dictionary_data`;

CREATE TABLE `t_dictionary_data` (
  `id` bigint(255) NOT NULL AUTO_INCREMENT,
  `create_date` datetime DEFAULT NULL,
  `creator_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `is_available` int(11) NOT NULL,
  `modifier_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `dictdata_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `dictdata_value` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `dict_id` bigint(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2ABB13E83763C65E` (`dict_id`),
  CONSTRAINT `fk_dict` FOREIGN KEY (`dict_id`) REFERENCES `t_dictionary` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `t_dictionary_data` */

insert  into `t_dictionary_data`(`id`,`create_date`,`creator_id`,`is_available`,`modifier_id`,`modify_date`,`version`,`dictdata_name`,`dictdata_value`,`dict_id`) values (1,'2016-03-28 16:08:56','koala',1,'koala','2016-03-28 16:08:56',0,'1','1',842),(2,'2016-03-28 16:31:03','koala',1,'koala','2016-03-28 16:31:03',0,'2','2',842);

/*Table structure for table `t_message` */

DROP TABLE IF EXISTS `t_message`;

CREATE TABLE `t_message` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `from_user_id` bigint(20) DEFAULT NULL,
  `to_user_id` bigint(20) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  `content` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_from_user` (`from_user_id`),
  KEY `FK_to_user` (`to_user_id`),
  CONSTRAINT `FK_from_user` FOREIGN KEY (`from_user_id`) REFERENCES `ks_actors` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_to_user` FOREIGN KEY (`to_user_id`) REFERENCES `ks_actors` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `t_message` */

/*Table structure for table `t_message_templdate` */

DROP TABLE IF EXISTS `t_message_templdate`;

CREATE TABLE `t_message_templdate` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `content` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `t_message_templdate` */

/*Table structure for table `t_project` */

DROP TABLE IF EXISTS `t_project`;

CREATE TABLE `t_project` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) COLLATE utf8_bin NOT NULL,
  `project_type_id` bigint(20) DEFAULT NULL,
  `project_stage_id` bigint(20) DEFAULT NULL,
  `department_id` bigint(20) DEFAULT NULL,
  `pm_user_id` bigint(20) DEFAULT NULL,
  `memo` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_type_project` (`project_type_id`),
  KEY `fk_stage_project` (`project_stage_id`),
  CONSTRAINT `fk_stage_project` FOREIGN KEY (`project_stage_id`) REFERENCES `t_project_stage` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_type_project` FOREIGN KEY (`project_type_id`) REFERENCES `t_project_type` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `t_project` */

/*Table structure for table `t_project_stage` */

DROP TABLE IF EXISTS `t_project_stage`;

CREATE TABLE `t_project_stage` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `project_type_id` bigint(20) DEFAULT NULL,
  `name` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `memo` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_type_stage` (`project_type_id`),
  CONSTRAINT `fk_type_stage` FOREIGN KEY (`project_type_id`) REFERENCES `t_project_type` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `t_project_stage` */

/*Table structure for table `t_project_type` */

DROP TABLE IF EXISTS `t_project_type`;

CREATE TABLE `t_project_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) COLLATE utf8_bin NOT NULL,
  `memo` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='项目类型';

/*Data for the table `t_project_type` */

/*Table structure for table `t_project_user` */

DROP TABLE IF EXISTS `t_project_user`;

CREATE TABLE `t_project_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `project_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user` (`user_id`),
  KEY `fk_project` (`project_id`),
  CONSTRAINT `fk_project` FOREIGN KEY (`project_id`) REFERENCES `t_project` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_user` FOREIGN KEY (`user_id`) REFERENCES `ko_persons` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `t_project_user` */

/*Table structure for table `t_template` */

DROP TABLE IF EXISTS `t_template`;

CREATE TABLE `t_template` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user_tepmlate` (`user_id`),
  CONSTRAINT `fk_user_tepmlate` FOREIGN KEY (`user_id`) REFERENCES `ko_persons` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `t_template` */

/*Table structure for table `t_template_detail` */

DROP TABLE IF EXISTS `t_template_detail`;

CREATE TABLE `t_template_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `template_id` bigint(20) DEFAULT NULL,
  `project_id` bigint(20) DEFAULT NULL,
  `project_stage_id` bigint(20) DEFAULT NULL,
  `task_desc` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_template` (`template_id`),
  KEY `fk_project_template` (`project_id`),
  KEY `fk_stage_template` (`project_stage_id`),
  CONSTRAINT `fk_project_template` FOREIGN KEY (`project_id`) REFERENCES `t_project` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_stage_template` FOREIGN KEY (`project_stage_id`) REFERENCES `t_project_stage` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_template` FOREIGN KEY (`template_id`) REFERENCES `t_template` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `t_template_detail` */

/*Table structure for table `t_time_setting` */

DROP TABLE IF EXISTS `t_time_setting`;

CREATE TABLE `t_time_setting` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` date DEFAULT NULL,
  `day_type` int(11) DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `creator_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `is_available` int(11) NOT NULL,
  `modifier_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  `content` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `t_time_setting` */

insert  into `t_time_setting`(`id`,`date`,`day_type`,`start_time`,`end_time`,`create_date`,`creator_id`,`is_available`,`modifier_id`,`modify_date`,`version`,`content`) values (1,'2016-03-14',1,'2016-03-14 09:00:00','2016-03-14 17:00:00','2016-03-28 16:09:08','koala',1,'koala','2016-03-28 16:09:08',0,'');

/*Table structure for table `t_timesheet` */

DROP TABLE IF EXISTS `t_timesheet`;

CREATE TABLE `t_timesheet` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user_timesheet` (`user_id`),
  CONSTRAINT `fk_user_timesheet` FOREIGN KEY (`user_id`) REFERENCES `ko_persons` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `t_timesheet` */

/*Table structure for table `t_timesheet_detail` */

DROP TABLE IF EXISTS `t_timesheet_detail`;

CREATE TABLE `t_timesheet_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `timesheet_id` bigint(20) DEFAULT NULL,
  `project_id` bigint(20) DEFAULT NULL,
  `project_stage_id` bigint(20) DEFAULT NULL,
  `task_desc` varchar(500) COLLATE utf8_bin DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_detail_timesheet` (`timesheet_id`),
  KEY `fk_detail_project` (`project_id`),
  KEY `fk_detail_stage` (`project_stage_id`),
  CONSTRAINT `fk_detail_project` FOREIGN KEY (`project_id`) REFERENCES `t_project` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_detail_stage` FOREIGN KEY (`project_stage_id`) REFERENCES `t_project_stage` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_detail_timesheet` FOREIGN KEY (`timesheet_id`) REFERENCES `t_timesheet` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `t_timesheet_detail` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
