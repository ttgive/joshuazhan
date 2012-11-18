package me.joshua.spring.schedule.demo;

import java.util.Date;

public class Test {
	public static void main(String[] args) {

		System.out.printf("[%s]Totally run %d times at %3$tH:%<tM:%<tS %<tL",
				"Hello", 1, new Date());
	}
}
