package com.cts.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notification {
	private long notificationId;
	private long customerId;
	private long policyId;
	private String message;

}
