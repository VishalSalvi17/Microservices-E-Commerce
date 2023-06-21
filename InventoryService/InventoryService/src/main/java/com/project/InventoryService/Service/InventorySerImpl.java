package com.project.InventoryService.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.discovery.converters.Auto;
import com.project.InventoryService.Model.InventoryService;
import com.project.InventoryService.Model.ProductCatlog;
import com.project.InventoryService.Model.TransactionRequest;
import com.project.InventoryService.Model.TransactionResponse;
import com.project.InventoryService.Repository.InventoryRepository;

@Service
public class InventorySerImpl implements InventorySer{
	
	@Autowired
	private InventoryRepository inventoryRepository;
	
	@Autowired
	private RestTemplate resttemplate;
	
	@Override
	public InventoryService saveInventory(InventoryService inventoryService) {
		return inventoryRepository.save(inventoryService);
	}

	@Override
	public TransactionResponse InventoryProduct(TransactionRequest request) {
		
		ProductCatlog productCatlog = request.getProductCatlog();
		InventoryService inventory = request.getInventory();
		inventoryRepository.save(inventory);
		
		productCatlog.setProductId(inventory.getProductId());
		productCatlog.setProductName(inventory.getProductName());
		productCatlog.setQuantity(inventory.getQuantity());
		
		ProductCatlog productresponse = resttemplate.postForObject("http://localhost:8084/productcatlog/saveproduct", productCatlog, ProductCatlog.class);
		return new TransactionResponse(inventory,productresponse.getProductId(),productresponse.getPrice());
	}

	@Override
	public List<InventoryService> getAllInventory() {
		return inventoryRepository.findAll();
	}

	@Override
	public InventoryService getById(int id) {
		return inventoryRepository.findByproductId(id);
	}
	
}
