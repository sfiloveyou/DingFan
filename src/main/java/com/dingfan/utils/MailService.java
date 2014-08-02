package com.dingfan.utils;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.task.TaskExecutor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

public class MailService {
	private JavaMailSender mailSender;
	private TaskExecutor taskExecutor;
	private String from;
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public JavaMailSender getMailSender() {
		return mailSender;
	}
	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}
	public void setTaskExecutor(TaskExecutor taskExecutor) {
		this.taskExecutor = taskExecutor;
	}
	public TaskExecutor getTaskExecutor() {
		return taskExecutor;
	}
	public MimeMessageHelper getMailHelper(String from,String to,String subject,String text) throws MessagingException{
		MimeMessage msg = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(msg, true, "utf-8");
		if(StringUtils.isNotBlank(from))
			helper.setFrom(from);
		else
			helper.setFrom(this.from);
		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText(text, true);
		return helper;
	}

	public MimeMessageHelper setInlineMail(String from,String to,String subject,String text,Map<String, String> resourceMap) throws MessagingException {
		MimeMessageHelper helper=getMailHelper(from, to, subject, text);
		for (String resourceName : resourceMap.keySet()) {
			ClassPathResource img = new ClassPathResource(resourceMap.get(resourceName));
			helper.addInline(resourceName, img);			
		}
		return helper;
	}

	public MimeMessageHelper setAttachmentMail(String from,String to,String subject,String text,Map<String, String> resourceMap) throws MessagingException, IOException {
		MimeMessageHelper helper=getMailHelper(from, to, subject, text);
		for (String resourceName : resourceMap.keySet()) {
			helper.addAttachment(resourceName, new File(resourceMap.get(resourceName)));
		}
		return helper;
	}

	public MimeMessagePreparator sendAlternativeMail(final String from,final String to,final String subject,
			final String text,final String htmlText) throws Exception {
		MimeMessagePreparator mmp = new MimeMessagePreparator() {
			public void prepare(MimeMessage msg) throws Exception {
				MimeMessageHelper helper = new MimeMessageHelper(msg, true,
						"utf-8");
				helper.setFrom(from);
				helper.setTo(to);
				helper.setSubject(subject);

				MimeMultipart mmPart = new MimeMultipart();
				msg.setContent(mmPart);

				BodyPart plainTextPart = new MimeBodyPart();
				plainTextPart.setText(text);
				mmPart.addBodyPart(plainTextPart);

				BodyPart htmlPart = new MimeBodyPart();
				htmlPart.setContent(htmlText, "text/html;charset=utf-8");
				mmPart.addBodyPart(htmlPart);
			}
		};
		return mmp;
	}
	public void sendAsyncMail(final MimeMessage msg) {
		taskExecutor.execute(new Runnable() {
			public void run() {
				try {
					mailSender.send(msg);
				} catch (Exception e) {
					System.out.println("邮件发送失败！，异常信息：" + e.getMessage());
				}
			}
		});
	}

}
