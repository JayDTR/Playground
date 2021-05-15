-- --------------------------------------------------------
-- Anfitrião:                    127.0.0.1
-- Versão do servidor:           10.1.38-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Versão:              10.2.0.5599
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for soquartos
CREATE DATABASE IF NOT EXISTS `soquartos` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci */;
USE `soquartos`;

-- Dumping structure for table soquartos.aluguer
CREATE TABLE IF NOT EXISTS `aluguer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idquarto` int(11) DEFAULT NULL,
  `idinquilino` int(11) DEFAULT NULL,
  `dataentrada` date DEFAULT NULL,
  `datasaida` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_quarto_aluguer` (`idquarto`),
  KEY `FK_inquilino_aluguer` (`idinquilino`),
  CONSTRAINT `FK_inquilino_aluguer` FOREIGN KEY (`idinquilino`) REFERENCES `utilizador` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_quarto_aluguer` FOREIGN KEY (`idquarto`) REFERENCES `quarto` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf32 COLLATE=utf32_unicode_ci;

-- Dumping data for table soquartos.aluguer: ~4 rows (approximately)
/*!40000 ALTER TABLE `aluguer` DISABLE KEYS */;
INSERT INTO `aluguer` (`id`, `idquarto`, `idinquilino`, `dataentrada`, `datasaida`) VALUES
	(33, 11, 2, '2020-01-22', '2020-05-31'),
	(37, 8, 2, '2020-01-28', '2020-03-31'),
	(38, 9, 2, '2020-01-28', '2020-10-08'),
	(39, 14, 1, '2020-01-29', '2020-06-30'),
	(40, 18, 4, '2020-01-29', '2020-03-31');
/*!40000 ALTER TABLE `aluguer` ENABLE KEYS */;

-- Dumping structure for table soquartos.casa
CREATE TABLE IF NOT EXISTS `casa` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idtipocasa` int(11) DEFAULT NULL,
  `idsenhorio` int(11) DEFAULT NULL,
  `morada` varchar(100) COLLATE utf32_unicode_ci DEFAULT NULL,
  `iddistrito` int(11) DEFAULT NULL,
  `internet` tinyint(1) DEFAULT NULL,
  `foto` varchar(300) COLLATE utf32_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_tipocasa_casa` (`idtipocasa`),
  KEY `FK_senhorio_casa` (`idsenhorio`),
  KEY `FK_distrito_casa` (`iddistrito`),
  CONSTRAINT `FK_distrito_casa` FOREIGN KEY (`iddistrito`) REFERENCES `distrito` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_senhorio_casa` FOREIGN KEY (`idsenhorio`) REFERENCES `utilizador` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_tipocasa_casa` FOREIGN KEY (`idtipocasa`) REFERENCES `tipocasa` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf32 COLLATE=utf32_unicode_ci;

-- Dumping data for table soquartos.casa: ~9 rows (approximately)
/*!40000 ALTER TABLE `casa` DISABLE KEYS */;
INSERT INTO `casa` (`id`, `idtipocasa`, `idsenhorio`, `morada`, `iddistrito`, `internet`, `foto`) VALUES
	(2, 4, 2, 'Rua do Padre Luís Cabra', 13, 1, 'upload/casa/casa1.jpg'),
	(3, 5, 3, 'Largo de Santo Agostinho', 12, 0, 'upload/casa/casa2.jpg'),
	(4, 6, 4, 'Nossa Senhora da Saude', 7, 1, 'upload/casa/44.jpg'),
	(6, 3, 2, 'Rua Lourenço de Brito', 7, 1, 'upload/casa/casa5.jpg'),
	(7, 4, 4, 'Santa Vitória E Mombeja ', 2, 1, 'upload/casa/casa6.jpg'),
	(8, 1, 3, 'Rua Fran Pacheco', 15, 1, 'upload/casa/casa7.jpg'),
	(9, 2, 4, 'Rua de João Mendonça', 1, 1, 'upload/casa/casa8.jpg'),
	(11, 6, 4, 'Rua Bento Jesus Caraça', 8, 1, 'upload/casa/411.jpg'),
	(12, 2, 1, 'Rua Humberto Delgado', 2, 1, 'upload/casa/112.jpg');
/*!40000 ALTER TABLE `casa` ENABLE KEYS */;

