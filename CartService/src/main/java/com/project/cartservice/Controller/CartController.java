package com.project.cartservice.Controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.project.cartservice.Model.Cart;
import com.project.cartservice.Service.CartService;
import com.project.cartservice.Service.CartServiceImpl;
import com.project.cartservice.Model.TransactionRequest;
import com.project.cartservice.Model.TransactionResponse;

@RestController
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private CartServiceImpl cartServiceimpl;

	@PostMapping(value = "savecart", consumes = "application/json")
	public Cart saveCart(@RequestBody Cart cart) {
		return cartServiceimpl.saveCart(cart);
	}
	
	@PostMapping(value="/save", consumes = "application/json")
	public TransactionResponse cartOrder(@RequestBody TransactionRequest request)
	{
		return cartServiceimpl.saveCartOrder(request);
	}
}
