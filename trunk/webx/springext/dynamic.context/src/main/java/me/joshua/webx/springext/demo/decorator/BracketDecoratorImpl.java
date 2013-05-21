/**
 * 
 */
package me.joshua.webx.springext.demo.decorator;

/**
 * @author <a href="mailto:daonan.zhan@gmail.com">Joshua Zhan</a>
 */
public class BracketDecoratorImpl implements StringDecorator {

	/**
	 * 在字符串前后加上方括号
	 * 
	 * @see me.joshua.webx.springext.demo.decorator.StringDecorator#decorate(java.lang.String)
	 */
	@Override
	public String decorate(String s) {
		StringBuilder builder = new StringBuilder();
		builder.append("[").append(s).append("]");
		return builder.toString();
	}

}
