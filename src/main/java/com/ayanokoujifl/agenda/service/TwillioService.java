package com.ayanokoujifl.agenda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.ayanokoujifl.agenda.repositories.ContatoRepository;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class TwillioService {

	@Autowired
	ContatoRepository repository;

	@Value("${twilio.sid}")
	private String twilioSid;

	@Value("${twilio.key}")
	private String twilioKey;

	@Value("${twilio.phone.from}")
	private String twilioPhoneFrom;

	private String twilioPhoneTo;

	public void sendSms(Long id, String numberTo) {
		twilioPhoneTo = numberTo;
		Twilio.init(twilioSid, twilioKey);
		PhoneNumber to = new PhoneNumber(twilioPhoneTo);
		PhoneNumber from = new PhoneNumber(twilioPhoneFrom);
		Message message = Message.creator(to, from, "Where's Wallace?").create();

		System.out.println(message.getSid());
	}
}
