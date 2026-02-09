package ch14;

public class ch14_Question1 {
	String accountNumber;
	int balance;
	/********************
	 * 口座番号と残高を表示させる
	 *  System.out.println(a);で画面に¥1529(口座番号：4649)と表示される
	 *******************/
		//文字列表現のメソッド
		public String toString() {
			return "¥¥" + this.balance + "(口座番号：" + this.accountNumber + " )";
		}
		//等価判定のメソッド
		public boolean equals(Object o) {
			if (this == o) {
				return true;
			}
			if (o instanceof ch14_Question1 a) {
				String an1 = this.accountNumber.trim();
				String an2 = a.accountNumber.trim();
				if(an1.equals(an2)) {
					return true;
				}
			}
			return false;
		}




    // メインメソッド
    public static void main(String[] args) {
        // インスタンス a の作成とデータ入力
        ch14_Question1 a = new ch14_Question1();
        a.accountNumber = "4649";
        a.balance = 1529;

        // 【確認1】toString() の動作
        // System.out.println(a) により自動的に toString() が実行される
        System.out.println("--- 表示結果 ---");
        System.out.println(a); 

        // 【確認2】equals() の動作
        System.out.println("\n--- 比較結果 ---");
        ch14_Question1 b = new ch14_Question1();
        b.accountNumber = " 4649 "; // スペースあり
        b.balance = 1529;

        if (a.equals(b)) {
            System.out.println("aとbは「同じ口座」と判定されました。");
        } else {
            System.out.println("aとbは「別の口座」と判定されました。");
        }
    }
}
