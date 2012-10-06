package me.joshua.webx.springext.configurationpoints.echo.decorator;

import com.alibaba.citrus.springext.support.parser.AbstractSingleBeanDefinitionParser;

/**
 * 把消息中的所有字母变成大写的
 * 
 * @author <a href="mailto:daonan.zhan@gmail.com">Joshua Zhan</a> 2012-10-5
 */
public class UpperCaseDecorator implements Decorator {

	@Override
	public String decorate(String in) {
		return in.toUpperCase();
	}

	public static class DefinitionParser extends
			AbstractSingleBeanDefinitionParser<UpperCaseDecorator> {
	}
}
