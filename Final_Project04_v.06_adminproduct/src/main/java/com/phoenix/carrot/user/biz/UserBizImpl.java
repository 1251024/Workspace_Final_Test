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
		// ���̹��� ��� ���̹� ����, gmail�� ��� gmail ����
		final String user = "carrotparma01@gmail.com";
		// ���� ���� �н�����
		final String password = "carrot01";

		String msg = "";
		msg += "<div align='center' style='border:1px solid black; font-family:verdana'>";
		msg += "<h3 style='color: blue;'>";
		msg += dto.getUserid() + "���� �ӽ� ��й�ȣ �Դϴ�. ��й�ȣ�� �����Ͽ� ����ϼ���.</h3>";
		msg += "<p>�ӽ� ��й�ȣ : ";
		msg += dto.getPassword() + "</p></div>";
		
		//SMTP ���� ���� �����ϱ�
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
			
			// ������ ���� �ּ�
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(dto.getUseremail()));
			
			// Subject ���� ������ �Է��ϴ� �κ�
			message.setSubject("����� �ӽ� ��й�ȣ �Դϴ�.");
			
			// Text ���� ������ �Է��ϴ� �κ�
			message.setContent(msg, "text/html;charset=UTF-8");
			
			// send the message
			Transport.send(message); // ����
			System.out.println("���� ���������� ����");
			
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
	}

	//��й�ȣã��
	@Override
	public void findPw(UserDto dto) throws Exception {
		
			// �ӽ� ��й�ȣ ����
			String pw = "";
			for (int i = 0; i < 12; i++) {
				pw += (char) ((Math.random() * 26) + 97);
			}
			
			dto.setPassword(pw);
			
			// ��й�ȣ ����
			dao.updatePw(dto);
			
			// ��й�ȣ ���� ���� �߼�
			sendEmail(dto);
	
		}	
}
