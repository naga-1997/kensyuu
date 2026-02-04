package ch11;

 class Dancer extends Character {
	 
	 public void damce() {
		 System.out.println(this.name + "は情熱的に踊った");
	 }
	 /****************************************
	  * オーバーライドしなければならない(抽象クラスを継承しているから) 
	 public void attack() {
		 System.out.println("攻撃した");
	 }
	 ******************************************/
}
