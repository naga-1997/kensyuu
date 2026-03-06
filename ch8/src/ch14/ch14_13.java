package ch14;


class Hero {
	String name;
	int hp;
	static int money;
	
	public static void setRandomMoney() {
		Hero.money = (int)(Math.random() * 1000);
	}
	
}