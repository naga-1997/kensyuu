package Test;

/*****************************  
・図形描画アプリケーションの共通機能2つを定義する。
・図形描画機能
・長さ測定機能
****************************: */

public abstract class Shape implements Figure {
	//図面描画機能の定義
	public abstract void draw();
	//長さ測定の定義
	public abstract double getPerimeter();
}
