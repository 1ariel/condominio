/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arb.condominio.controllers;


import com.arb.condominio.classHelpers.CookieHelper;
import com.arb.condominio.controllers.Beans.loginBean;
import java.io.IOException;
import javax.inject.Inject;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/Security")
public class Security implements Filter {

	private ServletContext context;
	
        @Override
	public void init(FilterConfig fConfig) throws ServletException {
		this.context = fConfig.getServletContext();
		this.context.log("AuthenticationFilter initialized");
	}
	
        @Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
               HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;    
            try{
		
		
		String uri = req.getRequestURI();
		this.context.log("Requested Resource::"+uri);
		 HttpSession session;
                        loginBean login = null;
                        
                                          
                         
                        /*
                      CookieHelper cookie = new CookieHelper();
                      Cookie cokielogin=  cookie.getCookie("logged");
                     
                      if (cokielogin!=null) {                          
                          login = new loginBean();
                          if ("true".equals(cokielogin.getValue())) {
                              login.setLogged(true);
                               Cookie cokieusername=  cookie.getCookie("username");
                               login.setUsername(cokieusername.getValue());
                          }
                          
                          
                    
                                     }
                      else{   */             
                     session = req.getSession(false);
                    login = (loginBean) session.getAttribute("loginWithSession");
                  /*    }*/
		
		if(login != null && login.getLogged()){
                        
                    //    cookie.setCookie("username", login.getUsername(), 3600);
                    //    cookie.setCookie("logged", "true", 3600);        
                        chain.doFilter(request, response);
		}else{
			// pass the request along the filter chain			
			this.context.log("Unauthorized access request");
			res.sendRedirect("/condominio/Login.xhtml");
		}
                }
                catch(Exception e){
                res.sendRedirect("/condominio/Login.html");
                }
		
		
	}

	

	public void destroy() {
		//close any resources here
	}

}