/**
 * 
 */
package me.joshua.webx.springext.diamond.resource;

import static com.alibaba.citrus.util.BasicConstant.EMPTY_STRING;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

import com.alibaba.citrus.service.resource.Resource;
import com.alibaba.citrus.service.resource.ResourceLoader;
import com.alibaba.citrus.service.resource.ResourceLoaderContext;
import com.alibaba.citrus.service.resource.ResourceLoadingOption;
import com.alibaba.citrus.service.resource.ResourceLoadingService;
import com.alibaba.citrus.util.StringUtil;

/**
 * @author <a href="mailto:daonan.zhan@gmail.com">Joshua Zhan</a>
 */
public class DiamondResourceLoader implements ResourceLoader {

	private final static String SEPERATOR = "/";

	private final Map<String, DiamondResource> resourceCache = new HashMap<String, DiamondResource>();
	private final ReentrantLock lock = new ReentrantLock();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.alibaba.citrus.service.resource.ResourceLoader#init(com.alibaba.citrus
	 * .service.resource.ResourceLoadingService)
	 */
	@Override
	public void init(ResourceLoadingService resourceLoadingService) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.alibaba.citrus.service.resource.ResourceLoader#getResource(com.alibaba
	 * .citrus.service.resource.ResourceLoaderContext, java.util.Set)
	 */
	@Override
	public Resource getResource(ResourceLoaderContext context,
			Set<ResourceLoadingOption> options) {
		String resourceName = context.substitute(EMPTY_STRING);

		if (resourceName.startsWith(SEPERATOR)) {
			resourceName = resourceName.substring(1);
		}

		String[] diamondConfig = resourceName.split(SEPERATOR);
		if (diamondConfig.length != 2) {
			return null;
		}

		String group = StringUtil.trimToNull(diamondConfig[0]);
		String dataId = StringUtil.trimToNull(diamondConfig[1]);
		if (null == group || null == dataId) {
			return null;
		}

		String cacheKey = new StringBuilder(group).append(SEPERATOR)
				.append(dataId).toString();
		DiamondResource resource = resourceCache.get(cacheKey);

		if (null == resource) {
			lock.lock();
			try {
				resource = resourceCache.get(cacheKey);
				if (null == resource) {
					resource = new DiamondResource(group, dataId);
					resourceCache.put(cacheKey, resource);
				}
			} finally {
				lock.unlock();
			}
		}

		return resource;
	}

}
