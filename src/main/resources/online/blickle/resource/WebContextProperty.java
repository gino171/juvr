package online.blickle.resource;

import javax.servlet.ServletContext;

public class WebContextProperty {
	
	private ServletContext servletContext;
	
	public WebContextProperty(ServletContext webContext) {
		 this.servletContext = webContext;
	}
	
	public String getProperty(String name) {
		return servletContext.getInitParameter(name);
	}
	
	public int getIntegerProperty(String name) {
		String value = getProperty(name);
		return Integer.parseInt(value);
	}
	
}