package com.project.paymentservice.service;

import java.util.List;

import com.project.paymentservice.model.Payment;
import com.project.paymentservice.model.TransactionRequest;
import com.project.paymentservice.model.TransactionResponse;

public interface PaymentService {
	public Payment dopayment(Payment payment);
	public TransactionResponse savedelivery(TransactionRequest payment);
	List<Payment> getallpayment();
}
