package model;

public class Devis {

	private int idDevis;
	private float montantReduction;
	private int durreePrevueLocation;
	private int idAgence;
	private int idUtilisateur;
	private boolean assurance;
	
	public boolean getAssurance() {
		return assurance;
	}

	public void setAssurance(boolean assurance) {
		this.assurance = assurance;
	}

	private String matricule;
	
	public Devis(int idDevis, float montantReduction, int durreePrevueLocation, int idAgence, int idUtilisateur, String matricule) {
		this.idDevis = idDevis;
		this.montantReduction = montantReduction;
		this.durreePrevueLocation = durreePrevueLocation;
		this.idAgence = idAgence;
		this.idUtilisateur = idUtilisateur;
		this.matricule = matricule;
	}
	
	public Devis(float montantReduction, int durreePrevueLocation, int idAgence, int idUtilisateur, String matricule) {
		this.montantReduction = montantReduction;
		this.durreePrevueLocation = durreePrevueLocation;
		this.idAgence = idAgence;
		this.idUtilisateur = idUtilisateur;
		this.matricule = matricule;
	}

	public int getIdDevis() {
		return idDevis;
	}

	public void setIdDevis(int idDevis) {
		this.idDevis = idDevis;
	}

	public float getMontantReduction() {
		return montantReduction;
	}

	public void setMontantReduction(float montantReduction) {
		this.montantReduction = montantReduction;
	}

	public int getDurreePrevueLocation() {
		return durreePrevueLocation;
	}

	public void setDurreePrevueLocation(int durreePrevueLocation) {
		this.durreePrevueLocation = durreePrevueLocation;
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

	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	@Override
	public String toString() {
		return "Devis [idDevis=" + idDevis + ", montantReduction=" + montantReduction + ", durreePrevueLocation="
				+ durreePrevueLocation + ", idAgence=" + idAgence + ", idUtilisateur=" + idUtilisateur + ", assurance="
				+ assurance + ", matricule=" + matricule + "]";
	}
	
	
	
}
