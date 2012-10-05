package me.joshua.webx.springext.demo.echo;

import com.alibaba.citrus.springext.support.parser.AbstractSingleBeanDefinitionParser;

public class LowerCaseDecorator implements Decorator {

	@Override
	public String decorate(String in) {
		return in.toLowerCase();
	}

	public static class DefinitionParser extends
			AbstractSingleBeanDefinitionParser<LowerCaseDecorator> {
	}

}