-- Dumping structure for table soquartos.chat
CREATE TABLE IF NOT EXISTS `chat` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `DataHora` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  `idconversa` int(11) DEFAULT NULL,
  `user` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `texto` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `id_user` int(11) DEFAULT NULL,
  `foto` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_chat_utilizador` (`id_user`),
  CONSTRAINT `FK_chat_utilizador` FOREIGN KEY (`id_user`) REFERENCES `utilizador` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=33484 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table soquartos.chat: ~9 rows (approximately)
/*!40000 ALTER TABLE `chat` DISABLE KEYS */;
INSERT INTO `chat` (`id`, `DataHora`, `idconversa`, `user`, `texto`, `id_user`, `foto`) VALUES
	(33483, '01-29 16:30', 4, 'Joao Raposo', 'Ola', 4, 'upload/user/4.jpg');
/*!40000 ALTER TABLE `chat` ENABLE KEYS */;

-- Dumping structure for table soquartos.despesasgerais
CREATE TABLE IF NOT EXISTS `despesasgerais` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idcasa` int(11) DEFAULT NULL,
  `idtipodespesa` int(11) DEFAULT NULL,
  `datalimite` date DEFAULT NULL,
  `mesemissao` varchar(50) COLLATE utf32_unicode_ci DEFAULT NULL,
  `valor` double DEFAULT NULL,
  `foto` varchar(300) COLLATE utf32_unicode_ci DEFAULT NULL,
  `idestado` int(11) DEFAULT NULL,
  `datetime` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `FK_casa_despesas` (`idcasa`),
  KEY `FK_tipodespesa_despesa` (`idtipodespesa`),
  KEY `FK_estado_despesa` (`idestado`),
  KEY `FK_mes_mes` (`mesemissao`),
  CONSTRAINT `FK_casa_despesas` FOREIGN KEY (`idcasa`) REFERENCES `casa` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_estado_despesa` FOREIGN KEY (`idestado`) REFERENCES `estadodespesa` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_tipodespesa_despesa` FOREIGN KEY (`idtipodespesa`) REFERENCES `tipodespesas` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=132 DEFAULT CHARSET=utf32 COLLATE=utf32_unicode_ci;

-- Dumping data for table soquartos.despesasgerais: ~2 rows (approximately)
/*!40000 ALTER TABLE `despesasgerais` DISABLE KEYS */;
INSERT INTO `despesasgerais` (`id`, `idcasa`, `idtipodespesa`, `datalimite`, `mesemissao`, `valor`, `foto`, `idestado`, `datetime`) VALUES
	(119, 4, 1, '2020-01-23', '1', 150, 'upload/despesas/41191.png', 1, '2020-01-22 14:59:34'),
	(120, 4, 1, '2020-01-31', '1', 100, 'upload/despesas/41201.png', 1, '2020-01-22 15:02:20');
/*!40000 ALTER TABLE `despesasgerais` ENABLE KEYS */;

-- Dumping structure for table soquartos.despesa_aluguer
CREATE TABLE IF NOT EXISTS `despesa_aluguer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idaluguer` int(11) DEFAULT NULL,
  `iddespesa` int(11) DEFAULT NULL,
  `valor` double DEFAULT NULL,
  `estado` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idaluguer` (`idaluguer`),
  KEY `iddespesa` (`iddespesa`),
  KEY `FK_despesa_aluguer_estadodespesa` (`estado`),
  CONSTRAINT `FK_despesa_aluguer_estadodespesa` FOREIGN KEY (`estado`) REFERENCES `estadodespesa` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `despesa_aluguer_ibfk_1` FOREIGN KEY (`idaluguer`) REFERENCES `aluguer` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `despesa_aluguer_ibfk_2` FOREIGN KEY (`iddespesa`) REFERENCES `despesasgerais` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=159 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table soquartos.despesa_aluguer: ~24 rows (approximately)
/*!40000 ALTER TABLE `despesa_aluguer` DISABLE KEYS */;
INSERT INTO `despesa_aluguer` (`id`, `idaluguer`, `iddespesa`, `valor`, `estado`) VALUES
	(113, 33, NULL, 50, 2),
	(114, 33, NULL, 50, 2),
	(115, 33, NULL, 50, 2),
	(116, 33, NULL, 50, 2),
	(117, 33, NULL, 50, 2),
	(118, 33, 119, 150, 1),
	(119, 33, 120, 100, 1),
	(131, 37, NULL, 200, 2),
	(132, 37, NULL, 200, 2),
	(133, 37, NULL, 200, 2),
	(134, 38, NULL, 342, 2),
	(135, 38, NULL, 342, 2),
	(136, 38, NULL, 342, 2),
	(137, 38, NULL, 342, 2),
	(138, 38, NULL, 342, 2),
	(139, 38, NULL, 342, 2),
	(140, 38, NULL, 342, 2),
	(141, 38, NULL, 342, 2),
	(142, 38, NULL, 342, 2),
	(143, 38, NULL, 342, 2),
	(144, 39, NULL, 250, 1),
	(145, 39, NULL, 250, 2),
	(146, 39, NULL, 250, 2),
	(147, 39, NULL, 250, 2),
	(148, 39, NULL, 250, 2),
	(149, 39, NULL, 250, 2),
	(154, 40, NULL, 150, 2),
	(155, 40, NULL, 150, 2),
	(156, 40, NULL, 150, 2);
/*!40000 ALTER TABLE `despesa_aluguer` ENABLE KEYS */;

-- Dumping structure for table soquartos.destaque_quarto
CREATE TABLE IF NOT EXISTS `destaque_quarto` (
  `id` int(11) NOT NULL,
  `idquarto` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_destaque_destaque_quarto` (`idquarto`),
  CONSTRAINT `FK_destaque_destaque_quarto` FOREIGN KEY (`idquarto`) REFERENCES `quarto` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Dumping data for table soquartos.destaque_quarto: ~2 rows (approximately)
