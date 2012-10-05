package me.joshua.webx.springext.configurationpoints.echo.decorator;

public class FakeDecorator {
	private static final String SMILE_STRING = " :-(";

	public String decorate(String in) {
		return in + SMILE_STRING;
	}
}
