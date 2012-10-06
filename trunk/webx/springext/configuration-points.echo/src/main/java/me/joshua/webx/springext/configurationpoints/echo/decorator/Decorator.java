package me.joshua.webx.springext.configurationpoints.echo.decorator;

/**
 * 用于修饰要回显的消息
 * 
 * @author <a href="mailto:daonan.zhan@gmail.com">Joshua Zhan</a> 2012-10-5
 */
public interface Decorator {
	
	public String decorate(String in);
}
