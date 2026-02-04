package ch12;
//エラー回避のために宣言
class Matango{
	int hp;
}

//抽象クラスCharacterを宣言
abstract class Character {
	String name;
	int hp;
	
	public void run(){}
	public abstract void attack(Matango m); 
}

class Wizard extends Character{
	int mp;
	//メソッド
	public void attack(Matango m) {
		System.out.println(this.name + "の攻撃！");
		System.out.println("敵に３ポイントのダメージ");
		m.hp -= 3;
	}
	
	//追加メソッド
	public void fireball(Matango m) {
		System.out.println(this.name + "は火の玉を放った！");
		System.out.println("敵に２０ポイントのダメージ");
		m.hp -= 20;
		this.mp -= 5;
	}
	
	
}
