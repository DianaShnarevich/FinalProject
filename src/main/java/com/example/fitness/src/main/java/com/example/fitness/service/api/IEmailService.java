package com.example.fitness.service.api;

public interface IEmailService {
	void sendSimpleMessage(String to, String subject, String text);
}
