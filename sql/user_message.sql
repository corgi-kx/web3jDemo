CREATE TABLE `user_message` (
  `user_index` int(11) NOT NULL AUTO_INCREMENT,
  `id_card` varchar(11) NOT NULL,
  `passworld` varchar(255) NOT NULL,
  `states` int(11) NOT NULL DEFAULT '0',
  `eth_address` varchar(255) NOT NULL,
  `eth_private_key` varchar(255) NOT NULL,
  `eth_public_key` varchar(255) NOT NULL,
  `eth_key_store` varchar(255) NOT NULL,
  `score` int(11) NOT NULL DEFAULT '500',
  PRIMARY KEY (`user_index`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;