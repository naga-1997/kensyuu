package Test;

/*********************
 * ・座標位置情報を表すクラス								
 *********************/
public class Point {
	//x,yの座標を表すフィールドを定義
	private int x;
	private int y;
	
	/**************************
	 * 引数なしのコンストラクタを定義
	 * x、y座標ともに0で初期化
	 *************************/
	public Point() {
		this.x = 0;
		this.y = 0;
	}

	/**************************************
	 * x座標、y座標を受け取りその値で初期化するコンストラクタの定義
　   *第1引数で渡された値をxフィールドに代入する。
　   *第2引数で渡された値をyフィールドに代入する。"							
	 **************************************/
	public Point(int x,int y) {
		this.x = x;
		this.y = y;
	}
	
	//アクセサ
	public int getX() {return this.x;}
	public void setX(int x) {this.x = x;}
	
	public int getY() {return this.y;}
	public void setY(int y) {this.y = y;}
	
}
