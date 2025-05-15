package com.example.asynchronous.controller;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.asynchronous.service.EmailService;

@RestController
@RequestMapping("/send")
public class EmailController {

	private final EmailService emailService;

	public EmailController(EmailService emailService) {
		this.emailService = emailService;
	}

	@PostMapping("/mail")
	public String send() {
		emailService.sendEmail();
		emailService.sendEmailAgain();
		emailService.sendEmailRepeatAgain();
		emailService.info();
		emailService.process();
		emailService.payment();

		return "Execution finished";
	}

	@PostMapping("/initiate")
	public String initiate() throws InterruptedException, ExecutionException {
		emailService.sendEmail();
		emailService.sendEmailAgain();
		emailService.sendEmailRepeatAgain();
		CompletableFuture<String> info = emailService.initiate();
		System.out.println(info.get());
		emailService.process();
		emailService.info();
		emailService.payment();

		return "Execution finished";
	}
}
