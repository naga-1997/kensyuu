package com.example.nagashimatravel.event;

import java.util.Locale;
import java.util.UUID;

import org.springframework.context.MessageSource;
import org.springframework.context.event.EventListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.example.nagashimatravel.entity.User;
import com.example.nagashimatravel.service.VerificationTokenService;

@Component
public class SignupEventListener {
	private final VerificationTokenService verificationTokenService;
	private final JavaMailSender javaMailSender;
	private final MessageSource messageSource;

	public SignupEventListener(VerificationTokenService verificationTokenService, JavaMailSender mailSender,
			MessageSource messageSource) {
		this.verificationTokenService = verificationTokenService;
		this.javaMailSender = mailSender;
		this.messageSource = messageSource;
	}

	@EventListener
	private void onSignupEvent(SignupEvent signupEvent) {
		User user = signupEvent.getUser();
		String token = UUID.randomUUID().toString();
		verificationTokenService.create(user, token);

		String recipientAddress = user.getEmail();
		String subject = messageSource.getMessage("event.mail.auth", null, Locale.getDefault());
		String confirmationUrl = signupEvent.getRequestUrl() + "/verify?token=" + token;
		String message = messageSource.getMessage("event.mailMessage", null, Locale.getDefault());

		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(recipientAddress);
		mailMessage.setSubject(subject);
		mailMessage.setText(message + "\n" + confirmationUrl);
		javaMailSender.send(mailMessage);
	}
}
