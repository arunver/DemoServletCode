package com.login;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
@WebFilter("/AuthenticationFilter")
public class AuthenticationFilter implements Filter {
 
    private ServletContext context;
     
    public void init(FilterConfig fConfig) throws ServletException {
        this.context = fConfig.getServletContext();
        this.context.log("AuthenticationFilter initialized");
    }
     
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

    	HttpServletRequest req = (HttpServletRequest) request;
    	HttpServletResponse res = (HttpServletResponse) response;

    	String uri = req.getRequestURI();
    	System.out.println(uri);
    	this.context.log("Requested Resource::"+uri);

    	HttpSession session = req.getSession(false);

    	if(session == null && uri.equalsIgnoreCase("/JS1/login")){
    		this.context.log("Unauthorized access request");
    		res.sendRedirect("./JS1.jsp");
    	}else{
    		// pass the request along the filter chain
    		HttpServletResponse hsr = (HttpServletResponse) res;
    		hsr.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
    		hsr.setHeader("Pragma", "no-cache"); // HTTP 1.0.
    		hsr.setDateHeader("Expires", 0); // Proxies.
    		chain.doFilter(request, response);
    	}

    }
 
     
 
    public void destroy() {
        //close any resources here
    }
 
}