package com.project.searchservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequest {
	private Cart cart;
	private Search search;
	private Account account;
	private ProductCatlog productCatlog;
}
