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

/**
 * @author Zi Lai Ye
 * @date 2017/7/18
 */
public class MyFilter1 implements Filter {

    public static final Logger logger = LoggerFactory.getLogger(MyFilter1.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("{} filter init....", this.getClass());
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        logger.info("{} doFilter method invoked.", this.getClass());

        chain.doFilter(request, response);

        logger.info("{} doFilter method invoked completed.", this.getClass());

    }

    @Override
    public void destroy() {
        logger.info("{} filter destroy....", this.getClass());
    }
}
