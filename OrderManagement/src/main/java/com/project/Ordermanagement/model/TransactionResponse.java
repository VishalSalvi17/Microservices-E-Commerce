package com.project.Ordermanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponse {
	private OrderManagement orderManagement;
//	private Payment payemnt;
	private int orderId;
	private int transactionId;
}
