package ch13;

 class Hero {
	 private int hp;
	 String name;
	 Sword sword;
	 
	 public void bye() {
		 System.out.println("勇者は別れを告げた");
	 }
	 
	 public void die() {
		 System.out.println(this.name + "は死んでしまった！");
		 System.out.println("GAMEOVERです。");
	 }
	 
	public void sleep() {
		this.hp = 100;
		System.out.println(this.name + "は眠って回復した！");
	}
	
	public void attack(Matango m) {
		System.out.println(this.name + "の攻撃！");
		
		System.out.println("お化けキノコ" + m.suffix + "から２ポイント反撃された");
		this.hp -= 2;
		if (this.hp <= 0) {
			this.die();
		}
		
	}
	 

}
