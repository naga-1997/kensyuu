package ch11;

//抽象クラスを宣言
abstract class TangibleAsset{
	//メンバ変数
	String name;
	int price;
	String color;
	
	//コンストラクタ
	public TangibleAsset(String name,int price,String color) {
		this.name = name;
		this.price = price;
		this.color = color;
	}
	
	//メソッド
	public String getName() {return this.name;}
	public int getPrice() {return this.price;}
	public String getcolor() {return this.color;}
	
}

class Book1 extends TangibleAsset{
	//追加のメンバ変数
	String isbn;
	
	//コンストラクタ
	public Book1(String name,int price,String color,String isbn) {
		super(name, price, color);
		this.isbn = isbn;
	}
	//追加のメソッド
	public String getisbn() {return this.isbn;}
}

class Computer1 extends TangibleAsset{
	//追加のメンバ変数
	String makerName;
	
	//コンストラクタ
	public Computer1(String name,int price,String color,String makerName) {
		super(name, price, color);
		this.makerName = makerName;
	}
	
	//追加のメソッド
	public String getMakerName() {return this.makerName;}
}
