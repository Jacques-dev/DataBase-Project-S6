package model;

public class ProgramFidelite {
	
	private int idProgrammeFidelite;
	private int duree;
	private String description;
	private float prix;
	
	public ProgramFidelite(int idProgrammeFidelite, int duree, String description, float prix) {
		this.idProgrammeFidelite = idProgrammeFidelite;
		this.duree = duree;
		this.description = description;
		this.prix = prix;
	}

	public int getId() {
		return idProgrammeFidelite;
	}

	public void setId(int idProgrammeFidelite) {
		this.idProgrammeFidelite = idProgrammeFidelite;
	}

	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public float getPrix() {
		return prix;
	}

	public void setPrix(float prix) {
		this.prix = prix;
	}

	@Override
	public String toString() {
		return "ProgramFidelite [idProgrammeFidelite=" + idProgrammeFidelite + ", duree=" + duree + ", description="
				+ description + ", prix=" + prix + "]";
	}
	
	
}
