package ch11;
//練習問題11_3
/************************************************************
 * interface Thingでは、重さを取得、設定できるメソッドをインタフェースで定義
 *************************************************************/
interface Thing {
	//メソッド
	double getWeight();
	void setWeight(double getWeight);
}


//練習問題11_4
/**********************************************
 * AssetとThingを継承したTangibleAssetを宣言
 * Thingから重さに関するメソッドを継承
 ***********************************************/

abstract class TangibleAsset extends Asset implements Thing{
	//追加メンバ変数
	String color;
	double weight;
	
	//コンストラクタ　色、重さを追加
	public TangibleAsset(String name,int price,String color,double weight) {
		super(name,price);
		this.color = color;
		this.weight = weight;
	}
	
	//追加メソッド
	public String getcolor() {return this.color;}
	//interface Thingのオーバーライド
	public double getWeight() {return this.weight;}
	public void setWeight(double getWeight) {this.weight = weight;}
}

