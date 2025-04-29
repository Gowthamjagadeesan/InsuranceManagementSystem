package com.cts.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customer_info")
public class Customer {
	@Id
	private long customerId;
	private String customerName;
	private String customerEmail;
	private String customerPhone;
	private String customerAddress;

}
