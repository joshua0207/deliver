package com.order.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class SessionFilter implements Filter {

	protected FilterConfig filterConfig = null;

	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

	public void destroy() {
		this.filterConfig = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
//		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession(false);
		String path = req.getServletPath();
		
		if(path.matches("(/resources/).*")){
			chain.doFilter(request, response);
			System.out.println(" true ==>" + path);
			return;
		}
		System.out.println(" false ==>" + path);
		
		if (session == null) {
			if ("/dispatcher".equals(path) || "/".equals(path)) {
				req.getSession(true);
				chain.doFilter(request, response);
				return;
			} else {
				request.getRequestDispatcher("/sessionTimeout").forward(
	                    request,
	                    response);
				return;
			}
			
		}

		String userName = (String) session.getAttribute("userName");
		String companyId = (String) session.getAttribute("companyId");

		if (userName == null || companyId == null) {
			if ("/dispatcher".equals(path) || "/".equals(path)) {
				req.getSession(true);
				chain.doFilter(request, response);
				return;
			} else {
				request.getRequestDispatcher("/sessionTimeout").forward(
	                    request,
	                    response);
				return;
			}
		}
		
		if ("/dispatcher".equals(path)){
			String reqUserName = (String) request.getParameter("userName");
//			String reqCompanyId = (String) request.getParameter("companyId");
			
			if (!userName.equals(reqUserName)){
				req.getSession().invalidate();
				request.getRequestDispatcher("/sessionTimeout").forward(
	                    request,
	                    response);
				return;
			}
		}

		chain.doFilter(request, response);
	}

}
