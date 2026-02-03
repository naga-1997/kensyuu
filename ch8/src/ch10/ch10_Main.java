package ch10;
//クラスHeroを定義
  class Hero{
	  //メンバ変数
	String name;
	int hp = 50;
	
	public Hero(String name) {
		this.name = name;
	}
}
/***********************************
 * PoisonMatango（A）でHeroを攻撃
 ***********************************/
  //メインメソッド
class Main {
	public static void main(String[] args) {
		
		PoisonMatango pm = new PoisonMatango('A');
		Hero h = new Hero("勇者");
		
		pm.attack(h);
	}

}
