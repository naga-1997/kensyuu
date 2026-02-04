package ch12;
//抽象クラスのモンスターを作成
//敵は「逃げる」を必ず持つ
abstract class Monster{
	public void run() {
		System.out.println("モンスターは逃げ出した");
	}
}

//スライムを作成
class Slim extends Monster{
	public void run() {
		System.out.println("スライムはサササッと逃げ出した");
	}
}


 class Main {
	public static void main(String[] args) {
		Slim s = new Slim();
		Monster m = new Slim();
		
		s.run();m.run();
	}
 }
 
