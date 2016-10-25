package be.vdab.util;

import java.math.BigDecimal;
import java.sql.Date;

/**
 *
 * @author Dario.Jeurissen
 *
 */

public class HelperClass {
	public static boolean isStringNotEmpty(String s){
		if(s.trim().equals("")){
			return false;
		}
		return true;
	}
	
	public static boolean isDateNotNull(Date d){
		if(d == null){
			return false;
		}
		return true;
	}
	
	public static boolean isIDStrictlyPositive(long id){
		if(id > 0){
			return true;
		}
		return false;
	}
	public static boolean isIDStrictlyPositive(int id){
		if(id > 0){
			return true;
		}
		return false;
	}
	public static boolean isValueNotNegative(long value){
		if(value >= 0){
			return true;
		}
		return false;
	}

	public static boolean isPricePositiveOrZero(BigDecimal prijs) {
		if(prijs.signum() > 0){
			return true;
		}
		return false;
	}
}