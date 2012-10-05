package me.joshua.webx.springext.contribution.firststep.demo;

import static com.alibaba.citrus.springext.util.DomUtil.sameNs;
import static com.alibaba.citrus.springext.util.SpringExtUtil.attributesToProperties;
import static com.alibaba.citrus.springext.util.SpringExtUtil.subElementsToProperties;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

import com.alibaba.citrus.springext.support.parser.AbstractNamedBeanDefinitionParser;

public class EchoServiceDefinitionParser extends
		AbstractNamedBeanDefinitionParser<EchoServiceImpl> {

	@Override
	protected void doParse(Element element, ParserContext parserContext,
			BeanDefinitionBuilder builder) {
		subElementsToProperties(element, builder, sameNs(element));
		attributesToProperties(element, builder, "preTitle", "postTitle",
				"separator");
	}

	@Override
	protected String getDefaultName() {
		return EchoService.DEFAULT_NAME;
	}
}