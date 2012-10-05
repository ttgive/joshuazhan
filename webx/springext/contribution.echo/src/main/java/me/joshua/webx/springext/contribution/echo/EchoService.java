package me.joshua.webx.springext.contribution.echo;

/**
 * 回显服务，在终端显示所有传入的消息
 * 
 * @author <a href="mailto:daonan.zhan@gmail.com">Joshua Zhan</a> 2012-10-5
 */
public interface EchoService {

	public final static String DEFAULT_NAME = "echoService";

	public String echo(String message);

	public void setTitle(String title);

	public void setPreTitle(String preTitle);

	public void setPostTitle(String postTitle);

	public void setSeparator(String separator);

}
