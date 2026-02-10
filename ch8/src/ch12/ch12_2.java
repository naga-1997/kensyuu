package ch12;
//エラー回避のために宣言

abstract class Monster{
	int hp;
	public abstract void run();
}
class Matango extends Monster{
	public void run() {
		System.out.println("キノコは逃げ出した。");
	}
}

class Goblin extends Monster{
	public void run() {
		System.out.println("ゴブリンは逃げ出した。");
	}
}

class Slime extends Monster{
	public void run() {
		System.out.println("スライムは逃げ出した。");
		}
}

class DeathBat extends Monster{
	public void run() {
		System.out.println("地獄コウモリは逃げ出した。");
		}
}


/*
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
	
	
}*/
