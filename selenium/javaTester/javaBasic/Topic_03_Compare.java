package javaTester.javaBasic;

public class Topic_03_Compare {
	int number = 8;
	
	public static void main() {
		// Nguyên thuỷ
		// 1 vùng nhớ cho biến x
		int x = 5;
		
		// 1 vùng nhớ cho biến y
		int y = x; 
		
		System.out.println("x = " + x); // x=5
		System.out.println("y = " + y); // y = 5

		y = 10;
		System.out.println("x = " + x); // x = 5
		System.out.println("y = " + y); // y = 10
		
		// Tham chiếu
		Topic_03_Compare firstVariable =  new Topic_03_Compare();
		Topic_03_Compare secondVariable =  firstVariable;
		System.out.println(firstVariable.number);
		System.out.println(secondVariable.number);
		
		secondVariable.number = 10;
		System.out.println(firstVariable.number); // = 10
		System.out.println(secondVariable.number); // = 10
		
//		Topic_03_Compare firstVariable =  new Topic_03_Compare();
//		Topic_03_Compare secondVariable =  new Topic_03_Compare();
//		System.out.println(firstVariable.number);
//		System.out.println(secondVariable.number);
//		
//		secondVariable.number = 10;
//		System.out.println(firstVariable.number); = 8
//		System.out.println(secondVariable.number); = 10
	}
}
