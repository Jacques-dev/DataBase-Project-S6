package model;

public class Circuit {

	private int idCircuit;
	private int id_Agence_a;
	private int id_Agence_b;
	private String cheminA;
	private String cheminB;
	private String cheminC;

	
	public Circuit(int idCircuit, int id_Agence_a, int id_Agence_b, String cheminA, String cheminB, String cheminC) {
		this.idCircuit = idCircuit;
		this.id_Agence_a = id_Agence_a;
		this.id_Agence_b = id_Agence_b;
		this.cheminA = cheminA;
		this.cheminB = cheminB;
		this.cheminC = cheminC;
	}


	public int getId_Agence_a() {
		return id_Agence_a;
	}


	public void setId_Agence_a(int id_Agence_a) {
		this.id_Agence_a = id_Agence_a;
	}


	public int getId_Agence_b() {
		return id_Agence_b;
	}


	public void setId_Agence_b(int id_Agence_b) {
		this.id_Agence_b = id_Agence_b;
	}


	@Override
	public String toString() {
		return "Circuit [idCircuit=" + idCircuit + ", id_Agence_a=" + id_Agence_a + ", id_Agence_b=" + id_Agence_b
				+ ", cheminA=" + cheminA + ", cheminB=" + cheminB + ", cheminC=" + cheminC + "]";
	}


	public int getIdCircuit() {
		return idCircuit;
	}


	public void setIdCircuit(int idCircuit) {
		this.idCircuit = idCircuit;
	}


	public String getCheminA() {
		return cheminA;
	}


	public void setCheminA(String cheminA) {
		this.cheminA = cheminA;
	}


	public String getCheminB() {
		return cheminB;
	}


	public void setCheminB(String cheminB) {
		this.cheminB = cheminB;
	}


	public String getCheminC() {
		return cheminC;
	}


	public void setCheminC(String cheminC) {
		this.cheminC = cheminC;
	}
	
}
