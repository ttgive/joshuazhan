/**
 * 
 */
package me.joshua.webx.dynamic.handler

import me.joshua.webx.springext.demo.decorator.StringDecorator

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier

/**
 * @author <a href="mailto:daonan.zhan@gmail.com">Joshua Zhan</a>
 */
class GroovyHandler implements Handler {

	@Autowired
	@Qualifier("bracketDecorator")
	StringDecorator decorator;

	String name;

	/* (non-Javadoc)
	 * @see me.joshua.webx.dynamic.handler.Handler#handle(java.lang.String)
	 */
	@Override
	public String handle(String s) {
		return name + ": handle string \"" + s + "\"";
	}
}
