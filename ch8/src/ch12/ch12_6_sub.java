package ch12;

/********************************************
 * ここでは12_6が正常に動くための最低限のクラスを宣言
 *******************************************/


//抽象クラスCharacterを宣言
abstract class Character {
	String name;
	int hp;
	
	public void run(){}
	public abstract void attack(); 
}

class Wizard extends Character{
	int mp;
	//メソッド
	public void attack() {
		System.out.println(this.name + "の攻撃！");
		System.out.println("敵に３ポイントのダメージ");
	}
}

class Hero extends Character{
	@Override
	public void attack() {}
}

class Thief extends Character{
	@Override
	public void attack() {}
}