/*
 * Copyright (c) 2017 ICM Uniwersytet Warszawski All rights reserved.
 * See LICENCE.txt file for licensing information.
 */
package net.bixbit.vulnerablespam;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

@Component
public class ChromeHelperFilter implements Filter 
{
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, 
			FilterChain filterChain) throws IOException, ServletException 
	{
		final HttpServletResponse res = (HttpServletResponse) servletResponse;
		res.addHeader("X-XSS-Protection", "0");

		filterChain.doFilter(servletRequest, servletResponse);
	}

	@Override
	public void destroy() {}
}