package ch10;
//Heroを継承したクラスSupreHero2を作成
class SupreHero2 extends Hero{
	//フィールド
	boolean flying;
	
	//2回攻撃する
	public void attack() {
		super.attack();
		if (this.flying) {
			super.attack();
		}
	}
}
