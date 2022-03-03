CREATE DATABASE `readonly-demo`;
USE `readonly-demo`;

DROP TABLE IF EXISTS `engagement`;
CREATE TABLE `engagement` (
  `id` int NOT NULL AUTO_INCREMENT,
  `participant_id` int DEFAULT NULL,
  `engagement_type_id` int DEFAULT NULL,
  `engagement_outcome_id` int NOT NULL,
  `engagement_datetime` datetime DEFAULT NULL,
  `engagement_duration_id` int DEFAULT NULL,
  `summary` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `engagement`
--

INSERT INTO `engagement` VALUES (26,1,1,1,'2001-09-09 01:46:40',1,'Test insert manual');

CREATE DATABASE `readonly-demo-ro`;
USE `readonly-demo-ro`;

DROP TABLE IF EXISTS `engagement`;
CREATE TABLE `engagement` (
  `id` int NOT NULL AUTO_INCREMENT,
  `participant_id` int DEFAULT NULL,
  `engagement_type_id` int DEFAULT NULL,
  `engagement_outcome_id` int NOT NULL,
  `engagement_datetime` datetime DEFAULT NULL,
  `engagement_duration_id` int DEFAULT NULL,
  `summary` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `engagement`
--

INSERT INTO `engagement` VALUES (26,1,1,1,'2001-09-09 01:46:40',1,'Test insert manual');
