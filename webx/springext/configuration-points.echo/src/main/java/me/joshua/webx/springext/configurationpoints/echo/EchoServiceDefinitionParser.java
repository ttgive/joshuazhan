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

/**
 * @author <a href="mailto:daonan.zhan@gmail.com">Joshua Zhan</a> 2012-10-5
 */
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
		parseDecorator(element, parserContext, builder);
	}

	/**
	 * 解析回显服务的消息修饰器配置，如果存在就生成对应的修饰器Bean定义
	 */
	private void parseDecorator(Element element, ParserContext parserContext,
			BeanDefinitionBuilder builder) {
		// 获取唯一的Decorator子元素
		Element decoratorElement = theOnlySubElement(element,
				ns(DECORATOR_NAMESPACE));
		if (null == decoratorElement) {
			return;
		}
		
		// 根据扩展点生成Decorator元素对应的BeanDefinition
		BeanDefinitionHolder decoratorBean = parseConfigurationPointBean(
				decoratorElement, decoratorsConfigurationPoint, parserContext, builder);
		// 作为一个内部Bean设置到Echo Service的BeanDefinition中
		builder.addPropertyValue("decorator", decoratorBean);
	}

	@Override
	public void setContribution(Contribution contrib) {
		// 获取Decorator扩展点实例
		decoratorsConfigurationPoint = getSiblingConfigurationPoint(
				"services/echo-service/decorators", contrib);
	}

	@Override
	protected String getDefaultName() {
		return EchoService.DEFAULT_NAME;
	}
}