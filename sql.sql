#------------------------------------------------------------
#        Script MySQL.
#------------------------------------------------------------


#------------------------------------------------------------
# Table: Utilisateur
#------------------------------------------------------------

CREATE TABLE Utilisateur(
        id_utilisateur Int  Auto_increment  NOT NULL ,
        nom            Varchar (50) NOT NULL ,
        prenom         Varchar (50) NOT NULL ,
        email          Varchar (50) NOT NULL ,
        adresse        Varchar (50) NOT NULL ,
        telephone      Char (10) NOT NULL
	,CONSTRAINT Utilisateur_PK PRIMARY KEY (id_utilisateur)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Vehicule
#------------------------------------------------------------

CREATE TABLE Vehicule(
        matricule          Varchar (7) NOT NULL ,
        marque             Varchar (50) NOT NULL ,
        modele             Varchar (50) NOT NULL ,
        kilometrage        Int NOT NULL ,
        type_boite_vitesse Char (11) NOT NULL ,
        climatisation      Bool NOT NULL ,
        type_carburant     Varchar (15) NOT NULL
	,CONSTRAINT Vehicule_PK PRIMARY KEY (matricule)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: CategorieVehicule
#------------------------------------------------------------

CREATE TABLE CategorieVehicule(
        type      Char (9) NOT NULL ,
        tarif     Float NOT NULL ,
        caution   Float NOT NULL ,
        matricule Varchar (7)
	,CONSTRAINT CategorieVehicule_PK PRIMARY KEY (type)

	,CONSTRAINT CategorieVehicule_Vehicule_FK FOREIGN KEY (matricule) REFERENCES Vehicule(matricule)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Employe
#------------------------------------------------------------

CREATE TABLE Employe(
        id_utilisateur Int NOT NULL ,
        login          Varchar (50) NOT NULL ,
        mot_de_passe   Varchar (50) NOT NULL ,
        nom            Varchar (50) NOT NULL ,
        prenom         Varchar (50) NOT NULL ,
        email          Varchar (50) NOT NULL ,
        adresse        Varchar (50) NOT NULL ,
        telephone      Char (10) NOT NULL
	,CONSTRAINT Employe_PK PRIMARY KEY (id_utilisateur)

	,CONSTRAINT Employe_Utilisateur_FK FOREIGN KEY (id_utilisateur) REFERENCES Utilisateur(id_utilisateur)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Client
#------------------------------------------------------------

CREATE TABLE Client(
        id_utilisateur Int NOT NULL ,
        nom            Varchar (50) NOT NULL ,
        prenom         Varchar (50) NOT NULL ,
        email          Varchar (50) NOT NULL ,
        adresse        Varchar (50) NOT NULL ,
        telephone      Char (10) NOT NULL
	,CONSTRAINT Client_PK PRIMARY KEY (id_utilisateur)

	,CONSTRAINT Client_Utilisateur_FK FOREIGN KEY (id_utilisateur) REFERENCES Utilisateur(id_utilisateur)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: ProgrammeFidelite
#------------------------------------------------------------

CREATE TABLE ProgrammeFidelite(
        id_programme_fidelite Int  Auto_increment  NOT NULL ,
        duree                 Int NOT NULL ,
        description           Text NOT NULL ,
        prix                  Float NOT NULL ,
        taux_reduction        Float NOT NULL ,
        date_soucription      Date NOT NULL ,
        id_utilisateur        Int NOT NULL
	,CONSTRAINT ProgrammeFidelite_PK PRIMARY KEY (id_programme_fidelite)

	,CONSTRAINT ProgrammeFidelite_Client_FK FOREIGN KEY (id_utilisateur) REFERENCES Client(id_utilisateur)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Chauffeur
#------------------------------------------------------------

CREATE TABLE Chauffeur(
        id_utilisateur Int NOT NULL ,
        nom            Varchar (50) NOT NULL ,
        prenom         Varchar (50) NOT NULL ,
        email          Varchar (50) NOT NULL ,
        adresse        Varchar (50) NOT NULL ,
        telephone      Char (10) NOT NULL ,
        login          Varchar (50) NOT NULL ,
        mot_de_passe   Varchar (50) NOT NULL
	,CONSTRAINT Chauffeur_PK PRIMARY KEY (id_utilisateur)

	,CONSTRAINT Chauffeur_Employe_FK FOREIGN KEY (id_utilisateur) REFERENCES Employe(id_utilisateur)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Agence
#------------------------------------------------------------

CREATE TABLE Agence(
        id_agence            Int  Auto_increment  NOT NULL ,
        nom                  Varchar (50) NOT NULL ,
        telephone            Char (10) NOT NULL ,
        adresse              Varchar (50) NOT NULL ,
        coordonnees_GPS      Varchar (50) NOT NULL ,
        nombre_max_vehicules Int NOT NULL ,
        id_utilisateur       Int NOT NULL
	,CONSTRAINT Agence_PK PRIMARY KEY (id_agence)

	,CONSTRAINT Agence_Employe_FK FOREIGN KEY (id_utilisateur) REFERENCES Employe(id_utilisateur)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Location
#------------------------------------------------------------

CREATE TABLE Location(
        id_location    Int  Auto_increment  NOT NULL ,
        duree          Time NOT NULL ,
        assurance      Bool NOT NULL ,
        id_utilisateur Int NOT NULL ,
        matricule      Varchar (7) NOT NULL
	,CONSTRAINT Location_PK PRIMARY KEY (id_location)

	,CONSTRAINT Location_Client_FK FOREIGN KEY (id_utilisateur) REFERENCES Client(id_utilisateur)
	,CONSTRAINT Location_Vehicule0_FK FOREIGN KEY (matricule) REFERENCES Vehicule(matricule)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Facture
#------------------------------------------------------------

CREATE TABLE Facture(
        id_facture            Int  Auto_increment  NOT NULL ,
        duree_effective       Time NOT NULL ,
        consomation_carburant Float NOT NULL ,
        etat_vehicule         Varchar (50) NOT NULL ,
        id_agence             Int NOT NULL ,
        id_utilisateur        Int NOT NULL
	,CONSTRAINT Facture_PK PRIMARY KEY (id_facture)

	,CONSTRAINT Facture_Agence_FK FOREIGN KEY (id_agence) REFERENCES Agence(id_agence)
	,CONSTRAINT Facture_Client0_FK FOREIGN KEY (id_utilisateur) REFERENCES Client(id_utilisateur)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Devis
#------------------------------------------------------------

CREATE TABLE Devis(
        id_devis          Int  Auto_increment  NOT NULL ,
        duree             Time NOT NULL ,
        montant_reduction Float NOT NULL ,
        id_agence         Int NOT NULL ,
        id_utilisateur    Int NOT NULL
	,CONSTRAINT Devis_PK PRIMARY KEY (id_devis)

	,CONSTRAINT Devis_Agence_FK FOREIGN KEY (id_agence) REFERENCES Agence(id_agence)
	,CONSTRAINT Devis_Client0_FK FOREIGN KEY (id_utilisateur) REFERENCES Client(id_utilisateur)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Gerant
#------------------------------------------------------------

CREATE TABLE Gerant(
        id_utilisateur Int NOT NULL ,
        nom            Varchar (50) NOT NULL ,
        prenom         Varchar (50) NOT NULL ,
        email          Varchar (50) NOT NULL ,
        adresse        Varchar (50) NOT NULL ,
        telephone      Char (10) NOT NULL ,
        login          Varchar (50) NOT NULL ,
        mot_de_passe   Varchar (50) NOT NULL
	,CONSTRAINT Gerant_PK PRIMARY KEY (id_utilisateur)

	,CONSTRAINT Gerant_Employe_FK FOREIGN KEY (id_utilisateur) REFERENCES Employe(id_utilisateur)
)ENGINE=InnoDB;

