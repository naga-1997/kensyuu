package ch9;

public class ch9_4 {

	public static void main(String[] args) {
		ch9_3 c = new ch9_3();  //Swordをインスタンス
		c.name = "炎の剣";
		c.damage = 10;
		
		ch9__3 h = new ch9__3();  //Heroをインスタンス
		h.name = "ミナト";
		h.hp = 100 ;
		h.sword = c;
		
		System.out.println("現在の武器は" + h.sword.name);

	}

}
