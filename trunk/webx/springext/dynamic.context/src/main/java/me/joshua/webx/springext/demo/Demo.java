package me.joshua.webx.springext.demo;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.springframework.core.io.ClassPathResource;

import com.alibaba.citrus.service.resource.support.context.ResourceLoadingXmlApplicationContext;

/**
 * 运行示例的类
 * 
 * @author <a href="mailto:daonan.zhan@gmail.com">Joshua Zhan</a> 2012-10-5
 */
public class Demo {

	public static void main(String[] args) {
		ResourceLoadingXmlApplicationContext context = new ResourceLoadingXmlApplicationContext(
				new ClassPathResource("parent.xml"));
		Engine greetingEngine = context.getBean(Engine.class);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < 1000; i++) {
			try {
				String name = br.readLine();
				greetingEngine.handle(name);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
