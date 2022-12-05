CREATE TABLE `member` (
   `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
   `user_age` int(11) NOT NULL,
   `user_email` varchar(255) COLLATE utf8mb4_bin NOT NULL,
   `password` varchar(255) COLLATE utf8mb4_bin NOT NULL,
   `user_role` varchar(255) COLLATE utf8mb4_bin NOT NULL,
   `user_name` varchar(255) COLLATE utf8mb4_bin NOT NULL,
   `reg_date` datetime DEFAULT NOW() NOT NULL,
   PRIMARY KEY (`user_id`),
   UNIQUE KEY `UK_dxhhhyumsuvqr8c5qjx94pepw` (`user_email`)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin