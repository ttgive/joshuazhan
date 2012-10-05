package me.joshua.webx.springext.configurationpoints.echo.decorator;

import com.alibaba.citrus.springext.support.parser.AbstractSingleBeanDefinitionParser;

public class UpperCaseDecorator implements Decorator {

	@Override
	public String decorate(String in) {
		return in.toUpperCase();
	}

	public static class DefinitionParser extends
			AbstractSingleBeanDefinitionParser<UpperCaseDecorator> {
	}
}
