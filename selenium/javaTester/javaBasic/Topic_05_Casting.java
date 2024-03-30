package javaTester.javaBasic;

public class Topic_05_Casting {
	public static void main(String[] args) {
		// Ngầm định, không mất dữ liệu
//		byte bNumber = 126;
//		System.out.println(bNumber);
//
//		short sNumber = bNumber;
//		System.out.println(sNumber);
//		
//		int iNumber = bNumber;
//		System.out.println(iNumber);
//		
//		long lNumber = bNumber;
//		System.out.println(lNumber);
//		
//		float fNumber = bNumber;
//		System.out.println(fNumber);
//		
//		double dNumber = bNumber;
//		System.out.println(dNumber);
		
		// Tường minh (Ép kiểu/ Cast)
		double dNumber = 654321789456789d;
		System.out.println(dNumber);
		
		float fNumber = (float) dNumber;
		System.out.println(fNumber);
		
		long lNumber = (long) fNumber;
		System.out.println(lNumber);
		
		int iNumber = (int) lNumber;
		System.out.println(iNumber);
	}
}
