package matome;
//ポケモンの素材のクラス
 class Pokemon{
	private String name;
	private int hp;
	//名前のgetterの宣言
	public String getName() {
		return this.name;
	}
	//名前のsetterの宣言
	public void setName(String name) {
		//値の妥当性をチェック
		if (name == null) {
			throw new IllegalArgumentException("名前がありません");
		}
		if (name.length()<=1) {
			throw new IllegalArgumentException("１文字は設定できません。");
		}
		if (name.length() >= 7) {
			throw new IllegalArgumentException("7文字以上は設定できません");
		}
		this.name = name;
	}
	//HPのアクセサ
	public int getHp() {
		return this.hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	//コンストラクタ
	public Pokemon(String name,int hp) {
		this.setName(name); 
		this.hp = hp;
	}
}

//ピカチュウのクラス
class Pikachu extends Pokemon {
	public Pikachu(String name,int hp) {
		super(name, hp);
	}
}

class Mian{
	public static void main(String[] args) {
		Pokemon p1 = new Pikachu("うんこまんのピカチュウだよ",100);
		Kizukusuri k = new Kizukusuri();
		
		k.usePotion(p1);
	}
}

