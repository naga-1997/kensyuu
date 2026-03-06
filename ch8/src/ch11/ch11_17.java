package ch11;

//Humanはch11_16
//Characterはch11_2
class Fool extends Character implements Human  {
	//Characterからhpやnameなどのフィールドを継承している
	//Characterから継承した抽象メソッドattack()を実装
	public void attack(Matango m) {
		System.out.println(this.name + "は戦わず遊んでいる");
	}
	
//さらにHumanから継承した４つの抽象メソッドを実装
	public void talk() {
		System.out.println("a");
	}
	public void watch() {
		System.out.println("b");
	}
	public void hear() {
		System.out.println("c");
	}
	public void run() {
		System.out.println("d");
	}
}
