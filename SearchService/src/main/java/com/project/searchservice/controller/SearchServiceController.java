package com.project.searchservice.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.project.searchservice.model.Search;
import com.project.searchservice.model.TransactionRequest;
import com.project.searchservice.model.TransactionResponse;
import com.project.searchservice.service.SearchServiceImpl;

@RestController
@RequestMapping("/search")
public class SearchServiceController {
	
	@Autowired
	private SearchServiceImpl searchServiceImpl;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping("getall")
	public List<Search> getall(){
		return searchServiceImpl.getall();
	}
	
	@PostMapping(value = "save", consumes = "application/json")
	public TransactionResponse save(@RequestBody TransactionRequest search) {
		return searchServiceImpl.save(search);
	}
}
