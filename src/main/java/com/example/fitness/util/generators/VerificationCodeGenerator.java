package com.example.fitness.util.generators;

import java.util.concurrent.ThreadLocalRandom;

public class VerificationCodeGenerator {

	public static String generate(){
		String code;
		ThreadLocalRandom random = ThreadLocalRandom.current();
		long num = random.nextLong();
		if(num < 0) {
			num = -num;
			code = String.valueOf(num);
		} else {
			code = String.valueOf(num);
		}
		return code;
	}
}
