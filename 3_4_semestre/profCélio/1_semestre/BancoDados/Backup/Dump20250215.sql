CREATE DATABASE  IF NOT EXISTS `confeitaria_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `confeitaria_db`;
-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: confeitaria_db
-- ------------------------------------------------------
-- Server version	5.5.5-10.4.32-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `clientes`
--

DROP TABLE IF EXISTS `clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `clientes` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `idade` int(11) DEFAULT NULL,
  `endereco_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes`
--

LOCK TABLES `clientes` WRITE;
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
INSERT INTO `clientes` VALUES (1,'Ana Silva',30,NULL),(2,'Carlos Pereira',45,NULL),(3,'Mariana Souza',28,NULL),(4,'Pedro Santos',35,NULL),(5,'Juliana Costa',40,NULL);
/*!40000 ALTER TABLE `clientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `embalagens`
--

DROP TABLE IF EXISTS `embalagens`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `embalagens` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `tipo` varchar(50) NOT NULL,
  `valor` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `embalagens`
--

LOCK TABLES `embalagens` WRITE;
/*!40000 ALTER TABLE `embalagens` DISABLE KEYS */;
/*!40000 ALTER TABLE `embalagens` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `encomendas`
--

DROP TABLE IF EXISTS `encomendas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `encomendas` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `data` timestamp NOT NULL DEFAULT current_timestamp(),
  `cliente_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `encomendas`
--

LOCK TABLES `encomendas` WRITE;
/*!40000 ALTER TABLE `encomendas` DISABLE KEYS */;
INSERT INTO `encomendas` VALUES (1,'2025-02-10 03:00:00',1),(2,'2025-02-12 03:00:00',2),(3,'2025-02-15 03:00:00',3),(4,'2025-02-18 03:00:00',4),(5,'2025-02-20 03:00:00',5);
/*!40000 ALTER TABLE `encomendas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fornecedores`
--

DROP TABLE IF EXISTS `fornecedores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fornecedores` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `cnpj` varchar(18) NOT NULL,
  `nome` varchar(150) NOT NULL,
  `endereco_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `cnpj` (`cnpj`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fornecedores`
--

LOCK TABLES `fornecedores` WRITE;
/*!40000 ALTER TABLE `fornecedores` DISABLE KEYS */;
INSERT INTO `fornecedores` VALUES (1,'00.000.000/0001-01','Fornecedor A',NULL),(2,'00.000.000/0001-02','Fornecedor B',NULL),(3,'00.000.000/0001-03','Fornecedor C',NULL),(4,'00.000.000/0001-04','Fornecedor D',NULL),(5,'00.000.000/0001-05','Fornecedor E',NULL),(6,'00.000.000/0001-06','Fornecedor F',NULL),(7,'00.000.000/0001-07','Fornecedor G',NULL),(8,'00.000.000/0001-08','Fornecedor H',NULL),(9,'00.000.000/0001-09','Fornecedor I',NULL),(10,'00.000.000/0001-10','Fornecedor J',NULL);
/*!40000 ALTER TABLE `fornecedores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ingredientes`
--

DROP TABLE IF EXISTS `ingredientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ingredientes` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `descricao` text DEFAULT NULL,
  `quantidade_estoque` decimal(10,2) NOT NULL,
  `unidade` varchar(20) NOT NULL,
  `embalagem_id` int(11) DEFAULT NULL,
  `custo_unitario` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ingredientes`
--

LOCK TABLES `ingredientes` WRITE;
/*!40000 ALTER TABLE `ingredientes` DISABLE KEYS */;
INSERT INTO `ingredientes` VALUES (1,'Farinha de trigo','Farinha branca para bolos',1000.00,'kg',NULL,5.00),(2,'Açúcar','Açúcar refinado',500.00,'kg',NULL,4.00),(3,'Chocolate em pó','Chocolate 70% cacau',300.00,'kg',NULL,10.00),(4,'Manteiga','Manteiga sem sal',200.00,'kg',NULL,20.00),(5,'Leite','Leite integral',100.00,'L',NULL,3.50),(6,'Ovos','Ovos frescos',1000.00,'un',NULL,0.50),(7,'Fermento','Fermento químico em pó',150.00,'kg',NULL,12.00),(8,'Baunilha','Extrato de baunilha',50.00,'ml',NULL,25.00),(9,'Chocolate branco','Chocolate branco para cobertura',200.00,'kg',NULL,15.00),(10,'Coco ralado','Coco seco ralado',300.00,'kg',NULL,8.00),(11,'Ingrediente 11','Descrição do ingrediente 11',111.00,'kg',NULL,27.50),(12,'Ingrediente 12','Descrição do ingrediente 12',112.00,'kg',NULL,30.00),(13,'Ingrediente 13','Descrição do ingrediente 13',113.00,'kg',NULL,32.50),(14,'Ingrediente 14','Descrição do ingrediente 14',114.00,'kg',NULL,35.00),(15,'Ingrediente 15','Descrição do ingrediente 15',115.00,'kg',NULL,37.50),(16,'Ingrediente 16','Descrição do ingrediente 16',116.00,'kg',NULL,40.00),(17,'Ingrediente 17','Descrição do ingrediente 17',117.00,'kg',NULL,42.50),(18,'Ingrediente 18','Descrição do ingrediente 18',118.00,'kg',NULL,45.00),(19,'Ingrediente 19','Descrição do ingrediente 19',119.00,'kg',NULL,47.50),(20,'Ingrediente 20','Descrição do ingrediente 20',120.00,'kg',NULL,50.00);
/*!40000 ALTER TABLE `ingredientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ingredientes_fornecedores`
--

DROP TABLE IF EXISTS `ingredientes_fornecedores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ingredientes_fornecedores` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `fornecedor_id` int(11) DEFAULT NULL,
  `ingrediente_id` int(11) DEFAULT NULL,
  `valor` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ingredientes_fornecedores`
--

LOCK TABLES `ingredientes_fornecedores` WRITE;
/*!40000 ALTER TABLE `ingredientes_fornecedores` DISABLE KEYS */;
/*!40000 ALTER TABLE `ingredientes_fornecedores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ingredientes_produtos`
--

DROP TABLE IF EXISTS `ingredientes_produtos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ingredientes_produtos` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `produto_id` int(11) DEFAULT NULL,
  `ingrediente_id` int(11) DEFAULT NULL,
  `quantidade_usada` decimal(10,2) NOT NULL,
  `unidade` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ingredientes_produtos`
--

LOCK TABLES `ingredientes_produtos` WRITE;
/*!40000 ALTER TABLE `ingredientes_produtos` DISABLE KEYS */;
INSERT INTO `ingredientes_produtos` VALUES (1,1,2,2.50,'kg'),(2,2,3,5.00,'g'),(3,3,4,7.50,'ml'),(4,4,5,10.00,'mg'),(5,5,6,12.50,'kg'),(6,6,7,15.00,'g'),(7,7,8,17.50,'ml'),(8,8,9,20.00,'mg'),(9,9,10,22.50,'kg'),(10,10,11,25.00,'g'),(11,11,12,27.50,'ml'),(12,12,13,30.00,'mg'),(13,13,14,32.50,'kg'),(14,14,15,35.00,'g'),(15,15,16,37.50,'ml'),(16,16,17,40.00,'mg'),(17,17,18,42.50,'kg'),(18,18,19,45.00,'g'),(19,19,20,47.50,'ml'),(20,20,21,50.00,'mg'),(21,21,22,52.50,'kg'),(22,22,23,55.00,'g'),(23,23,24,57.50,'ml'),(24,24,25,60.00,'mg'),(25,25,26,62.50,'kg'),(26,26,27,65.00,'g'),(27,27,28,67.50,'ml'),(28,28,29,70.00,'mg'),(29,29,30,72.50,'kg'),(30,30,31,75.00,'g'),(31,31,32,77.50,'ml'),(32,32,33,80.00,'mg'),(33,33,34,82.50,'kg'),(34,34,35,85.00,'g'),(35,35,36,87.50,'ml'),(36,36,37,90.00,'mg'),(37,37,38,92.50,'kg'),(38,38,39,95.00,'g'),(39,39,40,97.50,'ml'),(40,40,41,100.00,'mg'),(41,41,42,102.50,'kg'),(42,42,43,105.00,'g'),(43,43,44,107.50,'ml'),(44,44,45,110.00,'mg'),(45,45,46,112.50,'kg'),(46,46,47,115.00,'g'),(47,47,48,117.50,'ml'),(48,48,49,120.00,'mg'),(49,49,50,122.50,'kg'),(50,50,1,125.00,'g'),(51,51,2,127.50,'ml'),(52,52,3,130.00,'mg'),(53,53,4,132.50,'kg'),(54,54,5,135.00,'g'),(55,55,6,137.50,'ml'),(56,56,7,140.00,'mg'),(57,57,8,142.50,'kg'),(58,58,9,145.00,'g'),(59,59,10,147.50,'ml'),(60,60,11,150.00,'mg');
/*!40000 ALTER TABLE `ingredientes_produtos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `itens_encomenda`
--

DROP TABLE IF EXISTS `itens_encomenda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `itens_encomenda` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `encomenda_id` int(11) DEFAULT NULL,
  `produto_id` int(11) DEFAULT NULL,
  `quantidade` int(11) NOT NULL,
  `valor_total` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `itens_encomenda`
--

LOCK TABLES `itens_encomenda` WRITE;
/*!40000 ALTER TABLE `itens_encomenda` DISABLE KEYS */;
INSERT INTO `itens_encomenda` VALUES (1,1,1,2,70.00),(2,1,5,1,10.00),(3,2,2,3,90.00),(4,3,4,1,15.00),(5,4,6,5,60.00),(6,5,3,2,90.00);
/*!40000 ALTER TABLE `itens_encomenda` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produtos`
--

DROP TABLE IF EXISTS `produtos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `produtos` (
  `_id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  `descricao_produto` text DEFAULT NULL,
  `valor_venda` decimal(10,2) NOT NULL,
  `data_vabricacao` date NOT NULL,
  `data_validade` date DEFAULT NULL,
  `quantidade_em_estoque` int(11) NOT NULL,
  `status` enum('ativo','inativo') NOT NULL,
  PRIMARY KEY (`_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produtos`
--

LOCK TABLES `produtos` WRITE;
/*!40000 ALTER TABLE `produtos` DISABLE KEYS */;
INSERT INTO `produtos` VALUES (1,'Bolo de Chocolate','Bolo com recheio de chocolate',35.00,'2025-01-01','2025-02-01',10,'ativo'),(2,'Bolo de Cenoura','Bolo de cenoura com cobertura de chocolate',30.00,'2025-01-02','2025-02-05',8,'ativo'),(3,'Bolo Red Velvet','Bolo vermelho com recheio de cream cheese',45.00,'2025-01-03','2025-02-10',5,'ativo'),(4,'Pão de Queijo','Pão de queijo tradicional',15.00,'2025-01-04','2025-02-15',20,'ativo'),(5,'Donuts','Rosquinhas doces com cobertura',10.00,'2025-01-05','2025-02-20',25,'ativo'),(6,'Brownie','Brownie de chocolate meio amargo',12.00,'2025-01-06','2025-02-18',18,'ativo');
/*!40000 ALTER TABLE `produtos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `telefones`
--

DROP TABLE IF EXISTS `telefones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `telefones` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `ddd` varchar(3) NOT NULL,
  `numero` varchar(15) NOT NULL,
  `categoria` varchar(20) DEFAULT NULL CHECK (`categoria` in ('comercial','whatsapp','residencial','outro')),
  `nome` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `telefones`
--

LOCK TABLES `telefones` WRITE;
/*!40000 ALTER TABLE `telefones` DISABLE KEYS */;
INSERT INTO `telefones` VALUES (1,'11','999999999','whatsapp','Ana WhatsApp'),(2,'21','988888888','comercial','Carlos Escritório'),(3,'31','977777777','residencial',NULL),(4,'41','966666666','outro','Juliana Celular');
/*!40000 ALTER TABLE `telefones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `telefones_contato`
--

DROP TABLE IF EXISTS `telefones_contato`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `telefones_contato` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `entidade_id` int(11) NOT NULL,
  `tipo_contato` varchar(20) DEFAULT NULL CHECK (`tipo_contato` in ('cliente','fornecedor')),
  `telefone_id` int(11) DEFAULT NULL,
  `prioridade` varchar(20) DEFAULT NULL CHECK (`prioridade` in ('primário','secundário','outro')),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `telefones_contato`
--

LOCK TABLES `telefones_contato` WRITE;
/*!40000 ALTER TABLE `telefones_contato` DISABLE KEYS */;
INSERT INTO `telefones_contato` VALUES (1,1,'cliente',1,'primário'),(2,2,'cliente',2,'secundário'),(3,3,'cliente',3,'primário'),(4,4,'cliente',4,'primário'),(5,5,'cliente',1,'secundário');
/*!40000 ALTER TABLE `telefones_contato` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-02-15 18:44:04
