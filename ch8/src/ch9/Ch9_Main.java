package ch9;
//クラス　Ch9_Mainを定義
public class Ch9_Main {
	//ヒールの回復量を定義(int型)
	public static void heal(int hp) {
		hp += 10;
	}
	//ヒールの回復量を定義（インスタンス型）
	public static void heal(Ch9_Thief thief) {
		thief.hp += 10;
	}
//メインメソッドを定義
	public static void main(String[] args) {
		//変数baseHpに25に設定
		int baseHp = 25;
		
		//シーフを生成　名前「アサカ」　HPは２５　MPは初期値５（h9_Thiefで宣言）
		Ch9_Thief t = new Ch9_Thief("アサカ",baseHp);
		//int型でヒールに渡す
		heal(baseHp);
		System.out.println(baseHp + ":" + t.hp);
		//インスタンス型でヒールに渡す
		heal(t);
		System.out.println(baseHp + ":" + t.hp);
	}
}

/*
 * int型のヒールは、２５が代入されているから数値は変わらない
 * インスタンス型のヒールは、Ch9_Thiefが参照されるため、t.hpが10足される
 */

