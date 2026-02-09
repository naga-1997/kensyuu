package ch15;

import java.util.Date;

public class ch15_11 {

	public static void main(String[] args) {
		Date now = new Date();
		System.out.println(now);
		System.out.println(now.getTime());
		Date past = new Date(16989400000L);
		System.out.println(past);
 
	}

}
