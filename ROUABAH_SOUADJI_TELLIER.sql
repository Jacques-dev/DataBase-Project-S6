-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : lun. 31 mai 2021 à 20:32
-- Version du serveur :  5.7.31
-- Version de PHP : 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `databaseprojects6`
--

-- --------------------------------------------------------

--
-- Structure de la table `adresse`
--

DROP TABLE IF EXISTS `adresse`;
CREATE TABLE IF NOT EXISTS `adresse` (
  `idAdresse` varchar(50) NOT NULL,
  `ville` varchar(50) NOT NULL,
  `codePostale` char(5) NOT NULL,
  PRIMARY KEY (`idAdresse`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `adresse`
--

INSERT INTO `adresse` (`idAdresse`, `ville`, `codePostale`) VALUES
('35  Place du Jeu de Paume', 'VIERZON', '97300'),
('55  Rue du Limas', 'BASSE-TERRE', '97100'),
('57  Place du Jeu de Paume', 'VIERZON', '97300'),
('93  Rue de Verdun', 'MONTÉLIMAR', '97200'),
('94  Avenue des Pr', 'MONTMORENCY', '97500');

-- --------------------------------------------------------

--
-- Structure de la table `agence`
--

DROP TABLE IF EXISTS `agence`;
CREATE TABLE IF NOT EXISTS `agence` (
  `idAgence` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) NOT NULL,
  `telephone` char(10) NOT NULL,
  `coordonneesGPS` varchar(50) NOT NULL,
  `nombreMaxVehicules` int(11) NOT NULL,
  `idAdresse` varchar(50) NOT NULL,
  PRIMARY KEY (`idAgence`),
  KEY `Agence_Adresse_FK` (`idAdresse`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `agence`
--

INSERT INTO `agence` (`idAgence`, `nom`, `telephone`, `coordonneesGPS`, `nombreMaxVehicules`, `idAdresse`) VALUES
(1, 'RentCar', '0162354978', '47.21667,2.08333', 25, '35  Place du Jeu de Paume'),
(2, 'JacksCar', '065624891', '90.54654,6.083212', 10, '4, rue Gérard Millet'),
(3, 'MrnCar', '0196457362', '1.144464,50.54644', 8, '82, rue Verdier');

-- --------------------------------------------------------

--
-- Structure de la table `categorievehicule`
--

DROP TABLE IF EXISTS `categorievehicule`;
CREATE TABLE IF NOT EXISTS `categorievehicule` (
  `type` char(10) NOT NULL,
  `tarif` float NOT NULL,
  `caution` float NOT NULL,
  PRIMARY KEY (`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `categorievehicule`
--

INSERT INTO `categorievehicule` (`type`, `tarif`, `caution`) VALUES
('Diesel', 50, 150),
('Electrique', 100, 250),
('Essence', 25, 100);

-- --------------------------------------------------------

--
-- Structure de la table `chauffeur`
--

DROP TABLE IF EXISTS `chauffeur`;
CREATE TABLE IF NOT EXISTS `chauffeur` (
  `idUtilisateur` int(11) NOT NULL,
  PRIMARY KEY (`idUtilisateur`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `chauffeur`
--

INSERT INTO `chauffeur` (`idUtilisateur`) VALUES
(2);

-- --------------------------------------------------------

--
-- Structure de la table `circuit`
--

DROP TABLE IF EXISTS `circuit`;
CREATE TABLE IF NOT EXISTS `circuit` (
  `idCircuit` int(11) NOT NULL AUTO_INCREMENT,
  `idAgence_depart` int(11) NOT NULL,
  `idAgence_arrive` int(11) NOT NULL,
  `repere_un` varchar(50) NOT NULL,
  `repere_deux` varchar(50) NOT NULL,
  `repere_trois` varchar(50) NOT NULL,
  PRIMARY KEY (`idCircuit`),
  KEY `Circuit_Agence_un_FK` (`idAgence_depart`),
  KEY `Circuit_Agence_deux_FK` (`idAgence_arrive`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `circuit`
--

INSERT INTO `circuit` (`idCircuit`, `idAgence_depart`, `idAgence_arrive`, `repere_un`, `repere_deux`, `repere_trois`) VALUES
(1, 1, 1, 'Route A', 'Route B', 'Route C'),
(2, 2, 3, 'Route A', 'Route B', 'Route C'),
(3, 2, 1, 'Route A', 'Route B', 'Route C'),
(4, 3, 2, 'Route A', 'Route B', 'Route C'),
(5, 3, 1, 'Route A', 'Route B', 'Route C'),
(6, 1, 2, 'Route A', 'Route B', 'Route C'),
(7, 1, 3, 'Route A', 'Route B', 'Route C');

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

DROP TABLE IF EXISTS `client`;
CREATE TABLE IF NOT EXISTS `client` (
  `idUtilisateur` int(11) NOT NULL,
  PRIMARY KEY (`idUtilisateur`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `client`
--

INSERT INTO `client` (`idUtilisateur`) VALUES
(3),
(4);

-- --------------------------------------------------------

--
-- Structure de la table `devis`
--

DROP TABLE IF EXISTS `devis`;
CREATE TABLE IF NOT EXISTS `devis` (
  `idDevis` int(11) NOT NULL AUTO_INCREMENT,
  `montantReduction` float NOT NULL,
  `dureePrevueLocation` int(11) NOT NULL,
  `idAgence` int(11) DEFAULT NULL,
  `idUtilisateur` int(11) DEFAULT NULL,
  `matricule` varchar(7) DEFAULT NULL,
  PRIMARY KEY (`idDevis`),
  KEY `Devis_Agence_FK` (`idAgence`),
  KEY `Devis_Client0_FK` (`idUtilisateur`),
  KEY `Devis_Vehicule1_FK` (`matricule`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `devis`
--

INSERT INTO `devis` (`idDevis`, `montantReduction`, `dureePrevueLocation`, `idAgence`, `idUtilisateur`, `matricule`) VALUES
(1, 10, 6, 1, 3, 'AA125FF'),
(2, 10, 6, 1, 3, 'AA125EE'),
(6, 10, 6, 1, 3, 'AA125AA'),
(7, 10, 6, 1, 3, 'AA125FF'),
(8, 10, 6, 1, 3, 'AA125BB'),
(9, 10, 6, 1, 3, 'AA125CC'),
(10, 10, 6, 1, 3, 'AA125EE'),
(11, 10, 6, 1, 3, 'AA125DD'),
(13, 10, 6, 1, 3, 'AA125FF'),
(14, 10, 6, 1, 3, 'AA125AA'),
(15, 10, 6, 1, 3, 'AA125BB'),
(16, 10, 6, 1, 3, 'AA125CC'),
(17, 10, 6, 1, 3, 'AA125EE'),
(18, 10, 6, 1, 3, 'AA125DD'),
(19, 10, 6, 1, 3, 'AA125EE'),
(20, 10, 6, 1, 3, 'AA125AA'),
(21, 10, 6, 1, 3, 'AA125BB'),
(22, 10, 6, 1, 3, 'AA125CC'),
(23, 10, 6, 1, 3, 'AA125DD'),
(24, 10, 6, 1, 3, 'AA125EE'),
(25, 10, 3, 1, 3, 'AA125AA'),
(26, 10, 3, 1, 3, 'AA125BB'),
(27, 10, 3, 1, 3, 'AA125CC'),
(28, 10, 3, 1, 3, 'AA125DD'),
(29, 10, 3, 1, 3, 'AA125EE'),
(30, 10, 3, 1, 3, 'AA125FF'),
(31, 10, 6, 1, 3, 'AA125AA'),
(32, 10, 6, 1, 3, 'AA125AA'),
(33, 10, 6, 1, 3, 'AA125AA'),
(34, 10, 6, 1, 3, 'AA125AA'),
(35, 10, 6, 1, 3, 'AA125AA'),
(36, 10, 6, 1, 3, 'AA125AA'),
(37, 10, 6, 1, 3, 'AA125AA'),
(38, 10, 6, 1, 3, 'AA125AA'),
(39, 10, 6, 1, 3, 'AA125AA'),
(40, 10, 2, 1, 3, 'AA125AA'),
(41, 10, 6, 1, 3, 'AA125AA'),
(42, 10, 3, 1, 3, 'AA125BB');

-- --------------------------------------------------------

--
-- Structure de la table `employe`
--

DROP TABLE IF EXISTS `employe`;
CREATE TABLE IF NOT EXISTS `employe` (
  `idUtilisateur` int(11) NOT NULL,
  `login` varchar(50) NOT NULL,
  `motDePasse` varchar(50) NOT NULL,
  `idAgence` int(11) NOT NULL,
  PRIMARY KEY (`idUtilisateur`),
  KEY `Employe_Agence0_FK` (`idAgence`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `employe`
--

INSERT INTO `employe` (`idUtilisateur`, `login`, `motDePasse`, `idAgence`) VALUES
(1, 'durand', 'durand', 1),
(2, 'dupuis', 'dupuis', 1);

-- --------------------------------------------------------

--
-- Structure de la table `facture`
--

DROP TABLE IF EXISTS `facture`;
CREATE TABLE IF NOT EXISTS `facture` (
  `idFacture` int(11) NOT NULL AUTO_INCREMENT,
  `dureeEffective` int(11) NOT NULL,
  `consomationCarburant` float NOT NULL,
  `etatVehicule` int(11) NOT NULL,
  `idAgence` int(11) DEFAULT NULL,
  `idUtilisateur` int(11) NOT NULL,
  `frais_remise` int(11) NOT NULL,
  PRIMARY KEY (`idFacture`),
  KEY `Facture_Agence_FK` (`idAgence`),
  KEY `Facture_Client0_FK` (`idUtilisateur`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `facture`
--

INSERT INTO `facture` (`idFacture`, `dureeEffective`, `consomationCarburant`, `etatVehicule`, `idAgence`, `idUtilisateur`, `frais_remise`) VALUES
(1, 0, 8000, 0, 1, 3, 40),
(2, 0, 4000, 0, 1, 3, 20),
(3, 0, 31, 3, 1, 3, 3),
(4, 0, 38.7932, 1, 1, 3, 60),
(5, 0, 13.8931, 2, 1, 3, 70),
(6, 0, 2.43049, 2, 1, 3, 10),
(7, 0, 9.63181, 0, 1, 3, 40),
(8, 0, 19.4184, 3, 1, 3, 0),
(9, 0, 10.1713, 0, 1, 3, 20),
(10, 0, 26.0675, 5, 1, 3, 100),
(11, 0, 34.4652, 5, 1, 3, 80),
(12, 0, 39.6332, 5, 1, 3, 100),
(13, 0, 25.7048, 5, 1, 3, 40),
(14, 1, 35.072, 5, 1, 3, 40),
(15, 1, 5.24337, 5, 1, 3, 60),
(16, 1, 39.1392, 5, 1, 3, 80),
(17, 0, 14.2809, 5, 1, 3, 60),
(18, 9, 1.91905, 5, 1, 3, 40);

-- --------------------------------------------------------

--
-- Structure de la table `gerant`
--

DROP TABLE IF EXISTS `gerant`;
CREATE TABLE IF NOT EXISTS `gerant` (
  `idUtilisateur` int(11) NOT NULL,
  PRIMARY KEY (`idUtilisateur`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `gerant`
--

INSERT INTO `gerant` (`idUtilisateur`) VALUES
(1);

-- --------------------------------------------------------

--
-- Structure de la table `loue`
--

DROP TABLE IF EXISTS `loue`;
CREATE TABLE IF NOT EXISTS `loue` (
  `matricule` varchar(7) NOT NULL,
  `idUtilisateur` int(11) NOT NULL,
  `assurance` tinyint(1) NOT NULL,
  `duree` int(11) NOT NULL,
  `datePriseDeReservation` date NOT NULL,
  PRIMARY KEY (`matricule`,`idUtilisateur`),
  KEY `loue_Client0_FK` (`idUtilisateur`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `loue`
--

INSERT INTO `loue` (`matricule`, `idUtilisateur`, `assurance`, `duree`, `datePriseDeReservation`) VALUES
('AA125BB', 3, 0, 3, '2021-05-31');

-- --------------------------------------------------------

--
-- Structure de la table `programmefidelite`
--

DROP TABLE IF EXISTS `programmefidelite`;
CREATE TABLE IF NOT EXISTS `programmefidelite` (
  `idProgrammeFidelite` int(11) NOT NULL AUTO_INCREMENT,
  `duree` int(11) NOT NULL,
  `description` text NOT NULL,
  `prix` float NOT NULL,
  PRIMARY KEY (`idProgrammeFidelite`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `programmefidelite`
--

INSERT INTO `programmefidelite` (`idProgrammeFidelite`, `duree`, `description`, `prix`) VALUES
(1, 6, '10% de réduction pour les membres sur notre tarif public pour une location de voiture ou d\'utilitaire de 3 jours et plus\r\n\r\nRéservation sécurisée - nous réservons votre voiture et la gardons même en cas de retard', 100),
(2, 6, 'Processus de prise en charge plus rapide du véhicule\r\n\r\nUn week-end de location gratuit dès que vous passez au niveau Executive', 100),
(3, 6, 'Un surclassement gratuit d\'une catégorie sur l\'ensemble de vos locations\r\n\r\nUn week-end de location gratuit supplémentaire après votre 3ème location ', 100);

-- --------------------------------------------------------

--
-- Structure de la table `reserve`
--

DROP TABLE IF EXISTS `reserve`;
CREATE TABLE IF NOT EXISTS `reserve` (
  `idUtilisateur` int(11) NOT NULL,
  `matricule` varchar(7) NOT NULL,
  `dateReservation` date NOT NULL,
  `datePriseDeReservation` date NOT NULL,
  PRIMARY KEY (`idUtilisateur`,`matricule`),
  KEY `reserve_Vehicule0_FK` (`matricule`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `reserve`
--

INSERT INTO `reserve` (`idUtilisateur`, `matricule`, `dateReservation`, `datePriseDeReservation`) VALUES
(3, 'AA125AA', '2022-05-05', '2021-05-30'),
(3, 'AA125BB', '2021-05-13', '2021-05-31'),
(4, 'AA125DD', '2021-05-07', '2021-05-31');

-- --------------------------------------------------------

--
-- Structure de la table `retour`
--

DROP TABLE IF EXISTS `retour`;
CREATE TABLE IF NOT EXISTS `retour` (
  `idRetour` int(11) NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `etatOrigine` int(11) NOT NULL,
  `idUtilisateur` int(11) NOT NULL,
  `matricule` varchar(7) NOT NULL,
  PRIMARY KEY (`idRetour`),
  KEY `Retour_Utilisateur_FK` (`idUtilisateur`),
  KEY `Retour_Vehicule_FK` (`matricule`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `retour`
--

INSERT INTO `retour` (`idRetour`, `date`, `etatOrigine`, `idUtilisateur`, `matricule`) VALUES
(1, '2021-05-31', 4, 3, 'AA125FF'),
(2, '2021-05-31', 4, 3, 'AA125EE'),
(7, '2021-05-31', 5, 3, 'AA125EE'),
(8, '2021-05-31', 5, 3, 'AA125DD'),
(9, '2021-05-31', 5, 3, 'AA125CC'),
(11, '2021-05-31', 4, 3, 'AA125FF'),
(12, '2021-05-31', 4, 3, 'AA125DD'),
(13, '2021-05-31', 4, 3, 'AA125CC'),
(15, '2021-05-31', 5, 3, 'AA125FF'),
(16, '2021-05-31', 5, 3, 'AA125DD'),
(17, '2021-05-31', 5, 3, 'AA125CC'),
(21, '2021-05-31', 2, 3, 'AA125FF'),
(22, '2021-05-31', 2, 3, 'AA125EE'),
(23, '2021-05-31', 2, 3, 'AA125CC'),
(24, '2021-05-31', 2, 3, 'AA125CC'),
(25, '2021-05-31', 2, 3, 'AA125CC'),
(26, '2021-05-31', 2, 3, 'AA125BB'),
(27, '2021-05-31', 2, 3, 'AA125AA'),
(28, '2021-05-31', 2, 3, 'AA125CC'),
(29, '2021-05-31', 2, 4, 'AA125CC'),
(31, '2021-05-31', 3, 3, 'AA125FF'),
(32, '2021-05-31', 3, 3, 'AA125EE'),
(33, '2021-05-31', 3, 3, 'AA125CC'),
(34, '2021-05-31', 3, 3, 'AA125DD'),
(35, '2021-05-31', 3, 3, 'AA125DD'),
(36, '2021-05-31', 3, 3, 'AA125AA'),
(37, '2021-05-31', 3, 3, 'AA125BB'),
(38, '2021-05-31', 3, 3, 'AA125EE'),
(39, '2021-05-31', 4, 3, 'AA125AA'),
(40, '2021-05-31', 4, 3, 'AA125BB'),
(41, '2021-05-31', 4, 3, 'AA125CC'),
(42, '2021-05-31', 4, 3, 'AA125DD'),
(43, '2021-05-31', 4, 3, 'AA125EE'),
(44, '2021-05-31', 4, 3, 'AA125FF'),
(46, '2021-05-31', 4, 3, 'AA125AA'),
(47, '2021-05-31', 3, 3, 'AA125EE'),
(48, '2021-05-31', 2, 3, 'AA125BB'),
(49, '2021-05-31', 2, 3, 'AA125CC'),
(50, '2021-05-31', 4, 3, 'AA125DD'),
(51, '2021-05-31', 4, 3, 'AA125AA'),
(52, '2021-05-31', 3, 3, 'AA125BB'),
(53, '2021-05-31', 3, 3, 'AA125CC'),
(54, '2021-05-31', 3, 3, 'AA125DD'),
(55, '2021-05-31', 2, 3, 'AA125EE'),
(56, '2021-06-27', 3, 3, 'AA125AA'),
(57, '2021-06-23', 2, 3, 'AA125AA'),
(58, '2021-06-23', 3, 3, 'AA125AA'),
(59, '2021-06-23', 4, 3, 'AA125AA'),
(60, '2021-05-13', 3, 3, 'AA125AA'),
(61, '2021-05-05', 3, 3, 'AA125AA'),
(62, '2021-05-06', 2, 3, 'AA125AA'),
(63, '2021-06-23', 3, 3, 'AA125AA'),
(64, '2021-06-24', 3, 3, 'AA125AA'),
(65, '2021-05-20', 4, 3, 'AA125AA'),
(66, '2021-05-22', 3, 3, 'AA125AA');

-- --------------------------------------------------------

--
-- Structure de la table `souscrire`
--

DROP TABLE IF EXISTS `souscrire`;
CREATE TABLE IF NOT EXISTS `souscrire` (
  `idUtilisateur` int(11) NOT NULL,
  `idProgrammeFidelite` int(11) NOT NULL,
  `dateSouscription` date NOT NULL,
  `tauxReduction` float DEFAULT NULL,
  PRIMARY KEY (`idUtilisateur`,`idProgrammeFidelite`),
  KEY `souscrire_ProgrammeFidelite0_FK` (`idProgrammeFidelite`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `souscrire`
--

INSERT INTO `souscrire` (`idUtilisateur`, `idProgrammeFidelite`, `dateSouscription`, `tauxReduction`) VALUES
(3, 1, '2021-05-30', 0),
(4, 1, '2021-05-31', 25),
(4, 2, '2021-05-31', 0),
(4, 3, '2021-05-30', 0);

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

DROP TABLE IF EXISTS `utilisateur`;
CREATE TABLE IF NOT EXISTS `utilisateur` (
  `idUtilisateur` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(50) NOT NULL,
  `prenom` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `telephone` char(10) NOT NULL,
  `idAdresse` varchar(50) NOT NULL,
  PRIMARY KEY (`idUtilisateur`),
  KEY `Utilisateur_Adresse_FK` (`idAdresse`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`idUtilisateur`, `nom`, `prenom`, `email`, `telephone`, `idAdresse`) VALUES
(1, 'Durand', 'Royden', 'roydendurand@gmail.com', '0662158265', '55  Rue du Limas'),
(2, 'Dupuis', 'Arnaude', 'arnaudedupuis@gmail.com', '0665982374', '57  Place du Jeu de Paume'),
(3, 'Létourneau', 'Ninette', 'ninetteletourneau@gmail.com', '0635946782', '93  Rue de Verdun'),
(4, 'Garceau', 'Rabican', 'rabicangarceau@gmail.com', '0695364875', '94  Avenue des Pr'),
(9, 'aze', 'aze', 'azezeze', 'aze', 'aze');

-- --------------------------------------------------------

--
-- Structure de la table `vehicule`
--

DROP TABLE IF EXISTS `vehicule`;
CREATE TABLE IF NOT EXISTS `vehicule` (
  `matricule` varchar(7) NOT NULL,
  `marque` varchar(50) NOT NULL,
  `modele` varchar(50) NOT NULL,
  `kilometrage` int(11) NOT NULL,
  `climatisation` tinyint(1) NOT NULL,
  `typeBoiteDeVitesse` char(11) NOT NULL,
  `type` char(10) NOT NULL,
  `idAgence` int(11) NOT NULL,
  PRIMARY KEY (`matricule`),
  KEY `Vehicule_CategorieVehicule_FK` (`type`),
  KEY `Vehicule_Agence_FK` (`idAgence`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `vehicule`
--

INSERT INTO `vehicule` (`matricule`, `marque`, `modele`, `kilometrage`, `climatisation`, `typeBoiteDeVitesse`, `type`, `idAgence`) VALUES
('AA125AA', 'Renault', 'Clio', 15000, 1, 'Manuelle', 'Essence', 1),
('AA125BB', 'Renault', 'Clio', 50000, 0, 'Manuelle', 'Essence', 1),
('AA125CC', 'Tesla', 'S', 10000, 1, 'Automatique', 'Electrique', 1),
('AA125DD', 'Tesla', 'S', 5000, 1, 'Automatique', 'Electrique', 1),
('AA125EE', 'Pegeot', '208', 60000, 1, 'Automatique', 'Diesel', 1),
('AA125FF', 'Pegeot', '208', 100000, 1, 'Automatique', 'Diesel', 1),
('AA125GG', 'Mercedes', 'Classe A', 42000, 1, 'Automatique', 'Diesel', 1);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `chauffeur`
--
ALTER TABLE `chauffeur`
  ADD CONSTRAINT `Chauffeur_Employe_FK` FOREIGN KEY (`idUtilisateur`) REFERENCES `employe` (`idUtilisateur`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `circuit`
--
ALTER TABLE `circuit`
  ADD CONSTRAINT `Circuit_Agence_deux_FK` FOREIGN KEY (`idAgence_arrive`) REFERENCES `agence` (`idAgence`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `Circuit_Agence_un_FK` FOREIGN KEY (`idAgence_depart`) REFERENCES `agence` (`idAgence`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `client`
--
ALTER TABLE `client`
  ADD CONSTRAINT `Client_Utilisateur_FK` FOREIGN KEY (`idUtilisateur`) REFERENCES `utilisateur` (`idUtilisateur`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `devis`
--
ALTER TABLE `devis`
  ADD CONSTRAINT `Devis_Agence_FK` FOREIGN KEY (`idAgence`) REFERENCES `agence` (`idAgence`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `Devis_Client0_FK` FOREIGN KEY (`idUtilisateur`) REFERENCES `client` (`idUtilisateur`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `Devis_Vehicule1_FK` FOREIGN KEY (`matricule`) REFERENCES `vehicule` (`matricule`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `employe`
--
ALTER TABLE `employe`
  ADD CONSTRAINT `Employe_Agence0_FK` FOREIGN KEY (`idAgence`) REFERENCES `agence` (`idAgence`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `Employe_Utilisateur_FK` FOREIGN KEY (`idUtilisateur`) REFERENCES `utilisateur` (`idUtilisateur`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `facture`
--
ALTER TABLE `facture`
  ADD CONSTRAINT `Facture_Agence_FK` FOREIGN KEY (`idAgence`) REFERENCES `agence` (`idAgence`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `Facture_Client0_FK` FOREIGN KEY (`idUtilisateur`) REFERENCES `client` (`idUtilisateur`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `gerant`
--
ALTER TABLE `gerant`
  ADD CONSTRAINT `Gerant_Employe_FK` FOREIGN KEY (`idUtilisateur`) REFERENCES `employe` (`idUtilisateur`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `loue`
--
ALTER TABLE `loue`
  ADD CONSTRAINT `loue_Client0_FK` FOREIGN KEY (`idUtilisateur`) REFERENCES `client` (`idUtilisateur`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `loue_Vehicule_FK` FOREIGN KEY (`matricule`) REFERENCES `vehicule` (`matricule`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `reserve`
--
ALTER TABLE `reserve`
  ADD CONSTRAINT `reserve_Client_FK` FOREIGN KEY (`idUtilisateur`) REFERENCES `client` (`idUtilisateur`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `reserve_Vehicule0_FK` FOREIGN KEY (`matricule`) REFERENCES `vehicule` (`matricule`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `retour`
--
ALTER TABLE `retour`
  ADD CONSTRAINT `Retour_Utilisateur_FK` FOREIGN KEY (`idUtilisateur`) REFERENCES `utilisateur` (`idUtilisateur`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `Retour_Vehicule_FK` FOREIGN KEY (`matricule`) REFERENCES `vehicule` (`matricule`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `souscrire`
--
ALTER TABLE `souscrire`
  ADD CONSTRAINT `souscrire_Client_FK` FOREIGN KEY (`idUtilisateur`) REFERENCES `client` (`idUtilisateur`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `souscrire_ProgrammeFidelite0_FK` FOREIGN KEY (`idProgrammeFidelite`) REFERENCES `programmefidelite` (`idProgrammeFidelite`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `vehicule`
--
ALTER TABLE `vehicule`
  ADD CONSTRAINT `Vehicule_Agence_FK` FOREIGN KEY (`idAgence`) REFERENCES `agence` (`idAgence`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `Vehicule_CategorieVehicule_FK` FOREIGN KEY (`type`) REFERENCES `categorievehicule` (`type`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
