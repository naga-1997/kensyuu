package ch16;

class Hero{
	private String name;
	public Hero(String name) {this.name = name;}
	public String getName() {return this.name;}
}



/*************************
 * 斉藤、鈴木をHeroとしてインスタンスして
 * ArrayListに格納
 * １人ずつ名前を表示
 *************************/

/*
class Main {
	public static void main(String[] args) {
		Hero h1 = new Hero("斉藤");
		Hero h2 = new Hero("鈴木");
		List<Hero> list = new ArrayList<Hero>();
		list.add(h1);
		list.add(h2);
		for(Hero s : list) {
			System.out.println(s.getName() + "さん");
		}
	}
}*/
