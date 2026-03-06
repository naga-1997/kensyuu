package ch10;
//class名をPoisonMatangoにする為、publicは省略（練習用）
//Matangoを継承
class PoisonMatango extends Matango {
	
	/*******************************
	 * PoisonMatangoが毒を用いた攻撃が可能な回数をint poisonとして宣言（初期値５）
	 * attackメソッドを呼ぶと次の行動をする
	 * ・Matangoと同様の攻撃を行う
	 * ・毒を用いた攻撃が０でなければ、HeroのHPの1/5のダメージを与える
	 *********************************/
	int poison = 5;
	int damage;
	
	// 親クラス(Matango)のコンストラクタを呼び出す
	public PoisonMatango(char suffix) {
        super(suffix); 
    }
	
	public void attack(Hero h) {
		//Heroに１０のダメージ
		h.hp -= 10;
		//poisonが0でなければHeroのHPの1/5のダメージを与える
		if (poison >= 1) {
			System.out.println("さらに毒の胞子をばらばらまいた！");
			int damage = (h.hp / 5);
			h.hp -= damage;
			System.out.println(damage + "ポイントのダメージ！");
			//poisonを１減らす
			 poison --;;
		}
	}
}
