package ch8;

public class ch8_7 {  //Hero
	String name;
	int hp;
	
	//眠る　sleepメソッド
	public void sleep() {
		this.hp = 100;
		System.out.println(this.name + "は、眠って回復した！");
	}
	//座る　sitメソッド
	public void sit(int sec) {  //int sec で座った秒数を引数で受け取る
		this.hp += sec;
		System.out.println(this.name + "は、" + sec + "秒座った！");
		System.out.println("HPが"+ sec + "ポイント回復した");
	}
	
	//転ぶ　slipメソッド
	public void slip() {
		this.hp -= 5;
		System.out.println(this.name + "は、転んだ！");
		System.out.println("5のダメージ！");
	}
	
	//逃げる　runメソッド
	public void run() {
		System.out.println(this.name + "は、逃げ出した！");
		System.out.println("GAMEOVER");
		System.out.println("最終HPは" + this.hp + "でした");
	}
}
