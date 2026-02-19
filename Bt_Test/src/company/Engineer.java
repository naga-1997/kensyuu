package company;
//エンジニアクラス
public class Engineer extends Employee {
	private String language;  //使用言語
	
	/*
	 * 開発実施　「{使用言語}で開発を行った」と出力
	 * 情報開示　「{名前}:{所属部署}　使用言語：{使用言語}」と出力
	 */
	
	//コンストラクタ
	public Engineer(String name,String department,String language) {
		this.setName(name);
		this.setDepartment(department);
		this.language = language;
	}
	
	//開発実施
	public void development() {
		System.out.println(this.language + "で開発を行った");
	}
	
	//情報開示
	@Override
	public void informationDisplay() {
		System.out.println("{" + this.getName() + "}:{" + this.getDepartment() + "}  使用言語：{" + this.language + "}");
	}

	//アクセサ
	public String getLanguage() {
		return this.language;
	}

}