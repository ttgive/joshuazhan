package me.joshua.spring.schedule.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Launcher {

	private ApplicationContext context;

	public Launcher(String config) {
		context = new ClassPathXmlApplicationContext(new String[] { config });
	}

	public void basic() {
		TaskExample example = context.getBean(TaskExample.class);
		example.printMessages();
	}

	public void terminate() {
		try {
			Thread.sleep(4 * 60 * 1000);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.exit(0);
		}

	}

	public static void main(String[] args) {
		Launcher launcher = new Launcher("configuration.xml");
		// launcher.basic();
		launcher.terminate();
	}

}
