package com.project.Supplier.service;

import java.util.List;

import com.project.Supplier.model.Supplier;
import com.project.Supplier.model.TransactionRequest;
import com.project.Supplier.model.TransactionResponse;

public interface Supplierservice {
	
	public TransactionResponse saveSupplier(TransactionRequest request);
	
	public List<Supplier> getAllSupplier();

}
