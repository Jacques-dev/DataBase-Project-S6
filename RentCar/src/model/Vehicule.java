package model;

public class Vehicule {

	private String matricule;
	private String marque;
	private String modele;
	private int kilometrage;
	private boolean climatisation;
	private String typeBoiteDeVitesse;
	private String type;
	
	public Vehicule(String matricule, String marque, String modele, int kilometrage, boolean climatisation,
			String typeBoiteDeVitesse, String type) {
		this.matricule = matricule;
		this.marque = marque;
		this.modele = modele;
		this.kilometrage = kilometrage;
		this.climatisation = climatisation;
		this.typeBoiteDeVitesse = typeBoiteDeVitesse;
		this.type = type;
	}

	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public String getMarque() {
		return marque;
	}

	public void setMarque(String marque) {
		this.marque = marque;
	}

	public String getModele() {
		return modele;
	}

	public void setModele(String modele) {
		this.modele = modele;
	}

	public int getKilometrage() {
		return kilometrage;
	}

	public void setKilometrage(int kilometrage) {
		this.kilometrage = kilometrage;
	}

	public boolean isClimatisation() {
		return climatisation;
	}

	public void setClimatisation(boolean climatisation) {
		this.climatisation = climatisation;
	}

	public String getTypeBoiteDeVitesse() {
		return typeBoiteDeVitesse;
	}

	public void setTypeBoiteDeVitesse(String typeBoiteDeVitesse) {
		this.typeBoiteDeVitesse = typeBoiteDeVitesse;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Vehicule [matricule=" + matricule + ", marque=" + marque + ", modele=" + modele + ", kilometrage="
				+ kilometrage + ", climatisation=" + climatisation + ", typeBoiteDeVitesse=" + typeBoiteDeVitesse
				+ ", type=" + type + "]";
	}
	
	
}
