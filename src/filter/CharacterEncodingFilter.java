package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;



@WebFilter("*")
public class CharacterEncodingFilter implements Filter {
	private static final String DEFAULT_ENCODING = "UTF-8";
	
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		System.out.println("filter 실행!!!!!!!");
		req.setCharacterEncoding(DEFAULT_ENCODING);
		resp.setCharacterEncoding(DEFAULT_ENCODING);
		chain.doFilter(req, resp);
		
	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
}
