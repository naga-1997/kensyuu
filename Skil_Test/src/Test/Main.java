package Test;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("[0:円,2:線,3:三角形,4:長方形,44:正方形]");
		try {
			//キーボードから数字を取得
			int number = scanner.nextInt();

			Figure f = null;

			//インスタンスを分岐
			if (number == 0) { //円のオブジェクト
				f = new Circle(100, 100, 20);
			} else if (number == 2) { //線のオブジェクト
				f = new Line(0, 0, 100, 100);
			} else if (number == 3) { //三角形のオブジェクト
				f = new Triangle(0, 0, 100, 100, 0, 200);
			} else if (number == 4) { //長方形のオブジェクト
				f = new Rectangle(0, 0, 100, 50);
			} else if (number == 44) { //正方形のオブジェクト
				f = new Square(0, 0, 200);
			}

			if (f != null) {
				f.draw();
				System.out.println("周囲の長さは" + f.getPerimeter());
				if (number == 3 || number == 4 || number == 44) {
					Polygon p = (Polygon) f;
					System.out.println("内角の和は、" + p.getInternalAngle());
				}
			} else {
				System.out.println("正しい数値を入力してください");
			}
		} catch (Exception e) {
			System.out.println("例外が発生しました");
		} finally {
			scanner.close();
		}
	}
}