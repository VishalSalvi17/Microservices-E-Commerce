package com.project.paymentservice.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.project.paymentservice.model.DeliveryManagement;
import com.project.paymentservice.model.OrderManagement;
import com.project.paymentservice.model.Payment;
import com.project.paymentservice.model.TransactionRequest;
import com.project.paymentservice.model.TransactionResponse;
import com.project.paymentservice.repository.PaymentServiceRepository;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentServiceRepository paymentservicerepository;
	
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public Payment dopayment(Payment payment) {
		// TODO Auto-generated method stub
		return paymentservicerepository.save(payment);
	}
	
	@Override
	public TransactionResponse savedelivery(TransactionRequest request) {
		// TODO Auto-generated method stub
		Payment payment = request.getPayment();
		DeliveryManagement deliveryManagement = request.getDeliveryManagement();
//		OrderManagement orderManagement = request.getOrderManagement();
		
		deliveryManagement.setOrderId(payment.getOrderId());
		deliveryManagement.setTotalAmount(payment.getTotalAmount());
		deliveryManagement.setUserId(payment.getUserId());
		
		int orid = payment.getOrderId();
		OrderManagement res = restTemplate.getForObject("http://localhost:8088/orders/get/"+orid, OrderManagement.class);
		deliveryManagement.setProductId(res.getProductId());
		deliveryManagement.setProductName(res.getProductName());
		
		paymentservicerepository.save(payment);
		deliveryManagement.setTransactionId(payment.getTransactionId());
		
		DeliveryManagement deliveryManagementResponse = restTemplate.postForObject("http://localhost:8089/delivery/bookDelivery", deliveryManagement, DeliveryManagement.class);
		return new TransactionResponse(payment,deliveryManagementResponse.getTransactionId());
	}

	@Override
	public List<Payment> getallpayment() {
		// TODO Auto-generated method stub
		return paymentservicerepository.findAll();
	}
}
