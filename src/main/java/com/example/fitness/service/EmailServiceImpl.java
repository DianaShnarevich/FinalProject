package com.example.fitness.service;

import com.example.fitness.service.api.IEmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class EmailServiceImpl implements IEmailService {

	private final JavaMailSender mailSender;

	@Value("${spring.mail.username}")
	private String username;

	public EmailServiceImpl(JavaMailSender sender){
		this.mailSender = sender;
	}

	@Override
	public void sendSimpleMessage(String to, String subject, String text) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(username);
		message.setSubject(subject);
		message.setTo(to);
		message.setText(text);

		mailSender.send(message);
	}
}
