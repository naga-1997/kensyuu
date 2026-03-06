package ch15;

public class ch15_2 {

	public static void main(String[] args) {
		String s1 = "java and JavaScript";
		if (s1.contains("Java")) {
			System.out.println("文字列S1は、Javaを含んでいます");
		}
		if (s1.endsWith("Java")) {
			System.out.println("文字列S1は、Javaが末尾にあります");
		}
		System.out.println("文字列S1で最初にJavaが登場する位置は" + s1.indexOf("Java"));
		System.out.println("文字列S1で最後にJavaが登場する位置は" + s1.lastIndexOf("Java"));
	}

}
