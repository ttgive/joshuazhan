/**
 * 
 */
package me.joshua.webx.springext.diamond.resource;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

import com.alibaba.citrus.springext.support.parser.AbstractSingleBeanDefinitionParser;

/**
 * @author <a href="mailto:daonan.zhan@gmail.com">Joshua Zhan</a>
 */
public class DiamondResourceLoaderDefinitionParser extends
		AbstractSingleBeanDefinitionParser<DiamondResourceLoader> {
	@Override
	protected void doParse(Element element, ParserContext parserContext,
			BeanDefinitionBuilder builder) {
	}
}