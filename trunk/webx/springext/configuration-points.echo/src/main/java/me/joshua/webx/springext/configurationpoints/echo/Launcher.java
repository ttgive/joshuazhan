package me.joshua.webx.springext.configurationpoints.echo;

import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;

import com.alibaba.citrus.springext.support.context.XmlApplicationContext;

/**
 * @author <a href="mailto:daonan.zhan@gmail.com">Joshua Zhan</a> 2012-10-5
 */
public class Launcher {

	private final static String LINE_SEPARATOR = "============================";
	private final static String GREETING_MESSAGE = "Hello World!";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// 配置1为使用自定义扩展元素的示例
		extendTest("configuration1.xml");
		// 配置2为使用默认元素的示例
		extendTest("configuration2.xml");
		// 配置3为在默认元素中引用Bean的示例
		extendTest("configuration3.xml");
	}

	public static void extendTest(String config) {
		ApplicationContext context = new XmlApplicationContext(
				new ClassPathResource(config));
		EchoService echoService = (EchoService) context.getBean("echoService");
		System.out.println();
		System.out.println(LINE_SEPARATOR);
		System.out.println(echoService.echo(GREETING_MESSAGE));
		System.out.println(LINE_SEPARATOR);
	}
}
