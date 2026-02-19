package company;

public class Main {
	public static void main(String[] args) {
		Bt bt = new Bt();
		
		HumanResource hr = new HumanResource("人事部長", "人事部", "pass");
		
		hr.processHiring(bt,"エンジニア","java","長嶋");
		
		bt.showAll();
	}
}
