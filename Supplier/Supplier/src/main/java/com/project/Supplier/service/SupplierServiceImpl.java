package com.project.Supplier.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.project.Supplier.dao.SupplierRepository;
import com.project.Supplier.model.InventoryService;
import com.project.Supplier.model.Supplier;
import com.project.Supplier.model.TransactionRequest;
import com.project.Supplier.model.TransactionResponse;

@Service
public class SupplierServiceImpl implements Supplierservice{

	@Autowired
	private SupplierRepository spRepo;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public TransactionResponse saveSupplier(TransactionRequest request) {
		InventoryService inventory = request.getInventory();
		Supplier supplier = request.getSupplier();
		
		inventory.setProductId(supplier.getProductId());
		inventory.setProductName(supplier.getProductName());
		
		int prid = supplier.getProductId();
		InventoryService res = restTemplate.getForObject("http://localhost:8083/inventory/get/"+prid, InventoryService.class);
		int totalQuantity = res.getQuantity() + supplier.getQuantity();
		inventory.setQuantity(totalQuantity);
		
		spRepo.save(supplier);
		InventoryService response = restTemplate.postForObject("http://localhost:8083/inventory/save", inventory, InventoryService.class);
		return new TransactionResponse(supplier, response.getProductName(), response.getQuantity());
	}
	@Override
	public List<Supplier> getAllSupplier() {
		// TODO Auto-generated method stub
		return spRepo.findAll();
	}

}
