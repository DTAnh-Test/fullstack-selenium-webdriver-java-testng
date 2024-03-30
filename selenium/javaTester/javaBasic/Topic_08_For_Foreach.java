package javaTester.javaBasic;

import java.util.Iterator;

import org.testng.annotations.Test;

public class Topic_08_For_Foreach {

	@Test
	public void TC_01() {
		// Vế 1: Biến tạm để tăng giá trị lên sau mỗi lần duyệt
		// Dùng để so sánh với giá trị max
		// Vế 2: So sánh với max
		// Vế 3: Tăng i thêm 1 đơn vị sau khi chạy vào thân vòng for
		for (int i = 0; i < 5; i++) {
			System.out.println(i);
		}
		
		
	}
}