/*!40000 ALTER TABLE `destaque_quarto` DISABLE KEYS */;
INSERT INTO `destaque_quarto` (`id`, `idquarto`) VALUES
	(2, 2),
	(3, 6);
/*!40000 ALTER TABLE `destaque_quarto` ENABLE KEYS */;

-- Dumping structure for table soquartos.distrito
CREATE TABLE IF NOT EXISTS `distrito` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(50) COLLATE utf32_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf32 COLLATE=utf32_unicode_ci;

-- Dumping data for table soquartos.distrito: ~18 rows (approximately)
/*!40000 ALTER TABLE `distrito` DISABLE KEYS */;
INSERT INTO `distrito` (`ID`, `descricao`) VALUES
	(1, 'Aveiro'),
	(2, 'Beja'),
	(3, 'Braga'),
	(4, 'Bragança'),
	(5, 'Castelo Branco'),
	(6, 'Coimbra'),
	(7, 'Évora'),
	(8, 'Faro'),
	(9, 'Guarda'),
	(10, 'Leiria'),
	(11, 'Lisboa'),
	(12, 'Portalegre'),
	(13, 'Porto'),
	(14, 'Santarém'),
	(15, 'Setúbal'),
	(16, 'Viana Do Castelo'),
	(17, 'Vila Real'),
	(18, 'Viseu');
/*!40000 ALTER TABLE `distrito` ENABLE KEYS */;

-- Dumping structure for table soquartos.estadodespesa
CREATE TABLE IF NOT EXISTS `estadodespesa` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(100) COLLATE utf32_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf32 COLLATE=utf32_unicode_ci;

-- Dumping data for table soquartos.estadodespesa: ~4 rows (approximately)
/*!40000 ALTER TABLE `estadodespesa` DISABLE KEYS */;
INSERT INTO `estadodespesa` (`id`, `descricao`) VALUES
	(1, 'Pago'),
	(2, 'Por Pagar'),
	(3, 'Em Atraso'),
	(4, 'Cancelado');
/*!40000 ALTER TABLE `estadodespesa` ENABLE KEYS */;

-- Dumping structure for table soquartos.listaservico
CREATE TABLE IF NOT EXISTS `listaservico` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idcasa` int(11) DEFAULT NULL,
  `idservico` int(11) DEFAULT NULL,
  `descricao` varchar(200) COLLATE utf32_unicode_ci DEFAULT NULL,
  `preco` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_casa_listaservico` (`idcasa`),
  KEY `FK_servico_listaservico` (`idservico`),
  CONSTRAINT `FK_casa_listaservico` FOREIGN KEY (`idcasa`) REFERENCES `casa` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_servico_listaservico` FOREIGN KEY (`idservico`) REFERENCES `servico` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf32 COLLATE=utf32_unicode_ci;

-- Dumping data for table soquartos.listaservico: ~0 rows (approximately)
/*!40000 ALTER TABLE `listaservico` DISABLE KEYS */;
/*!40000 ALTER TABLE `listaservico` ENABLE KEYS */;

-- Dumping structure for table soquartos.mes
CREATE TABLE IF NOT EXISTS `mes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- Dumping data for table soquartos.mes: ~12 rows (approximately)
/*!40000 ALTER TABLE `mes` DISABLE KEYS */;
INSERT INTO `mes` (`id`, `descricao`) VALUES
	(1, 'Janeiro'),
	(2, 'Fevereiro'),
	(3, 'Março'),
	(4, 'Abril'),
	(5, 'Maio'),
	(6, 'Junho'),
	(7, 'Julho'),
	(8, 'Agosto'),
	(9, 'Setembro'),
	(10, 'Outubro'),
	(11, 'Novembro'),
	(12, 'Dezembro');
