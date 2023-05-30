package com.restful.booker.utils;

import java.time.LocalDate;
import java.util.Random;

public class TestUtils {

	public static String getRandomValue(){
		Random random = new Random();
		int randomInt = random.nextInt(100000);
		return Integer.toString(randomInt);
	}
	public static int getRandomNumber(){
		Random randI = new Random();
		int myRandInt = randI.nextInt(100);
		myRandInt = myRandInt+1;
		return myRandInt;
	}
	public static int createRandomIntBetween(int start, int end) {
		return start + (int) Math.round(Math.random() * (end - start));
	}
	public static LocalDate createRandomDate(int startYear, int endYear) {
		int day = createRandomIntBetween(1, 28);
		int month = createRandomIntBetween(1, 12);
		int year = createRandomIntBetween(startYear, endYear);
		return LocalDate.of(year, month, day);
	}
}
