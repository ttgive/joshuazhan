package me.joshua.spring.groovy.demo;

import me.joshua.spring.groovy.demo.service.MemberService;
import me.joshua.spring.groovy.demo.service.OrderService;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Launcher {

	// private ApplicationContext context;
	//
	// public Launcher(String config) {
	// context = new ClassPathXmlApplicationContext(new String[] { config });
	// }
	//
	// public void basic() {
	// TaskExample example = context.getBean(TaskExample.class);
	// example.printMessages();
	// }
	//
	// public void terminate() {
	// try {
	// Thread.sleep(4 * 60 * 1000);
	// } catch (Exception e) {
	// e.printStackTrace();
	// } finally {
	// System.exit(0);
	// }
	//
	// }

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:groovy-simple.xml");
		MemberService memberService = context.getBean(MemberService.class);
		System.out.println(memberService.findMember("daonan.zhan@gmail.com"));
		OrderService orderService = context.getBean(OrderService.class);
		System.out.println(orderService.findOrder(1));
		context.close();
	}

}
