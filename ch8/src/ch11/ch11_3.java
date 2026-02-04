package ch11;
//Characterクラスを継承
class Hero extends Character {
	
	/**************************
	 * attackメソッドをオーバーライド
	 * MatangoのHPを10減らす
	************************** */
	public void attack(Matango m) {
		System.out.println(this.name + "の攻撃！");
		System.out.println("敵に10ポイントのダメージをあたえた！");
		m.hp -= 10;
	}
}
