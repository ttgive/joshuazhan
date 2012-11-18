package me.joshua.spring.schedule.demo;

import java.util.Random;

public class ScheduleUtil {

	private static Random random = new Random(System.currentTimeMillis());

	public static void randomSleep() {
		randomSleep(1);
	}

	public static void randomSleep(int max) {
		max = Math.abs(max);
		try {
			Thread.sleep((int) (random.nextDouble() * 1000 * max));
		} catch (Exception e) {
		}
	}
}
