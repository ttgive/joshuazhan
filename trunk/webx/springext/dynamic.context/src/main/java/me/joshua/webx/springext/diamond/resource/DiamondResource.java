/**
 * 
 */
package me.joshua.webx.springext.diamond.resource;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

import org.apache.commons.io.input.ReaderInputStream;

import com.alibaba.citrus.service.resource.Resource;
import com.alibaba.citrus.util.Assert;
import com.alibaba.citrus.util.StringUtil;
import com.taobao.diamond.client.Diamond;

/**
 * @author <a href="mailto:daonan.zhan@gmail.com">Joshua Zhan</a>
 */
public class DiamondResource implements Resource {

	private URL url;
	private String content;
	private Date updateTime;

	public DiamondResource(String group, String dataId) {
		group = StringUtil.trimToNull(group);
		dataId = StringUtil.trimToNull(dataId);
		Assert.assertNotNull(group);
		Assert.assertNotNull(dataId);

		try {
			content = Diamond.getConfig(dataId, group, 1000);
			update(content);
		} catch (IOException e) {
			throw new RuntimeException();
		}
		Diamond.addListener(dataId, group, new DiamondListerner(this));

		/**
		 * Spring在生成Groovy Bean的时候，使用资源名来生成Bean名。<br>
		 * Webx的Resource接口没有定义获取资源名的方法，只在适配器里把URL的最后一段作为文件名返回。<br>
		 * 此处生成的URL只为了让Spring能给Groovy生成名字。
		 */
		StringBuilder urlBuilder = new StringBuilder();
		urlBuilder.append("http://diamond").append("/").append(group)
				.append("/").append(dataId);
		try {
			url = new URL(urlBuilder.toString());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	public void update(String content) {
		this.content = content;
		updateTime = new Date();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.alibaba.citrus.service.resource.Resource#getURL()
	 */
	@Override
	public URL getURL() {
		return url;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.alibaba.citrus.service.resource.Resource#getFile()
	 */
	@Override
	public File getFile() {
		return null;
	}

	/**
	 * 根据从Diamond中获得的内容生成InputStream
	 * 
	 * @see com.alibaba.citrus.service.resource.Resource#getInputStream()
	 */
	@Override
	public InputStream getInputStream() throws IOException {
		// 做一次局部Copy，避免并发问题
		String content = this.content;
		// 如果內容是空的，则设置成空字符串，避免出错
		StringReader sr = new StringReader(StringUtil.isBlank(content) ? ""
				: content);
		return new ReaderInputStream(sr);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.alibaba.citrus.service.resource.Resource#exists()
	 */
	@Override
	public boolean exists() {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.alibaba.citrus.service.resource.Resource#lastModified()
	 */
	@Override
	public long lastModified() {
		if (null != updateTime) {
			return updateTime.getTime();
		} else {
			return 0;
		}
	}

}
