/**
 * 
 */
package me.joshua.webx.springext.diamond.resource;

import java.util.concurrent.Executor;

import com.alibaba.citrus.util.Assert;
import com.taobao.diamond.manager.ManagerListener;

/**
 * @author <a href="mailto:daonan.zhan@gmail.com">Joshua Zhan</a>
 */
public class DiamondListerner implements ManagerListener {

	private DiamondResource resource;

	public DiamondListerner(DiamondResource resource) {
		Assert.assertNotNull(resource);
		this.resource = resource;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.taobao.diamond.manager.ManagerListener#getExecutor()
	 */
	@Override
	public Executor getExecutor() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.taobao.diamond.manager.ManagerListener#receiveConfigInfo(java.lang
	 * .String)
	 */
	@Override
	public void receiveConfigInfo(String configInfo) {
		resource.update(configInfo);
	}

}
