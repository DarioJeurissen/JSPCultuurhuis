package be.vdab.entities;

/**
 *
 * @author Dario.Jeurissen
 *
 */

public class Reservatie {
	private long voorstellingID;
	private long plaatsen;
	private boolean status;
	
	public Reservatie(Long l, long plaatsen){
		this.voorstellingID = l;
		this.plaatsen = plaatsen;
		this.status = false;
	}

	public long getVoorstellingID() {
		return voorstellingID;
	}

	public long getPlaatsen() {
		return plaatsen;
	}
	public boolean getStatus(){
		return status;
	}
	public void setProcessed(){
		status = true;
	}
}
