/**
 * 
 */
package me.joshua.webx.dynamic;

import java.io.IOException;
import java.io.StringReader;
import java.util.concurrent.Executor;

import me.joshua.webx.dynamic.handler.Handler;

import org.apache.commons.io.input.ReaderInputStream;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.InputStreamResource;

import com.alibaba.citrus.service.resource.support.context.ResourceLoadingXmlApplicationContext;
import com.alibaba.citrus.util.Assert;
import com.alibaba.citrus.util.StringUtil;
import com.taobao.diamond.client.Diamond;
import com.taobao.diamond.manager.ManagerListener;

/**
 * @author <a href="mailto:daonan.zhan@gmail.com">Joshua Zhan</a>
 */
public class EngineImpl implements Engine, ApplicationContextAware {
	private ApplicationContext parentContext;
	private ResourceLoadingXmlApplicationContext engineContext;
	private String group;

	private String dataId;

	public void init() {
		group = StringUtil.trimToNull(group);
		dataId = StringUtil.trimToNull(dataId);
		Assert.assertNotNull(group);
		Assert.assertNotNull(dataId);

		try {
			String config = Diamond.getConfig(dataId, group, 1000);
			initEngineContext(config);
		} catch (IOException e) {
			throw new RuntimeException();
		}
		Diamond.addListener(dataId, group, new ManagerListener() {

			@Override
			public void receiveConfigInfo(String configInfo) {
				initEngineContext(configInfo);
			}

			@Override
			public Executor getExecutor() {
				return null;
			}
		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see me.joshua.webx.dynamic.Engine#handle(java.lang.String)
	 */
	@Override
	public String handle(String handlerBeanName, String s) {
		try {
			Handler handler = engineContext.getBean(handlerBeanName,
					Handler.class);
			return handler.handle(s);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void initEngineContext(String config) {
		InputStreamResource resource = new InputStreamResource(
				new ReaderInputStream(new StringReader(config)));
		if (null != engineContext) {
			synchronized (engineContext) {
				if (engineContext.isActive()) {
					engineContext.close();
				}
			}
		}
		engineContext = new ResourceLoadingXmlApplicationContext(resource,
				parentContext);
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

	public void setGroup(String group) {
		this.group = group;
	}

	public void setDataId(String dataId) {
		this.dataId = dataId;
	}

}
