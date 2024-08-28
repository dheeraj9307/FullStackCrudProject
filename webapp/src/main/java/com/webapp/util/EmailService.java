package com.webapp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
@Component
public class EmailService {
	@Autowired
	private JavaMailSender javaMailSender;
    public void sendEmail(String to,String sub,String message) {
    	SimpleMailMessage sm=new SimpleMailMessage();
    	sm.setTo(to);
    	sm.setSubject(sub);
    	sm.setText(message);
    	javaMailSender.send(sm);
    }
}
