package Test;
/****************************
 * ・図形描画機能の定義 ・長さ測定機能の定義のインタフェース							
 ****************************/
public interface Figure {
     //図形描画機能の定義
	public void draw();
	//長さ測定機能の定義
	public double getPerimeter();
}
