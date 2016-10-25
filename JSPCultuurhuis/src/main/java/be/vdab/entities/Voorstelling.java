package be.vdab.entities;

import java.math.BigDecimal;
import java.sql.Date;

import be.vdab.util.HelperClass;

/**
 *
 * @author Dario.Jeurissen
 *
 */

public class Voorstelling {
	private long id;
	private String titel;
	private String uitvoerders;
	private Date datum;
	private long genreid;
	private BigDecimal prijs;
	private long vrijeplaatsen;
	

	
	
	public Voorstelling(long id, String titel, String uitvoerders, Date datum, long genreid, BigDecimal prijs,
			long vrijeplaatsen) {
		if(HelperClass.isIDStrictlyPositive(id)){
			this.id = id;
		}
		if(HelperClass.isStringNotEmpty(titel)){
			this.titel = titel;
		}
		if(HelperClass.isStringNotEmpty(uitvoerders)){
			this.uitvoerders = uitvoerders;
		}
		if(HelperClass.isDateNotNull(datum)){
			this.datum = datum;
		}
		if(HelperClass.isIDStrictlyPositive(genreid)){
			this.genreid = genreid;
		}
		if(HelperClass.isPricePositiveOrZero(prijs)){
			this.prijs = prijs;
		}
		if(HelperClass.isValueNotNegative(vrijeplaatsen)){
			this.vrijeplaatsen = vrijeplaatsen;
		}
	}
	@Override
	public String toString() {
		return "Voorstelling [id=" + id + ", titel=" + titel + ", vrijeplaatsen=" + vrijeplaatsen + "]";
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
		Voorstelling other = (Voorstelling) obj;
		if (id != other.id)
			return false;
		return true;
	}
	public long getId() {
		return id;
	}
	public String getTitel() {
		return titel;
	}
	public String getUitvoerders() {
		return uitvoerders;
	}
	public Date getDatum() {
		return datum;
	}
	public long getGenreid() {
		return genreid;
	}
	public BigDecimal getPrijs() {
		return prijs;
	}
	public long getVrijeplaatsen() {
		return vrijeplaatsen;
	}
	
	
}
