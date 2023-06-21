package com.project.InventoryService.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.InventoryService.Model.InventoryService;
import com.project.InventoryService.Model.TransactionRequest;
import com.project.InventoryService.Model.TransactionResponse;
import com.project.InventoryService.Service.InventorySerImpl;

@RestController
@RequestMapping("inventory")
public class InventoryController {
	
	@Autowired
	private InventorySerImpl inventorySerImpl;
	
	@PostMapping(value="/save", consumes = "application/json")
	public InventoryService saveInventory(@RequestBody InventoryService request){
		return inventorySerImpl.saveInventory(request);
	}
	
	@PostMapping(value="/saveprod", consumes = "application/json")
	public TransactionResponse InventoryProduct(@RequestBody TransactionRequest request){
		return inventorySerImpl.InventoryProduct(request);
	}
	
	@GetMapping("getall")
	public List<InventoryService> getAllInventory(){
		return inventorySerImpl.getAllInventory();
	}
	
	@GetMapping(value = "get/{id}")
	public InventoryService getById(@PathVariable int id) {
		return inventorySerImpl.getById(id);
	}
}
