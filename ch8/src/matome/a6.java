package matome;

//キズ薬のクラス
class Kizukusuri{
	//キズ薬を使う
	public void usePotion(Pokemon p) {
		int hp = p.getHp();
		System.out.println(p.getName() +  "は10ポイント回復した");
		hp += 10;
		}
}