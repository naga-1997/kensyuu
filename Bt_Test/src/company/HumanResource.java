package company;

/*
 * 人事クラス
 */
public class HumanResource extends Employee {
	protected String idresult; //面接の合否
	protected final String pass = "pass"; //採用
	protected final String fail = "fail"; //不採用

	//社員面接
	/*
	 * 採用だった場合、MOAT従業員生成メソッドから従業員（人事、エンジニア、営業）を採用
	 */
	public HumanResource(String name,String department, String idr) {
		setName(name);
		setDepartment(department);
		this.idresult = idr;
	}

	public void processHiring(Bt bt,String role, String lang,String targetName) {
		if (pass.equals(this.idresult)) {
			System.out.println("面接を行い、結果は採用だった");
			Employee newEmp = null;
			
			if ("エンジニア".equals(role)) {
				newEmp = new Engineer(targetName, "エンジニア", lang);
			}else if("営業".equals(role)){
				newEmp = new Sales(targetName,"営業部");
			}else if ("人事".equals(role)) {
				newEmp = new HumanResource(targetName, "人事部","pass");
			}
			
			if (newEmp != null) {
				bt.generate(newEmp);
			}
		}else if (fail.equals(this.idresult)) {
			System.out.println("面接を行い、結果は不採用");
		}
	}

	//給料計算
	public int salaryCalculation() {
		int r = 200000;
		return r;
	}

	//名前と部署を表示
	@Override
	public void informationDisplay() {
		System.out.print("「{" + getName() + "}:{" + getDepartment() + "}」");
	}
}


