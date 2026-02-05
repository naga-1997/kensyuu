package ch13;

//魔法使いのクラス
public class ch13_Question_2 {
	private int hp;
	private int mp;
	private String name;
 	private ch13_Question_1 wand;
	
	void heal(Hero h) {
		int basePoint = 10;  //基本回復ポイント
		int recovPoint = (int)(basePoint * this.getWand().getPower());  //杖に夜増幅
		
	    h.setHp(h.getHp() + recovPoint);  //勇者のHPを回復する
	    
	    System.out.println(h.getName() + "のHPを" + recovPoint + "回復した！");
	}
	
	/*********************************************
	 * 名前は３文字以上
	 * 魔法使いは必ず杖を装備する
	 * 魔法使いのHPとMPは０以上である
	 * HPが負の値を設定しようとした場合は自動的に０が設定される
	 **********************************************/
	//HPのアクセサ
	public int getHp() {return this.hp;}
	public void setHp(int hp) {
		if (hp < 0) {this.hp = 0;}
		 else {this.hp = hp;}
		}
	//MPのアクセサ
	public int getMp() {return this.mp;}
	public void setMp(int mp) {
		if (mp < 0) {throw new IllegalArgumentException("MPは0以下にはならない");}
		this.mp = mp;}
	//名前のアクセサ 3文字以上
	public String getName() {return this.name;}
	public void setName(String name) {
		if (name == null || name.length() < 3) {
			throw new IllegalArgumentException("文字は３文字以上にしてください");
		   }
		this.name = name;
		}
	//private ch13_Question_1 wand;のアクセサ
	public ch13_Question_1 getWand() {return this.wand;}
	public void setWnad(ch13_Question_1 wand) {
		if (wand == null) {
			throw new IllegalArgumentException("杖を装備してください");
		}
		this.wand = wand;
		}
}
