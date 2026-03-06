package company;
/*****************************
 * 従業員クラス
 * フィールドで社員名、所属している部署を宣言
 * abstractで情報表示メソッド
 ******************************/

public abstract class Employee {
	private String name; //名前
	private String department; //所属部署

	//情報表示
	public abstract void informationDisplay(); 
	
	
	//アクセサ
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

}
