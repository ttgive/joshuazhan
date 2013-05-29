package me.joshua.spring.groovy.demo;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import me.joshua.spring.groovy.demo.script.Script;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Launcher {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:groovy-simple.xml");

		Script script = context.getBean(Script.class);

		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));
		try {
			while (true) {
				String line = reader.readLine();
				if ("exit".equals(line)) {
					break;
				}
				script.run();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		context.close();
	}
}
