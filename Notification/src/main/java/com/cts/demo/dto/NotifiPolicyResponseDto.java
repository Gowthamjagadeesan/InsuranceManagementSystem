package com.cts.demo.dto;

import com.cts.demo.entity.Notification;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotifiPolicyResponseDto {
	Policy policy;
	Notification notification;
}	
