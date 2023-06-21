package com.project.Ordermanagement.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.project.Ordermanagement.dao.OrderManagementRepository;
import com.project.Ordermanagement.model.OrderManagement;
import com.project.Ordermanagement.model.Payment;
import com.project.Ordermanagement.model.TransactionRequest;
import com.project.Ordermanagement.model.TransactionResponse;

@Service
public class OrderManagementServiceImpl implements OrderManagementService {

	@Autowired
	OrderManagementRepository omRepo;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public OrderManagement saveOrder(OrderManagement request) {
		// TODO Auto-generated method stub
		return omRepo.save(request);
	}

	@Override
	public List<OrderManagement> getAllOrders() {
		return omRepo.findAll();
	}
	
	public TransactionResponse OrderPayment(TransactionRequest request) {
		OrderManagement orderManagement = request.getOrderManagement();
		Payment payment = request.getPayment();
		
		
		payment.setOrderId(orderManagement.getOrderId());
		payment.setUserId(orderManagement.getUserId());
		payment.setTotalAmount(orderManagement.getTotalAmount());
		
		omRepo.save(orderManagement);
		Payment paymentResponce = restTemplate.postForObject("http://localhost:8085/payment/dopayment", payment, Payment.class);
		
		
		return new TransactionResponse(orderManagement,paymentResponce.getOrderId(),paymentResponce.getTransactionId());
		
	}

	@Override
	public OrderManagement getById(int id) {
		return omRepo.findByorderId(id);
	}

	
	
	


}
