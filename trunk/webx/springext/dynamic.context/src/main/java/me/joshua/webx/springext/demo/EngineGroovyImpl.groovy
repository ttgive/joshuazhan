/**
 * 
 */
package me.joshua.webx.springext.demo;

import org.springframework.beans.BeansException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware

import com.alibaba.citrus.service.resource.ResourceLoadingService
import com.alibaba.citrus.service.resource.support.context.ResourceLoadingXmlApplicationContext

/**
 * @author <a href="mailto:daonan.zhan@gmail.com">Joshua Zhan</a>
 */
public class EngineGroovyImpl implements Engine, ApplicationContextAware {
	private ApplicationContext parentContext;
	private ApplicationContext engineContext;

	@Autowired
	private ResourceLoadingService resourceLoadingService;

	public void init() {
		try {
			String[] config = new String[1];
			config[0] = "/diamond/me.joshua/engine.config";
			engineContext = new ResourceLoadingXmlApplicationContext(config,
					parentContext);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void handle(String name) {
		try {
			Greeting greeting = engineContext.getBean(name, Greeting.class);
			if (null != greeting) {
				System.out.println(greeting.say());
			}
		} catch (Exception e) {
			System.out.println("No bean found for name[" + name + "]");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.context.ApplicationContextAware#setApplicationContext
	 * (org.springframework.context.ApplicationContext)
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
	throws BeansException {
		this.parentContext = applicationContext;
	}
}
