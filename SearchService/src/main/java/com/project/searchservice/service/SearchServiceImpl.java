package com.project.searchservice.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.project.searchservice.model.Account;
import com.project.searchservice.model.Cart;
import com.project.searchservice.model.ProductCatlog;
import com.project.searchservice.model.Search;
import com.project.searchservice.model.TransactionRequest;
import com.project.searchservice.model.TransactionResponse;
import com.project.searchservice.repository.SearchServiceRepository;

@Service
public class SearchServiceImpl implements SearchService {

	@Autowired
	private SearchServiceRepository searchServiceRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public List<Search> getall() {
		return searchServiceRepository.findAll();
	}

	@Override
	public TransactionResponse save(TransactionRequest request) {
		Search search = request.getSearch();
		Cart cart = request.getCart();
		Account account = request.getAccount();
		ProductCatlog productCatlog = request.getProductCatlog();
		
		cart.setUserId(account.getUserId());
		cart.setProductId(productCatlog.getProductId());
		double amount = search.getQuantity()*productCatlog.getPrice();
		cart.setTotalAmount(amount);
		
		searchServiceRepository.save(search);
		cart.setProductName(search.getProductName());
		cart.setQuantity(search.getQuantity());
		
		Cart cartResponse = restTemplate.postForObject("http://localhost:8087/cart/savecart", cart, Cart.class);
		return new TransactionResponse(search, cartResponse.getCartId());
	}

	

}
