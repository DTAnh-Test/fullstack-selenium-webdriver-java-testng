package javaTester.javaBasic;

public class Topic_04_Operator {
	public static void main(String[] args) {
		int number = 10;
		String address = "HCM";
		
//		number += 5; // number = number + 5
//		number -= 5; // number = number - 5
		number *= 3;
		System.out.println(number);
		
		// Lấy phần nguyên: 20/5 = 4
		System.out.println(number / 5);
		// Lấy phần dư: 20%6 =  3 dư 2
		System.out.println(number % 6);
		
		System.out.println(number++); // In number = 20 trước, ++ = tăng number lên 1 = 21
		System.out.println(++number); // ++ tăng number lên 22 trước, in number = 22 ra
//		System.out.println(number--); // In number = 20 trước, --+ = giảm number đi 1 = 19
//		System.out.println(--number); // -- giảm number xuống 18 trước, in number = 18 ra
		
		// Quan hệ và: &&
		// Hoặc: ||
		// Phủ định: !
		
		// Tam nguyên: = ? :
		boolean status = (address == "Ha Noi") ? true : false; // Nếu address == "Ha Noi" thì trả về true, không thì false
		
	}
}
