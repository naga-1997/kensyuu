package ch9;
//クラスCh9_Thiefを定義
public class Ch9_Thief {
	
	//フィールドを宣言
	String name;
	int hp;
	int mp;
	
	//メソッド　
	/*
	 * MainメソッドからCh9_Thiefの引数（名前、HP、MP）を受け取り、それらを表示させる
	 * 引数（名前）なしは受け付けない
	 */
	public Ch9_Thief(String name,int hp,int mp) {
		this.name = name;  
		this.hp = hp;
		this.mp = mp;
		System.out.println("名前：" + this.name + " HP：" + this.hp + " MP：" + this.mp);
	}
	
	public Ch9_Thief(String name,int hp) {
		this(name, hp, 5);
	}
	
	public Ch9_Thief(String name) {
		this(name, 40,5);
	}
	private Ch9_Thief() {
		System.out.println("名前を付けてください");
	}
	
}
