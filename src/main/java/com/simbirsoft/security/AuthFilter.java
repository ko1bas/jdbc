package com.simbirsoft.security;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(filterName = "AuthFilter", urlPatterns = { "*.xhtml" })
public class AuthFilter implements Filter {
	@Inject
	private AuthService authService;
	
	public AuthFilter() {}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
							FilterChain chain) throws IOException, ServletException {
		try {
			// check whether session variable is set
			HttpServletRequest req = (HttpServletRequest) request;
			HttpServletResponse res = (HttpServletResponse) response;
			
			// allow user to proccede if url is login.xhtml or user logged in or
			// user is accessing any page in //public folder
			String reqURI = req.getRequestURI();
			if (reqURI.indexOf("/login.xhtml") >= 0 || (authService.isLoggedIn())
					|| reqURI.indexOf("/pub/") >= 0 || reqURI.contains("javax.faces.resource")) {
				chain.doFilter(request, response);
			} else {
				res.sendRedirect(req.getContextPath() + "/login.xhtml");
			}
		} catch (Throwable t) {
			System.out.println(t.getMessage());
		}
	} 

	@Override
	public void destroy() {}
}