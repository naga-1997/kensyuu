package ch10;

//Heroを継承したクラスSupreHeroを作成
class SuperHero extends Hero {
	//フィールドの追加　
	boolean flying;
	
	//メソッド　「飛ぶ」「着地」を追加
	public void fly() {
		this.flying = true;
		System.out.println("飛び上がった！");
	}
	
	public void land() {
		this.flying = false;
		System.out.println("着地した！");
	}
}
