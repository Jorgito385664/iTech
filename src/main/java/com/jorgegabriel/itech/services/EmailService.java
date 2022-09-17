package com.jorgegabriel.itech.services;

import org.springframework.mail.SimpleMailMessage;

import com.jorgegabriel.itech.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);

}
