package company;

//営業クラス
public class Sales extends Employee {

	/*
	 * 週報返信　「週報の返信をした」と出力
	 * 面談組む　（引数：エンジニア）　「{エンジニア、名前}の面談を組んだ」と出力
	 * 打ち合わせ　「新規の打ち合わせをした」と出力
	 * 情報表示　「{名前}:{所属部署}」を出力する
	 */

	//コンストラクタ
	public Sales(String name,String department) {
		this.setName(name);
		this.setDepartment(department);
		}
	
	//週報返信
	public void weeklyReport() {
		System.out.println("週報の返信をした");
	}

	//面談組む
	public void interview(Engineer e) {
		
		System.out.println("{エンジニア、" + e.getName() + "}の面談を組んだ");//エンジニアの情報を入れる
	}

	//打ち合わせ
	public void meeting() {
		System.out.println("新規打ち合わせをした");
	}

	@Override
	public void informationDisplay() {
		System.out.println("「{" + this.getName() + ":{" + this.getDepartment() + "}」を出力する");
	}

}
