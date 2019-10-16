package cn.smbms.interceptor;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.smbms.pojo.User;
import cn.smbms.tools.Constants;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SysInterceptor extends HandlerInterceptorAdapter {
	private Logger logger = Logger.getLogger(SysInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler) throws Exception{
		logger.debug("SysInterceptor preHandle ==========================");
		HttpSession session = request.getSession();

		User user = (User) session.getAttribute(Constants.USER_SESSION);

		if (null == user){
			response.sendRedirect(request.getContextPath()+"/401.jsp");
			return false;
		}
		return true;
	}

	public void  doFilter(ServletRequest servletRequest,
						  ServletResponse servletResponse, FilterChain filterChain)throws Exception, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse)servletResponse;
		//获取请求URL
		String requestURL = request.getRequestURI();
		if (requestURL.contains("/dologin.html")){
			//如果当前请求路径包含登录，直接校验
			filterChain.doFilter(servletRequest,servletResponse);
		}else{
			HttpSession session = request.getSession();
			User user = (User)session.getAttribute(Constants.USER_SESSION);
		}
	}
}
