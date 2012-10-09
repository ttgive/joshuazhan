package me.joshua.webx.springext.configurationpoints.echo.decorator;

/**
 * 用于修饰要回显的消息
 * 
 * @author <a href="mailto:daonan.zhan@gmail.com">Joshua Zhan</a> 2012-10-5
 */
public interface Decorator {
	
	/**
	 * 对传入的字符串进行修饰
	 * 
	 * @param s, 传入的字符串
	 * @return 修饰后的字符串
	 */
	public String decorate(String s);
}
