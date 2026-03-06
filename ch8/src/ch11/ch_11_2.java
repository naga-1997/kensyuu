package ch11;

//クラスCharacterを作る
class Character {
	//メンバ変数は名前と体力
	String name;
	int hp;
	
	//メソッドは「逃げる」「攻撃する」
	public void run() {
		System.out.println(this.name + "は逃げ出した");
	}
	//攻撃対象としてMatangoクラスのインスタンスを受け取る
	//中身は空
	public void attack(Matango m) {
	}
}

//15行目のMatango mのエラー回避用で作成
class Matango{
	String name;
	int hp;
}