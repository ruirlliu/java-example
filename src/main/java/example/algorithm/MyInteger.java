package example.algorithm;

public class MyInteger {

	public int atoi(String str) {

		if (str == null || str.length() == 0) {
			return 0;
		}
		int length = str.length();

		int index = 0;
		while (index < length && str.charAt(index) == ' ') {
			index++;
		}
		if (index == length) {
			return 0;
		}

		char firstChar = str.charAt(index);
		boolean negative = false;
		int limit = -Integer.MAX_VALUE;
		if (!isNumber(firstChar)) {
			if (firstChar == '-') {
				negative = true;
				limit = Integer.MIN_VALUE;
			} else if (firstChar != '+') {
				return 0;
			}
			if (length == 1) {
				return 0;
			}
			index++;
		}

		int res = 0;
		while (index < length) {
			char curChar = str.charAt(index++);
			if (!isNumber(curChar)) {
				break;
			}
			if (res < limit / 10) {
				return negative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
			}
			res *= 10;
			if (res < limit + (curChar - '0')) {
				return negative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
			}
			res -= (curChar - '0');
		}
		return negative ? res : -res;
	}

	private boolean isNumber(char c) {
		return c >= '0' && c <= '9';
	}

	public String itoa(int val) {
		int size = size(val);
		char[] chars = new char[size];
		boolean negative = val < 0;
		if (!negative) {
			val = -val;
		}
		int index = size - 1;
		while (index >= 0 && val < -10) {
			chars[index] = (char) (-(val - (val / 10 * 10)) + '0');
			val = val / 10;
			index--;
		}
		chars[index--] = (char) (-val + '0');
		if (negative) {
			chars[index] = '-';
		}
		return new String(chars);
	}

	private int size(int num) {
		int d = 1;
		if (num > 0) {
			d = 0;
			num = -num;
		}
		int val = -10;
		for (int i = 1; i < 10; i++) {
			if (num > val) {
				return i + d;
			}
			val *= 10;
		}
		return 10 + d;
	}


	public static void main(String[] args) {
		MyInteger numberChange = new MyInteger();
		//		System.out.println(numberChange.atoi("25235"));
		//		System.out.println(numberChange.atoi("-2147483648"));
		//		System.out.println(numberChange.atoi(Integer.toString(Integer.MAX_VALUE)));
		//		System.out.println(numberChange.atoi("-45654564"));
		//		System.out.println(numberChange.atoi("-45654566656546544"));
		//		System.out.println(numberChange.atoi("45654566656546544"));
		System.out.println(numberChange.itoa(Integer.MAX_VALUE));
		System.out.println(numberChange.itoa(Integer.MIN_VALUE));
		System.out.println(numberChange.itoa(-456435));
		System.out.println(numberChange.itoa(54651324));

	}

}
