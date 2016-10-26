package be.vdab.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Mandje {
	private Map<Long, Long> mandje;	
	public Mandje(){
		mandje = new HashMap<Long, Long>();
	}
	public Map<Long, Long> getMandje(){
		return mandje;
	}
	public void add(Long l, Long p){
		mandje.put(l, p);
	}
	public void remove(long v){
		try{
			mandje.remove(v);
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
	public long getPlaatsenGereserveerd(Long l) {
		return mandje.get(l);
	}
	
}
