package ch15;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ch15_16 {

	public static void main(String[] args) {
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyy/MM/dd");
		LocalDate ldate = LocalDate.parse("2023/09/22", fmt);
		
		LocalDate ldatep = ldate.plusDays(1000);
		String str = ldatep.format(fmt);
		System.out.println("1000日後は" + str);
		
		//現在日付との比較
		LocalDate now = LocalDate.now();
		if (now.isAfter(ldatep)) {
			System.out.println("本日は、その日より未来です");
			
		}

	}

}
