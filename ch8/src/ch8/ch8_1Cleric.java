package ch8;

import java.util.Random;

public class ch8_1Cleric {
	//フィールド名
	String name; //名前
	int hp = 50; //HP 初期値50
	final int maxHp = 50;  //最大HP  定数50
	int mp = 10;  //MP  初期値10
	final int maxMp = 10;   //最大MP  定数10
	
	//メソッド
	public void selfAid() {         //「セルフエイド」MP-5でHP全回復
		System.out.println(this.name + "はセルフエイドを唱えた！");
		this.hp = this.maxHp;
		this.mp -= 5;
		System.out.println("HPが最大まで回復した");
	}
	
	public int pray(int sec) {      //「祈る」秒数に０〜２ポイントの補正を加えMP回復
		System.out.println(this.name + "は" + sec + "秒間天に祈った！");
		
		//理論上の回復量を乱数を用いて決定
		int recover = new Random().nextInt(3) + sec;
		
		//実際の回復量を計算
		int recoverAcctual = Math.min(this.maxMp - this.mp,recover);
		/*Math.mini(A,B);AかBどちらか（数値）小さい方を返すメソッド*/
		
		this.mp += recoverAcctual;
		System.out.println("MPが" + recoverAcctual + "回復した");
		
		return recoverAcctual;
		
	}
}

class Main{
     public static void main(String[] args) {
	      ch8_1Cleric c= new ch8_1Cleric();
	      
	      c.name = "長嶋";
	      c.selfAid();
	      c.pray(2);
	}
}



