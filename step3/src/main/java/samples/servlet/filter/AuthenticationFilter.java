package samples.servlet.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

/**
 * 身份认证权限控制过滤器
 *
 * 判断用户是否登录,未登录则退出系统
 *
 * @author Zi Lai Ye
 * @date 2017/7/18
 */
public class AuthenticationFilter implements Filter {

    public static final Logger logger = LoggerFactory.getLogger(AuthenticationFilter.class);

    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (!(request instanceof HttpServletRequest) || !(response instanceof HttpServletResponse)) {
            throw new ServletException("AuthenticationFilter just supports HTTP requests");
        }

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpServletResponseWrapper responseWrapper = new HttpServletResponseWrapper(resp);

        // 登录页面, 对于登录页面不需要做权限验证;
        String loginUrl = filterConfig.getInitParameter("loginUrl");

        // 是否禁用过滤器, Y 禁用过滤器  N 过滤生效
        String disableFilter = filterConfig.getInitParameter("disableFilter");

        // 是否已登录 true 为已登录 , false 为未登录
        String islogon = filterConfig.getInitParameter("islogon");

        if(null != disableFilter && disableFilter.toUpperCase().equals("Y")){
            logger.info("{} filter is disabled.", this.getClass());
            chain.doFilter(request, response);
            return;
        }

        String requestURI = req.getRequestURI();
        // 如果是登录页面,不做控制
        if(null != loginUrl && requestURI.equals(loginUrl)){
            chain.doFilter(request, response);
            return;
        }

        // 如果未登录。。。。
        if (null == islogon || islogon.equals("false")) {
            logger.info("{} is not login, redirect to login url: {}", this.getClass(), loginUrl);
            resp.sendRedirect(loginUrl);
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        this.filterConfig = null;
    }
}
