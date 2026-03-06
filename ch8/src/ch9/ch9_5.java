package ch9;

public class ch9_5 { //Wizardクラス
	String name;
	int hp;
	
	public void heal(ch9__3 h) { //クラスHero
		h.hp += 10;
		System.out.println(h.name + "のHPを１０回復した！");
		
	}

}
