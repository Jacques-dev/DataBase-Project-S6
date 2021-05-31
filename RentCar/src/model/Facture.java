package model;

public class Facture {
	private int idFacture;
	private int dureeEffective;
	private float consomationCarburant;
	private int etatVehicule;
	private int idAgence;
	private int idUtilisateur;
	private int frais_remise;
	
	public Facture(int idFacture, int dureeEffective, float consomationCarburant, int etatVehicule, int idAgence,
			int idUtilisateur, int frais_remise) {
		this.idFacture = idFacture;
		this.dureeEffective = dureeEffective;
		this.consomationCarburant = consomationCarburant;
		this.etatVehicule = etatVehicule;
		this.idAgence = idAgence;
		this.idUtilisateur = idUtilisateur;
		this.frais_remise = frais_remise;
	}
	
	public Facture(int dureeEffective, float consomationCarburant, int etatVehicule, int idAgence,
			int idUtilisateur, int frais_remise) {
		this.dureeEffective = dureeEffective;
		this.consomationCarburant = consomationCarburant;
		this.etatVehicule = etatVehicule;
		this.idAgence = idAgence;
		this.idUtilisateur = idUtilisateur;
		this.frais_remise = frais_remise;
	}

	public int getIdFacture() {
		return idFacture;
	}

	public void setIdFacture(int idFacture) {
		this.idFacture = idFacture;
	}

	public int getDureeEffective() {
		return dureeEffective;
	}

	public void setDureeEffective(int dureeEffective) {
		this.dureeEffective = dureeEffective;
	}

	public float getConsomationCarburant() {
		return consomationCarburant;
	}

	public void setConsomationCarburant(float consomationCarburant) {
		this.consomationCarburant = consomationCarburant;
	}

	public int getEtatVehicule() {
		return etatVehicule;
	}

	public void setEtatVehicule(int etatVehicule) {
		this.etatVehicule = etatVehicule;
	}

	public int getIdAgence() {
		return idAgence;
	}

	public void setIdAgence(int idAgence) {
		this.idAgence = idAgence;
	}

	public int getIdUtilisateur() {
		return idUtilisateur;
	}

	public void setIdUtilisateur(int idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

	public int getFrais_remise() {
		return frais_remise;
	}

	public void setFrais_remise(int frais_remise) {
		this.frais_remise = frais_remise;
	}

	@Override
	public String toString() {
		return "Facture [idFacture=" + idFacture + ", dureeEffective=" + dureeEffective + ", consomationCarburant="
				+ consomationCarburant + ", etatVehicule=" + etatVehicule + ", idAgence=" + idAgence
				+ ", idUtilisateur=" + idUtilisateur + "]";
	}
	
	
}
