package ch16;
import java.util.HashMap;
import java.util.Map;


/*
 * 斉藤が倒した敵＝３
 * 鈴木が倒した敵＝７
 */
class Main {
	public static void main(String[] args) {
		//インスタンス
		Hero h1 = new Hero("斉藤");
		Hero h2 = new Hero("鈴木");
		//ペアで格納
		Map<Hero, Integer> heros = new HashMap<>();
		heros.put(h1, 3);
		heros.put(h2, 7);
		//値の取得と表示
		for(Hero key : heros.keySet()) {
			int s = heros.get(key);
			System.out.println(key.getName() + "が倒した敵＝" + s);
		}
		
		/*
		int saitou = heros.get(h1.getName());
		System.out.println(h1.getName() + "が倒した敵＝" + saitou);
		int suzuki = heros.get(h2.getName());
		System.out.println(h2.getName() + "が倒した敵＝" + suzuki);
		*/
		
	}
}