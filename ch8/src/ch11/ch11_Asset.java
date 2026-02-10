package ch11;
/*********************************************
 * 資産を表す抽象クラス「class　Asset」を作り、これを元に今後のクラスを作っていく
 * メンバ変数name,price
 * コンストラクタを宣言
 * メソッドgetName,getPrice
 *********************************************/
abstract class Asset {
	//メンバ変数
	String name;
	int price;
	
	//コンストラクタ
	public Asset(String name,int price) {
		this.name = name;
		this.price = price;
	}
	
	//メソッド
	public String getName(){return this.name;}
	public int getPrice() {return this.price;}
}


/*****************************************
 * class Assetを継承している抽象クラスを宣言
 * 今後「本」や「コンピューター」の元となる
*************************************: */
/*abstract class TangibleAsset extends Asset{
	//追加メンバ変数
	String color;
	
	//コンストラクタ
	public TangibleAsset(String name,int price,String color) {
		super(name,price);
		this.color = color;
	}
	
	//追加メソッド
	public String getcolor() {return this.color;}
}*/
