package ch9;

public class ch9_6 {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		ch9__3 h1 = new ch9__3();  //Heroクラス　インスタンス
		h1.name = "ミナト";
		h1.hp = 100;
		
		ch9__3 h2 = new ch9__3();
		h2.name = "アサカ";
		h2.hp = 100;
		
		ch9_5 w1 = new ch9_5();  //クラスWizard　インスタンス
		w1.name = "スガワラ";
		w1.hp = 50;
		
		w1.heal(h1);
		w1.heal(h2);
		w1.heal(h2);
	}

}
