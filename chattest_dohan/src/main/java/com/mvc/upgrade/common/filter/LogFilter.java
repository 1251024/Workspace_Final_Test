package com.mvc.upgrade.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogFilter implements Filter {

	private Logger logger = LoggerFactory.getLogger(LogFilter.class);
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	// Filter : 요청과 응답에 대한 정보들을 변경할 수 있게 개발자들에게 제공하는 서블릿 컨데이너
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest)request; // 해당 요청을 HttpServletRequest로 형변환.
		
		String remoteAddr = req.getRemoteAddr();		//객체 내 함수로 클라이언트 ip를 가져온다.
		String uri = req.getRequestURI();				// 요청 URL 중 포트번호와 쿼리 사이의 부분을 가져온다 = 컨텍스트 경로 + 서블릿 경로
		String url = req.getRequestURL().toString();	// 프로토콜 + 도메인 + 포트번호 + 컨텍스트 경로 + 서블릿 경로 알려준다.
		String queryString = req.getQueryString();		// 쿼리를 가져온다.
		
		String referer = req.getHeader("referer");
		String agent = req.getHeader("User-Agent");
		
		StringBuffer sb = new StringBuffer();
		sb.append("\n* remoteAddr : " + remoteAddr) // ip주소
		  .append("\n* uri : " + uri)				// uri : 통합 자원 식별자 인터넷 자원을 나타내는 유일한 주소.
		  .append("\n* url : " + url)				// url : 네트워크 상에서 자원이 어디 있는지를 알려주기 위한 규약 = 웹 리소스
		  .append("\n* queryString : " + queryString)
		  .append("\n* referer : " + referer)		//이전 페이지(보내는 페이지)
		  .append("\n* agent : " + agent)			// 사용자 정보
		  .append("\n");
		
		logger.info("\nLOG Filter" + sb);
		
		chain.doFilter(req, response);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
