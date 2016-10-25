package be.vdab.entities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Mandje {
	private Map<Long, Integer> mandje;	
	public Mandje(){
		mandje = new HashMap<Long, Integer>();
	}
	public Map<Long, Integer> getMandje(){
		return mandje;
	}
	public void add(Long l, Integer p){
		mandje.put(l, p);
	}
	public void remove(Long l){
		try{
			mandje.remove(l);
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
		}
	}
	public List<Long> getVoorstellingen(){
		List<Long> lijst = new ArrayList<Long>();
		mandje.forEach((k,v)->lijst.add(k));
		return lijst;
	}
	
	public BigDecimal getTotalPrice(){
		BigDecimal total = new BigDecimal(0);
		return total;
	}
	public int getPlaatsenReserveerd(long id) {
		return mandje.get(id);
	}
	
}
