CREATE TABLE `admins` (
                          `id` int NOT NULL AUTO_INCREMENT,
                          `firstname` varchar(45) NOT NULL,
                          `lastname` varchar(45) NOT NULL,
                          `login` varchar(45) NOT NULL,
                          `password` varchar(64) NOT NULL,
                          `email` varchar(45) NOT NULL,
                          PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `accounts` (
                            `id` int NOT NULL AUTO_INCREMENT,
                            `worker_id` varchar(45) NOT NULL,
                            `hour` int DEFAULT NULL,
                            `bonus` int DEFAULT NULL,
                            `rebuke` varchar(45) DEFAULT NULL,
                            PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `user` (
                         `id` int NOT NULL AUTO_INCREMENT,
                         `firstname` varchar(45) NOT NULL,
                         `lastname` varchar(45) NOT NULL,
                         `login` varchar(45) NOT NULL,
                         `password` varchar(64) NOT NULL,
                         `email` varchar(45) NOT NULL,
                         `gender` varchar(45) NOT NULL,
                         `location` varchar(45) NOT NULL,
                         PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `workers` (
                           `id` int NOT NULL AUTO_INCREMENT,
                           `name` varchar(45) NOT NULL,
                           `surname` varchar(45) NOT NULL,
                           `fathername` varchar(45) NOT NULL,
                           `department` varchar(45) NOT NULL,
                           `position` varchar(45) NOT NULL,
                           `year` varchar(45) NOT NULL,
                           `salary` varchar(45) NOT NULL,
                           PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
