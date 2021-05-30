package model;

public class Utilisateur {
	
	private int idUtilisateur;
	private String nom;
	private String prenom;
	private String email;
	private String telephone;
	private String idAdresse;
	
	public Utilisateur(int idUtilisateur, String nom, String prenom, String email, String telephone, String idAdresse) {
		this.idUtilisateur = idUtilisateur;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.telephone = telephone;
		this.idAdresse = idAdresse;
	}
	

	public int getId() {
		return idUtilisateur;
	}

	public void setId(int idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getIdAdresse() {
		return idAdresse;
	}

	public void setIdAdresse(String idAdresse) {
		this.idAdresse = idAdresse;
	}

	@Override
	public String toString() {
		return "Utilisateur [idUtilisateur=" + idUtilisateur + ", nom=" + nom + ", prenom=" + prenom + ", email="
				+ email + ", telephone=" + telephone + ", idAdresse=" + idAdresse + "]";
	}
	
	
}
