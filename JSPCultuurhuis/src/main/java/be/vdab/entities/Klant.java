package be.vdab.entities;

import be.vdab.util.HelperClass;

public class Klant {
	private long id;
	private String familienaam;
	private String voornaam;
	private String straat;
	private String nummer;
	private String postcode;
	private String gemeente;
	private String gebruikersnaam;
	private String password;
	
	
	
	public Klant(long id, String voornaam, String familienaam, String straat, String nummer, String postcode, String gemeente, String gebruikersnaam, String password) {
		if(HelperClass.isIDStrictlyPositive(id)){
			this.id = id;
		}
		if(HelperClass.isStringNotEmpty(voornaam)){ this.voornaam = voornaam; }
		if(HelperClass.isStringNotEmpty(familienaam)){ this.familienaam = familienaam; }
		if(HelperClass.isStringNotEmpty(straat)){ this.straat = straat; }
		if(HelperClass.isStringNotEmpty(nummer)){ this.nummer = nummer; }
		if(HelperClass.isStringNotEmpty(postcode)){ this.postcode = postcode; }
		if(HelperClass.isStringNotEmpty(gemeente)){ this.gemeente = gemeente; }
		if(HelperClass.isStringNotEmpty(gebruikersnaam)){ this.gebruikersnaam = gebruikersnaam; }
		if(HelperClass.isStringNotEmpty(password)){ this.password = password; }
	}



	public long getId() {
		return id;
	}



	public String getFamilienaam() {
		return familienaam;
	}



	public String getVoornaam() {
		return voornaam;
	}



	public String getStraat() {
		return straat;
	}



	public String getNummer() {
		return nummer;
	}



	public String getPostcode() {
		return postcode;
	}



	public String getGemeente() {
		return gemeente;
	}



	public String getGebruikersnaam() {
		return gebruikersnaam;
	}



	public String getPassword() {
		return password;
	}

	


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Klant other = (Klant) obj;
		if (id != other.id)
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "Klant [id=" + id + ", familienaam=" + familienaam + ", voornaam=" + voornaam + "]";
	}
	
	
	
}