/*!40000 ALTER TABLE `mes` ENABLE KEYS */;

-- Dumping structure for table soquartos.quarto
CREATE TABLE IF NOT EXISTS `quarto` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idcasa` int(11) DEFAULT NULL,
  `preco` double DEFAULT NULL,
  `descricao` varchar(300) COLLATE utf32_unicode_ci DEFAULT NULL,
  `wcprivado` tinyint(1) DEFAULT NULL,
  `foto` varchar(300) COLLATE utf32_unicode_ci NOT NULL,
  `idcama` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_casa_quarto` (`idcasa`),
  KEY `FK_cama_quarto` (`idcama`),
  CONSTRAINT `FK_cama_quarto` FOREIGN KEY (`idcama`) REFERENCES `tipocama` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_casa_quarto` FOREIGN KEY (`idcasa`) REFERENCES `casa` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf32 COLLATE=utf32_unicode_ci;

-- Dumping data for table soquartos.quarto: ~14 rows (approximately)
/*!40000 ALTER TABLE `quarto` DISABLE KEYS */;
INSERT INTO `quarto` (`id`, `idcasa`, `preco`, `descricao`, `wcprivado`, `foto`, `idcama`) VALUES
	(2, 6, 222, 'Casa a bom preço', 1, 'upload/quarto/quarto1.jpg', 1),
	(3, 3, 145, 'Casa a bom preço', 0, 'upload/quarto/quarto2.jpg', 1),
	(4, 6, 388, 'Casa a bom preço', 1, 'upload/quarto/quarto3.jpg', 2),
	(5, 3, 67, 'Casa a bom preço', 0, 'upload/quarto/quarto4.jpg', 2),
	(6, 8, 90, 'Casa a bom preço', 1, 'upload/quarto/quarto5.jpg', 2),
	(7, 3, 499, 'Casa a bom preço', 1, 'upload/quarto/quarto6.jpg', 2),
	(8, 7, 200, 'Casa a bom', 1, 'upload/quarto/48.jpg', 1),
	(9, 9, 342, 'Casa barata', 1, 'upload/quarto/quarto8.jpg', 1),
	(11, 4, 50, 'Barato', 0, 'upload/quarto/411.jpg', 2),
	(12, 11, 250, 'Baratucho', 1, 'upload/quarto/412.jpg', 1),
	(13, 11, 50, 'kdamsodkapos', 1, 'upload/quarto/413.jpg', 1),
	(14, 4, 250, 'Casal', 1, 'upload/quarto/414.png', 1),
	(16, 6, 200, 'Quarto Apropriado para Rapariga', 1, 'upload/quarto/216.jpg', 1),
	(17, 4, 178, 'Boa Localização, Perto do Pingo Doce', 1, 'upload/quarto/417.jpg', 1),
	(18, 12, 150, 'Quarto Rural, Excelente Luminosidade Natural', 1, 'upload/quarto/118.jpg', 1),
	(19, 4, 150, 'ana', 1, 'upload/quarto/419.jpg', 1);
/*!40000 ALTER TABLE `quarto` ENABLE KEYS */;

-- Dumping structure for table soquartos.servico
CREATE TABLE IF NOT EXISTS `servico` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(50) COLLATE utf32_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf32 COLLATE=utf32_unicode_ci;

-- Dumping data for table soquartos.servico: ~0 rows (approximately)
/*!40000 ALTER TABLE `servico` DISABLE KEYS */;
/*!40000 ALTER TABLE `servico` ENABLE KEYS */;

-- Dumping structure for table soquartos.tipocama
CREATE TABLE IF NOT EXISTS `tipocama` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(50) COLLATE utf32_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf32 COLLATE=utf32_unicode_ci;

-- Dumping data for table soquartos.tipocama: ~2 rows (approximately)
/*!40000 ALTER TABLE `tipocama` DISABLE KEYS */;
INSERT INTO `tipocama` (`id`, `descricao`) VALUES
	(1, 'Casal'),
	(2, 'Solteiro');
/*!40000 ALTER TABLE `tipocama` ENABLE KEYS */;

-- Dumping structure for table soquartos.tipocasa
CREATE TABLE IF NOT EXISTS `tipocasa` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(20) COLLATE utf32_unicode_ci DEFAULT NULL,
  `blueprint` varchar(300) COLLATE utf32_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf32 COLLATE=utf32_unicode_ci;

-- Dumping data for table soquartos.tipocasa: ~6 rows (approximately)
/*!40000 ALTER TABLE `tipocasa` DISABLE KEYS */;
INSERT INTO `tipocasa` (`id`, `descricao`, `blueprint`) VALUES
	(1, 'T1', 'T1'),
	(2, 'T2', 'T2'),
	(3, 'T3', 'T3'),
	(4, 'T4', 'T4'),
	(5, 'T5', 'T5'),
	(6, 'T6', 'T6');
/*!40000 ALTER TABLE `tipocasa` ENABLE KEYS */;

-- Dumping structure for table soquartos.tipodespesas
CREATE TABLE IF NOT EXISTS `tipodespesas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(100) COLLATE utf32_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf32 COLLATE=utf32_unicode_ci;

