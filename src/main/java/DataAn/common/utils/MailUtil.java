package DataAn.common.utils;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

public class MailUtil {
	public static void sendSimpleMail(String host, String mailFrom, String mailTo, String userName, String password,
			String content, String subject) {
		JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();
		// 设定mail server
		senderImpl.setHost(host);
		senderImpl.setUsername(userName); // 根据自己的情况,设置username
		senderImpl.setPassword(password); // 根据自己的情况, 设置password
		Properties prop = new Properties();
		// 将这个参数设为true，让服务器进行认证,认证用户名和密码是否正确
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.timeout", "60000");
		senderImpl.setJavaMailProperties(prop);

		// 建立邮件消息
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		// 设置收件人，寄件人 用数组发送多个邮件
		// String[] array = new String[] {"sun111@163.com","sun222@sohu.com"};
		// mailMessage.setTo(array);
		mailMessage.setTo(mailTo);
		mailMessage.setFrom(mailFrom);
		mailMessage.setSubject(subject);
		mailMessage.setText(content);
		mailMessage.setSentDate(new Date());

		// 发送邮件
		senderImpl.send(mailMessage);

		System.out.println(" 邮件发送成功.. ");
	}

	public static void sendMimeMail(String host, String mailFrom, String mailTo, String userName, String password,
			String content, String subject, List<File> files) throws MessagingException {
		JavaMailSenderImpl senderImpl = new JavaMailSenderImpl();
		// 设定mail server
		senderImpl.setHost(host);
		senderImpl.setUsername(userName); // 根据自己的情况,设置username
		senderImpl.setPassword(password); // 根据自己的情况, 设置password
		Properties prop = new Properties();
		// 将这个参数设为true，让服务器进行认证,认证用户名和密码是否正确
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.timeout", "60000");
		senderImpl.setJavaMailProperties(prop);

		// 建立邮件消息
		MimeMessage msg = senderImpl.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(msg, true);
		helper.setTo(mailTo);
		helper.setText(content);
		helper.setFrom(mailFrom);
		helper.setSubject(subject);
		helper.setSentDate(new Date());
		// 附件对象，添加进邮件helper内
		for (File file : files) {
			String fileNm = file.getName();
			helper.addAttachment(fileNm, file);
		}

		// 发送邮件
		senderImpl.send(msg);
		System.out.println(" 邮件发送成功.. ");
	}
}
