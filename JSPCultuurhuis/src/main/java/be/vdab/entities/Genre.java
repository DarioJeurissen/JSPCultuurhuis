package be.vdab.entities;

import be.vdab.util.HelperClass;

public class Genre {
	private long id;
	private String naam;
	
	public Genre(long id, String naam) {
		setId(id);
		setNaam(naam);
	}

	public void setId(long id) {
		if(HelperClass.isIDStrictlyPositive(id)){
			this.id = id;
		}
		else{
			throw new IllegalArgumentException();
		}
	}

	public void setNaam(String naam) {
		if (HelperClass.isStringNotEmpty(naam)) {
			this.naam = naam;
		}
		else{
			throw new IllegalArgumentException();
		}
	}

	public long getId() {
		return id;
	}

	public String getNaam() {
		return naam;
	}	
}