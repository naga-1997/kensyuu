package ch17;

import java.io.FileWriter;

public class ch17_10 {

	public static void main(String[] args) {
		try(FileWriter fW = new FileWriter("date.txt");){
			fW.write("hello");
		}catch(Exception e) {
			System.out.println("何らかの例外が発生しました");
		}
	}
}
