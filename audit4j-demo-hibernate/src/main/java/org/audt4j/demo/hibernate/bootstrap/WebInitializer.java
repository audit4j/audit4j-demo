package org.audt4j.demo.hibernate.bootstrap;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration.Dynamic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * The Class WebInitializer.
 *
 * @author JanithB
 */
public class WebInitializer {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.web.WebApplicationInitializer#onStartup(javax.servlet
	 * .ServletContext)
	 */
	
	public void onStartup(ServletContext servletContext) {
		AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
		ctx.setConfigLocation("org.audt4j.demo.hibernate.config");
		servletContext.addListener(new ContextLoaderListener(ctx));
		ctx.setServletContext(servletContext);
		Dynamic servlet = servletContext.addServlet("dispatcher",
				new DispatcherServlet(ctx));
		servlet.addMapping("/*");
		servlet.setLoadOnStartup(1);
		FilterRegistration.Dynamic springSecurityFilterChain = servletContext
				.addFilter("springSecurityFilterChain",
						new DelegatingFilterProxy());
		springSecurityFilterChain.addMappingForUrlPatterns(null, false, "/*");
		springSecurityFilterChain.setAsyncSupported(true);
	}

}
