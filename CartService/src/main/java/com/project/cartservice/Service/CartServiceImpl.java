package com.project.cartservice.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.project.cartservice.Model.Cart;
import com.project.cartservice.Model.OrderManagement;
import com.project.cartservice.Model.ProductCatlog;
import com.project.cartservice.Model.TransactionRequest;
import com.project.cartservice.Model.TransactionResponse;
import com.project.cartservice.Repository.CartRepository;

@Service
public class CartServiceImpl implements CartService{

	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	public Cart saveCart(Cart cart) {
		return cartRepository.save(cart);
	}
	
	
	@Override
	public TransactionResponse saveCartOrder(TransactionRequest request) {
		// TODO Auto-generated method stub
		
		Cart cart = request.getCart();
		ProductCatlog productcatlog = request.getProductcatlog();
		OrderManagement orderManagement = request.getOrderManagement();
		
		double amount = productcatlog.getPrice()*cart.getQuantity();
		cart.setTotalAmount(amount);
		cartRepository.save(cart);
		
		orderManagement.setOrderId(cart.getCartId());
		orderManagement.setUserId(cart.getUserId());
		orderManagement.setProductId(cart.getProductId());
		orderManagement.setProductName(cart.getProductName());
		orderManagement.setTotalAmount(amount);
		
		
		OrderManagement orderManagementResponse =restTemplate.postForObject("http://localhost:8088/orders/save", orderManagement, OrderManagement.class);
		return new TransactionResponse(cart,orderManagementResponse.getOrderId(), orderManagementResponse.getTotalAmount());
		
	}

}
