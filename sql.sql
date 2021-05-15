#------------------------------------------------------------
#        Script MySQL.
#------------------------------------------------------------


#------------------------------------------------------------
# Table: Utilisateur
#------------------------------------------------------------

CREATE TABLE Utilisateur(
        idUtilisateur Int  Auto_increment  NOT NULL ,
        nom           Varchar (50) NOT NULL ,
        prenom        Varchar (50) NOT NULL ,
        email         Varchar (50) NOT NULL ,
        adresse       Varchar (50) NOT NULL ,
        telephone     Char (10) NOT NULL
	,CONSTRAINT Utilisateur_PK PRIMARY KEY (idUtilisateur)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Vehicule
#------------------------------------------------------------

CREATE TABLE Vehicule(
        matricule        Varchar (7) NOT NULL ,
        marque           Varchar (50) NOT NULL ,
        modele           Varchar (50) NOT NULL ,
        kilometrage      Int NOT NULL ,
        typeBoiteVitesse Char (11) NOT NULL ,
        climatisation    Bool NOT NULL ,
        typeCarburant    Varchar (15) NOT NULL
	,CONSTRAINT Vehicule_PK PRIMARY KEY (matricule)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: CategorieVehicule
#------------------------------------------------------------

CREATE TABLE CategorieVehicule(
        type      Char (9) NOT NULL ,
        tarif     Float NOT NULL ,
        caution   Float NOT NULL ,
        matricule Varchar (7) NOT NULL
	,CONSTRAINT CategorieVehicule_PK PRIMARY KEY (type)

	,CONSTRAINT CategorieVehicule_Vehicule_FK FOREIGN KEY (matricule) REFERENCES Vehicule(matricule)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Client
#------------------------------------------------------------

CREATE TABLE Client(
        idUtilisateur Int NOT NULL ,
        nom           Varchar (50) NOT NULL ,
        prenom        Varchar (50) NOT NULL ,
        email         Varchar (50) NOT NULL ,
        adresse       Varchar (50) NOT NULL ,
        telephone     Char (10) NOT NULL
	,CONSTRAINT Client_PK PRIMARY KEY (idUtilisateur)

	,CONSTRAINT Client_Utilisateur_FK FOREIGN KEY (idUtilisateur) REFERENCES Utilisateur(idUtilisateur)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: ProgrammeFidelite
#------------------------------------------------------------

CREATE TABLE ProgrammeFidelite(
        refProgrammeFidelite Int  Auto_increment  NOT NULL ,
        duree                Int NOT NULL ,
        description          Text NOT NULL ,
        prix                 Float NOT NULL ,
        tauxReduction        Float NOT NULL ,
        dateSoucription      Date NOT NULL ,
        idUtilisateur        Int NOT NULL
	,CONSTRAINT ProgrammeFidelite_PK PRIMARY KEY (refProgrammeFidelite)

	,CONSTRAINT ProgrammeFidelite_Client_FK FOREIGN KEY (idUtilisateur) REFERENCES Client(idUtilisateur)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Agence
#------------------------------------------------------------

CREATE TABLE Agence(
        identifiantAgence  Int  Auto_increment  NOT NULL ,
        nom                Varchar (50) NOT NULL ,
        telephone          Char (10) NOT NULL ,
        adresse            Varchar (50) NOT NULL ,
        coordonneesGPS     Varchar (50) NOT NULL ,
        nombreMaxVehicules Int NOT NULL
	,CONSTRAINT Agence_PK PRIMARY KEY (identifiantAgence)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Employe
#------------------------------------------------------------

CREATE TABLE Employe(
        idUtilisateur     Int NOT NULL ,
        login             Varchar (50) NOT NULL ,
        motDePasse        Varchar (50) NOT NULL ,
        nom               Varchar (50) NOT NULL ,
        prenom            Varchar (50) NOT NULL ,
        email             Varchar (50) NOT NULL ,
        adresse           Varchar (50) NOT NULL ,
        telephone         Char (10) NOT NULL ,
        identifiantAgence Int NOT NULL
	,CONSTRAINT Employe_PK PRIMARY KEY (idUtilisateur)

	,CONSTRAINT Employe_Utilisateur_FK FOREIGN KEY (idUtilisateur) REFERENCES Utilisateur(idUtilisateur)
	,CONSTRAINT Employe_Agence0_FK FOREIGN KEY (identifiantAgence) REFERENCES Agence(identifiantAgence)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Chauffeur
#------------------------------------------------------------

CREATE TABLE Chauffeur(
        idUtilisateur     Int NOT NULL ,
        nom               Varchar (50) NOT NULL ,
        prenom            Varchar (50) NOT NULL ,
        email             Varchar (50) NOT NULL ,
        adresse           Varchar (50) NOT NULL ,
        telephone         Char (10) NOT NULL ,
        login             Varchar (50) NOT NULL ,
        motDePasse        Varchar (50) NOT NULL ,
        identifiantAgence Int NOT NULL
	,CONSTRAINT Chauffeur_PK PRIMARY KEY (idUtilisateur)

	,CONSTRAINT Chauffeur_Employe_FK FOREIGN KEY (idUtilisateur) REFERENCES Employe(idUtilisateur)
	,CONSTRAINT Chauffeur_Agence0_FK FOREIGN KEY (identifiantAgence) REFERENCES Agence(identifiantAgence)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Location
#------------------------------------------------------------

CREATE TABLE Location(
        refLocation   Int  Auto_increment  NOT NULL ,
        duree         Time NOT NULL ,
        assurance     Bool NOT NULL ,
        idUtilisateur Int NOT NULL ,
        matricule     Varchar (7) NOT NULL
	,CONSTRAINT Location_PK PRIMARY KEY (refLocation)

	,CONSTRAINT Location_Client_FK FOREIGN KEY (idUtilisateur) REFERENCES Client(idUtilisateur)
	,CONSTRAINT Location_Vehicule0_FK FOREIGN KEY (matricule) REFERENCES Vehicule(matricule)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Facture
#------------------------------------------------------------

CREATE TABLE Facture(
        idFacture            Int  Auto_increment  NOT NULL ,
        dureeEffective       Time NOT NULL ,
        consomationCarburant Float NOT NULL ,
        etatVehicule         Varchar (50) NOT NULL ,
        identifiantAgence    Int ,
        idUtilisateur        Int NOT NULL
	,CONSTRAINT Facture_PK PRIMARY KEY (idFacture)

	,CONSTRAINT Facture_Agence_FK FOREIGN KEY (identifiantAgence) REFERENCES Agence(identifiantAgence)
	,CONSTRAINT Facture_Client0_FK FOREIGN KEY (idUtilisateur) REFERENCES Client(idUtilisateur)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Gerant
#------------------------------------------------------------

CREATE TABLE Gerant(
        idUtilisateur     Int NOT NULL ,
        nom               Varchar (50) NOT NULL ,
        prenom            Varchar (50) NOT NULL ,
        email             Varchar (50) NOT NULL ,
        adresse           Varchar (50) NOT NULL ,
        telephone         Char (10) NOT NULL ,
        login             Varchar (50) NOT NULL ,
        motDePasse        Varchar (50) NOT NULL ,
        identifiantAgence Int NOT NULL
	,CONSTRAINT Gerant_PK PRIMARY KEY (idUtilisateur)

	,CONSTRAINT Gerant_Employe_FK FOREIGN KEY (idUtilisateur) REFERENCES Employe(idUtilisateur)
	,CONSTRAINT Gerant_Agence0_FK FOREIGN KEY (identifiantAgence) REFERENCES Agence(identifiantAgence)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Devis
#------------------------------------------------------------

CREATE TABLE Devis(
        idDevis             Int  Auto_increment  NOT NULL ,
        montantReduction    Float NOT NULL ,
        dureePrevueLocation Int NOT NULL ,
        identifiantAgence   Int ,
        idUtilisateur       Int
	,CONSTRAINT Devis_PK PRIMARY KEY (idDevis)

	,CONSTRAINT Devis_Agence_FK FOREIGN KEY (identifiantAgence) REFERENCES Agence(identifiantAgence)
	,CONSTRAINT Devis_Client0_FK FOREIGN KEY (idUtilisateur) REFERENCES Client(idUtilisateur)
)ENGINE=InnoDB;

