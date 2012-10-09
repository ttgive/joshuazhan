package me.joshua.webx.springext.configurationpoints.echo.decorator;

import com.alibaba.citrus.springext.support.parser.AbstractSingleBeanDefinitionParser;

/**
 * 把消息中的所有字母变成小写的
 * 
 * @author <a href="mailto:daonan.zhan@gmail.com">Joshua Zhan</a> 2012-10-5
 */
public class LowerCaseDecorator implements Decorator {

	@Override
	public String decorate(String message) {
		return message.toLowerCase();
	}

	public static class DefinitionParser extends
			AbstractSingleBeanDefinitionParser<LowerCaseDecorator> {
	}

}
