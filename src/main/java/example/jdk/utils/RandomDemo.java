package example.jdk.utils;

import java.util.Random;

public class RandomDemo {

	public static void main(String[] args) {
		Random random = new Random();
		for (int i = 0; i < 10; i++) {
			int nextInt = random.nextInt(10);
			System.out.println(nextInt);
		}
	}

}
