package me.joshua.webx.springext.contribution.firststep.demo;

import me.joshua.webx.springext.contribution.firststep.demo.echo.Decorator;

public interface EchoService {
	
	public final static String DEFAULT_NAME = "echoService";
	
	public String echo(String message);
	
	public void setTitle(String title);
	
	public void setPreTitle(String preTitle);
	
	public void setPostTitle(String postTitle);
	
	public void setSeparator(String separator);
	
	public void setDecorator(Decorator decorator);
}
