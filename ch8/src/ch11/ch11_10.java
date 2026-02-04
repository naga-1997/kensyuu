package ch11;

//１９行目でコンパイルエラーを出さないためのMatangoクラスを宣言
class Matango{
	int hp =10;
}


/*************************
 * Characterクラスを継承して
 *「ダンス」メソッドを作り、attackメソッドをオーバーライドする 
**************************/
class Dancer extends Character {
	 
	 public void damce() {
		 System.out.println(this.name + "は情熱的に踊った");
	 }
	  
	 //オーバーライド
	 public void attack(Matango m) {
		 System.out.println(this.name + "の攻撃");
		 System.out.println("敵に３ポイントのダメージ");
		 m.hp -= 3;
	 }
}
