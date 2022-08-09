CREATE TABLE IF NOT EXISTS `role` (
	`id` int NOT NULL AUTO_INCREMENT,
	`name` varchar(50) NOT NULL,
	PRIMARY KEY(`id`)
);

CREATE TABLE IF NOT EXISTS `end_user` (
	`username` varchar(50) NOT NULL,
	`email_id` varchar(50) NOT NULL,
	`name` varchar(50) NOT NULL,
	`password` varchar(200) NOT NULL,
	`role_id` int NOT NULL,
	PRIMARY KEY(`username`),
	FOREIGN KEY(`role_id`) REFERENCES role(`id`)
);

CREATE TABLE IF NOT EXISTS `notice` (
	`id` int NOT NULL AUTO_INCREMENT,
	`title` varchar(200) NOT NULL,
	`description` varchar(1000) NOT NULL,
	`created_at` date NOT NULL,
	PRIMARY KEY(`id`)
);