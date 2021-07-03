package com.phoenix.carrot.user.biz;

import java.io.PrintWriter;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phoenix.carrot.user.dao.UserDao;
import com.phoenix.carrot.user.dto.UserDto;

@Service
public class UserBizImpl implements UserBiz {


	@Autowired
	private UserDao dao;
	
	
	@Override
	public UserDto login(UserDto dto) {
		// TODO Auto-generated method stub
		return dao.login(dto);
	}


	@Override
	public int regist(UserDto dto) {
		// TODO Auto-generated method stub
		return dao.regist(dto);
	}


	@Override
	public UserDto idcheck(String userid) {
		// TODO Auto-generated method stub
		return dao.idcheck(userid);
	}


	@Override
	public String find_id(String useremail) {
		// TODO Auto-generated method stub
		return dao.find_id(useremail);
	}


	@Override
	public void sendEmail(UserDto dto) {
		// 네이버일 경우 네이버 계정, gmail일 경우 gmail 계정
		final String user = "";
		// 위의 계정 패스워드
		final String password = "";

		String msg = "";
		msg += "<div align='center' style='border:1px solid black; font-family:verdana'>";
		msg += "<h3 style='color: blue;'>";
		msg += dto.getUserid() + "님의 임시 비밀번호 입니다. 비밀번호를 변경하여 사용하세요.</h3>";
		msg += "<p>임시 비밀번호 : ";
		msg += dto.getPassword() + "</p></div>";
		
		//SMTP 서버 정보 설정하기
		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", 465);
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.ssl.enable", "true");
		prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		
		Session session = Session.getDefaultInstance(prop, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});
		
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			
			// 수신자 메일 주소
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(dto.getUseremail()));
			
			// Subject 메일 제목을 입력하는 부분
			message.setSubject("당근팜 임시 비밀번호 입니다.");
			
			// Text 메일 내용을 입력하는 부분
			message.setContent(msg, "text/html;charset=UTF-8");
			
			// send the message
			Transport.send(message); // 전송
			System.out.println("메일 성공적으로 전송");
			
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
	}

	//비밀번호찾기
	@Override
	public void findPw(UserDto dto) throws Exception {
		
			// 임시 비밀번호 생성
			String pw = "";
			for (int i = 0; i < 12; i++) {
				pw += (char) ((Math.random() * 26) + 97);
			}
			
			dto.setPassword(pw);
			
			// 비밀번호 변경
			dao.updatePw(dto);
			
			// 비밀번호 변경 메일 발송
			sendEmail(dto);
	
		}	
}
