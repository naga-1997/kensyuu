package ch15;

import javax.crypto.CipherOutputStream;

public class ch15_9 {
	final String FOMAT = "%-9s %-13s 所持金$,6d";
	String s = String.format(FOMAT,hero.getName(), hero.getJob(),hero.getGold());
	System.out.println(s);
}
