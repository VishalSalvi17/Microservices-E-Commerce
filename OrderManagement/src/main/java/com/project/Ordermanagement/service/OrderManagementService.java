package com.project.Ordermanagement.service;

import java.util.List;

import com.project.Ordermanagement.model.OrderManagement;
import com.project.Ordermanagement.model.TransactionRequest;
import com.project.Ordermanagement.model.TransactionResponse;

public interface OrderManagementService {

	public OrderManagement saveOrder(OrderManagement orderManagement);
	
//	public OrderManagement saveOrderPayment(TransactionRequest orderManagement);

	public List<OrderManagement> getAllOrders();
	
	public TransactionResponse OrderPayment(TransactionRequest paymentservice);
	
	public OrderManagement getById(int id);

}
