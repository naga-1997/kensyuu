package ch13;

//杖のクラス
public class ch13_Question_1 {
	private String name; //杖の名前
	private double power; //杖の魔力
	
    /*****************************
     * 名前は必ず３文字以上
     * 杖による増幅率は0.5以上100以下　（ベースポイントが10だから0.05~10）
    ***************************** */
	//杖のアクセサ
	public double getPower() {
		return this.power;
	}
	public void setPower(double power) {
		if (power > 10) {
			throw new IllegalArgumentException("数値が超過しています");
		}
		if (power < 0.05) {
			throw new IllegalArgumentException("数値が小さすぎます");
		}
		this.power = power;
	}
	//名前のアクセサ  3文字以上
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		if(name == null || name.length() < 3) {
			throw new IllegalArgumentException("３文字以上にしてください");
		}
		this.name = name;
	}
}
