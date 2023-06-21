package com.project.InventoryService.Service;

import java.util.List;

import com.project.InventoryService.InventoryServiceApplication;
import com.project.InventoryService.Model.InventoryService;
import com.project.InventoryService.Model.TransactionRequest;
import com.project.InventoryService.Model.TransactionResponse;

public interface InventorySer {
	
	TransactionResponse InventoryProduct(TransactionRequest inventoryService);
	
	List<InventoryService> getAllInventory();

	InventoryService saveInventory(InventoryService inventoryService);
	
	InventoryService getById(int id);
}
