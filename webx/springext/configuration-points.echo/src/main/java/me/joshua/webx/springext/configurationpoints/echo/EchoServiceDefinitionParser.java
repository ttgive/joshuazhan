package me.joshua.webx.springext.configurationpoints.echo;

import static com.alibaba.citrus.springext.util.DomUtil.ns;
import static com.alibaba.citrus.springext.util.DomUtil.sameNs;
import static com.alibaba.citrus.springext.util.DomUtil.theOnlySubElement;
import static com.alibaba.citrus.springext.util.SpringExtUtil.attributesToProperties;
import static com.alibaba.citrus.springext.util.SpringExtUtil.getSiblingConfigurationPoint;
import static com.alibaba.citrus.springext.util.SpringExtUtil.parseConfigurationPointBean;
import static com.alibaba.citrus.springext.util.SpringExtUtil.subElementsToProperties;

import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

import com.alibaba.citrus.springext.ConfigurationPoint;
import com.alibaba.citrus.springext.Contribution;
import com.alibaba.citrus.springext.ContributionAware;
import com.alibaba.citrus.springext.support.parser.AbstractNamedBeanDefinitionParser;

public class EchoServiceDefinitionParser extends
		AbstractNamedBeanDefinitionParser<EchoServiceImpl> implements
		ContributionAware {

	private final String DECORATOR_NAMESPACE = "http://www.alibaba.com/schema/services/echo-service/decorators";
	private ConfigurationPoint decoratorsConfigurationPoint;

	@Override
	protected void doParse(Element element, ParserContext parserContext,
			BeanDefinitionBuilder builder) {
		subElementsToProperties(element, builder, sameNs(element));
		attributesToProperties(element, builder, "preTitle", "postTitle",
				"separator");
		parseDecorators(element, parserContext, builder);
	}

	private void parseDecorators(Element element, ParserContext parserContext,
			BeanDefinitionBuilder builder) {
		Element decoratorElement = theOnlySubElement(element,
				ns(DECORATOR_NAMESPACE));
		if (null == decoratorElement) {
			return;
		}
		BeanDefinitionHolder decoratorBean = parseConfigurationPointBean(
				decoratorElement, decoratorsConfigurationPoint, parserContext, builder);
		builder.addPropertyValue("decorator", decoratorBean);
	}

	@Override
	public void setContribution(Contribution contrib) {
		decoratorsConfigurationPoint = getSiblingConfigurationPoint(
				"services/echo-service/decorators", contrib);
	}

	@Override
	protected String getDefaultName() {
		return EchoService.DEFAULT_NAME;
	}
}