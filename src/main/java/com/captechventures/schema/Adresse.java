package com.captechventures.schema;

public class Adresse {
	
	private String codePostal;
	private String ville;
	
	public String getCodePostal() {
		return codePostal;
	}
	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
		
	@Override
	public String toString() {
		return "Adresse [codePostal=" + codePostal + ", ville=" + ville + "]";
	}

}
