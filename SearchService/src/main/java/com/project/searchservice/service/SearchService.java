package com.project.searchservice.service;

import java.util.List;

import com.project.searchservice.model.Search;
import com.project.searchservice.model.TransactionRequest;
import com.project.searchservice.model.TransactionResponse;

public interface SearchService {
	TransactionResponse save(TransactionRequest search);
	List<Search> getall();
}
