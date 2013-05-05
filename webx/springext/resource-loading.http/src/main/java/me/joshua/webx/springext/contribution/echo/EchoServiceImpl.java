package me.joshua.webx.springext.contribution.echo;

import static com.alibaba.citrus.springext.util.DomUtil.sameNs;
import static com.alibaba.citrus.springext.util.SpringExtUtil.attributesToProperties;
import static com.alibaba.citrus.springext.util.SpringExtUtil.subElementsToProperties;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

import com.alibaba.citrus.springext.support.parser.AbstractNamedBeanDefinitionParser;

/**
 * EchoServiceImpl回显的内容示例如下：<br>
 * <code>"[Echo]: XXXX"</code><br>
 * 其中"["为preTitle，"Echo"为title，"]"为postTitle，":"为separator，"XXXX"则是消息内容
 * 
 * @author <a href="mailto:daonan.zhan@gmail.com">Joshua Zhan</a> 2012-10-5
 */
public class EchoServiceImpl implements EchoService {

	private String title;
	private String preTitle;
	private String postTitle;
	private String separator;

	@Override
	public String echo(String message) {
		StringBuilder sb = new StringBuilder();

		sb.append(preTitle).append(title).append(postTitle).append(separator)
				.append(' ').append(message);

		return sb.toString();
	}

	@Override
	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public void setPreTitle(String preTitle) {
		this.preTitle = preTitle;
	}

	@Override
	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}

	@Override
	public void setSeparator(String separator) {
		this.separator = separator;
	}

	/**
	 * 用于解析Echo Service的XML配置，生成对应的Bean Definition
	 */
	public static class DefinitionParser extends
			AbstractNamedBeanDefinitionParser<EchoServiceImpl> {

		@Override
		protected void doParse(Element element, ParserContext parserContext,
				BeanDefinitionBuilder builder) {
			// 调用工具类SpringExtUtil中的方法，分别把子元素和属性值设置到EchoService中
			subElementsToProperties(element, builder, sameNs(element));
			attributesToProperties(element, builder, "preTitle", "postTitle",
					"separator");
		}

		@Override
		protected String getDefaultName() {
			return EchoService.DEFAULT_NAME;
		}
	}

}