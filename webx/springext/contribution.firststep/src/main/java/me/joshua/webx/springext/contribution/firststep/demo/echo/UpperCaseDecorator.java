package me.joshua.webx.springext.contribution.firststep.demo.echo;

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
