package ch9;

public class ch9_8{
	 String name;
	 int hp;
	 ch9_3 sword;
	 
	 //メソッド
	 public void attack(){
		 System.out.println(this.name + "は攻撃した！");
		 System.out.println("敵に５ポイントのダメージをあたえた！");
	 }
	 
	 public ch9_8() {
		 this.hp = 100;
	 }
}

