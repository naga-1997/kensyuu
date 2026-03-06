package ch17;

public class ch17_Question_3 {

	public static void main(String[] args) {
		try {
		int i = Integer.parseInt("三");
		}catch(NumberFormatException e){
			System.out.println("NumberFormatException例外をcatchしました");
		}

	}

}