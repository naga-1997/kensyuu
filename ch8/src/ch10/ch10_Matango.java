package ch10;
 //class名をMatangoにする為、publicは省略（練習用）
class Matango { 
	//Mtangoのフィールドを宣言
	int hp = 50;
	char suffix;
	
	//Matangoの接尾辞をコンストラクタで定義
	public Matango(char suffix) {
		this.suffix = suffix;
	}
	//MatangoがHeroに攻撃した時、Heroに10のダメージを与える
	public void attack(Hero h) {
		System.out.println("キノコ" + this.suffix + "の攻撃");
		System.out.println("10のダメージ");
		h.hp -= 10;
	}

}
