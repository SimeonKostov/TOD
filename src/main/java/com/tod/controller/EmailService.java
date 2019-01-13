package com.tod.controller;

public interface EmailService {

	void sendSimpleMessage(String to, String subject, String text);
}
