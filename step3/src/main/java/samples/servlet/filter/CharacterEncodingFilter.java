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
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 字符编码
 *
 * @author Zi Lai Ye
 * @date 2017/7/18
 */
@WebFilter(urlPatterns = {"/*"}, filterName = "characterEncodingFilter")
public class CharacterEncodingFilter implements Filter {

    public static final Logger logger = LoggerFactory.getLogger(CharacterEncodingFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (!(request instanceof HttpServletRequest) || !(response instanceof HttpServletResponse)) {
            throw new ServletException("CharacterEncodingFilter just supports HTTP requests");
        }

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        logger.info("request encoding :{} response encoding: {}", request.getCharacterEncoding(), response.getCharacterEncoding());

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
