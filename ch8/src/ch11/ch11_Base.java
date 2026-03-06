package ch11;

class Book {
	String name;
	int price;
	String color;
	String isbn;
	//コンストラクタ
	public Book(String name,int price,String color,String isbn) {
		this.name = name;
		this.price = price;
		this.color = color;
		this.isbn = isbn;
	}
	//メソッド
	public String getName() {return this.name;}
	public int getPrice() {return this.price;}
	public String getcolor() {return this.color;}
	public String getisbn() {return this.isbn;}
}


class Computer{
	String name;
	int price;
	String color;
	String makerName;
	
	//コンストラクタ
	public Computer(String name,int price,String color,String makerName) {
		this.name = name;
		this.price = price;
		this.color = color;
		this.makerName = makerName;
	}
	//メソッド
	public String getName() {return this.name;}
	public int getPrice() {return this.price;}
	public String getColor() {return this.color;}
	public String getMakerName() {return this.makerName;}
}


/*
class Main{
	public static void main(String[] args) {
		Book b = new Book("jaba入門", 450, "赤", "124");
		b.getName();
	}
}*/