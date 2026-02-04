package ch11;

//抽象クラスを宣言
abstract class TangibleAsset{
	//メンバ変数
	String name;
	int price;
	String color;
	
	//メソッド
	public String getName() {return this.name;}
	public int getPrice() {return this.price;}
	public String getcolor() {return this.color;}
	
}

class Book1 extends TangibleAsset{
	//追加のメンバ変数
	String isbn;
	//追加のメソッド
	public String getisbn() {return this.isbn;}
}

class Computer1 extends TangibleAsset{
	//追加のメンバ変数
	String makerName;
	
	//追加のメソッド
	public String getMakerName() {return this.makerName;}
}
