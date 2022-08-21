INSERT INTO `role` (`id`, `name`)
	VALUES(1, 'admin');
	
INSERT INTO `role` (`id`, `name`)
	VALUES(2, 'student');


INSERT INTO `end_user` (`username`, `email_id`, `name`, `password`)
	VALUES('admin', 'admin@email.com', 'Admin Guy', '$2a$10$GB.vRo32cMleKg9LsBjIpunXBdxmxNPm.on9CUCAmzMIWQtm03AqW');
	
INSERT INTO `end_user` (`username`, `email_id`, `name`, `password`)
	VALUES('begula', 'begula@email.com', 'Beluga GG', '$2a$10$/o/R5tms5DPTCOPTsH2pAuinCHmLL2h6dccJxo/RO8iwJ/Ts.5cGy');
	
INSERT INTO `user_role` (`username`, `role_id`)
	VALUES('admin', 1);
	
INSERT INTO `user_role` (`username`, `role_id`)
	VALUES('admin', 2);
	
INSERT INTO `user_role` (`username`, `role_id`)
	VALUES('begula', 2);
	
INSERT INTO `admin_end_user` (`username`)
	VALUES('admin');

INSERT INTO `notice` (`title`, `description`, `created_at`, `created_by`)
	VALUES('Mid term exam', 'Mid term exam is going to start from 12th June 2022. There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don''t look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn''t anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable.', '2022-06-01', 'admin');
	
INSERT INTO `notice` (`title`, `description`, `created_at`, `created_by`)
	VALUES('Summer vacation', 'Summer vacation is going to start from 1st July 2022. There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don''t look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn''t anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable.', '2022-06-12', 'admin');

INSERT INTO `notice` (`title`, `description`, `created_at`, `created_by`)
	VALUES('Winter vacation', 'Winter vacation is going to start from 1st November 2021. There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don''t look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn''t anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable.', '2021-10-12', 'admin');
	
INSERT INTO `notice` (`title`, `description`, `created_at`, `created_by`)
	VALUES('Summer vacation', 'Summer vacation is going to start from 1st July 2021. There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don''t look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn''t anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable.', '2021-06-12', 'admin');
	
INSERT INTO `notice` (`title`, `description`, `created_at`, `created_by`)
	VALUES('Mid term exam', 'Mid term exam is going to start from 12th June 2021. There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don''t look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn''t anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable.', '2021-06-01', 'admin');
	
INSERT INTO `notice` (`title`, `description`, `created_at`, `created_by`)
	VALUES('Winter vacation', 'Winter vacation is going to start from 1st November 2020. There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don''t look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn''t anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable.', '2020-10-12', 'admin');
	
INSERT INTO `notice` (`title`, `description`, `created_at`, `created_by`)
	VALUES('Summer vacation', 'Summer vacation is going to start from 1st July 2020. There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don''t look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn''t anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable.', '2020-06-12', 'admin');