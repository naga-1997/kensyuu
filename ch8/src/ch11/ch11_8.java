package ch11;
//Characterを抽象クラスとして宣言
 abstract class Character {
	 String name;
	 int hp;
	 
	 public void run() {
		 System.out.println(this.name + "は逃げ出した");
	 }
	 
	 //attackを抽象メソッドとして宣言
	 public abstract void attack();
}
