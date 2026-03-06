/*//リザードンは炎タイプと飛行タイプを併せ持つ
class Charzard extends Pokemon implements FireType,FlyType{
	double weight;
	//オーバーライド
	public void fire() {
		//処理文
	}
	public void fly() {
		//処理文
	}
}
	

interface FireType  {
	 public abstract void fire();
}
interface FlyType{
	public void fly();
}

 class Pokemon1{
	String name;
	int hp;
	public void rattack() {}
	public void run() {}
}
	/*
	//攻撃はできるけどポケモンごとに技は違うから抽象メソッド
	public abstract void attack();
	//逃げるはどのポケモンでも一緒
	public void run() {
		System.out.println(this.name + "は逃げた");
	}


//ヒトカゲクラスはポケモンクラスを継承
class Hitokage extends Pokemon {
	//オーバーライド
	public void attack() {
		//処理文
	}
}*/
package matome;








