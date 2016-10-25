package be.vdab.entities;

/**
 *
 * @author Dario.Jeurissen
 *
 */

public class Reservatie {
	private long voorstellingid;
	private int plaatsen;
	
	public Reservatie(long voorstellingid, int plaatsen){
		this.voorstellingid = voorstellingid;
		this.plaatsen = plaatsen;
	}

	public long getVoorstellingid() {
		return voorstellingid;
	}

	public int getPlaatsen() {
		return plaatsen;
	}
}
