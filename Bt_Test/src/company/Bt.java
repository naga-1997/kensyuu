package company;

import java.util.ArrayList;
import java.util.List;

//株式会社BTのクラス
public class Bt {
	private final String companyName = "BT"; //社名はBT
	//Employeeを格納するリスト
	private List<Employee> employeeList = new ArrayList<>();
    
    /*
     * ・従業員生成　人事クラスのみアクセス可能
     * エンジニアを生成する場合には使用言語も情報として持たせる
     * ・従業員情報表示　全従業員の情報を表示
     */
	//人事生成
	protected  void generate(Employee e) {
		employeeList.add(e);
		if (e instanceof Engineer) {
			Engineer eng = (Engineer)e;
			System.out.println("エンジニアを採用。使用言語は" + eng.getLanguage() + "です。");
		}else {
			System.out.println(e.getDepartment() + "を採用。");
		}
	}
	//従業員情報公開
	public void showAll() {
		for(Employee e : employeeList) {
			e.informationDisplay();
		}
	}
}
