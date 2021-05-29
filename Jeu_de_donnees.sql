-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : sam. 29 mai 2021 à 09:31
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `agence`
--

INSERT INTO `agence` (`idAgence`, `nom`, `telephone`, `coordonneesGPS`, `nombreMaxVehicules`, `idAdresse`) VALUES
(1, 'RentCar', '0162354978', '47.21667,2.08333', 25, '35  Place du Jeu de Paume');

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
  `dureeEffective` time NOT NULL,
  `consomationCarburant` float NOT NULL,
  `etatVehicule` varchar(50) NOT NULL,
  `idAgence` int(11) DEFAULT NULL,
  `idUtilisateur` int(11) NOT NULL,
  PRIMARY KEY (`idFacture`),
  KEY `Facture_Agence_FK` (`idAgence`),
  KEY `Facture_Client0_FK` (`idUtilisateur`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
  `duree` time NOT NULL,
  `datePriseDeResevration` date NOT NULL,
  PRIMARY KEY (`matricule`,`idUtilisateur`),
  KEY `loue_Client0_FK` (`idUtilisateur`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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

-- --------------------------------------------------------

--
-- Structure de la table `retourne`
--

DROP TABLE IF EXISTS `retourne`;
CREATE TABLE IF NOT EXISTS `retourne` (
  `matricule` varchar(7) NOT NULL,
  `idUtilisateur` int(11) NOT NULL,
  `date` date NOT NULL,
  `etatOrigine` tinyint(1) NOT NULL,
  PRIMARY KEY (`matricule`,`idUtilisateur`),
  KEY `retourne_Client0_FK` (`idUtilisateur`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `souscrire`
--

DROP TABLE IF EXISTS `souscrire`;
CREATE TABLE IF NOT EXISTS `souscrire` (
  `idUtilisateur` int(11) NOT NULL,
  `idProgrammeFidelite` int(11) NOT NULL,
  `dateSoucription` date NOT NULL,
  `tauxReduction` float DEFAULT NULL,
  PRIMARY KEY (`idUtilisateur`,`idProgrammeFidelite`),
  KEY `souscrire_ProgrammeFidelite0_FK` (`idProgrammeFidelite`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`idUtilisateur`, `nom`, `prenom`, `email`, `telephone`, `idAdresse`) VALUES
(1, 'Durand', 'Royden', 'roydendurand@gmail.com', '0662158265', '55  Rue du Limas'),
(2, 'Dupuis', 'Arnaude', 'arnaudedupuis@gmail.com', '0665982374', '57  Place du Jeu de Paume'),
(3, 'Létourneau', 'Ninette', 'ninetteletourneau@gmail.com', '0635946782', '93  Rue de Verdun'),
(4, 'Garceau', 'Rabican', 'rabicangarceau@gmail.com', '0695364875', '94  Avenue des Pr');

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
  PRIMARY KEY (`matricule`),
  KEY `Vehicule_CategorieVehicule_FK` (`type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `vehicule`
--

INSERT INTO `vehicule` (`matricule`, `marque`, `modele`, `kilometrage`, `climatisation`, `typeBoiteDeVitesse`, `type`) VALUES
('AA125AA', 'Renault', 'Clio', 15000, 1, 'Manuelle', 'Essence'),
('AA125BB', 'Renault', 'Clio', 50000, 0, 'Manuelle', 'Essence'),
('AA125CC', 'Tesla', 'S', 10000, 1, 'Automatique', 'Electrique'),
('AA125DD', 'Tesla', 'S', 5000, 1, 'Automatique', 'Electrique'),
('AA125EE', 'Pegeot', '208', 60000, 1, 'Automatique', 'Diesel'),
('AA125FF', 'Pegeot', '208', 100000, 1, 'Automatique', 'Diesel'),
('AA125GG', 'Mercedes', 'Classe A', 42000, 1, 'Automatique', 'Diesel');

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `agence`
--
ALTER TABLE `agence`
  ADD CONSTRAINT `Agence_Adresse_FK` FOREIGN KEY (`idAdresse`) REFERENCES `adresse` (`idAdresse`);

--
-- Contraintes pour la table `chauffeur`
--
ALTER TABLE `chauffeur`
  ADD CONSTRAINT `Chauffeur_Employe_FK` FOREIGN KEY (`idUtilisateur`) REFERENCES `employe` (`idUtilisateur`);

--
-- Contraintes pour la table `client`
--
ALTER TABLE `client`
  ADD CONSTRAINT `Client_Utilisateur_FK` FOREIGN KEY (`idUtilisateur`) REFERENCES `utilisateur` (`idUtilisateur`);

--
-- Contraintes pour la table `devis`
--
ALTER TABLE `devis`
  ADD CONSTRAINT `Devis_Agence_FK` FOREIGN KEY (`idAgence`) REFERENCES `agence` (`idAgence`),
  ADD CONSTRAINT `Devis_Client0_FK` FOREIGN KEY (`idUtilisateur`) REFERENCES `client` (`idUtilisateur`),
  ADD CONSTRAINT `Devis_Vehicule1_FK` FOREIGN KEY (`matricule`) REFERENCES `vehicule` (`matricule`);

--
-- Contraintes pour la table `employe`
--
ALTER TABLE `employe`
  ADD CONSTRAINT `Employe_Agence0_FK` FOREIGN KEY (`idAgence`) REFERENCES `agence` (`idAgence`),
  ADD CONSTRAINT `Employe_Utilisateur_FK` FOREIGN KEY (`idUtilisateur`) REFERENCES `utilisateur` (`idUtilisateur`);

--
-- Contraintes pour la table `facture`
--
ALTER TABLE `facture`
  ADD CONSTRAINT `Facture_Agence_FK` FOREIGN KEY (`idAgence`) REFERENCES `agence` (`idAgence`),
  ADD CONSTRAINT `Facture_Client0_FK` FOREIGN KEY (`idUtilisateur`) REFERENCES `client` (`idUtilisateur`);

--
-- Contraintes pour la table `gerant`
--
ALTER TABLE `gerant`
  ADD CONSTRAINT `Gerant_Employe_FK` FOREIGN KEY (`idUtilisateur`) REFERENCES `employe` (`idUtilisateur`);

--
-- Contraintes pour la table `loue`
--
ALTER TABLE `loue`
  ADD CONSTRAINT `loue_Client0_FK` FOREIGN KEY (`idUtilisateur`) REFERENCES `client` (`idUtilisateur`),
  ADD CONSTRAINT `loue_Vehicule_FK` FOREIGN KEY (`matricule`) REFERENCES `vehicule` (`matricule`);

--
-- Contraintes pour la table `reserve`
--
ALTER TABLE `reserve`
  ADD CONSTRAINT `reserve_Client_FK` FOREIGN KEY (`idUtilisateur`) REFERENCES `client` (`idUtilisateur`),
  ADD CONSTRAINT `reserve_Vehicule0_FK` FOREIGN KEY (`matricule`) REFERENCES `vehicule` (`matricule`);

--
-- Contraintes pour la table `retourne`
--
ALTER TABLE `retourne`
  ADD CONSTRAINT `retourne_Client0_FK` FOREIGN KEY (`idUtilisateur`) REFERENCES `client` (`idUtilisateur`),
  ADD CONSTRAINT `retourne_Vehicule_FK` FOREIGN KEY (`matricule`) REFERENCES `vehicule` (`matricule`);

--
-- Contraintes pour la table `souscrire`
--
ALTER TABLE `souscrire`
  ADD CONSTRAINT `souscrire_Client_FK` FOREIGN KEY (`idUtilisateur`) REFERENCES `client` (`idUtilisateur`),
  ADD CONSTRAINT `souscrire_ProgrammeFidelite0_FK` FOREIGN KEY (`idProgrammeFidelite`) REFERENCES `programmefidelite` (`idProgrammeFidelite`);

--
-- Contraintes pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD CONSTRAINT `Utilisateur_Adresse_FK` FOREIGN KEY (`idAdresse`) REFERENCES `adresse` (`idAdresse`);

--
-- Contraintes pour la table `vehicule`
--
ALTER TABLE `vehicule`
  ADD CONSTRAINT `Vehicule_CategorieVehicule_FK` FOREIGN KEY (`type`) REFERENCES `categorievehicule` (`type`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
