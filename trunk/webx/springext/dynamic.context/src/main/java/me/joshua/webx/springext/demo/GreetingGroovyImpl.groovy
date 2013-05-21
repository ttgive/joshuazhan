/**
 * 
 */
package me.joshua.webx.springext.demo

import me.joshua.webx.springext.demo.decorator.StringDecorator

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier

/**
 * @author <a href="mailto:daonan.zhan@gmail.com">Joshua Zhan</a>
 */
class GreetingGroovyImpl implements Greeting {

	/**
	 * 展示Groovy中调用其他Bean的功能
	 * titleDecorator是Spring中定义的另外一个Bean，负责装饰title
	 */
	@Autowired
	@Qualifier("bracketDecorator")
	StringDecorator titleDecorator

	String message
	String title

	/* (non-Javadoc)
	 * @see me.joshua.webx.springext.demo.Greeting#say()
	 */
	@Override
	public String say() {
		StringBuilder builder = new StringBuilder();
		builder.append(titleDecorator.decorate(title)).append(":").append(message);
		return builder.toString();
	}
}
