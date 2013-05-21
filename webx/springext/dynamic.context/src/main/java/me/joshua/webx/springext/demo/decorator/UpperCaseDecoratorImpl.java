/**
 * 
 */
package me.joshua.webx.springext.demo.decorator;

/**
 * @author <a href="mailto:daonan.zhan@gmail.com">Joshua Zhan</a>
 */
public class UpperCaseDecoratorImpl implements StringDecorator {

	/**
	 * 把字符串变成大写返回
	 * 
	 * @see me.joshua.webx.springext.demo.decorator.TitleDecorator#decorate(java.lang.String)
	 */
	@Override
	public String decorate(String s) {
		return null == s ? null : s.toUpperCase();
	}

}
