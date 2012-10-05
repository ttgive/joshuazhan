package me.joshua.webx.springext.configurationpoints.echo.decorator;

/**
 * @author <a href="mailto:daonan.zhan@gmail.com">Joshua Zhan</a> 2012-10-5
 */
public class SmileDecorator implements Decorator {
	private static final String SMILE_STRING = " :-)";

	@Override
	public String decorate(String in) {
		return in + SMILE_STRING;
	}
}
