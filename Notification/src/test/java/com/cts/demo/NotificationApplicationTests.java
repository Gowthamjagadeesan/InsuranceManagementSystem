package com.cts.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.cts.demo.entity.Notification;
import com.cts.demo.repository.NotificationRepository;
import com.cts.demo.service.NotificationServiceImpl;

@SpringBootTest
class NotificationApplicationTests {

	@Mock
	NotificationRepository notificationRepository;

	@InjectMocks
	NotificationServiceImpl notificationService;

	@Test
	void savePolicy() {
		Notification notification = new Notification(123, 1234, 70000, "hospinal expences", "gowtham@gmail.com", "9443126900");
		Mockito.when(notificationRepository.save(notification)).thenReturn(notification);
		String response = notificationService.saveNotification("hospinal expences", 1234, 70000, "gowtham@gmail.com");
		assertEquals("Notification saved Successfully", response);
	}
}