-- Dumping data for table soquartos.tipodespesas: ~4 rows (approximately)
/*!40000 ALTER TABLE `tipodespesas` DISABLE KEYS */;
INSERT INTO `tipodespesas` (`id`, `descricao`) VALUES
	(1, 'Água'),
	(2, 'Eletricidade'),
	(3, 'Gás'),
	(4, 'Internet');
/*!40000 ALTER TABLE `tipodespesas` ENABLE KEYS */;

-- Dumping structure for table soquartos.tipoutilizador
CREATE TABLE IF NOT EXISTS `tipoutilizador` (
  `id` int(11) NOT NULL,
  `descricao` varchar(50) COLLATE utf32_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf32 COLLATE=utf32_unicode_ci;

-- Dumping data for table soquartos.tipoutilizador: ~3 rows (approximately)
/*!40000 ALTER TABLE `tipoutilizador` DISABLE KEYS */;
INSERT INTO `tipoutilizador` (`id`, `descricao`) VALUES
	(1, 'admin'),
	(2, 'inquilino'),
	(3, 'misto');
/*!40000 ALTER TABLE `tipoutilizador` ENABLE KEYS */;

-- Dumping structure for table soquartos.utilizador
CREATE TABLE IF NOT EXISTS `utilizador` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) COLLATE utf32_unicode_ci DEFAULT NULL,
  `telefone` int(11) DEFAULT NULL,
  `email` varchar(100) COLLATE utf32_unicode_ci DEFAULT NULL,
  `datanascimento` date DEFAULT NULL,
  `nif` int(11) NOT NULL,
  `idtipoutilizador` int(11) DEFAULT NULL,
  `password` varchar(50) COLLATE utf32_unicode_ci DEFAULT NULL,
  `foto` varchar(50) COLLATE utf32_unicode_ci DEFAULT NULL,
  `nrcc` int(8) NOT NULL,
  PRIMARY KEY (`id`,`nrcc`,`nif`),
  KEY `idtipoutilizador` (`idtipoutilizador`),
  CONSTRAINT `utilizador_ibfk_1` FOREIGN KEY (`idtipoutilizador`) REFERENCES `tipoutilizador` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf32 COLLATE=utf32_unicode_ci;

-- Dumping data for table soquartos.utilizador: ~4 rows (approximately)
/*!40000 ALTER TABLE `utilizador` DISABLE KEYS */;
INSERT INTO `utilizador` (`id`, `nome`, `telefone`, `email`, `datanascimento`, `nif`, `idtipoutilizador`, `password`, `foto`, `nrcc`) VALUES
	(1, 'Diogo Pereira', 123456789, 'diogo@gmail.com', '2019-11-19', 266777123, 3, 'Nova*2019', 'upload/user/foto.jpg', 15494883),
	(2, 'Joao Delfino', 123456789, 'joaod@gmail.com', '2019-11-19', 266777124, 3, 'Nova*2019', 'upload/user/foto1.jpg', 15494884),
	(3, 'Luis Barradas', 123456789, 'luis@gmail.com', '2019-11-19', 1234567, 3, 'Nova*2019', 'upload/user/foto2.jpg', 15494885),
	(4, 'Joao Raposo', 966033903, 'joaor@gmail.com', '2019-11-19', 266777126, 3, 'Nova*2019', 'upload/user/4.jpg', 15494882),
	(5, 'Jose Maria', 963258741, 'josem@gmail.com', '1998-07-16', 123456789, 2, 'Nova*2019', 'upload/user/5.jpg', 5264898);
/*!40000 ALTER TABLE `utilizador` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
