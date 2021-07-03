package com.mvc.upgrade.common.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class LogFilter implements javax.servlet.Filter {

	//slf4j로 임포트 하기!!
	private Logger logger = LoggerFactory.getLogger(LogFilter.class);
	
	
	// init은 필터가 만들어질때
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		//1.request 파라미터를 이용해서 요청의 필터 작업을 수행
		//2. 만약 다음필터 존재할 경우, 체인의 다음 필터를 처리한다.
		//		chain.doFilter(request, response);
		//3. response를 이용하여 응답의 필터링 작업을 수행
		
		
		HttpServletRequest req = (HttpServletRequest) request;	//요청받은 내용을 HttpServletRequest로 형변환
		
		String remoteAddr = req.getRemoteAddr();				//클라이언트 ip주소 가져오기
		String uri = req.getRequestURI();
		String url = req.getRequestURL().toString();
		String queryString = req.getQueryString();
		
		String referer = req.getHeader("referer");
		String agent = req.getHeader("User-Agent");
		
		//append의 역할?
		StringBuffer sb = new StringBuffer();				// 한번에 모아서 보내는 strinbuffer 객체 생성해서 sb로 저장
		sb.append("\n* remoteAddr : " + remoteAddr)			// ip주소  //localhost는 Ipv6 로 뜸//Ipv4 버전으로 요청하면  127.0.0.1 뜸 //http://127.0.0.1:8787/upgrade/
		  .append("\n* uri : " + uri)						// http 요청 url중 queryString까지 변환.(context path) 요청 URL 중 포트번호와 쿼리 사이의 부분, 즉 컨텍스트 경로+서블릿 경로 //(URL경로+URN이름)유니파잉 리소스(객체)가 어떤 위치+어떤놈 전체적으로 보여줌
		  .append("\n* url : " + url)						// protocol+servername+portnumber_serverpath // 쿼리를 제외한 프로토콜+도메인+포트번호+컨텍스트 경로+서블릿 경로
		  .append("\n* queryString : " + queryString)		// 경로 뒤에 있는 요청 쿼리 문자열(key-value형태로 데이터 전달)
		  .append("\n* referer : " + referer)				// 이전 페이지(보내는 페이지) url (getHeader:지정한 요청 헤더값을 문자열로 반환)
		  .append("\n* agent : " + agent)					// 사용자 정보(brower version, os등 ...)
		  .append("\n");
		
		
		logger.info("\nLOG Filter" + sb);					// filter 실행
		
		chain.doFilter(req, response);//중요
		//FilterChaining: reqest와 response를 받아서 chain.do와 연결해서 넘겨줌 
		//filter는 여러개 쓸수있으며 필터를 연쇄적으로 연결해 줌(인코딩 필터 + 로그필터 + ....)
		// 다음에 존재하는 필터가 있으면, 그 필터가 실행될 수 있도록 chaining!
		// request에 들어있는 정보를 읽어서 log.info한 것!
				
		//Filter : 리퀘스트가 서버에 들어가기 전에, 응답될때 동작
	}

	// destroy 필터가 종료될때
	@Override
	public void destroy() {
		
	}

}
