/*
SQLyog Ultimate v11.33 (64 bit)
MySQL - 5.5.8-log : Database - placementcell
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`placementcell` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `placementcell`;

/*Table structure for table `event_notification` */

DROP TABLE IF EXISTS `event_notification`;

CREATE TABLE `event_notification` (
  `ev_id` int(20) NOT NULL AUTO_INCREMENT,
  `officer_id` varchar(100) NOT NULL,
  `ev_subject` varchar(100) NOT NULL,
  `ev_description` varchar(1000) NOT NULL,
  `ev_date` varchar(100) NOT NULL,
  `added_date` varchar(100) NOT NULL,
  `time` varchar(100) NOT NULL,
  PRIMARY KEY (`ev_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;

/*Data for the table `event_notification` */

insert  into `event_notification`(`ev_id`,`officer_id`,`ev_subject`,`ev_description`,`ev_date`,`added_date`,`time`) values (16,'3','rty','tgg','16-05-2023','2023-05-16','15:36');

/*Table structure for table `exam_result` */

DROP TABLE IF EXISTS `exam_result`;

CREATE TABLE `exam_result` (
  `result_id` int(11) NOT NULL AUTO_INCREMENT,
  `job_id` varchar(100) NOT NULL,
  `student_id` varchar(100) NOT NULL,
  `aptitude` varchar(100) NOT NULL,
  `numerical` varchar(100) NOT NULL,
  `technical` varchar(100) NOT NULL,
  `coding` varchar(100) NOT NULL,
  `total` varchar(100) NOT NULL,
  `date` varchar(100) NOT NULL,
  PRIMARY KEY (`result_id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=latin1;

/*Data for the table `exam_result` */

insert  into `exam_result`(`result_id`,`job_id`,`student_id`,`aptitude`,`numerical`,`technical`,`coding`,`total`,`date`) values (20,'21','12','4','4','8','2','18','2023-05-08'),(21,'21','12','4','6','7','3','20','2023-05-08'),(22,'18','10','2','4','9','2','17','2023-05-08'),(23,'17','9','4','3','8','2','17','2023-05-12'),(24,'17','9','4','6','8','2','20','2023-05-12'),(25,'17','9','2','3','3','2','10','2023-05-16');

/*Table structure for table `login` */

DROP TABLE IF EXISTS `login`;

CREATE TABLE `login` (
  `l_id` int(20) NOT NULL AUTO_INCREMENT,
  `reg_id` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `type` varchar(100) DEFAULT NULL,
  `status` varchar(100) DEFAULT '0',
  PRIMARY KEY (`l_id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=latin1;

/*Data for the table `login` */

insert  into `login`(`l_id`,`reg_id`,`email`,`password`,`type`,`status`) values (0,'0','admin@gmail.com','admin','ADMIN','1'),(43,'3','manu@gmail.com','123','OFFICER','1'),(44,'9','meenu@gmail.com','123','STUDCOMMON','1'),(45,'10','gary@gmail.com','123','STUDCOMMON','1'),(46,'11','vineeth@gmail.com','123','STUDEXTERNAL','1'),(47,'12','vishnu@gmail.com','123','STUDCOMMON','1'),(48,'13','g@gmail.com','133','STUDEXTERNAL','1'),(49,'14','ranju@stc.com','ilynihal','STUDCOMMON','1'),(50,'4','teena@stc.com','tinu','OFFICER','1');

/*Table structure for table `officer` */

DROP TABLE IF EXISTS `officer`;

CREATE TABLE `officer` (
  `officerId` int(11) NOT NULL AUTO_INCREMENT,
  `officer_name` varchar(100) DEFAULT NULL,
  `officer_email` varchar(100) DEFAULT NULL,
  `officer_phone` varchar(100) DEFAULT NULL,
  `officer_quali` varchar(100) DEFAULT NULL,
  `college` varchar(100) DEFAULT NULL,
  `officer_address` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`officerId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `officer` */

insert  into `officer`(`officerId`,`officer_name`,`officer_email`,`officer_phone`,`officer_quali`,`college`,`officer_address`) values (3,'Manu','manu@gmail.com','9865328184','MEd','STC College','Near Menaka'),(4,'Teena Anna','teena@stc.com','9544240482','B Tech','St.Thomas College of Engineering ','Teena villa\nPTA');

/*Table structure for table `placement` */

DROP TABLE IF EXISTS `placement`;

CREATE TABLE `placement` (
  `pl_id` int(11) NOT NULL AUTO_INCREMENT,
  `officer_id` int(11) NOT NULL,
  `job_title` varchar(100) NOT NULL,
  `job_location` varchar(100) NOT NULL,
  `comp_name` varchar(100) NOT NULL,
  `comp_email` varchar(100) NOT NULL,
  `job_type` varchar(100) NOT NULL,
  `vacancies` varchar(100) NOT NULL,
  `job_desc` varchar(500) NOT NULL,
  `placement_date` varchar(100) NOT NULL,
  `min_qualification` varchar(100) NOT NULL,
  `college` varchar(100) NOT NULL,
  `plustwo` varchar(100) NOT NULL,
  `tenth` varchar(100) NOT NULL,
  `posted_date` varchar(100) NOT NULL,
  `backlogs` varchar(100) NOT NULL,
  `url` varchar(100) NOT NULL,
  `status` varchar(100) NOT NULL DEFAULT 'POSTED',
  PRIMARY KEY (`pl_id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=latin1;

/*Data for the table `placement` */

insert  into `placement`(`pl_id`,`officer_id`,`job_title`,`job_location`,`comp_name`,`comp_email`,`job_type`,`vacancies`,`job_desc`,`placement_date`,`min_qualification`,`college`,`plustwo`,`tenth`,`posted_date`,`backlogs`,`url`,`status`) values (17,3,'Android Developer','Noida','Wipro','helpcarewipro@gmail.com','Full Time','70','A job description is a useful, plain-language tool that explains the tasks, duties, function and res','10-05-2023','MCA','85','80','98','2023-04-29','2','https://careers.wipro.com/careers-home/','POSTED'),(18,3,'Python Developer','Gugaon','Tata Consultancy Services','tatacare@gmail.com','Full Time','45','A Python Developer is responsible for coding, designing, deploying, and debugging development projec','18-05-2023','BCA','90','90','90','2023-05-03','1','https://www.tcs.com/careers/india/internship','POSTED'),(21,3,'SQL Developer','Trivandrum','Claysys','claysys@gmail.com','Full Time','2','The role of an SQL developer is to develop and manage SQL databases by planning, developing, and mai','20-05-2023','BCA','70','70','70','2023-05-03','0','https://www.claysys.com/careers/','POSTED'),(22,4,'Software Engineer','Banglore','Impaqtive','impaqtive123@gmail.com','Full Time','5','We are looking for highly skilled software Engineer who have expertise in python and networking with an opportunity to work with an international team','17-05-2023','B.Tech','80','70','80','2023-05-16','2','impaqtive.com','POSTED'),(23,3,'Business Analyst','Manglore','UST Global','ustglobal@gmail.com','Full Time','5','Looking for highly qualified business Analyst who have expertise in python and are with an oppertunity to work with our team','17-05-2023','B.Tech','80','70','80','2023-05-16','2','ustglobal.com','POSTED'),(24,4,'test','tes','ye','test@gmail.com','Full Time','25','ggg','16-05-2023','B.Tech','25','25','25','2023-05-16','0','test.com','POSTED'),(25,4,'test','teet','test','test@gmail.com','Full Time','25','test','16-05-2023','B.Tech','58','58','58','2023-05-16','0','test.com','POSTED');

/*Table structure for table `student` */

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `s_id` int(20) NOT NULL AUTO_INCREMENT,
  `s_name` varchar(100) NOT NULL,
  `s_phone` varchar(100) NOT NULL,
  `s_email` varchar(100) NOT NULL,
  `s_gender` varchar(100) NOT NULL,
  `dob` varchar(100) NOT NULL,
  `s_department` varchar(100) NOT NULL,
  `s_address` varchar(300) NOT NULL,
  `college_name` varchar(100) NOT NULL,
  `degree` varchar(100) DEFAULT 'NULL',
  `plustwo` varchar(100) NOT NULL,
  `tenth` varchar(100) NOT NULL,
  `type` varchar(100) NOT NULL,
  `backlogs` varchar(100) DEFAULT '0',
  PRIMARY KEY (`s_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

/*Data for the table `student` */

insert  into `student`(`s_id`,`s_name`,`s_phone`,`s_email`,`s_gender`,`dob`,`s_department`,`s_address`,`college_name`,`degree`,`plustwo`,`tenth`,`type`,`backlogs`) values (9,'Meenu','9865328484','meenu@gmail.com','Female','27-02-1995','MCA','Kaloor','STC','88','80','98','COMMON','0'),(10,'gary','9845346946','gary@gmail.com','Male','12/18/2000','BCA','peruvanthanam','St Antonys college','80','90','85','COMMON','1'),(11,'Vineeth','9747691771','vineeth@gmail.com','Male','08-05-2002','M.Com','Kaloor','MBITS','89','86','100','OUTSIDE','1'),(12,'Vishnu','9865321584','vishnu@gmail.com','Male','08-05-1998','BCA','kaloor','KITS','90','90','90','COMMON','2'),(13,'Govind','9747691772','g@gmail.com','Male','12-05-1962','MCA','Kochi','St Thomas Konni','96','98','100','OUTSIDE','0'),(14,'Ranju Jacob','6235089518','ranju@stc.com','Male','03-03-2001','B.Tech','Ranju villa\nedathua','St.Thomas College of Engineering','88','78','99','COMMON','0');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
