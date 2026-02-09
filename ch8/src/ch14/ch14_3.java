package ch14;

class Printer {
	public void printAnyThing(Object o) {
		//どんな型のインスタンスでも受け取って画面に表示
		System.out.println(o.toString());
	}

}
