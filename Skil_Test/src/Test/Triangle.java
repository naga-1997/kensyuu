package Test;

/****************************************
   ・3つの座標データを指定することで、三角形を描画するクラス
　　・三角形描画メソッド
　　・三角形の周囲の長さを取得するメソッド 
 ***************************************/

public class Triangle extends Polygon{
	private Point p1;  //三角形の点１
	private Point p2;  //三角形の点２
	private Point p3;  //三角形の点３
	
	/**************************************
	 * "引数で受け取ったデータを用いて、3つのPointオブジェクトを生成。
      p1フィールドとp2フィールド、p3フィールドにそれぞれを代入する。
      スーパークラス内で定義されているangleフィールドに3を代入する。"							
	 **************************************/
	public Triangle(int x1,int y1,int x2,int y2,int x3,int y3) {
		this.p1 = new Point(x1,y1);
		this.p2 = new Point(x2,y2);
		this.p3 = new Point(x3,y3);
		this.angle = 3;
	}
	
	/**************************
      以下のようなメッセージを表示する。
      出力例：
　    "[三角形を描画] 点1(0,0)から点2(100,100)、点3(0, 200)の三角形"
	 */
	@Override
	public void draw() {
		System.out.println("[三角形を描画] 点1(" + p1.getX() + "," + p1.getY() + ")から点2(" + p2.getX() + "," + p2.getY() +")、点3(" + p3.getX() + "," + p3.getY() +")の三角形");
	}
	
	/******************************************
	   "3つの座標を使い、以下の計算式で算出した結果を返す。
　　　　p1からp2までの長さ + p2からp3までの長さ + p3からp1までの長さ"							
	 *****************************************/
	@Override
	public double getPerimeter() {
		//p1からp2の長さ
		double result1 = Math.sqrt(Math.pow(p2.getX() - p1.getX(),2) + Math.pow(p2.getY() - p1.getY(),2));
		//p2からp3の長さ
		double result2 = Math.sqrt(Math.pow(p3.getX() - p2.getX(),2) + Math.pow(p3.getY() - p2.getY(),2));
		//p3からp1の長さ
		double result3 =  Math.sqrt(Math.pow(p1.getX() - p3.getX(),2) + Math.pow(p1.getY() - p3.getY(),2));
		return (result1 + result2 + result3);
	}
}
