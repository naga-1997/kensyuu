package ch13;

 class Main{
	public static void main(String[] args) {
		ch13_Question_1 w = new ch13_Question_1();
		ch13_Question_2 wizard = new ch13_Question_2();
		Hero hero = new Hero();
		//杖の魔力、名前
		w.setPower(5);
		w.setName("魔法の杖");
		//魔法使いのHP、MP、名前、杖を持つ
		wizard.setHp(100);
		wizard.setMp(50);
		wizard.setName("アサカうんこ");
		wizard.setWnad(w);
		//勇者のHP,名前
		hero.setHp(100);
		hero.setName("うんこまん");
		
		//メソッド
		wizard.heal(hero);
	}
}
