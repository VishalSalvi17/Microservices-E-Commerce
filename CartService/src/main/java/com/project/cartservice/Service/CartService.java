package com.project.cartservice.Service;

import com.project.cartservice.Model.Cart;
import com.project.cartservice.Model.TransactionRequest;
import com.project.cartservice.Model.TransactionResponse;

public interface CartService {

	public Cart saveCart(Cart cart);
	public TransactionResponse saveCartOrder(TransactionRequest cartservice);
}
