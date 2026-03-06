package ch9;

public class ch9_10{
	 String name;
	 int hp;
	 ch9_3 sword;
	 
	 //メソッド
	 public void attack(){
		 System.out.println(this.name + "は攻撃した！");
		 System.out.println("敵に５ポイントのダメージをあたえた！");
	 }
	 
	 public ch9_10(String name) {
		 this.hp = 100;
		 this.name = name;
	 }
}
