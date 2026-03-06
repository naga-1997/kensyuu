package ch15;

public class ch15_7 {

	public static void main(String[] args) {
		String s = "abc,def:ghi";
		String[] words = s.split("[,:]");
		for(String w : words) {
			System.out.println(w + "->");
		}

	}

}
