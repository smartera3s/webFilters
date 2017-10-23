package com.smartera.infrastructure.webFilters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class requestCacheFilter implements Filter {
	
	private static HttpServletRequest request;

	public static HttpServletRequest getCachedRequest() {
		return requestCacheFilter.request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}		
	
	public void init(FilterConfig filterConfig) throws ServletException {	
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		if (!(request instanceof HttpServletRequest) || !(response instanceof HttpServletResponse)) {
            throw new ServletException("RequestTracingFilter only supports HTTP requests");
        }
		
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        System.out.println("=========================================================");
        System.out.println("[JOB ID] : "  + httpRequest.getHeader("request-id"));
        System.out.println("[JOB Story] : " + httpRequest.getHeader("JOB_STORY"));

        setRequest(httpRequest);
        
        chain.doFilter(request,response);

	}
	

	public void destroy() {
		
		
	}
	
	

}
