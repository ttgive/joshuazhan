package me.joshua.webx.springext.configurationpoints.echo.decorator;

public class SmileDecorator implements Decorator {
	private static final String SMILE_STRING = " :-)";

	@Override
	public String decorate(String in) {
		return in + SMILE_STRING;
	}
}
